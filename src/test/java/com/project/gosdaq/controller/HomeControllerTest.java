package com.project.gosdaq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gosdaq.dto.home.InterestRequestDTO;
import com.project.gosdaq.service.home.HomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.project.gosdaq.ApiDocumentUtils.getDocumentRequest;
import static com.project.gosdaq.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "gosdaq-spring.herokuapp.com")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HomeService homeService;

    @Test
    public void test_관심종목_조회_시_ticker_정상여부_확인() throws Exception {
        InterestRequestDTO dto = new InterestRequestDTO();
//        dto.setTickers( new ArrayList<String>(Arrays.asList("COIN", "TSLA")));
        ;
        dto.setTickers(Arrays.asList("COIN", "SBUX"));

        HashMap<String, Object> response = new HashMap<>();
        response.put("isError", false);
        response.put("message", "[Spring] /home/interest Success");

        given(homeService.getInterest(dto.getTickers())).willReturn(response);

        ResultActions result = this.mockMvc.perform(
                post("/home/interest")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(document("home-interest",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("tickers").type(JsonFieldType.ARRAY).description("티커 리스트")
                        ),
                        responseFields(
                                fieldWithPath("isError").type(JsonFieldType.BOOLEAN).description("결과 성공 여부"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                        )
                        ));
    }
}

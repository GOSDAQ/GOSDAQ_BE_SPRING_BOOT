package com.project.gosdaq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gosdaq.dto.common.Exchange;
import com.project.gosdaq.dto.common.Search;
import com.project.gosdaq.service.common.CommonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static com.project.gosdaq.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommonController.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "gosdaq-spring.herokuapp.com")
public class CommonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommonService commonService;

    @Test
    public void 종목검색_Test() throws Exception {
        Search.ResponseDTO response = new Search.ResponseDTO();
        Search.ResponseDataDTO data = new Search.ResponseDataDTO();

        data.setName("Tplex");
        data.setTicker("081150.KQ");

        response.setData(data);
        response.setCode(200);
        response.setMsg("[Spring] /common/search Success");

        given(commonService.getStockNameFromTicker("081150", "KR")).willReturn(response);

        ResultActions result = this.mockMvc.perform(
                get("/common/search?ticker=081150&region=KR")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(document("common-search",
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메시지"),
                                fieldWithPath("data.ticker").type(JsonFieldType.STRING).description("티커명"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("종목명")
                        )
                ));
    }

    @Test
    public void 환율조회_Test() throws Exception {
        Exchange.ResponseDTO response = new Exchange.ResponseDTO();
        Exchange.ExchangeRateDTO data = new Exchange.ExchangeRateDTO();

        data.setExchange(1253.84);

        response.setData(data);
        response.setCode(200);
        response.setMsg("[Spring] /common/exchange Success");

        given(commonService.getExchange()).willReturn(response);

        ResultActions result = this.mockMvc.perform(
                get("/common/exchange")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(document("common-exchange",
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메시지"),
                                fieldWithPath("data.exchange").type(JsonFieldType.NUMBER).description("환율")
                        )
                ));
    }
}

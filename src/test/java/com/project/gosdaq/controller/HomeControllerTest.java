package com.project.gosdaq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gosdaq.dto.common.HistoryDTO;
import com.project.gosdaq.dto.home.InterestRequestDTO;
import com.project.gosdaq.dto.home.MyStockRequestDTO;
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

import java.util.*;

import static com.project.gosdaq.ApiDocumentUtils.getDocumentRequest;
import static com.project.gosdaq.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void test_interest() throws Exception {
        InterestRequestDTO dto = new InterestRequestDTO();
        dto.setTickers(Collections.singletonList("SBUX"));

        List<HashMap<String, Object>> data = new ArrayList<>();
        HashMap<String, Object> dataMap = new HashMap<>();
        HistoryDTO history = new HistoryDTO();
        HashMap<String, Object> historyData = new HashMap<>();

        historyData.put("date", "2022-06-15T00:00:00.000Z");
        historyData.put("open", 73.25);
        historyData.put("high", 75.269997);
        historyData.put("low", 73.110001);
        historyData.put("close", 74.190002);
        historyData.put("adjClose", 74.190002);
        historyData.put("volume", 7682300);

        history.setData(Collections.singletonList(historyData));
        history.setCnt(20);

        dataMap.put("ticker", "SBUX");
        dataMap.put("price", 7009.3);
        dataMap.put("rate", 1.41);
        dataMap.put("history", history);

        data.add(dataMap);

        HashMap<String, Object> response = new HashMap<>();

        response.put("isError", false);
        response.put("message", "[Spring] /home/interest Success");
        response.put("data", data);

        given(homeService.getInterest(dto.getTickers())).willReturn(response);
        System.out.println(homeService.getInterest(dto.getTickers()));

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
                                fieldWithPath("tickers").type(JsonFieldType.ARRAY).description("?????? ?????????")
                        ),
                        responseFields(
                                fieldWithPath("isError").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("?????? ?????????"),
                                fieldWithPath("data[].ticker").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("data[].rate").type(JsonFieldType.NUMBER).description("?????????"),
                                fieldWithPath("data[].price").type(JsonFieldType.NUMBER).description("?????????"),
                                fieldWithPath("data[].history").type(JsonFieldType.OBJECT).description("30?????? ???????????? ?????????"),
                                fieldWithPath("data[].history.data[].date").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("data[].history.data[].open").type(JsonFieldType.NUMBER).description("??????"),
                                fieldWithPath("data[].history.data[].high").type(JsonFieldType.NUMBER).description("??????"),
                                fieldWithPath("data[].history.data[].low").type(JsonFieldType.NUMBER).description("??????"),
                                fieldWithPath("data[].history.data[].close").type(JsonFieldType.NUMBER).description("??????"),
                                fieldWithPath("data[].history.data[].adjClose").type(JsonFieldType.NUMBER).description("?????? ??????"),
                                fieldWithPath("data[].history.data[].volume").type(JsonFieldType.NUMBER).description("?????????"),
                                fieldWithPath("data[].history.cnt").type(JsonFieldType.NUMBER).description("????????? ?????? ??????")
                        )
                ));
    }


    @Test
    public void test_have() throws Exception{
        MyStockRequestDTO dto = new MyStockRequestDTO();
        MyStockRequestDTO.StockInfo reqData = new MyStockRequestDTO.StockInfo();

        reqData.ticker = "SBUX";
        reqData.avg = (float) 65.48;
        reqData.amt = 4;

        dto.setData(Collections.singletonList(reqData));

        HashMap<String, Object> response = new HashMap<>();
        List<HashMap<String, Object>> data = new ArrayList<>();
        HashMap<String, Object> dataMap = new HashMap<>();

        dataMap.put("ticker", "SBUX");
        dataMap.put("revenue", "76,322");

        data.add(dataMap);

        response.put("isError", false);
        response.put("message", "[Spring] /home/have Success");
        response.put("exchange", 1319.55);
        response.put("data", data);

        given(homeService.getMyStock(dto)).willReturn(response);

        ResultActions result = this.mockMvc.perform(
                post("/home/have")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(document("home-have",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????? ?????? ?????????"),
                                fieldWithPath("data[].ticker").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("data[].avg").type(JsonFieldType.NUMBER).description("?????????"),
                                fieldWithPath("data[].amt").type(JsonFieldType.NUMBER).description("??????")
                        ),
                        responseFields(
                               fieldWithPath("isError").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????").optional(),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("?????? ?????????").optional(),
                                fieldWithPath("exchange").type(JsonFieldType.STRING).description("??????").optional(),
                                fieldWithPath("data[].ticker").type(JsonFieldType.NUMBER).description("?????????").optional(),
                                fieldWithPath("data[].revenue").type(JsonFieldType.NUMBER).description("??????(??????)").optional()
                        )
                ));
    }
}

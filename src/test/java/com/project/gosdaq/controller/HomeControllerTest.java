package com.project.gosdaq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gosdaq.dto.home.Have;
import com.project.gosdaq.dto.home.Interest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "gosdaq-spring.herokuapp.com")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HomeService homeService;

    @Test
    public void test_interest() throws Exception {
        Interest.RequestDTO dto = new Interest.RequestDTO();
        dto.setTickers(Collections.singletonList("KO"));

        Interest.ResponseDTO response = new Interest.ResponseDTO();
        Interest.ResponseDataDTO data = new Interest.ResponseDataDTO();

        List<Interest.ResponseStockListDataDTO> responseDataList = new ArrayList<>();
        Interest.ResponseStockListDataDTO stockListData = new Interest.ResponseStockListDataDTO();

        List<Interest.HistoryDTO> historyDataList = new ArrayList<>();
        Interest.HistoryDTO historyData = new Interest.HistoryDTO();

        historyData.setDate("2022-07-27T00:00:00.000Z");
        historyData.setClose(62.88f);

        historyDataList.add(historyData);

        stockListData.setTicker("KO");
        stockListData.setName("Coca-Cola Company (The)");
        stockListData.setPrice(63.11f);
        stockListData.setRate(-2.41f);
        stockListData.setHistory(historyDataList);
        stockListData.setCnt(23);

        responseDataList.add(stockListData);

        data.setList(responseDataList);
        data.setExchange(1428.3);

        response.setCode(200);
        response.setMsg("[Spring] /home/interest Success");
        response.setData(data);

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
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("결과 코드 - 성공:200, 실패:500"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("관심종목 데이터"),
                                fieldWithPath("data.exchange").type(JsonFieldType.NUMBER).description("환율"),
                                fieldWithPath("data.list").type(JsonFieldType.ARRAY).description("관심종목 데이터 리스트"),
                                fieldWithPath("data.list[].ticker").type(JsonFieldType.STRING).description("종목코드"),
                                fieldWithPath("data.list[].name").type(JsonFieldType.STRING).description("종목명"),
                                fieldWithPath("data.list[].rate").type(JsonFieldType.NUMBER).description("등락율"),
                                fieldWithPath("data.list[].price").type(JsonFieldType.NUMBER).description("현재가"),
                                fieldWithPath("data.list[].history").type(JsonFieldType.ARRAY).description("30일간 가격변화 리스트"),
                                fieldWithPath("data.list[].history[].date").type(JsonFieldType.STRING).description("날짜"),
                                fieldWithPath("data.list[].history[].close").type(JsonFieldType.NUMBER).description("종가"),
                                fieldWithPath("data.list[].cnt").type(JsonFieldType.NUMBER).description("리스트 결과 갯수")
                        )
                ));
    }


    @Test
    public void test_have() throws Exception {
        Have.RequestDTO dto = new Have.RequestDTO();
        List<Have.RequestDataDTO> requestDataList = new ArrayList<>();
        Have.RequestDataDTO requestData = new Have.RequestDataDTO();

        requestData.setTicker("SBUX");
        requestData.setAvg(65.48f);
        requestData.setAmt(4);

        requestDataList.add(requestData);

        dto.setData(requestDataList);

        Have.ResponseDTO response = new Have.ResponseDTO();
        Have.ResponseDataDTO responseData = new Have.ResponseDataDTO();
        List<Have.ResponseStockListDataDTO> stockList = new ArrayList<>();
        Have.ResponseStockListDataDTO stockListData = new Have.ResponseStockListDataDTO();

        stockListData.setTicker("SBUX");
        stockListData.setName("Starbucks Corporation");
        stockListData.setRevenue("102,570");

        stockList.add(stockListData);

        responseData.setList(stockList);
        responseData.setExchange(1341.13);

        response.setData(responseData);
        response.setCode(200);
        response.setMsg("[Spring] /home/have Success");

        given(homeService.getHave(dto)).willReturn(response);

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
                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("요청 데이터 리스트"),
                                fieldWithPath("data[].ticker").type(JsonFieldType.STRING).description("종목코드"),
                                fieldWithPath("data[].avg").type(JsonFieldType.NUMBER).description("평단가"),
                                fieldWithPath("data[].amt").type(JsonFieldType.NUMBER).description("보유 개수")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("결과 코드 - 성공:200, 실패:500"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("보유종목 데이터"),
                                fieldWithPath("data.list").type(JsonFieldType.ARRAY).description("보유종목 결과 리스트"),
                                fieldWithPath("data.list[].ticker").type(JsonFieldType.STRING).description("종목코드"),
                                fieldWithPath("data.list[].name").type(JsonFieldType.STRING).description("종목명"),
                                fieldWithPath("data.list[].revenue").type(JsonFieldType.STRING).description("원화 수익"),
                                fieldWithPath("data.exchange").type(JsonFieldType.NUMBER).description("환율")
                        )
                ));
    }
}


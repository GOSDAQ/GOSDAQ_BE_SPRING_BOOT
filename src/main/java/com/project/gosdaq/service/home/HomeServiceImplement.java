package com.project.gosdaq.service.home;

import com.project.gosdaq.dto.common.Search;
import com.project.gosdaq.dto.home.Have;
import com.project.gosdaq.dto.home.Interest;
import com.project.gosdaq.repository.common.exchange.ExchangeRepository;
import com.project.gosdaq.repository.home.HomeRepository;
import com.project.gosdaq.service.common.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImplement implements HomeService{

    private final HomeRepository homeRepository;
    private final ExchangeRepository exchangeRepository;

    private final SearchService searchService;

    @Autowired
    public HomeServiceImplement(HomeRepository homeRepository, ExchangeRepository exchangeRepository, SearchService searchService) {

        this.homeRepository = homeRepository;
        this.exchangeRepository = exchangeRepository;
        this.searchService = searchService;
    }

    @Override
    public Interest.ResponseDTO getInterest(List<String> tickers) {

        Interest.ResponseDTO result = new Interest.ResponseDTO();
        List<Interest.ResponseDataDTO> data = new ArrayList<>();

        result.setCode(200);
        result.setMsg("[Spring] /home/interest Success");

        try {
            for(String ticker : tickers){
                Interest.ResponseDataDTO response = homeRepository.getInterestData(ticker, 30).getData();

                if(response.getHistory().size() == 0){
                    result.setCode(500);
                    result.setMsg("[Spring] /home/interest Fail, Ckeck Node Error Log");
                    break;
                }

                String name = searchService.getStockNameFromTicker(ticker, "").getData().getName();
                response.setName(name);

                data.add(response);
            }

            result.setData(data);
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("[Spring] /home/interest Fail, Ckeck Node Error Log");
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Have.ResponseDTO getHave(Have.RequestDTO dto) {

        List<Have.RequestDataDTO> requestDataList = dto.getData();

        Have.ResponseDTO result = new Have.ResponseDTO();
        result.setCode(200);
        result.setMsg("[Spring] /home/have Success");

        Have.ResponseDataDTO data = new Have.ResponseDataDTO();
        List<Have.ResponseStockListDataDTO> list = new ArrayList<>();

        try {
            double exchange = exchangeRepository.getExchangeRate().getData().getExchange();
            data.setExchange(exchange);
        } catch (Exception e){
            result.setCode(500);
            result.setMsg("[Spring] /home/have Fail - While Get Exchange Rate, Ckeck Spring Error Log");
        }

        DecimalFormat formatter = new DecimalFormat("###,###");

        if(data.getExchange() == 0){
            return result;
        }

        try {
            for (Have.RequestDataDTO requestData : requestDataList) {
                Have.NodeResponseDTO nodeResponse = homeRepository.getHaveData(requestData.getTicker());

                if (nodeResponse.getCode() == 500) {
                    result.setCode(500);
                    result.setMsg("[Spring] /home/have Fail - While Get Price & Rate, Ckeck Spring Error Log");
                    break;
                }

                Have.NodeResponseListDataDTO nodeResponseData = nodeResponse.getData();
                double revenue = Math.round((nodeResponseData.getPrice() - requestData.getAvg()) * requestData.getAmt() * data.getExchange());

                Have.ResponseStockListDataDTO responseStockListDataDTO = new Have.ResponseStockListDataDTO();
                responseStockListDataDTO.setTicker(requestData.getTicker());
                responseStockListDataDTO.setRevenue(formatter.format(revenue));

                String name = searchService.getStockNameFromTicker(requestData.getTicker(), "").getData().getName();
                responseStockListDataDTO.setName(name);

                list.add(responseStockListDataDTO);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("[Spring] /home/have Fail - While Get Price & Rate, Ckeck Spring Error Log");
            e.printStackTrace();
        }

        data.setList(list);
        result.setData(data);

        return result;
    }
}

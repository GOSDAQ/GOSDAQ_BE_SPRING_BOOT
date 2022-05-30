package com.project.gosdaq.common;

import org.springframework.web.client.RestTemplate;


public class NodeApi {
    String URL = "http://localhost:4000";

    public String requstToNode(String ticker) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL + "/aa", String.class);
        System.out.println("RESULT : " + result);

        return "NODE API";
    }
}

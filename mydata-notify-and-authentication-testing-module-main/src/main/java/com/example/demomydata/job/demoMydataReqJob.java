package com.example.demomydata.job;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

@Component
public class demoMydataReqJob {

    boolean getEnable =Boolean.parseBoolean(ResourceBundle.getBundle("mydataTest").getString("getEnable"));
    boolean postEnable = Boolean.parseBoolean(ResourceBundle.getBundle("mydataTest").getString("postEnable"));

    //排程固定POST DP-API
    @Scheduled(fixedRate = 60000)
    public void runTask() {

        if(getEnable) {
            //TODO: GET 呼叫
            RestTemplate restTemplate = new RestTemplate();
            String testUrl = ResourceBundle.getBundle("mydataTest").getString("testUrl");
            String token = ResourceBundle.getBundle("mydataTest").getString("token");
            ResponseEntity<byte[]> response= restTemplate.getForEntity(testUrl,byte[].class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getHeaders());
            System.out.println(response.getBody());
            System.out.println(response);
        }

        if(postEnable) {
            //TODO: POST 呼叫
            RestTemplate restTemplate = new RestTemplate();
            String testUrl = ResourceBundle.getBundle("mydataTest").getString("testUrl");
            String token = ResourceBundle.getBundle("mydataTest").getString("token");
            ResponseEntity<byte[]> response= restTemplate.postForEntity(testUrl,getHttpEntity(token),byte[].class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getHeaders());
            System.out.println(response.getBody());
            System.out.println(response);
        }
    }

    private HttpEntity<Map<String, Object>> getHttpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //TODO:填入header
        headers.add("Accept", "*/*");
        headers.add("Content-Type", "application/zip");
        headers.add("Authorization","Bearer mydata::"+token);
        Set<String> set= ResourceBundle.getBundle("mydataHeaderInfo").keySet();
        for (String key : set){
            headers.add(key,ResourceBundle.getBundle("mydataHeaderInfo").getString(key));
        }

        Map<String, Object> mydataEntity=new HashMap<>();

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(mydataEntity, headers);

        return httpEntity;
    }

}

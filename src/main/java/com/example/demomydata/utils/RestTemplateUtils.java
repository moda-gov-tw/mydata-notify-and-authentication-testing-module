package com.example.demomydata.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.*;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Slf4j
public class RestTemplateUtils {

    public static RestTemplate getRestTemplate() {
        try {

            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();

            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
            DefaultHostnameVerifier defaultHostnameVerifier = new DefaultHostnameVerifier();
            TrustSelfSignedStrategy trustSelfSignedStrategy = new TrustSelfSignedStrategy();
            //本機測試時請自行調整為不進行憑證驗證

            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                    .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                            .setSslContext(SSLContextBuilder.create()
                                    .loadTrustMaterial(trustSelfSignedStrategy)
                                    .build())
                            .setHostnameVerifier(defaultHostnameVerifier)
                            .build())
                    .build())
                    .build();


            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

            requestFactory.setHttpClient(httpClient);

            RestTemplate restTemplate = new RestTemplate(requestFactory);
            // 設定此物件將restTemplate回傳錯誤catch住
//            restTemplate.setErrorHandler(new RestTemplateException());

            return restTemplate;

        } catch (KeyStoreException | NoSuchAlgorithmException e){
            log.error("Get SSL exception : {}" , e.getMessage());
            return new RestTemplate();
        } catch (KeyManagementException e) {
            log.error("KeyManagementException : {}" , e.getMessage());
            return new RestTemplate();
        }
    }

}

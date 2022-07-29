package com.thtns.cloud.config;

import com.thtns.cloud.filter.RestTemplateLogRequestInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * notes :
 *
 * @author :  liuyujun
 * time : 2019-01-11:11:13
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 打印请求参数
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {

        RestTemplate build = new RestTemplateBuilder().build();

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();

        CloseableHttpClient client = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        httpRequestFactory.setHttpClient(client);
        build.setRequestFactory(httpRequestFactory);

        build.getInterceptors().add(new RestTemplateLogRequestInterceptor());
        return build;
    }

}

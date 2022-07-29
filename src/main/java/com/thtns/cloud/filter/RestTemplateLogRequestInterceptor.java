package com.thtns.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * restTemplate 请求拦截器
 *
 * @author liuyujun
 */
@Slf4j
public class RestTemplateLogRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        return execution.execute(request, body);
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        log.info("request begin");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());
        log.info("Request body: {}", new String(body));
        log.info("request end");
    }

    //private void traceResponse(ClientHttpResponse response) throws IOException {
    //    StringBuilder inputStringBuilder = new StringBuilder();
    //    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"))) {
    //        String line = bufferedReader.readLine();
    //        while (line != null) {
    //            inputStringBuilder.append(line);
    //            inputStringBuilder.append('\n');
    //            line = bufferedReader.readLine();
    //        }
    //    }
    //    log.debug("============================response begin==========================================");
    //    log.debug("Status code  : {}", response.getStatusCode());
    //    log.debug("Status text  : {}", response.getStatusText());
    //    log.debug("Headers      : {}", response.getHeaders());
    //    log.debug("Response body: {}", inputStringBuilder.toString());//WARNING: comment out in production to improve performance
    //    log.debug("=======================response end=================================================");
    //}

}

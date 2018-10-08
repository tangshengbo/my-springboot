package com.tangshengbo.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StopWatch;

import java.io.IOException;

/**
 * Created by Tangshengbo on 2016/12/26 rest template 耗时统计
 */
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution)
            throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        ClientHttpResponse response;
        try {
            response = execution.execute(request, body);
            watch.stop();
            logger.info("rest_start# request={} cost={} status={} #rest_end", request.getURI(),
                    watch.getTotalTimeSeconds(), "SUCCESS");
        } catch (Exception e) {
            logger.info("rest_start# request={} cost={} status={} #rest_end", request.getURI(),
                    watch.getTotalTimeSeconds(), "FAIL");
            throw e;
        }
        return response;
    }
}

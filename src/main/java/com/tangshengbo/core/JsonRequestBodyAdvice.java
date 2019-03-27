package com.tangshengbo.core;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Created by Tangshengbo on 2019/3/27
 */
@ControllerAdvice
public class JsonRequestBodyAdvice extends RequestBodyAdviceAdapter implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return new EscapeInputMessage(super.beforeBodyRead(inputMessage, parameter, targetType, converterType));
    }

    private static class EscapeInputMessage implements HttpInputMessage {
        private HttpHeaders headers;
        private InputStream body;

        EscapeInputMessage(HttpInputMessage inputMessage) throws IOException {
            headers = inputMessage.getHeaders();
            body = inputMessage.getBody();
            String content = IOUtils.toString(body, "UTF-8");
            content = StringEscapeUtils.unescapeEcmaScript(content);
            System.out.println(JSON.toJSON(content));
            System.out.println(content);

            this.body = IOUtils.toInputStream(content, "UTF-8");
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}

package com.tangshengbo.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
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
 * 清除JSON转义字符
 */
@ControllerAdvice
public class PurgeJsonEscapeRequestBodyAdvice extends RequestBodyAdviceAdapter implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(PurgeJsonEscape.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return new PurgeEscapeInputMessage(super.beforeBodyRead(inputMessage, parameter, targetType, converterType), parameter);
    }

    private static class PurgeEscapeInputMessage implements HttpInputMessage {
        private HttpHeaders headers;
        private InputStream body;

        PurgeEscapeInputMessage(HttpInputMessage inputMessage, MethodParameter parameter) throws IOException {
            headers = inputMessage.getHeaders();
            body = inputMessage.getBody();
            purgeEscape(parameter);
        }

        private void purgeEscape(MethodParameter parameter) throws IOException {
            PurgeJsonEscape methodAnnotation = parameter.getMethodAnnotation(PurgeJsonEscape.class);
            String content = IOUtils.toString(body, methodAnnotation.encoding());
            JSONObject jsonObject = JSON.parseObject(content);
            jsonObject.put(methodAnnotation.value(), jsonObject.getJSONObject(methodAnnotation.value()));
            content = jsonObject.toJSONString();
            this.body = IOUtils.toInputStream(content, methodAnnotation.encoding());
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

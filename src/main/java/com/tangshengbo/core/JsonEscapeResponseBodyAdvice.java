package com.tangshengbo.core;

import com.alibaba.fastjson.JSON;
import com.tangshengbo.model.ApiResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * Created by Tangshengbo on 2019/3/29
 */
@ControllerAdvice
public class JsonEscapeResponseBodyAdvice implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.hasMethodAnnotation(JsonEscape.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!(body instanceof ApiResult)) {
            return body;
        }
        ApiResult apiResult = (ApiResult) body;
        Object value = apiResult.getData();
        if (Objects.isNull(value) || value instanceof String) {
            return body;
        }
        String jsonString = JSON.toJSONString(value);
        apiResult.setData(jsonString);
        return apiResult;
    }
}

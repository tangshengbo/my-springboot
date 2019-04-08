package com.tangshengbo.aspect;

import com.tangshengbo.model.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Tangshengbo on 2019/4/8
 */
@RestControllerAdvice(basePackages = "com.tangshengbo.controller",  annotations = RestController.class)
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 统一处理不存在的参数，并提示给前端错误信息
     *
     * @param req
     * @param e
     * @return
     * @throws MissingServletRequestParameterException
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ApiResult<String> jsonErrorHandler(HttpServletRequest req, MissingServletRequestParameterException e) throws Exception {
        logger.error("uri[" + req.getRequestURI() + "]调用异常,请求参数[" + getParamString(req) + "]错误信息:", e);
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setStatus(-1);
        apiResult.setMessage("参数【" + e.getParameterName() + "】不存在，请检查");
        apiResult.setData("url:" + req.getRequestURI());
        return apiResult;
    }

    /**
     * 网络IO统一异常处理:断点超时异常测试得
     *
     * @param req
     * @param e
     * @return
     * @throws
     */
    @ExceptionHandler(value = {ResourceAccessException.class, HttpClientErrorException.class, IOException.class})
    @ResponseBody
    public ApiResult<String> timeoutErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("uri[" + req.getRequestURI() + "]调用异常,请求参数[" + getParamString(req) + "]错误信息:", e);
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setStatus(-1);
        apiResult.setMessage("网络错误，请稍后再试");
        apiResult.setData("url:" + req.getRequestURI());
        return apiResult;
    }

    /**
     * 通用错误处理，没有具体处理的错误都走这里
     *
     * @param req
     * @param e
     * @return
     * @throws
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ApiResult<String> commonException(HttpServletRequest req, Exception e) throws Exception {
        logger.error("uri[" + req.getRequestURI() + "]调用异常,请求参数[" + getParamString(req) + "]错误信息:", e);
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setStatus(-1);
        apiResult.setMessage("服务器繁忙！");
        return apiResult;
    }

    private String getParamString(HttpServletRequest req) {
        StringBuilder param = new StringBuilder();
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (String key : parameterMap.keySet()) param.append(key).append("=").append(parameterMap.get(key)[0]).append(",");
        String result = param.toString();
        if (StringUtils.isNotBlank(result)) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}

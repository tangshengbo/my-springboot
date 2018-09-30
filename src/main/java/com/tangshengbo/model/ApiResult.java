package com.tangshengbo.model;

/**
 * Created by Tangshengbo on 2018/9/30
 */
public class ApiResult<T> {

    //判断结果
    private boolean success;
    //返回描述
    private String message;
    //返回状态
    private int status;
    //对象
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResult() {
    }

    public static <T> ApiResult<T> success(String message) {
        ApiResult<T> result = new ApiResult<>();
        result.setStatus(0);
        result.setMessage(message);
        result.setSuccess(true);
        return result;
    }



    public static <T> ApiResult<T> success(String message, T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setData(data);
        result.setStatus(0);
        result.setMessage(message);
        result.setSuccess(true);
        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setData(data);
        result.setStatus(0);
        result.setMessage("操作成功");
        result.setSuccess(true);
        return result;
    }

    public static <T> ApiResult<T> error(String message) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(false);
        result.setStatus(-1);
        result.setMessage(message);
        return result;
    }
}

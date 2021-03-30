package com.hinsliu.iotapp.domain;

import java.io.Serializable;

/**
 * @Description: Format definition of RPC results.
 * @author: liuxuanming
 * @date: 2021/03/23 11:53 上午
 */
public class RpcResult<T> implements Serializable {

    private Boolean success;

    private int code;

    private String message;

    private T data;

    public RpcResult(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static<T> RpcResult<T> successResult(T data) {
        return new RpcResult<T>(true, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDesc(), data);
    }

    public static<T> RpcResult<T> errorResult(String msg) {
        return new RpcResult<T>(false, ErrorCode.FAIL.getCode(), msg, null);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        this.setCode(success ? ErrorCode.SUCCESS.getCode() : this.getCode());
    }

    public void setSuccess(Boolean success, T data) {
        this.success = success;
        this.message = "success";
        this.setCode(success? ErrorCode.SUCCESS.getCode(): this.getCode());
        this.setData(data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCode(ErrorCode code) {
        this.code = code.getCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

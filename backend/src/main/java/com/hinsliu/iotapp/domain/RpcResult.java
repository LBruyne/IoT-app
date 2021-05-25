package com.hinsliu.iotapp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

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

    public static <T> RpcResult<T> successResult() {
        return new RpcResult<T>(true, ErrorCodeEnum.SUCCESS.getCode(), "success", null);
    }

    public static <T> RpcResult<T> successResult(T data) {
        return new RpcResult<T>(true, ErrorCodeEnum.SUCCESS.getCode(), "success", data);
    }

    public static <T> RpcResult<T> errorResult(String msg) {
        return new RpcResult<T>(false, ErrorCodeEnum.FAIL.getCode(), msg, null);
    }

    public static <T> RpcResult<T> errorResult(int code, String msg) {
        return new RpcResult<T>(false, code, msg, null);
    }

    public RpcResult(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        this.message = "success";
        this.setCode(success ? ErrorCodeEnum.SUCCESS.getCode() : this.getCode());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCodeEnum(ErrorCodeEnum code) {
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

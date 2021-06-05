package com.hinsliu.iotapp.domain.exception;


import com.hinsliu.iotapp.domain.ErrorCodeEnum;

/**
 * @Description: 业务异常
 * @author: liuxuanming
 * @date: 2021/05/08 12:06 下午
 */
public class BusinessException extends RuntimeException {

    //业务异常编码 @see ErrorCodeEnum
    private int code;

    //业务异常信息
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.code = ErrorCodeEnum.FAIL.getCode();
        this.message = message;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public static BusinessException fail(String message) {
        return new BusinessException(message);
    }

    public static BusinessException fail(ErrorCodeEnum errorCode) {
        return new BusinessException(errorCode.getCode(), errorCode.getDesc());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

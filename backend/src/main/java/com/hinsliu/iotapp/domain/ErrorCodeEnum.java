package com.hinsliu.iotapp.domain;

/**
 * @Description: Definition of error code in RPC.
 * @author: liuxuanming
 * @date: 2021/03/23 12:00 上午
 */
public enum ErrorCodeEnum {
    /**
     * HTTP 200状态
     */
    HTTP_STATUS_200(200, "HTTP status: 200"),

    /**
     * HTTP 301状态
     */
    HTTP_STATUS_301(301, "Http status: 301"),

    /**
     * HTTP 400状态
     */
    HTTP_STATUS_400(400, "Http status: 400"),

    /**
     * HTTP 401状态
     */
    HTTP_STATUS_401(401, "Http status: 401"),

    /**
     * 成功
     */
    SUCCESS(0, "请求成功"),

    /**
     * 失败
     */
    FAIL(-1, "请求失败");

    /**
     * 错误编码，唯一
     */
    private final Integer code;

    /**
     * 错误描述
     */
    private final String desc;

    ErrorCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

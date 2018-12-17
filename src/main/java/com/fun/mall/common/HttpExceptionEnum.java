package com.fun.mall.common;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 17:54 2018/12/15
 * @ Description：HTTP异常枚举
 * @ Modified By：
 * @Version: 1.0$
 */
public enum HttpExceptionEnum {

    NOT_FOUND_EXCEPTION(404,"文件未找到异常"),
    NOT_SUPPORTED_METHOD_EXCEPTION(404,"NOT_SUPPORTED_METHOD_EXCEPTION"),
    NOT_SUPPORTED_MEDIA_TYPE_EXCEPTION(404,"NOT_SUPPORTED_MEDIA_TYPE_EXCEPTION"),
    NOT_ACCEPTABLE_MEDIA_TYPE_EXCEPTION(404,"NOT_ACCEPTABLE_MEDIA_TYPE_EXCEPTION"),
    MISSING_REQUEST_PARAMETER_EXCEPTION(404,"MISSING_REQUEST_PARAMETER_EXCEPTION"),
    REQUEST_BINDING_EXCEPTION(404,"REQUEST_BINDING_EXCEPTION"),
    NOT_SUPPORTED_CONVERSION_EXCEPTION(404,"NOT_SUPPORTED_CONVERSION_EXCEPTION"),
    TYPE_MISMATCH_EXCEPTION(400,"TYPE_MISMATCH_EXCEPTION"),
    MESSAGE_NOT_READABLE_EXCEPTION(400,"MESSAGE_NOT_READABLE_EXCEPTION"),
    MESSAGE_NOT_WRITABLE_EXCEPTION(400,"MESSAGE_NOT_WRITABLE_EXCEPTION"),
    NOT_VALID_METHOD_ARGUMENT_EXCEPTION(400,"NOT_VALID_METHOD_ARGUMENT_EXCEPTION"),
    MISSING_REQUEST_PART_EXCEPTION(400,"MISSING_REQUEST_PART_EXCEPTION"),
    BIND_EXCEPTION(400,"BIND_EXCEPTION"),
    ASYNC_REQUEST_TIMEOUT_EXCEPTION(503,"ASYNC_REQUEST_TIMEOUT_EXCEPTION")
    ;

    private int code;
    private String message;

    HttpExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.paosntech.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVO<T> {

    private Integer code;

    private String msg;

    private T data;

    private ResponseVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseVO(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> ResponseVO<T> successByMsg(String msg) {
        return new ResponseVO<>(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<>(ResultEnum.SUCCESS.getCode(), data);
    }

    public static <T> ResponseVO<T> success() {
        return new ResponseVO<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
    }

    public static <T> ResponseVO<T> error(ResultEnum resultEnum) {
        return new ResponseVO<>(resultEnum.getCode(), resultEnum.getDesc());
    }

    public static <T> ResponseVO<T> error(ResultEnum resultEnum, String msg) {
        return new ResponseVO<>(resultEnum.getCode(), msg);
    }

    public static <T> ResponseVO<T> error(ResultEnum resultEnum, BindingResult bindingResult) {
        return new ResponseVO<>(resultEnum.getCode(),
                Objects.requireNonNull(bindingResult.getFieldError()).getField() + " " + bindingResult.getFieldError().getDefaultMessage());
    }
}

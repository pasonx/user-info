package com.pasontech.controller.handler;

import com.paosntech.common.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static com.paosntech.common.ResultEnum.ERROR;

/**
 * @author pason.wang
 * @date 2021-10-10 07:54:45
 */
@RestControllerAdvice
public class RuntimeExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(RuntimeExceptionHandler.class);


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseVO handle(RuntimeException e) {
        logger.error(e.getMessage());
        return ResponseVO.error(ERROR, e.getMessage());
    }
}
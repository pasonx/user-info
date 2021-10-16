package com.pasontech.controller.interceptor;

import com.paosntech.common.UserConst;
import com.pasontech.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author pason.wang
 * @date 2021-10-14 12:02:28
 */
public class UserLoginInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);


    /**
     * true => continue，false => interrupted
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle...");
        User user = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        if (user == null) {
            logger.info("user info is null");
            throw new RuntimeException("please login or register first...");
        }
        return true;
    }
}

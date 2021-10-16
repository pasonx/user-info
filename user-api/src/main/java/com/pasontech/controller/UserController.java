package com.pasontech.controller;

import com.paosntech.common.ResponseVO;
import com.paosntech.common.UserConst;
import com.pasontech.pojo.User;
import com.pasontech.service.NearbyService;
import com.pasontech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author pason.wang
 * @date 2021-10-11 11:39:14
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private NearbyService nearbyService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    /**
     * get user info
     * @param userId
     * @return ResponseVO<User>
     */
    @GetMapping("/user/get")
    public ResponseVO<User> getUser(@RequestParam("userId") Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("/user/get userId illegal");
        }
        logger.info("This is a test");
        return userService.getUserInfo(userId);
    }

    /**
     * insert a user into DB
     * @param user
     * @return ResponseVO<Void>
     */
    @PostMapping("/user/insert")
    public ResponseVO<Void> insertUser(@RequestBody User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("/user/insert userId illegal");
        }
        return userService.saveUser(user);
    }


    /**
     * update user into
     * @param user
     * @return ResponseVO<Void>
     */
    @PostMapping("/user/update")
    public ResponseVO<Void> updateUser(@RequestBody User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("/user/update userId illegal");
        }
        return userService.updateUser(user);
    }

    /**
     * delete user in DB
     * @param user
     * @return ResponseVO<Void>
     */
    @PostMapping("/user/delete")
    public ResponseVO<Void> deleteUser(@RequestBody User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("/user/delete userId illegal");
        }
        return userService.deleteUser(user.getId());
    }


    /**
     * user register
     * @param user
     * @return ResponseVO<Void>
     */
    @PostMapping("/user/register")
    public ResponseVO<User> register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * user login
     * @param user
     * @param session
     * @return ResponseVO<User>
     */
    @PostMapping("/user/login")
    public ResponseVO<User> login(@RequestBody User user,
                                  HttpSession session) {
        ResponseVO<User> userResponseVo = userService.login(user.getUserName(), user.getPassword());

        //set user session
        session.setAttribute(UserConst.LOGIN_USER, userResponseVo.getData());
        logger.info("/login sessionId = {}", session.getId());

        return userResponseVo;
    }

    /**
     * get user session info
     * @param session
     * @return ResponseVO<User>
     */
    @GetMapping("/user")
    public ResponseVO<User> userInfo(HttpSession session) {
        logger.info("/user sessionId={}", session.getId());
        User user = (User) session.getAttribute(UserConst.LOGIN_USER);
        return ResponseVO.success(user);
    }


    /**
     * user logout
     * @param session
     * @return ResponseVO<User>
     */
    @PostMapping("/user/logout")
    public ResponseVO logout(HttpSession session) {
        session.removeAttribute(UserConst.LOGIN_USER);
        return ResponseVO.success();
    }

    /**
     * get nearby users
     * @param radius
     * @param lon
     * @param lat
     * @return
     */
    @GetMapping("/user/nearby")
    public ResponseVO<List<User>> nearBy(HttpSession session, Integer radius, Float lon, Float lat) {
        User user = (User) session.getAttribute(UserConst.LOGIN_USER);
        return nearbyService.findNearBy(user.getId(), radius, lon, lat);
    }
}

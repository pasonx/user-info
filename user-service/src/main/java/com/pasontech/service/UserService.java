package com.pasontech.service;

import com.paosntech.common.ResponseVO;
import com.pasontech.pojo.User;

/**
 * @author pason.wang
 * @date 2021-10-10 14:23:20
 */
public interface UserService {

    /**
     * get user info service
     * @param id userId
     */
    ResponseVO<User> getUserInfo(Long id);

    /**
     * save user service
     * @param user
     * @return
     */
    ResponseVO<Void> saveUser(User user);

    /**
     * update user service
     * @param user
     * @return
     */
    ResponseVO<Void> updateUser(User user);

    /**
     * delete user service
     * @param id
     * @return
     */
    ResponseVO<Void> deleteUser(Long id);

    /**
     * user login service
     * @param userName
     * @param password
     * @return
     */
    ResponseVO<User> login(String userName, String password);

    /**
     * user register service
     * @param user
     * @return
     */
    ResponseVO<User> register(User user);
}
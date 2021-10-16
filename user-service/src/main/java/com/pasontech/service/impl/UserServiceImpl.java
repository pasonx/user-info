package com.pasontech.service.impl;

import com.paosntech.common.ResponseVO;
import com.paosntech.common.ResultEnum;
import com.pasontech.mapper.UserMapper;
import com.pasontech.pojo.User;
import com.pasontech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

import static com.paosntech.common.ResultEnum.ERROR;
import static com.paosntech.common.ResultEnum.USERNAME_EXIST;


/**
 * @author pason.wang
 * @date 2021-10-10 14:23:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVO<User> getUserInfo(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("illegal user id");
        }
        User user = userMapper.selectByUserId(id);
        if (user != null) {
            return ResponseVO.success(userMapper.selectByUserId(id));
        } else {
            throw new RuntimeException("Can not get the user info");
        }

    }

    @Override
    public ResponseVO<Void> saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("illegal user info");
        }
        int affectedRows = userMapper.insert(user);
        if (affectedRows <= 0) {
            throw new RuntimeException("insert user info failed");
        }
        return ResponseVO.success();
    }

    @Override
    public ResponseVO<Void> updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("illegal user info");
        }
        int affectedRows = userMapper.updateByExampleSelective(user);
        if (affectedRows <= 0) {
            throw new RuntimeException("update user info failed");
        }
        return ResponseVO.success();
    }

    @Override
    public ResponseVO<Void> deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("illegal user id");
        }
        int affectedRows = userMapper.deleteByUserId(id);
        if (affectedRows <= 0) {
            throw new RuntimeException("delete user info failed");
        }
        return ResponseVO.success();
    }

    @Override
    public ResponseVO<User> login(String userName, String password) {
        User user = userMapper.selectByUsername(userName);
        if (user == null) {
            return ResponseVO.error(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            return ResponseVO.error(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        user.setPassword("");
        return ResponseVO.success(user);
    }

    @Override
    public ResponseVO<User> register(User user) {

        //username cannot repeat
        int countByUsername = userMapper.countByUsername(user.getUserName());
        if (countByUsername > 0) {
            return ResponseVO.error(USERNAME_EXIST);
        }

        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)));

        System.out.println(user.getPassword());
        //insert into DB
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ResponseVO.error(ERROR);
        }

        return ResponseVO.success();
    }

}

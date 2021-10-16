package com.pasontech.test;

import com.paosntech.common.ResponseVO;
import com.pasontech.mapper.UserMapper;
import com.pasontech.pojo.User;
import com.pasontech.service.impl.UserServiceImpl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.*;


/**
 * @author pason.wang
 * @date 2021-10-15 17:23:52
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserInfoApplicationTests {

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    User user;

    @Before
    public void setUp() {
        this.user = new User();
    }

    @Test
    public void testGetUserInfo() {
        Mockito.when(userMapper.selectByUserId(anyLong())).thenReturn(user);
        Assert.assertThat(userServiceImpl.getUserInfo(1L), CoreMatchers.equalTo(ResponseVO.success(user)));
    }


    @Test
    public void testUpdateUserInfo() {
        Mockito.when(userMapper.updateByExampleSelective(any(User.class))).thenReturn(1);
        Assert.assertThat(userServiceImpl.updateUser(user), CoreMatchers.equalTo(ResponseVO.success()));
    }

    @Test
    public void testDeleteUserInfo() {
        Mockito.when(userMapper.deleteByUserId(anyLong())).thenReturn(1);
        Assert.assertThat(userServiceImpl.deleteUser(1L), CoreMatchers.equalTo(ResponseVO.success()));
    }

    @Test
    public void testRegisterUserInfo() {
        Mockito.when(userMapper.insert(any(User.class))).thenReturn(1);
        Mockito.when(userMapper.countByUsername("Pason")).thenReturn(1);
        Mockito.when(userMapper.countByUsername("Juha")).thenReturn(0);
        user.setPassword("test");
        Assert.assertThat(userServiceImpl.register(user), CoreMatchers.equalTo(ResponseVO.success()));
    }

}

package com.pasontech.test;

import com.pasontech.mapper.UserMapper;
import com.pasontech.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
    }
}

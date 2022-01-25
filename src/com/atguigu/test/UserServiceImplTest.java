package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"张三","123","zhangsan@qq.com"));
        userService.registUser(new User(null,"张四","123","zhangsi@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "admin", "admin", "")));
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("张三") == true) {
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名不存在");
        }
    }
}
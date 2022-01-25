package com.atguigu.test;

import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public class UserDaoTest {

    UserDaoImpl userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
//        User user = userDao.queryUserByUsername("admin");
//        System.out.println(Arrays.asList(user));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
//        System.out.println(userDao.queryUserByUsernameAndPassword("admin", "admin"));
        if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null){
            System.out.println("用户名和密码错误，登录失败");
        } else {
            System.out.println("用户名和密码正确，登录成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.addUser(new User(null, "张三", "123456", "admin@qq.com")));
    }
}
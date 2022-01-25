package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User login(User user) {
        User user1 = null;
        user1 = userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword()) != null){
            System.out.println("登录成功");
            return user1;
        }
        System.out.println("登录失败");
        return null;
    }

    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            //为null，表明数据库中没有该用户，可以使用该用户名
            return false;
        }
        return true;
    }
}

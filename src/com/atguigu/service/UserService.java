package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败;否则登录成功
     */
    public User login(User user);

    /**
     * 返回用户名是否可以使用
     * @param username
     * @return 返回true表示用户名已经存在，不可以注册新用户；返回false表示用户名可以注册新用户
     */
    public boolean existUsername(String username);

}

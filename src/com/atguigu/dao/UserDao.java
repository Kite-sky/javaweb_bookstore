package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回null，说明没有该用户信息
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @return 如果返回null，说明数据库中没有该用户
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int addUser(User user);

}

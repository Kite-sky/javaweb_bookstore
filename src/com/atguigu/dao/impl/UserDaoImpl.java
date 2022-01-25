package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public class UserDaoImpl extends BaseDao1 implements UserDao {


    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id, username, password, email from t_user where username = ?";
        return queryForOne(User.class, sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into t_user(username, password, email) values(?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(), user.getEmail());
    }
}

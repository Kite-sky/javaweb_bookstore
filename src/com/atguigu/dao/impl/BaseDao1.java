package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.beans.Customizer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public abstract class BaseDao1 {
    //使用DbUtils操作数据库
   private QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用来执行，insert、update、delete语句
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @return 如果返回-1说明update方法执行失败，其他表示影响的行数。
     */
    public int update(String sql, Object ... args) {
        Connection conn = JdbcUtils.getConnection();

        try {
           return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResource(conn);
        }
        return -1;
    }

    /**
     * 查询返回一个javaBean的方法
     * @param clazz 返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回的类型的泛型
     * @return  返回null表示数据库中没有存在该数据，
     */
    public <T> T queryForOne(Class<T> clazz, String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResource(conn);
        }
        return null;
    }

    /**
     * 查询返回多个javaBean的方法
     * @param clazz 返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回的类型的泛型
     * @return  返回null表示数据库中没有存在该数据，
     */
    public <T>List<T> queryForList(Class<T> clazz, String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(clazz),args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResource(conn);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql  返回的对象类型
     * @param args sql对应的参数值
     * @return 返回null表示数据库中没有存在该数据，
     */
    public Object queryForSingleValue(String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            queryRunner.query(sql,new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResource(conn);
        }
        return null;
    }
}

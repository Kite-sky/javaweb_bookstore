package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @description：TODO
 * @date ：2022/1/19 11:10
 */
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.closeResource(connection);
        }
    }

    @Test
    public void testJdbcUtils2(){
        System.out.println(JdbcUtils.getConnection());
    }

}

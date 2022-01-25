package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.cj.protocol.Resultset;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @description：TODO
 * @date ：2022/1/19 10:13
 */
public class JdbcUtils {

    private static DataSource dataSource = null;

    static {
        try {
            //1. 加载数据库配置文件使其转成字节流
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            //2. 创建Properties对象加载数据库字节流
            Properties pro = new Properties();
            pro.load(is);
            //3. 使用Druid的数据库工厂，创建数据库连接资源
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() {
        //4. 使用Druid的创建的数据库连接池获得连接
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库资源（方法重载）
     * @param conn
     */
    public static void closeResource(Connection conn) {
        try {
            if (conn != null)
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * 一下重载方法在使用Druid数据库连接池技术的时候不需要使用
     *      原因：Druid数据库连接池自行处理 PreparedStatement 和 ResultSet 的创建和关闭
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null)
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        try {
            if (conn != null)
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

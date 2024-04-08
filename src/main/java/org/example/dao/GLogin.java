package org.example.dao;

import java.sql.*;

public class GLogin {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3307/jdbc_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "Jason20040903";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);

        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stat = con.createStatement();
        String sql = "select * from player";
        ResultSet resultSet = stat.executeQuery(sql);
        while(resultSet.next()) {
            System.out.println("name = " + resultSet.getObject("username"));
            System.out.println("password = "+resultSet.getObject("password"));
        }
    }
    // 建立连接
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    // 释放连接
    public static void release(Connection cnn, Statement stmt, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(cnn!=null){
            try {
                cnn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

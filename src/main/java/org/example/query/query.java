package org.example.query;

import java.sql.*;

import org.example.dao.GLogin;

public class query {
    public void selectMonster() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = GLogin.getConnection();
            String sqlSearch = "SELECT * FROM player_item WHERE";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

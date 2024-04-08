package org.example.src;

import org.example.dao.GLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertItem extends JFrame {

    private static final Integer WIDTH=800;
    private static final Integer HEIGHT=400;

    public InsertItem(String inputMessage){

        setTitle("插入新道具");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon imgBg= new ImageIcon("src/main/resources/img/a.gif");
        JLabel jblBg = new JLabel(imgBg);//可插入背景图片
        jblBg.setBounds(0,0,WIDTH,HEIGHT);
        jblBg.setLayout(null);
        this.add(jblBg);

        JTextField itemInput = new JTextField();
        itemInput.setBounds(50, 50, 300, 30);
        jblBg.add(itemInput);

        JButton btnInsert=new JButton("插入");
        btnInsert.setBounds(200, 200, 100, 50);
        btnInsert.setBackground(Color.PINK);
        btnInsert.setForeground(Color.WHITE);
        btnInsert.setFont(new Font("PingFang SC", Font.BOLD, 17));
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
                try {
                    conn= GLogin.getConnection();
                    String sqlInsert="INSERT INTO player_item"
                            + " (it_name, it_time, username) VALUE "
                            + "(?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sqlInsert);
                    // 将变量设置到预编译的 SQL 语句中

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    stmt.setString(1, itemInput.getText());
                    stmt.setString(2, sdf.format(new Date()));
                    stmt.setString(3, inputMessage);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "插入成功");
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    GLogin.release(conn,pstmt,rs);
                }
            }
        });
        jblBg.add(btnInsert);
        setVisible(true);
    }
}

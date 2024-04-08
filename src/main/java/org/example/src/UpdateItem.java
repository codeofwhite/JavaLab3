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

public class UpdateItem extends JFrame {

    private static final Integer WIDTH=800;
    private static final Integer HEIGHT=400;

    public UpdateItem(String inputMessage){

        setTitle("更新已有道具");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon imgBg= new ImageIcon("src/main/resources/img/a.gif");
        JLabel jblBg = new JLabel(imgBg);//可插入背景图片
        jblBg.setBounds(0,0,WIDTH,HEIGHT);
        jblBg.setLayout(null);
        this.add(jblBg);

        // 输入框
        JTextField itemInputNew = new JTextField();
        itemInputNew.setBounds(50, 50, 300, 30);
        itemInputNew.setText("请输入新的物品名");
        jblBg.add(itemInputNew);

        JTextField itemInputOld = new JTextField();
        itemInputOld.setBounds(50, 90, 300, 30);
        itemInputOld.setText("请输入想要更改的物品名");
        jblBg.add(itemInputOld);

        JButton btnInsert=new JButton("更新");
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
                    String sqlUpdate="UPDATE player_item SET it_name = ? WHERE username = ? and it_name = ?";
                    PreparedStatement stmt = conn.prepareStatement(sqlUpdate);
                    // 将变量设置到预编译的 SQL 语句中
                    stmt.setString(1, itemInputNew.getText());
                    stmt.setString(2, inputMessage);
                    stmt.setString(3, itemInputOld.getText());
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "更新成功");
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
    public static void main(String [] args){
        new UpdateItem("123456");
    }
}

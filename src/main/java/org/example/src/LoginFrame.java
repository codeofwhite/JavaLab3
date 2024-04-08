package org.example.src;

import org.example.dao.GLogin;
import org.example.src.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Integer WIDTH=800;
    private static final Integer HEIGHT=400;
    public String InputMessage;

    public UserFrame userFrame = new UserFrame();

    public LoginFrame(){
        setTitle("登录界面");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // 打印出应用标题
        JLabel appName = new JLabel("欢迎来到探险者联盟");
        appName.setBounds(100,0,290,100);
        appName.setFont(new Font("PingFang SC", Font.BOLD, 30));
        appName.setOpaque(true);
        appName.setBackground(Color.orange);
        appName.setBorder(BorderFactory.createBevelBorder(1));
        this.add(appName);

        ImageIcon imgBg= new ImageIcon("src/main/resources/img/a.gif");
        JLabel jblBg = new JLabel(imgBg);//可插入背景图片
        jblBg.setBounds(0,0,WIDTH,HEIGHT);
        jblBg.setLayout(null);
        this.add(jblBg);

        // 绘制怪物图片
        ImageIcon monster = new ImageIcon("src/main/resources/img/monster1.jpg");
        JLabel monsterLb = new JLabel(monster);
        monsterLb.setBounds(WIDTH/2 + 100,HEIGHT/3,200,200);
        monsterLb.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.GREEN));
        jblBg.add(monsterLb);

        //账号
        JLabel uid = new JLabel("Account：");
        uid.setBounds(150, 120, 110, 30);
        uid.setFont(new Font("PingFang SC", Font.BOLD, 17));
        uid.setOpaque(true); // 设置JLabel为不透明
        uid.setForeground(Color.BLACK);
        uid.setBackground(Color.ORANGE);
        uid.setBorder(BorderFactory.createBevelBorder(1));
        jblBg.add(uid);
        //账号输入框
        JTextField uidInput = new JTextField();
        uidInput.setBounds(260, 120, 180, 30);
        jblBg.add(uidInput);

        //密码
        JLabel upwd=new JLabel("Password：");
        upwd.setBounds(150, 180, 110, 30);
        upwd.setFont(new Font("PingFang SC", Font.BOLD, 17));
        upwd.setOpaque(true); // 设置JLabel为不透明
        upwd.setForeground(Color.BLACK);
        upwd.setBackground(Color.ORANGE);
        upwd.setBorder(BorderFactory.createBevelBorder(1));
        jblBg.add(upwd);
        //密码输入框
        JPasswordField upwdInput = new JPasswordField();
        upwdInput.setBounds(260, 180, 180, 30);
        jblBg.add(upwdInput);

        //登录按钮及其触发事件
        JButton btnLogin=new JButton("Login");
        btnLogin.setBounds(270, 250, 110, 40);
        btnLogin.setBackground(Color.PINK);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("PingFang SC", Font.BOLD, 17));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getInputUid=uidInput.getText();
                InputMessage = uidInput.getText();
                @SuppressWarnings("deprecation")
                String getInputUpwd=upwdInput.getText();
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
                try {
                    conn= GLogin.getConnection();
                    String sql="SELECT * FROM player WHERE username =? AND password =?";
                    pstmt=conn.prepareStatement(sql);
                    pstmt.setString(1,getInputUid);
                    pstmt.setString(2,getInputUpwd);
                    rs= pstmt.executeQuery();
                    //创建弹窗
                    JDialog dialog = new JDialog();
                    dialog.setLocationRelativeTo(null);
                    dialog.setSize(200,200);
                    if(rs.next()){
                        dialog.add(new JLabel("登录成功！"));
                        setVisible(false);
                        userFrame.welcome(InputMessage);
                    }else{
                        dialog.add(new JLabel("登录失败！"));
                    }
                    dialog.setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    GLogin.release(conn,pstmt,rs);
                }
            }
        });
        jblBg.add(btnLogin);
        setVisible(true);
    }
    //主函数只需运行loginFrame即可
    public static void main(String[] args) {
        new LoginFrame();
    }

}

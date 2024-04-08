package org.example.src;

import org.example.dao.GLogin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFrame{

    private static final Integer WIDTH=800;
    private static final Integer HEIGHT=400;

    public LevelOne levelOne = new LevelOne();
    public UserFrame(){

    }

    public void welcome(String inputMessage) {
        // 获取屏幕的尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // 新Frame
        JFrame userFrame = new JFrame("Welcome");

        // 计算新的屏幕位置
        int x = (screenSize.width - userFrame.getWidth()) / 2;
        int y = (screenSize.height - userFrame.getHeight()) / 2;

        // 将窗口定位到屏幕的中心
        userFrame.setTitle("欢迎来到冒险者的乐园");
        userFrame.setLocation(x, y);
        userFrame.setSize(WIDTH, HEIGHT);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 要加布局管理，不然默认setBound用不了
        userFrame.setLayout(null);
        userFrame.setLocationRelativeTo(null);

        ImageIcon imgBg= new ImageIcon("src/main/resources/img/a.gif");
        JLabel jblBg = new JLabel(imgBg);//可插入背景图片
        jblBg.setBounds(0,0,WIDTH,HEIGHT);
        jblBg.setLayout(null);
        userFrame.add(jblBg);

        // 查询按钮
        JButton btnSearch=new JButton("查询");
        btnSearch.setBounds(50, 200, 100, 50);
        btnSearch.setBackground(Color.BLUE);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("PingFang SC", Font.BOLD, 17));
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                try {
                    conn = GLogin.getConnection();
                    String sqlSearch = "SELECT it_name, it_time FROM player_item WHERE username = ?";
                    stmt = conn.prepareStatement(sqlSearch);
                    // 将变量设置到预编译的 SQL 语句中
                    stmt.setString(1, inputMessage);
                    rs = stmt.executeQuery();

                    // 创建表格模型
                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("物品名"); // 添加列名
                    tableModel.addColumn("入库时间");

                    // 添加数据行
                    while (rs.next()) {
                        Object[] rowData = new Object[2];
                        rowData[0] = rs.getString("it_name");
                        rowData[1] = rs.getString("it_time");
                        tableModel.addRow(rowData);
                    }

                    // 创建表格并显示
                    JTable table = new JTable(tableModel);
                    JScrollPane scrollPane = new JScrollPane(table);
                    JFrame frame = new JFrame("查询结果");
                    frame.add(scrollPane);
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    GLogin.release(conn, stmt, rs);
                }

            }

        });

        // 插入按钮
        JButton btnInsert=new JButton("插入");
        btnInsert.setBounds(200, 200, 100, 50);
        btnInsert.setBackground(Color.PINK);
        btnInsert.setForeground(Color.WHITE);
        btnInsert.setFont(new Font("PingFang SC", Font.BOLD, 17));
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertItem(inputMessage);
            }
        });

        // 更新按钮
        JButton btnUpdate=new JButton("更新");
        btnUpdate.setBounds(350, 200, 100, 50);
        btnUpdate.setBackground(Color.PINK);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("PingFang SC", Font.BOLD, 17));
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
                try {
                    conn= GLogin.getConnection();
                    String sqlUpdate="UPDATE player_item SET it_name = ? WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(sqlUpdate);
                    // 将变量设置到预编译的 SQL 语句中
                    stmt.setString(1, "we");
                    stmt.setString(2, inputMessage);
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

        // 删除按钮
        JButton btnDrop=new JButton("删除");
        btnDrop.setBounds(500, 200, 100, 50);
        btnDrop.setBackground(Color.PINK);
        btnDrop.setForeground(Color.WHITE);
        btnDrop.setFont(new Font("PingFang SC", Font.BOLD, 17));
        btnDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
                try {
                    conn= GLogin.getConnection();
                    String sqlUpdate="DELETE FROM player_item WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(sqlUpdate);
                    // 将变量设置到预编译的 SQL 语句中
                    stmt.setString(1, inputMessage);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "删除成功");
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    GLogin.release(conn,pstmt,rs);
                }
            }

        });

        // 开启关卡一
        JButton levelOneBtn=new JButton("杰尼王八");
        levelOneBtn.setBounds(500, 100, 120, 50);
        levelOneBtn.setBackground(Color.RED);
        levelOneBtn.setForeground(Color.WHITE);
        levelOneBtn.setFont(new Font("PingFang SC", Font.BOLD, 17));
        levelOneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelOne.startLevelOne();
            }

        });

        // 登出
        JButton quitBtn=new JButton("登出");
        quitBtn.setBounds(500, 300, 100, 50);
        quitBtn.setBackground(Color.PINK);
        quitBtn.setForeground(Color.WHITE);
        quitBtn.setFont(new Font("PingFang SC", Font.BOLD, 17));
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                userFrame.dispose();
            }

        });

        // 查看所有怪兽
        JButton selectMonsterBtn=new JButton("怪兽图鉴");
        selectMonsterBtn.setBounds(200, 300, 100, 50);
        selectMonsterBtn.setBackground(Color.PINK);
        selectMonsterBtn.setForeground(Color.WHITE);
        selectMonsterBtn.setFont(new Font("PingFang SC", Font.BOLD, 17));
        selectMonsterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                try {
                    conn = GLogin.getConnection();
                    String sqlSearch = "SELECT monster_name,monster_lvl,monster_hp,monster_img FROM monster";
                    stmt = conn.prepareStatement(sqlSearch);
                    // 将变量设置到预编译的 SQL 语句中
                    rs = stmt.executeQuery();

                    // 创建表格模型
                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("怪兽名"); // 添加列名
                    tableModel.addColumn("怪兽等级");
                    tableModel.addColumn("怪兽生命值");
                    tableModel.addColumn("怪兽资讯");

                    // 添加数据行
                    while (rs.next()) {
                        Object[] rowData = new Object[5];
                        rowData[0] = rs.getString("monster_name");
                        rowData[1] = rs.getString("monster_lvl");
                        rowData[3] = rs.getString("monster_hp");
                        rowData[4] = rs.getString("monster_img");
                        tableModel.addRow(rowData);
                    }

                    // 创建表格并显示
                    JTable table = new JTable(tableModel);
                    table.setBackground(Color.PINK);
                    table.setBorder(BorderFactory.createBevelBorder(2));
                    JScrollPane scrollPane = new JScrollPane(table);
                    JFrame frame = new JFrame("怪兽图鉴");
                    frame.add(scrollPane);
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    GLogin.release(conn, stmt, rs);
                }

            }

        });

        jblBg.add(btnSearch);
        jblBg.add(btnInsert);
        jblBg.add(btnUpdate);
        jblBg.add(btnDrop);
        jblBg.add(levelOneBtn);
        jblBg.add(quitBtn);
        jblBg.add(selectMonsterBtn);

        userFrame.setVisible(true);
    }
    public static void main(String[] args) {
        UserFrame userFrame = new UserFrame();
        userFrame.welcome("123456");
    }
}

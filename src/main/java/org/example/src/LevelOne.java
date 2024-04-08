package org.example.src;

import javax.swing.*;
import java.awt.*;

public class LevelOne extends JFrame {
    private static final Integer WIDTH=500;
    private static final Integer HEIGHT=400;

    public void startLevelOne(){
        // 获取屏幕的尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame levelOneFrame = new JFrame("Pokemon Battle");
        // 计算新的屏幕位置
        int x = (screenSize.width - levelOneFrame.getWidth()) / 2;
        int y = (screenSize.height - levelOneFrame.getHeight()) / 2;
        levelOneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        levelOneFrame.setSize(WIDTH, HEIGHT);
        levelOneFrame.setLocation(x, y);

        // 创建状态栏
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusBar.add(new JLabel("玩家状态：HP: 100/100"));
        statusBar.add(new JLabel("敌方状态：HP: 80/100"));

        // 创建技能选项区域
        JPanel skillPanel = new JPanel();
        skillPanel.setLayout(new GridLayout(4, 1));
        skillPanel.add(new JButton("水枪"));
        skillPanel.add(new JButton("飞叶快刀"));
        skillPanel.add(new JButton("火焰拳"));
        skillPanel.add(new JButton("电击"));

        // 将状态栏和技能选项区域添加到主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(statusBar, BorderLayout.NORTH);
        mainPanel.add(skillPanel, BorderLayout.CENTER);

        levelOneFrame.add(mainPanel);
        levelOneFrame.setVisible(true);
    }

    public static void main(String[] args) {
        LevelOne levelOne = new LevelOne();
        levelOne.startLevelOne();
    }
}

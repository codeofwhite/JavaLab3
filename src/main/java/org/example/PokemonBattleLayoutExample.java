package org.example;

import javax.swing.*;
import java.awt.*;

public class PokemonBattleLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pokemon Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 创建状态栏
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusBar.add(new JLabel("玩家宝可梦状态：HP: 100/100"));
        statusBar.add(new JLabel("敌方宝可梦状态：HP: 80/100"));

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

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

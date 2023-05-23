package com.wby.tankgame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame extends JFrame {
    public static void main(String[] args) {
        new TankGame();
    }
    public TankGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入选择：1：开启新游戏 2：继续上局");
        String key = scanner.next();
        MyPanel mp = new MyPanel(key);
        new Thread(mp).start();
        this.add(mp); //把面板添加到画框上
        this.addKeyListener(mp);
        this.setSize(1300, 797);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}

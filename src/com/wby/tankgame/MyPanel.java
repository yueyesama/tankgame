package com.wby.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

@SuppressWarnings("all")
public class MyPanel extends JPanel implements KeyListener, Runnable {
    private MyTank myTank;
    private Vector<EnemyTank> enemyTanks = new Vector<>();
    private Vector<BOOM> booms = new Vector<>();
    private Vector<Node> nodes = new Vector<>();
    private int enemyTankNum = 3;
    private Image image1 = null;
    private Image image2 = null;
    private Image image3 = null;


    public MyPanel(String key) {
        File file = new File(Recorder.getFilePath());
        Recorder.setEnemyTanks(enemyTanks);
        if (file.exists()) {
            nodes = Recorder.getNodes();
        }else {
            System.out.println("没有游戏记录，将开启新游戏...");
            key = "1";
        }
        myTank = new MyTank(500, 100, 0);
        switch (key) {
            case "1":
                for (int i = 0; i < enemyTankNum; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 + i * 100, 0, 2);
                    enemyTank.setEnemyTanks(enemyTanks); //实现防止坦克碰撞功能
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY(), node.getDirect());
                    enemyTank.setEnemyTanks(enemyTanks); //实现防止坦克碰撞功能
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("输入错误...");
        }


        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }

    @Override
    public void paint(Graphics g) { //重写paint方法，在面板上绘制图像
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); //填充矩形，默认黑色
        g.setColor(Color.GRAY);
        g.fillRect(1000, 0, 300, 750);

        if (myTank.isLive()) { //我方坦克
            drawTank(g, myTank.getX(), myTank.getY(), myTank.getDirect(), 0);
        }

        for (int i = 0; i < myTank.getBullets().size(); i++) { //我方坦克子弹
            Bullet bullet = myTank.getBullets().get(i);
            if (bullet != null && bullet.isLive()) {
                drawBullet(g, bullet.getX(), bullet.getY(), bullet.getDirect());
            }
            if (!bullet.isLive()) {
                myTank.getBullets().remove(i);
            }
        }

        for (int i = 0; i < enemyTanks.size(); i++) { //敌方坦克和子弹
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(g, enemyTank.getX(), enemyTank.getY(), enemyTank.getDirect(), 1);
            for (int j = 0; j < enemyTank.getBullets().size(); j++) {
                Bullet bullet = enemyTank.getBullets().get(j);
                if (bullet != null && bullet.isLive()) {
                    drawBullet(g, bullet.getX(), bullet.getY(), bullet.getDirect());
                }
                if (bullet != null && !bullet.isLive()) {
                    enemyTank.getBullets().remove(bullet);
                }
            }
        }
        for (int i = 0; i < booms.size(); i++) { //爆炸效果
            BOOM boom = booms.get(i);
            if (boom.getLife() > 8) {
                g.drawImage(image1, boom.getX(), boom.getY(), 60, 60, this);
            }else if (boom.getLife() > 4) {
                g.drawImage(image2, boom.getX(), boom.getY(), 60, 60, this);
            }else if (boom.getLife() > 0) {
                g.drawImage(image3, boom.getX(), boom.getY(), 60, 60, this);
            }
            boom.lifeDown();
            if (!boom.isLive()) {
                booms.remove(boom);
            }
        }

        showInfo(g);
    }

    public void showInfo(Graphics g) {
        g.setColor(Color.WHITE);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累计击毁敌方坦克", 1020, 30);
        drawTank(g, 1020, 60, 0, 1);
        g.setColor(Color.WHITE);
        g.drawString(Recorder.getDestroyEnemyTankNum() + "", 1080, 100);
    }

    public void drawBullet(Graphics g, int x, int y, int direct) {
        g.setColor(Color.white);
        switch (direct) {
            case 0:
                g.fill3DRect(x - 2, y - 4, 4, 4, true);
                break;
            case 1:
                g.fill3DRect(x, y - 2, 4, 4, true);
                break;
            case 2:
                g.fill3DRect(x - 2, y, 4, 4, true);
                break;
            case 3:
                g.fill3DRect(x - 4, y - 2, 4, 4, true);
                break;
        }
    }

    //这里不适合传入Tank对象，因为要大量使用Tank对象的属性，直接传入属性可以大大简化代码，增加代码的可读性
    public void drawTank(Graphics g, int x, int y, int direct, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y, x + 20, y + 30);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 60, y + 20, x + 30, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 60, x + 20, y + 30);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x, y + 20, x + 30, y + 20);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            myTank.setDirect(0);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            myTank.setDirect(1);
            myTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            myTank.setDirect(2);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            myTank.setDirect(3);
            myTank.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            myTank.hitEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void hitTank(Bullet bullet, Tank tank) {
        switch (tank.getDirect()) {
            case 0:
            case 2:
                if (bullet.getX() >= tank.getX() && bullet.getX() <= tank.getX() + 60
                        && bullet.getY() >= tank.getY() && bullet.getY() <= tank.getY() + 40) {
                    if (tank.isLive()) {
                        BOOM boom = new BOOM(tank.getX(), tank.getY());
                        booms.add(boom);
                    }
                    tank.setLive(false);
                    bullet.setLive(false);
                    if (tank instanceof EnemyTank) {
                        enemyTanks.remove(tank);
                        Recorder.addNum();
                    }

                }
                break;
            case 1:
            case 3:
                if (bullet.getX() >= tank.getX() && bullet.getX() <= tank.getX() + 40
                        && bullet.getY() >= tank.getY() && bullet.getY() <= tank.getY() + 60) {
                    tank.setLive(false);
                    bullet.setLive(false);
                    if (tank instanceof EnemyTank) {
                        enemyTanks.remove(tank);
                    }
                    BOOM boom = new BOOM(tank.getX(), tank.getY());
                    booms.add(boom);
                    Recorder.addNum();
                }
                break;
        }
    }

    @Override
    public void run() {
        while (true) {

            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                for (int j = 0; j < enemyTank.getBullets().size(); j++) {
                    Bullet bullet = enemyTank.getBullets().get(j);
                    hitTank(bullet, myTank);
                }

                for (int j = 0; j < myTank.getBullets().size(); j++) {
                    Bullet bullet = myTank.getBullets().get(j);
                    hitTank(bullet, enemyTank);
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}

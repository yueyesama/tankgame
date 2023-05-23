package com.wby.tankgame;

import java.util.Vector;
@SuppressWarnings("all")
public class MyTank extends Tank{

    private Vector<Bullet> bullets = new Vector<>();
    public MyTank(int x, int y, int direct) {
        super(x, y, direct);
    }

    public void hitEnemyTank() {
        Bullet bullet = null;
        if (bullets.size() == 5 || !isLive()) {
            return;
        }
        switch (this.getDirect()) {
            case 0:
               bullet = new Bullet(getX() + 20, getY(), 0);
               break;
            case 1:
                bullet = new Bullet(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                bullet = new Bullet(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                bullet = new Bullet(getX(), getY() + 20, 3);
                break;
        }
        bullets.add(bullet);
        new Thread(bullet).start();
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }
}

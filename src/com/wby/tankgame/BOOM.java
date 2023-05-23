package com.wby.tankgame;

public class BOOM {
    private int x;
    private int y;
    private int life = 12;
    private boolean isLive = true;

    public BOOM(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() {
        if (isLive && life > 0) {
            life--;
        }else {
            isLive = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}

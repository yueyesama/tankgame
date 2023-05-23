package com.wby.tankgame;

import java.util.Vector;

@SuppressWarnings("all")
public class EnemyTank extends Tank implements Runnable {

    private Vector<Bullet> bullets = new Vector<>();
    private Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y, int direct) {
        super(x, y, direct);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    public boolean isTouchEnemyTank() {
        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (getX() > enemyTank.getX()  //左上角进入范围
                                    && getX() < enemyTank.getX() + 40
                                    && getY() > enemyTank.getY()
                                    && getY() < enemyTank.getY() + 60) {
                                return true;
                            }
                            if (getX() + 40 > enemyTank.getX() //右上角进入范围
                                    && getX() + 40 < enemyTank.getX() + 40
                                    && getY() > enemyTank.getY()
                                    && getY() < enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (getX() > enemyTank.getX()
                                    && getX() < enemyTank.getX() + 60
                                    && getY() > enemyTank.getY()
                                    && getY() < enemyTank.getY() + 40) {
                                return true;
                            }
                            if (getX() + 40 > enemyTank.getX()
                                    && getX() + 40 < enemyTank.getX() + 60
                                    && getY() > enemyTank.getY()
                                    && getY() < enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (getX() + 60 > enemyTank.getX()  //右上角进入范围
                                    && getX() + 60 < enemyTank.getX() + 40
                                    && getY() > enemyTank.getY()
                                    && getY() < enemyTank.getY() + 60) {
                                return true;
                            }
                            if (getX() + 60 > enemyTank.getX() //右下角进入范围
                                    && getX() + 60 < enemyTank.getX() + 40
                                    && getY() + 40 > enemyTank.getY()
                                    && getY() + 40 < enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (getX() + 60 > enemyTank.getX()
                                    && getX() + 60 < enemyTank.getX() + 60
                                    && getY() > enemyTank.getY()
                                    && getY() < enemyTank.getY() + 40) {
                                return true;
                            }
                            if (getX() + 60 > enemyTank.getX()
                                    && getX() + 60 < enemyTank.getX() + 60
                                    && getY() + 40 > enemyTank.getY()
                                    && getY() + 40 < enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (getX() > enemyTank.getX()  //左下角进入范围
                                    && getX() < enemyTank.getX() + 40
                                    && getY() + 60 > enemyTank.getY()
                                    && getY() + 60 < enemyTank.getY() + 60) {
                                return true;
                            }
                            if (getX() + 40 > enemyTank.getX() //右下角进入范围
                                    && getX() + 40 < enemyTank.getX() + 40
                                    && getY() + 60 > enemyTank.getY()
                                    && getY() + 60 < enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (getX() > enemyTank.getX()
                                    && getX() < enemyTank.getX() + 60
                                    && getY() + 60 > enemyTank.getY()
                                    && getY() + 60 < enemyTank.getY() + 40) {
                                return true;
                            }
                            if (getX() + 40 > enemyTank.getX()
                                    && getX() + 40 < enemyTank.getX() + 60
                                    && getY() + 60 > enemyTank.getY()
                                    && getY() + 60 < enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (getX() > enemyTank.getX()  //左上角进入范围
                                    && getX() < enemyTank.getX() + 40
                                    && getY() > enemyTank.getY()
                                    && getY() < enemyTank.getY() + 60) {
                                return true;
                            }
                            if (getX() > enemyTank.getX() //左下角进入范围
                                    && getX() < enemyTank.getX() + 40
                                    && getY() + 40 > enemyTank.getY()
                                    && getY() + 40 < enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (getX() > enemyTank.getX()
                                    && getX() < enemyTank.getX() + 60
                                    && getY() > enemyTank.getY()
                                    && getY() < enemyTank.getY() + 40) {
                                return true;
                            }
                            if (getX() > enemyTank.getX()
                                    && getX() < enemyTank.getX() + 60
                                    && getY() + 40 > enemyTank.getY()
                                    && getY() + 40 < enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        Bullet bullet = null;
        this.setSpeed(2);

        while (true) {
            if (bullets.size() < 2) {
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

                new Thread(bullet).start();
                bullets.add(bullet);
            }

            switch (getDirect()) { //按当前方向随机移动 20 - 40 个像素
                case 0:
                    for (int i = 0; i < (int) (Math.random() * 30 + 21); i++) {
                        if (!isTouchEnemyTank())
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < (int) (Math.random() * 30 + 21); i++) {
                        if (!isTouchEnemyTank())
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < (int) (Math.random() * 30 + 21); i++) {
                        if (!isTouchEnemyTank())
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < (int) (Math.random() * 30 + 21); i++) {
                        if (!isTouchEnemyTank())
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }


            int newDirect = (int) (Math.random() * 4);
            setDirect(newDirect);

            if (!this.isLive()) {
                break;
            }
        }
    }
}

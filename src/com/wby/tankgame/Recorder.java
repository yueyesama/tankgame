package com.wby.tankgame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int destroyEnemyTankNum;
    private static Vector<EnemyTank> enemyTanks = new Vector<>();
    private static Vector<Node> nodes = new Vector<>();
    private static String filePath = "src\\game_record.txt";
    private static BufferedWriter bw;
    private static BufferedReader br;

    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive()) {
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Vector<Node> getNodes() {
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println("记录文件已损坏... 游戏可能出现异常");
        } finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }

    public static void setDestroyEnemyTankNum(int destroyEnemyTankNum) {
        Recorder.destroyEnemyTankNum = destroyEnemyTankNum;
    }

    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        Recorder.filePath = filePath;
    }

    public static void addNum() {
        destroyEnemyTankNum ++;
    }

    public static int getDestroyEnemyTankNum() {
        return destroyEnemyTankNum;
    }
}

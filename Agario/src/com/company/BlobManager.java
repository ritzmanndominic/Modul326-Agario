package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlobManager extends JPanel implements KeyListener {
    private Blob enemyBlob;
    private ArrayList<Blob> enemyBlobs = new ArrayList<>();
    private boolean gameOver = false;
    Blob player = new Blob(10, Color.BLUE, 500, 500, 2, 2, 'd', 's');

    public BlobManager() {
        gameOver = false;
    }

    public Blob createFood(Builder builder) {
        builder.setBlobColor(Color.RED);
        builder.setBlobRadius(
                player.getRadius() - 5 + (int) (Math.random() * (30 + player.getRadius() - 10)));

        for (int i = 0; i < enemyBlobs.size(); i++) {
            if (player.getCoordinateX() == enemyBlobs.get(i).getCoordinateX() &&
                    player.getCoordinateY() == enemyBlobs.get(i).getCoordinateY()) {
                builder.setBlobRadius(player.getRadius() - 5 +
                        (int) (Math.random() * (30 + player.getRadius() - 10)));
            }
        }

        builder.setBlobCoordinates((int) (Math.random() * (1000 - player.getRadius() * 2)), (int) (Math.random() * (1000 - player.getRadius() * 2)));
        return (Blob) builder.getBlob();
    }

    public void addPlayers() {
        if (enemyBlobs.size() < 20) {
            enemyBlob = createFood(new BlobBuilder());
            enemyBlobs.add(enemyBlob);
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);
        player.draw(g);
        for (int i = 0; i < enemyBlobs.size(); i++) {
            enemyBlobs.get(i).draw(g);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        char c;
        c = e.getKeyChar();

        if (c == 'w') {
            player.moveUp();
        } else if (c == 'a') {
            player.moveLeft();
        } else if (c == 's') {
            player.moveDown();
        } else if (c == 'd') {
            player.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c;
        c = e.getKeyChar();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
    }

    public void run() {
        while (!gameOver) {
            addPlayers();

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            player.move();

            checkForCollision();

            paintImmediately(0, 0, 1000, 1000);
        }
    }

    public static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }

    public void checkForCollision() {
        for (int i = 0; i < enemyBlobs.size(); i++) {
            if (distance(player.getCoordinateX(), player.getCoordinateY(),
                    enemyBlobs.get(i).getCoordinateX(),
                    enemyBlobs.get(i).getCoordinateY()) <
                    player.getRadius() + enemyBlobs.get(i).getRadius()) {
                if (player.getRadius() > enemyBlobs.get(i).getRadius()) {
                    enemyBlobs.remove(i);
                    player.setRadius(player.getRadius() + 5);
                } else {
                    enemyBlobs.get(i).setRadius(enemyBlobs.get(i).getRadius() + player.getRadius());
                    player.setRadius(0);
                    gameOver = true;
                }
            }
        }
    }
}

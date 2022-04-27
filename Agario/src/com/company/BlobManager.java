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
    Blob player = new Blob(10, Color.BLUE, 500, 500, 2, 2, 'a', 'd');

    public BlobManager() {
        gameOver = false;
    }

    public Blob createEnemys(Builder builder) {
        builder.setBlobColor(Color.RED);
        builder.setBlobRadius(5 + (int) (Math.random() * 10));
        builder.setBlobCoordinates((int) (Math.random() * 1000), (int) (Math.random() * 1000));
        return (Blob) builder.getBlob();
    }

    public void addPlayers() {
        if (enemyBlobs.size() < 20) {
            enemyBlob = createEnemys(new BlobBuilder());
            enemyBlobs.add(enemyBlob);
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fill3DRect(0, 0, 1000, 1000, true);
        player.draw(g);
        for (int i = 0; i < enemyBlobs.size(); i++) {
            enemyBlobs.get(i).draw(g);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        char c;
        c = e.getKeyChar();

        switch (c) {
            case 'a':
                player.moveLeft();
                break;
            case 'd':
                player.moveRight();
                break;
            case 'w':
                player.moveUp();
                break;
            case 's':
                player.moveDown();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c;
        c = e.getKeyChar();
    }

    @Override
    public void keyTyped(KeyEvent e) {

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

            paintImmediately(0, 0, 1000, 1000);
        }
    }
}

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class BlobManager extends JPanel implements KeyListener {
    private Blob food;

    private Blob specialFood;
    private ArrayList<Blob> fieldFood = new ArrayList<>();

    private ArrayList<Blob> specialFoodList = new ArrayList<>();
    private boolean gameOver = false;
    Blob player = new Blob(10, Color.BLUE, 500, 500, 2, 2, 'd', 's');

    public BlobManager() {
        gameOver = false;
    }

    public Blob createBlob(Builder builder, Color color, ArrayList<Blob> listOfBlobs) {
        builder.setBlobColor(color);
        builder.setBlobRadius(
                player.getRadius() - 5 + (int) (Math.random() * (30 + player.getRadius() - 10)));

        for (int i = 0; i < listOfBlobs.size(); i++) {
            if (player.getCoordinateX() == listOfBlobs.get(i).getCoordinateX() &&
                    player.getCoordinateY() == listOfBlobs.get(i).getCoordinateY()) {
                builder.setBlobRadius(player.getRadius() - 5 +
                        (int) (Math.random() * (30 + player.getRadius() - 10)));
            }
        }

        builder.setBlobCoordinates((int) (Math.random() * (1000 - player.getRadius() * 2)), (int) (Math.random() * (1000 - player.getRadius() * 2)));
        return (Blob) builder.getBlob();
    }

    public Color getRandomColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);

        if (randomColor != Color.BLUE && randomColor != Color.GREEN) {
            return randomColor;
        }else {
            return Color.MAGENTA;
        }
    }
    public void addFoodToField() {
        Color randomColor = getRandomColor();

        if (fieldFood.size() < 20) {
            food = createBlob(new BlobBuilder(), randomColor, fieldFood);
            fieldFood.add(food);
        }
    }

    public void addSpecialFood() {
        if (specialFoodList.size() < 20) {
            specialFood = createBlob(new BlobBuilder(), Color.green, specialFoodList);
            specialFood.setRadius(50);
            specialFoodList.add(specialFood);
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);
        player.draw(g);

        //draw food
        for (int i = 0; i < fieldFood.size(); i++) {
            fieldFood.get(i).draw(g);
        }

        //draw enemys
        for (int i = 0; i < specialFoodList.size(); i++) {
            specialFoodList.get(i).draw(g);
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
            addFoodToField();
            addSpecialFood();

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
        for (int i = 0; i < fieldFood.size(); i++) {
            if (distance(player.getCoordinateX(), player.getCoordinateY(),
                    fieldFood.get(i).getCoordinateX(),
                    fieldFood.get(i).getCoordinateY()) <
                    player.getRadius() + fieldFood.get(i).getRadius()) {
                if (player.getRadius() > fieldFood.get(i).getRadius()) {
                    fieldFood.remove(i);
                    player.setRadius(player.getRadius() + 5);
                } else {
                    fieldFood.get(i).setRadius(fieldFood.get(i).getRadius() + player.getRadius());
                    player.setRadius(0);
                    gameOver = true;
                }
            }
        }
    }
}

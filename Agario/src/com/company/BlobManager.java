package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class BlobManager extends JPanel implements KeyListener {
    private Blob food;

    private double counter = 0;

    private Blob specialFood;
    private ArrayList<Blob> fieldFood = new ArrayList<>();

    private ArrayList<Blob> specialFoodList = new ArrayList<>();
    private boolean gameOver = false;
    Blob player = new Blob(10, Color.BLUE, 500, 500, 2, 2, 'd', 's');

    public BlobManager() {
        gameOver = false;
    }

    /*
    Ein Blob wird hier erstellt. Es spielt in dieser Methode noch keine Rolle,
    ob es schlussendlich ein food oder ein specialfood wird.
    Sie erhalten ebenfalls eine random position.
     */
    public Blob createBlob(Builder builder, Color color, ArrayList<Blob> listOfBlobs) {
        builder.setBlobColor(color);
        builder.setBlobRadius(
                player.getRadius() - 6 + (Math.random() * (30 + player.getRadius() - 10)));

        for (int i = 0; i < listOfBlobs.size(); i++) {
            if (player.getCoordinateX() == listOfBlobs.get(i).getCoordinateX() &&
                    player.getCoordinateY() == listOfBlobs.get(i).getCoordinateY()) {
                builder.setBlobRadius(player.getRadius() - 5 +
                        (Math.random() * (30 + player.getRadius() - 10)));
            }
        }

        builder.setBlobCoordinates((Math.random() * (1000 - player.getRadius() * 2)),
                (Math.random() * (1000 - player.getRadius() * 2)));
        return (Blob) builder.getBlob();
    }

    /*
    Es wird eine random Farbe generiert. Die Farbe darf aber nicht Blau oder Grün sein.
     */
    public Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);

        if (randomColor != Color.BLUE && randomColor != Color.GREEN) {
            return randomColor;
        } else {
            return Color.MAGENTA;
        }
    }

    /*
    Hier wird food zu einer Liste hinzugefügt.
    Es wird die createBlob Methode aufgerufen und den Blob hinzugefügt.
     */
    public void addFoodToField() {
        Color randomColor = getRandomColor();

        if (fieldFood.size() < 40) {
            food = createBlob(new BlobBuilder(), randomColor, fieldFood);
            fieldFood.add(food);
        }
    }

    /*
    Hier wird specialfood zu einer Liste hinzugefügt.
    Es wird die createBlob Methode aufgerufen und den Blob hinzugefügt.
    */
    public void addSpecialFood() {
        if (specialFoodList.size() < 5) {
            specialFood = createBlob(new BlobBuilder(), Color.green, specialFoodList);
            specialFood.setRadius(50);
            specialFoodList.add(specialFood);
        }
    }

    /*
    Hier wird alles gezeichnedt. Die Map, Blobs und den Endbildschirm.
     */
    public void paint(Graphics g) {
        if (gameOver == true) {
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            g.drawString("Game Over", 300, 320);
            g.drawString("Dimensions: " + counter, 300, 370);
        } else if (gameOver == false && player.getRadius() > 100) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            g.drawString("You Won", 300, 320);
            g.drawString("Dimensions: " + counter, 300, 370);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1000, 1000);

            player.draw(g);

            for (int i = 0; i < fieldFood.size(); i++) {
                if (fieldFood.get(i).getCoordinateX() != player.getCoordinateX() &&
                        fieldFood.get(i).getCoordinateY() != player.getCoordinateY()) {
                    fieldFood.get(i).draw(g);
                }
            }

            for (int i = 0; i < specialFoodList.size(); i++) {
                if (specialFoodList.get(i).getCoordinateX() != player.getCoordinateX() &&
                        specialFoodList.get(i).getCoordinateY() != player.getCoordinateY()) {
                    specialFoodList.get(i).draw(g);
                }
            }
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

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }

    /*
    Hier wird geprüft, ob der Spieler ein food oder ein specialfood berührt.
    Wenn das food kleiner ist, wird der Spieler grösser.
    Wenn das food grösser ist stirbt der Spieler.
     */
    public void checkForCollision() {
        for (int i = 0; i < fieldFood.size(); i++) {
            if (distance(player.getCoordinateX(), player.getCoordinateY(),
                    fieldFood.get(i).getCoordinateX(),
                    fieldFood.get(i).getCoordinateY()) <
                    player.getRadius() + fieldFood.get(i).getRadius()) {
                if (player.getRadius() > fieldFood.get(i).getRadius()) {
                    player.setRadius(player.getRadius() + fieldFood.get(i).getRadius());
                    fieldFood.remove(i);
                    counter = player.getRadius();
                } else {
                    fieldFood.get(i)
                            .setRadius(fieldFood.get(i).getRadius() + player.getRadius());
                    player.setRadius(0);
                    gameOver = true;
                }
            }
        }
    }

    /*
    Überprüft, ob der Spieler ein specialfood berührt.
    Wenn es der Fall ist, stirbt der Spieler.
     */
    public void checkForCollisionWithSpecialFood() {
        for (int i = 0; i < specialFoodList.size(); i++) {
            if (distance(player.getCoordinateX(), player.getCoordinateY(),
                    specialFoodList.get(i).getCoordinateX(),
                    specialFoodList.get(i).getCoordinateY()) <
                    player.getRadius() + specialFoodList.get(i).getRadius()) {
                gameOver = true;
            }
        }
    }

    /*
    Eigentlich unsere Main-Methode. Sie läuft solange, bis gamOver auf true ist.
    GameOver ist auf true, wenn der Spieler stirbt.
     */
    public void run() {
        while (!gameOver) {
            addFoodToField();
            addSpecialFood();

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkForCollision();
            checkForCollisionWithSpecialFood();
            player.move();

            paintImmediately(0, 0, 1000, 1000);
        }
    }

}

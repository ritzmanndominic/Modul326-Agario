package com.company;

import java.awt.*;

public class Blob {

    private int radius;
    private Color color;
    private int coordinateX, coordinateY;
    private int deltaX, deltaY;
    private char leftRight, upDown;


    public Blob(int radius, Color color, int coordinateX, int coordinateY, int deltaX, int deltaY, char leftRight, char upDown) {
        this.radius = radius;
        this.color = color;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.leftRight = leftRight;
        this.upDown = upDown;
    }

    public Blob() {
    }

    public void move() {
        if (leftRight == 'd') {
            coordinateX += deltaX;
        }
        if (leftRight == 'a') {
            coordinateX += deltaX;
        }
        if (upDown == 'w') {
            coordinateY += deltaY;
        }
        if (upDown == 's') {
            coordinateY += deltaY;
        }
    }

    public void moveRight() {
        deltaX += 1;
        leftRight = 'd';
    }

    public void moveLeft() {
        deltaX -= 1;
        leftRight = 'a';
    }

    public void moveUp() {
        deltaY -= 1;
        upDown = 'w';
    }

    public void moveDown() {
        deltaY += 1;
        upDown = 's';
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(coordinateX - radius, coordinateY - radius, radius, radius);
    }

    public void eat(){
        radius+=2;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public Color setColor(Color color) {
        return this.color = color;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
}

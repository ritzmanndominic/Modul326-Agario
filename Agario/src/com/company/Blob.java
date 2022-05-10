package com.company;

import java.awt.*;

public class Blob {

    private double radius;
    private Color color;
    private double coordinateX, coordinateY;
    private double deltaX, deltaY;
    private char leftRight, upDown;


    public Blob(double radius, Color color, double coordinateX, double coordinateY, double deltaX,
                double deltaY, char leftRight, char upDown) {
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
        g.fillOval((int) coordinateX, (int) coordinateY, (int) radius, (int) radius);
    }

    public void eat() {
        radius += 2;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public Color setColor(Color color) {
        return this.color = color;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }
}

package com.company;

import java.awt.*;

public class BlobBuilder implements Builder {

    Blob blob = new Blob();

    @Override
    public int setBlobRadius(int radius) {
        blob.setRadius(radius);
        return this.blob.getRadius();
    }

    @Override
    public Color setBlobColor(Color color) {
        blob.setColor(color);
        return blob.getColor();
    }

    @Override
    public int setBlobCoordinates(int x, int y) {
        blob.setCoordinateX(x);
        blob.setCoordinateY(y);
        return blob.getCoordinateX() + blob.getCoordinateY();
    }

    @Override
    public Object getBlob() {
        return blob;
    }
}

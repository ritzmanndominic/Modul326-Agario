package com.company;

import java.awt.*;

public interface Builder {

    double setBlobRadius(double radius);

    Color setBlobColor(Color color);

    double setBlobCoordinates(double x, double y);

    Object getBlob();
}

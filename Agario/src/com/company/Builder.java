package com.company;

import java.awt.*;

public interface Builder {

    int setBlobRadius(int radius);

    Color setBlobColor(Color color);

    int setBlobCoordinates(int x, int y);

    Object getBlob();
}

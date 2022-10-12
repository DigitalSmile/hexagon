package org.digitalsmile.hexagon.shapes.rectangle;

import org.digitalsmile.hexagon.shapes.Bounds;

public record RectangleBounds(int top, int bottom, int left, int right) implements Bounds {

    public RectangleBounds(int rows, int cols) {
        this(0, rows, 0, cols);
    }
}

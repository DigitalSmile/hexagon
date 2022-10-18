package org.digitalsmile.hexgrid.shapes.rectangle;

import org.digitalsmile.hexgrid.shapes.Shape;

/**
 * Represents props for creating Rectangle shape.
 * Props can be negative, which means we can offset the coordinates of initial hexagon.
 * For simple top left initial starting point use special constructor.
 *
 * @param top    top offset of initial hexagon
 * @param bottom bottom offset of initial hexagon
 * @param left   left offset of initial hexagon
 * @param right  right offset of initial hexagon
 */
public record RectangleShape(int top, int bottom, int left, int right) implements Shape {

    public RectangleShape {
        if (bottom < top || right < left) {
            throw new IllegalArgumentException("Bottom/right offset cannot be less, than top/right! "
                    + bottom + "<" + top + ", " + right + "<" + left);
        }
    }

    /**
     * Helper constructor to create simple rectangle which starts from top left corner.
     *
     * @param rows number of rows
     * @param cols number of columns
     */
    public RectangleShape(int rows, int cols) {
        this(0, rows - 1, 0, cols - 1);
    }

    public int getRows() {
        int gridSizeY;
        if (top < 0 || bottom < 0) {
            gridSizeY = Math.abs(top) + Math.abs(bottom) + 1;
        } else {
            gridSizeY = bottom - top + 1;
        }
        return gridSizeY;
    }

    public int getCols() {
        int gridSizeX;
        if (left < 0 || right < 0) {
            gridSizeX = Math.abs(left) + Math.abs(right) + 1;
        } else {
            gridSizeX = right - left + 1;
        }
        return gridSizeX;
    }

    @Override
    public int getGridSize() {
        return getRows() * getCols();
    }
}

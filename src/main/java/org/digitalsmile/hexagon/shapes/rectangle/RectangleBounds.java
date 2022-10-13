package org.digitalsmile.hexagon.shapes.rectangle;

import org.digitalsmile.hexagon.shapes.Bounds;

/**
 * Represents props for creating Rectangle shape.
 * Props can be negative, which means we can offset the coordinates of initial hexagon.
 * For simple top left initial starting point use special constructor.
 *
 * @param top    - top offset of initial hexagon
 * @param bottom - bottom offset of initial hexagon
 * @param left   - left offset of initial hexagon
 * @param right  - right offset of initial hexagon
 */
public record RectangleBounds(int top, int bottom, int left, int right) implements Bounds {

    /**
     * Helper constructor to create simple rectangle which starts from top left corner.
     *
     * @param rows - number of rows
     * @param cols - number of columns
     */
    public RectangleBounds(int rows, int cols) {
        this(0, rows - 1, 0, cols -1);
    }
}

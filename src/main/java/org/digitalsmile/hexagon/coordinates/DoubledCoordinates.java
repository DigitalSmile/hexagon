package org.digitalsmile.hexagon.coordinates;

import org.digitalsmile.hexagon.layout.Hexagon;

/**
 * Record class to represent doubled coordinates of hexagon in a {@link org.digitalsmile.hexagon.Grid} as described <a href="https://www.redblobgames.com/grids/hexagons/#coordinates-doubled">here</a>.
 * @param row - row index of hexagon
 * @param col - column index of hexagon
 */
public record DoubledCoordinates(int row, int col) {

    /**
     * Creates doubled coordinates by row based doubling.
     * @param hexagon - provided hexagon
     * @return doubled coordinate of hexagon
     */
    public static DoubledCoordinates rowDoubledFromCube(Hexagon hexagon) {
        var row = 2 * hexagon.r() + hexagon.q();
        var col = hexagon.q();
        return new DoubledCoordinates(row, col);
    }

    /**
     * Creates hexagon from doubled row coordinates.
     * @return hexagon record with current doubled coordinates
     */
    public Hexagon rowDoubledToCube() {
        var q = col;
        var r = (row - col) / 2;
        var s = -q - r;
        return new Hexagon(q, r, s);
    }

    /**
     * Creates doubled coordinates by column based doubling.
     * @param hexagon - provided hexagon
     * @return doubled coordinate of hexagon
     */
    public static DoubledCoordinates colDoubledFromCube(Hexagon hexagon) {
        var row = hexagon.r();
        var col = 2 * hexagon.q() + hexagon.r();
        return new DoubledCoordinates(row, col);
    }

    /**
     * Creates hexagon from doubled column coordinates.
     * @return hexagon record with current doubled coordinates
     */
    public Hexagon colDoubledToCube() {
        var q = (col - row) / 2;
        var r = row;
        var s = -q - r;
        return new Hexagon(q, r, s);
    }
}

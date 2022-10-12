package org.digitalsmile.hexagon.coordinates;

import org.digitalsmile.hexagon.layout.Hexagon;

public record DoubledCoordinates(int col, int row) {

    public static DoubledCoordinates qdoubledFromCube(Hexagon hexagon) {
        int col = hexagon.q();
        int row = 2 * hexagon.r() + hexagon.q();
        return new DoubledCoordinates(col, row);
    }


    public Hexagon qdoubledToCube() {
        int q = col;
        int r = (row - col) / 2;
        int s = -q - r;
        return new Hexagon(q, r, s);
    }


    public static DoubledCoordinates rdoubledFromCube(Hexagon hexagon) {
        int col = 2 * hexagon.q() + hexagon.r();
        int row = hexagon.r();
        return new DoubledCoordinates(col, row);
    }


    public Hexagon rdoubledToCube() {
        int q = (col - row) / 2;
        int r = row;
        int s = -q - r;
        return new Hexagon(q, r, s);
    }

}

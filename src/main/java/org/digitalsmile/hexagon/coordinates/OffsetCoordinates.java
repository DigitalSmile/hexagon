package org.digitalsmile.hexagon.coordinates;

import org.digitalsmile.hexagon.layout.Hexagon;
import org.digitalsmile.hexagon.layout.Orientation;

public record OffsetCoordinates(int row, int col) {

    static public OffsetCoordinates fromCube(Orientation orientation, Hexagon hexagon) {
        int col, row, offset;
        switch (orientation){
            case POINTY -> {
                offset = hexagon.r() % 2 == 0 ? 1 : -1;
                col = hexagon.q() + (hexagon.r() + offset * (hexagon.r() & 1)) / 2;
                row = hexagon.r();
            }
            case FLAT -> {
                offset = hexagon.q() % 2 == 0 ? 1 : -1;
                col = hexagon.q();
                row = hexagon.r() + (hexagon.q() + offset * (hexagon.q() & 1)) / 2;
            }
            default -> throw new IllegalStateException("Unexpected value: " + orientation);
        }
        return new OffsetCoordinates(row, col);
    }


    static public Hexagon toCube(Orientation orientation, OffsetCoordinates offsetCoordinates) {
        int q, r, s, offset;
        switch (orientation){
            case POINTY -> {
                offset = offsetCoordinates.row % 2 == 0 ? 1 : -1;
                q = offsetCoordinates.col - (offsetCoordinates.row + offset * (offsetCoordinates.row & 1)) / 2;
                r = offsetCoordinates.row;
                s = -q - r;
            }
            case FLAT -> {
                offset = offsetCoordinates.col % 2 == 0 ? 1 : -1;
                q = offsetCoordinates.col;
                r = offsetCoordinates.row - (offsetCoordinates.col + offset * (offsetCoordinates.col & 1)) / 2;
                s = -q - r;
            }
            default -> throw new IllegalStateException("Unexpected value: " + orientation);
        }
        return new Hexagon(q, r, s);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}


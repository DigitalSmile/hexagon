package org.digitalsmile.hexgrid.shapes.hexagonal;

import org.digitalsmile.hexgrid.shapes.Shape;

/**
 * Represents props for creating Hexagonal shape.
 * @param mapRadius radius of hexagonal view
 */
public record HexagonalShape(int mapRadius) implements Shape {
    @Override
    public int getGridSize() {
        // see https://www.reddit.com/r/Geometry/comments/atdklp/calculating_the_hex_count_in_a_ring_of_hexes/
        return 3 * ((int) Math.pow(mapRadius, 2)) + 3 * mapRadius + 1;
    }
}

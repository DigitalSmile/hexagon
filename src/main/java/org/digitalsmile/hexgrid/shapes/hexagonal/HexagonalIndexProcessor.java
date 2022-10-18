package org.digitalsmile.hexgrid.shapes.hexagonal;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.shapes.IndexProcessor;

public class HexagonalIndexProcessor implements IndexProcessor {
    private final HexagonalShape hexagonalShape;

    public HexagonalIndexProcessor(HexagonalShape hexagonalShape) {
        this.hexagonalShape = hexagonalShape;
    }

    @Override
    public int getIndex(Hexagon hexagon) {
        // https://observablehq.com/@sanderevers/hexmod-representation
        return Math.floorMod(hexagon.r() + (3 * hexagonalShape.mapRadius() + 2) * hexagon.q(), hexagonalShape.getGridSize());
    }
}

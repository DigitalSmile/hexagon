package org.digitalsmile.hexgrid.operations;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Point;
import org.digitalsmile.hexgrid.layout.GridLayout;
import org.digitalsmile.hexgrid.layout.HexagonLayout;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShape;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Tag("RangeOperationsTest")
class RangeOperationsTest {

    @Test
    void testGetRange() {
        var centerHexagon = new Hexagon(0, 0, 0);
        var list = new ArrayList<>() {{
            add(new Hexagon(-3, 0, 3));
            add(new Hexagon(-3, 1, 2));
            add(new Hexagon(-3, 2, 1));
            add(new Hexagon(-3, 3, 0));
            add(new Hexagon(-2, -1, 3));
            add(new Hexagon(-2, 0, 2));
            add(new Hexagon(-2, 1, 1));
            add(new Hexagon(-2, 2, 0));
            add(new Hexagon(-2, 3, -1));
            add(new Hexagon(-1, -2, 3));
            add(new Hexagon(-1, -1, 2));
            add(new Hexagon(-1, 0, 1));
            add(new Hexagon(-1, 1, 0));
            add(new Hexagon(-1, 2, -1));
            add(new Hexagon(-1, 3, -2));
            add(new Hexagon(0, -3, 3));
            add(new Hexagon(0, -2, 2));
            add(new Hexagon(0, -1, 1));
            add(new Hexagon(0, 0, 0));
            add(new Hexagon(0, 1, -1));
            add(new Hexagon(0, 2, -2));
            add(new Hexagon(0, 3, -3));
            add(new Hexagon(1, -3, 2));
            add(new Hexagon(1, -2, 1));
            add(new Hexagon(1, -1, 0));
            add(new Hexagon(1, 0, -1));
            add(new Hexagon(1, 1, -2));
            add(new Hexagon(1, 2, -3));
            add(new Hexagon(2, -3, 1));
            add(new Hexagon(2, -2, 0));
            add(new Hexagon(2, -1, -1));
            add(new Hexagon(2, 0, -2));
            add(new Hexagon(2, 1, -3));
            add(new Hexagon(3, -3, 0));
            add(new Hexagon(3, -2, -1));
            add(new Hexagon(3, -1, -2));
            add(new Hexagon(3, 0, -3));
        }};
        assertIterableEquals(list, RangeOperations.getRange(centerHexagon, 3));
        assertIterableEquals(list, RangeOperations.getRange(centerHexagon, 3, true));
    }

    @Test
    void testGetBoundingHexagons() {
        var boundingPolygon = List.of(
                new Point(0, 0),
                new Point(500, 0),
                new Point(500, 250),
                new Point(250, 500),
                new Point(0, 250)
        );
        var boundedHexagonIncl = List.of(
                new Hexagon(2, 0, -2),
                new Hexagon(2, -1, -1),
                new Hexagon(1, 0, -1),
                new Hexagon(1, -1, 0),
                new Hexagon(0, 0, 0),
                new Hexagon(0, 1, -1),
                new Hexagon(0, 0, 0),
                new Hexagon(-1, 1, 0),
                new Hexagon(-1, 2, -1),
                new Hexagon(0, 2, -2),
                new Hexagon(1, 1, -2),
                new Hexagon(0, 2, -2),
                new Hexagon(1, 2, -3),
                new Hexagon(0, 2, -2),
                new Hexagon(0, 3, -3),
                new Hexagon(1, 3, -4),
                new Hexagon(2, 2, -4),
                new Hexagon(2, 1, -3),
                new Hexagon(2, 2, -4),
                new Hexagon(3, 1, -4),
                new Hexagon(3, 0, -3),
                new Hexagon(2, -1, -1),
                new Hexagon(3, 0, -3),
                new Hexagon(3, -1, -2),
                new Hexagon(3, -2, -1),
                new Hexagon(2, -1, -1),
                new Hexagon(3, 0, -3),
                new Hexagon(4, -1, -3),
                new Hexagon(4, -2, -2)
        );
        var boundedHexagonNotIncl = List.of(
                new Hexagon(2, 0, -2),
                new Hexagon(1, 0, -1),
                new Hexagon(0, 1, -1),
                new Hexagon(1, 1, -2),
                new Hexagon(1, 2, -3),
                new Hexagon(2, 1, -3),
                new Hexagon(3, -1, -2)
        );

        var hexagonalShape = new HexagonalShape(3);
        var gridLayout = new GridLayout<>(hexagonalShape,
                new HexagonLayout(Orientation.FLAT, 150, 129.903, 75, new Point(0, 0)));
        assertIterableEquals(boundedHexagonIncl, RangeOperations.getBoundingHexagon(boundingPolygon, gridLayout, true));
        assertIterableEquals(boundedHexagonNotIncl, RangeOperations.getBoundingHexagon(boundingPolygon, gridLayout, false));
    }
}

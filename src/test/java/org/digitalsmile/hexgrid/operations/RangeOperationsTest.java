package org.digitalsmile.hexgrid.operations;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Tag("RangeOperationsTest")
public class RangeOperationsTest {

    @Test
    void testGetRange() {
        var centerHexagon = new Hexagon(0,0,0);
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
}

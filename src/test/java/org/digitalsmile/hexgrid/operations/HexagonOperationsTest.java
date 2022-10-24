package org.digitalsmile.hexgrid.operations;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Tag("HexagonOperationsTest")
class HexagonOperationsTest {
    @Test
    void testHexagonLinePath() {
        List<Hexagon> testList = new ArrayList<>() {{
            add(new Hexagon(0, 0, 0));
            add(new Hexagon(0, -1, 1));
            add(new Hexagon(0, -2, 2));
            add(new Hexagon(1, -3, 2));
            add(new Hexagon(1, -4, 3));
            add(new Hexagon(1, -5, 4));
        }};
        var resultHexagons = HexagonOperations.hexagonLinePath(new Hexagon(0, 0, 0), new Hexagon(1, -5, 4));
        assertIterableEquals(testList, resultHexagons);
    }

    @Test
    void testReflectQ90() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(2, -3, 1), HexagonOperations.reflectQ90(hexagon));
    }

    @Test
    void testReflectQ45() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(2, 1, -3), HexagonOperations.reflectQ45(hexagon));
    }

    @Test
    void testReflectR90() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(-3, 1, 2), HexagonOperations.reflectR90(hexagon));
    }

    @Test
    void testReflectR45() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(2, 1, -3), HexagonOperations.reflectR45(hexagon));
    }

    @Test
    void testReflectS90() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(1, 2, -3), HexagonOperations.reflectS90(hexagon));
    }

    @Test
    void testReflectS45() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(2, 1, -3), HexagonOperations.reflectS45(hexagon));
    }
}

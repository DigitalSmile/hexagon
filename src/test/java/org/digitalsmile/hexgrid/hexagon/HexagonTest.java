package org.digitalsmile.hexgrid.hexagon;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("HexagonTest")
class HexagonTest {

    @Test
    void testHexagonThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Hexagon(1, 2, 3));
    }

    @Test
    void testToString() {
        var hexagon = new Hexagon(4, -10, 6);
        assertEquals("(4, -10, 6)", hexagon.toString());
    }

    @Test
    void testHexagonAdd() {
        var hexagon1 = new Hexagon(4, -10, 6);
        var hexagon2 = new Hexagon(1, -3, 2).add(new Hexagon(3, -7, 4));
        assertEquals(hexagon1, hexagon2);
    }

    @Test
    void testHexagonSubtract() {
        var hexagon1 = new Hexagon(-2, 4, -2);
        var hexagon2 = new Hexagon(1, -3, 2).subtract(new Hexagon(3, -7, 4));
        assertEquals(hexagon1, hexagon2);
    }

    @Test
    void testHexagonScale() {
        var hexagon1 = new Hexagon(2, -2, 0);
        var hexagon2 = new Hexagon(4, -4, 0);
        assertEquals(hexagon2, hexagon1.scale(2));
    }

    @Test
    void testHexagonDirection() {
        var hexagon1 = new Hexagon(0, -1, 1);
        var hexagon2 = new Hexagon(0, 0, 0);
        assertEquals(HexagonDirection.NORTH, hexagon2.direction(hexagon1));
    }

    @Test
    void testHexagonNeighbour() {
        var hexagon1 = new Hexagon(1, -3, 2);
        var hexagon2 = new Hexagon(1, -2, 1).neighbor(HexagonDirection.NORTH);
        assertEquals(hexagon1, hexagon2);
    }

    @Test
    void testHexagonDiagonal() {
        var hexagon1 = new Hexagon(-1, -1, 2);
        var hexagon2 = new Hexagon(1, -2, 1).diagonalNeighbor(3);
        assertEquals(hexagon1, hexagon2);
    }

    @Test
    void testHexagonDistance() {
        assertEquals(7, new Hexagon(3, -7, 4).distance(new Hexagon(0, 0, 0)));
    }

    @Test
    void testHexagonRotateRight() {
        var hexagon1 = new Hexagon(1, -3, 2).rotateRight();
        var hexagon2 = new Hexagon(3, -2, -1);
        assertEquals(hexagon1, hexagon2);
    }

    @Test
    void testHexagonRotateLeft() {
        var hexagon1 = new Hexagon(1, -3, 2).rotateLeft();
        var hexagon2 = new Hexagon(-2, -1, 3);
        assertEquals(hexagon1, hexagon2);
    }

    @Test
    void testReflectQ() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(-2, 3, -1), hexagon.reflectQ());
    }

    @Test
    void testReflectR() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(3, -1, -2), hexagon.reflectR());
    }

    @Test
    void testReflectS() {
        var hexagon = new Hexagon(-2, -1, 3);
        assertEquals(new Hexagon(-1, -2, 3), hexagon.reflectS());
    }

    @Test
    void testNegate() {
        var hexagon = new Hexagon(1, -3, 2);
        assertEquals(new Hexagon(-1, 3, -2), hexagon.negate());
    }

}

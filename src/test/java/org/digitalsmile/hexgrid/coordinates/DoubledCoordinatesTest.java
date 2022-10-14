package org.digitalsmile.hexgrid.coordinates;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("DoubledCoordinatesTest")
public class DoubledCoordinatesTest {

    @Test
    void testRowDoubledFromCube() {
        var hexagon = new Hexagon(1, -1, 0);
        var doubledCoordinate = new DoubledCoordinates(-1, 1);
        assertEquals(doubledCoordinate, DoubledCoordinates.rowDoubledFromCube(hexagon));
    }

    @Test
    void testColDoubledFromCube() {
        var hexagon = new Hexagon(2, -1, -1);
        var doubledCoordinate = new DoubledCoordinates(-1, 3);
        assertEquals(doubledCoordinate, DoubledCoordinates.colDoubledFromCube(hexagon));
    }

    @Test
    void testRowDoubledToCube() {
        var hexagon = new Hexagon(1, -1, 0);
        var doubledCoordinate = new DoubledCoordinates(-1, 1);
        assertEquals(hexagon, doubledCoordinate.rowDoubledToCube());
    }

    @Test
    void testColDoubledToCube() {
        var hexagon = new Hexagon(2, -1, -1);
        var doubledCoordinate = new DoubledCoordinates(-1, 3);
        assertEquals(hexagon, doubledCoordinate.colDoubledToCube());
    }
}

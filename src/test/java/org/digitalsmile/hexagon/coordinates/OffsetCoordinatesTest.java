package org.digitalsmile.hexagon.coordinates;

import org.digitalsmile.hexagon.layout.Hexagon;
import org.digitalsmile.hexagon.layout.Orientation;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("OffsetCoordinatesTest")
public class OffsetCoordinatesTest {
    @Test
    void testFromCube() {
        var hexagon = new Hexagon(2, -2, 0);
        var offsetCoordinatesFlat = new OffsetCoordinates(-1, 2);
        var offsetCoordinatesPointy = new OffsetCoordinates(-2, 1);

        assertEquals(offsetCoordinatesFlat, OffsetCoordinates.fromCube(Orientation.FLAT, hexagon));
        assertEquals(offsetCoordinatesPointy, OffsetCoordinates.fromCube(Orientation.POINTY, hexagon));
    }

    @Test
    void testToCube() {
        var hexagon = new Hexagon(2, -2, 0);
        var offsetCoordinatesFlat = new OffsetCoordinates(-1, 2);
        var offsetCoordinatesPointy = new OffsetCoordinates(-2, 1);

        assertEquals(hexagon, OffsetCoordinates.toCube(Orientation.FLAT, offsetCoordinatesFlat));
        assertEquals(hexagon, OffsetCoordinates.toCube(Orientation.POINTY, offsetCoordinatesPointy));
    }

    @Test
    void testToString() {
        var offsetCoordinates = new OffsetCoordinates(1,1);
        assertEquals("(1, 1)", offsetCoordinates.toString());
    }
}

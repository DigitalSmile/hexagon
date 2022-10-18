package org.digitalsmile.hexgrid.layout;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("OrientationTest")
class OrientationTest {

    @Test
    void testPixelCoordinate() {
        var hexagon = new Hexagon(2, -1, -1);
        var epsilon = 0.000001d;
        assertEquals(194.8557158514987d, Orientation.POINTY.pixelCoordinateX(hexagon, 75), epsilon);
        assertEquals(-112.5d, Orientation.POINTY.pixelCoordinateY(hexagon, 75), epsilon);

        assertEquals(225.0d, Orientation.FLAT.pixelCoordinateX(hexagon, 75), epsilon);
        assertEquals(0.0d, Orientation.FLAT.pixelCoordinateY(hexagon, 75), epsilon);
    }

    @Test
    void testCornerList() {
        var epsilon = 0.000001d;
        var flatValuesCos = List.of(1.0d, 0.5000000000000001d, -0.4999999999999998d, -1.0d, -0.5000000000000004d, 0.5000000000000001d);
        var flatValuesSin = List.of(0.0d, 0.8660254037844386d, 0.8660254037844387d, 1.2246467991473532E-16d, -0.8660254037844384d, -0.8660254037844386d);
        var pointyValuesCos = List.of(0.8660254037844387d, 6.123233995736766E-17d, -0.8660254037844387d, -0.8660254037844388d, -1.8369701987210297E-16d, 0.8660254037844384d);
        var pointyValuesSin = List.of(0.49999999999999994d, 1.0d, 0.49999999999999994d, -0.4999999999999997d, -1.0d, -0.5000000000000004d);

        IntStream.range(0, 6)
                .forEach(value -> {
                    assertEquals(
                            flatValuesCos.get(value),
                            Orientation.FLAT.getCosCorner(value),
                            epsilon);
                    assertEquals(
                            flatValuesSin.get(value),
                            Orientation.FLAT.getSinCorner(value),
                            epsilon);
                    assertEquals(
                            pointyValuesCos.get(value),
                            Orientation.POINTY.getCosCorner(value),
                            epsilon);
                    assertEquals(
                            pointyValuesSin.get(value),
                            Orientation.POINTY.getSinCorner(value),
                            epsilon);
                });
    }
}

package org.digitalsmile.hexagon.layout;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Tag("LayoutTest")
public class LayoutTest {

    @Test
    void testHexagonCenterPoint() {
        var layoutFlat = new Layout(Orientation.FLAT, 0,0,75, new Point(535,321));
        var testPointFlat = new Point(497.5d,191.0961894323342d);
        var layoutPointy = new Layout(Orientation.POINTY, 0,0,75, new Point(535,321));
        var testPointPointy = new Point(405.0961894323342d,283.5d);

        var hexagon = new Hexagon(-1, -1, 2);
        assertEquals(testPointFlat, layoutFlat.getHexagonCenterPoint(hexagon));
        assertEquals(testPointPointy, layoutPointy.getHexagonCenterPoint(hexagon));
    }

    @Test
    void testHexagonCornerPoints() {
        var layoutFlat = new Layout(Orientation.FLAT, 0,0,75, new Point(535,321));
        var flatCorners = List.of(
                new Point(75.0d, 0.0d),
                new Point(37.50000000000001d, 64.9519052838329d),
                new Point(-37.499999999999986d, 64.9519052838329d),
                new Point(-75.0d, 9.184850993605149E-15d),
                new Point(-37.500000000000036d, -64.95190528383287d),
                new Point(37.50000000000001d, -64.9519052838329d)
        );
        var layoutPointy = new Layout(Orientation.POINTY, 0,0,75, new Point(535,321));
        var pointyCorners = List.of(
                new Point(64.9519052838329d, 37.49999999999999d),
                new Point(4.592425496802574E-15d, 75.0d),
                new Point(-64.9519052838329d, 37.49999999999999d),
                new Point(-64.95190528383291d, -37.49999999999998d),
                new Point(-1.3777276490407723E-14d, -75.0d),
                new Point(64.95190528383287d, -37.500000000000036d)
        );
        IntStream.range(0, 6).forEach(value -> {
            assertEquals(flatCorners.get(value),  layoutFlat.getHexagonCornerPoint(value));
            assertEquals(pointyCorners.get(value),  layoutPointy.getHexagonCornerPoint(value));
        });
    }

    @Test
    void testHexagonPoints() {
        var hexagon = new Hexagon(-1, -1, 2);
        var layoutFlat = new Layout(Orientation.FLAT, 0,0,75, new Point(535,321));
        var flatCorners = List.of(
                new Point(572.5d, 191.0961894323342d),
                new Point(535.0d, 256.0480947161671d),
                new Point(460.0d, 256.0480947161671d),
                new Point(422.5d, 191.0961894323342d),
                new Point(459.99999999999994d, 126.14428414850133d),
                new Point(535.0d, 126.1442841485013d)
        );

        var layoutPointy = new Layout(Orientation.POINTY, 0,0,75, new Point(535,321));
        var pointyCorners = List.of(
                new Point(470.0480947161671d, 321.0d),
                new Point(405.0961894323342d, 358.5d),
                new Point(340.1442841485013d, 321.0d),
                new Point(340.1442841485013d, 246.00000000000003d),
                new Point(405.0961894323342d, 208.5d),
                new Point(470.04809471616704d, 245.99999999999997d)
        );
        assertIterableEquals(pointyCorners, layoutPointy.getHexagonPoints(hexagon));
        assertIterableEquals(flatCorners, layoutFlat.getHexagonPoints(hexagon));
    }

    @Test
    void testLayoutHexagonDimensions() {
        var epsilon = 0.000001d;
        var layoutFlat = new Layout(Orientation.FLAT, 0,0,75, new Point(535,321));
        assertEquals(150d, layoutFlat.getHexagonWidth(), epsilon);
        assertEquals(129.9038105676658d, layoutFlat.getHexagonHeight(), epsilon);
        layoutFlat = new Layout(Orientation.FLAT, 150,0,0, new Point(535,321));
        assertEquals(75d, layoutFlat.getSide(), epsilon);
        assertEquals(129.9038105676658d, layoutFlat.getHexagonHeight(), epsilon);
        layoutFlat = new Layout(Orientation.FLAT, 0,129.9038105676658d,0, new Point(535,321));
        assertEquals(75d, layoutFlat.getSide(), epsilon);
        assertEquals(150d, layoutFlat.getHexagonWidth(), epsilon);

        assertEquals(new Point(535,321), layoutFlat.getOffset());
    }

    @Test
    void testFlatLayoutPosition() {
        var hexagon = new Hexagon(-2, 0, 2);
        var layout = new Layout(Orientation.FLAT,150, 0,  0, new Point(0, 0));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-157, -121)));
        layout = new Layout(Orientation.FLAT,0, 129.9,  0, new Point(0, 0));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-157, -121)));
        layout = new Layout(Orientation.FLAT,0, 0,  75, new Point(0, 0));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-157, -121)));
        layout = new Layout(Orientation.FLAT,0, 0,  75, new Point(100, 100));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-57, -21)));
    }

    @Test
    void testPointyLayoutPosition() {
        var hexagon = new Hexagon(-2, 1, 1);
        var layout = new Layout(Orientation.POINTY,150, 0,  0, new Point(0, 0));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-163, 139)));
        layout = new Layout(Orientation.POINTY,0, 129.9,  0, new Point(0, 0));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-163, 139)));
        layout = new Layout(Orientation.POINTY,0, 0,  75, new Point(0, 0));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-163, 139)));
        layout = new Layout(Orientation.POINTY,0, 0,  75, new Point(100, 100));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-63, 239)));
    }

    @Test
    void testHexagonRound() {
        var a = new FractionalHexagon(0.0, 0.0, 0.0);
        var b = new FractionalHexagon(1.0, -1.0, 0.0);
        var c = new FractionalHexagon(0.0, -1.0, 1.0);

        assertEquals(new Hexagon(5, -10, 5), new FractionalHexagon(0.0, 0.0, 0.0).interpolate(new FractionalHexagon(10.0, -20.0, 10.0), 0.5).roundToHexagon());
        assertEquals(a.roundToHexagon(), a.interpolate(b, 0.499).roundToHexagon());
        assertEquals(b.roundToHexagon(), a.interpolate(b, 0.501).roundToHexagon());
        assertEquals(a.roundToHexagon(), new FractionalHexagon(a.q() * 0.4 + b.q() * 0.3 + c.q() * 0.3, a.r() * 0.4 + b.r() * 0.3 + c.r() * 0.3, a.s() * 0.4 + b.s() * 0.3 + c.s() * 0.3).roundToHexagon());
        assertEquals(c.roundToHexagon(), new FractionalHexagon(a.q() * 0.3 + b.q() * 0.3 + c.q() * 0.4, a.r() * 0.3 + b.r() * 0.3 + c.r() * 0.4, a.s() * 0.3 + b.s() * 0.3 + c.s() * 0.4).roundToHexagon());
    }


}

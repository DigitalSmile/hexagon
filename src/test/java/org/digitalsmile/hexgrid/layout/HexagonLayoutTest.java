package org.digitalsmile.hexgrid.layout;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Point;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Tag("LayoutTest")
class HexagonLayoutTest {

    @Test
    void testHexagonCenterPoint() {
        var epsilon = 0.000001d;
        var layoutFlat = new HexagonLayout(Orientation.FLAT, 150, 129.9038, 75, new Point(535, 321));
        var testPointFlat = new Point(497.5d, 191.0961841485013d);
        var layoutPointy = new HexagonLayout(Orientation.POINTY, 150, 129.9038, 75, new Point(535, 321));
        var testPointPointy = new Point(415.1442841485013d, 273.4519d);

        var hexagon = new Hexagon(-1, -1, 2);
        assertEquals(testPointFlat, layoutFlat.getHexagonCenterPoint(hexagon));
        assertEquals(testPointPointy, layoutPointy.getHexagonCenterPoint(hexagon));
    }

    @Test
    void testHexagonPoints() {
        var hexagon = new Hexagon(-1, -1, 2);
        var layoutFlat = new HexagonLayout(Orientation.FLAT, 150, 129.9038, 75, new Point(535, 321));
        var flatCorners = List.of(
                new Point(572.5d, 191.0961841485013d),
                new Point(535.0d, 256.0480894323342d),
                new Point(460.0d, 256.0480894323342d),
                new Point(422.5d, 191.0961841485013d),
                new Point(459.99999999999994d, 126.14427886466842d),
                new Point(535.0d, 126.1442788646684d)
        );

        var layoutPointy = new HexagonLayout(Orientation.POINTY, 150, 129.9038, 75, new Point(535, 321));
        var pointyCorners = List.of(
                new Point(480.0961894323342d, 310.9519d),
                new Point(415.1442841485013d, 348.4519d),
                new Point(350.1923788646684d, 310.9519d),
                new Point(350.1923788646684d, 235.95190000000005d),
                new Point(415.1442841485013d, 198.45190000000002d),
                new Point(480.0961894323342d, 235.9519d)
        );
        assertIterableEquals(pointyCorners, layoutPointy.getHexagonCornerPoints(hexagon));
        assertIterableEquals(flatCorners, layoutFlat.getHexagonCornerPoints(hexagon));
    }

    @Test
    void testFlatLayoutPosition() {
        var hexagon = new Hexagon(-2, 0, 2);
        var layout = new HexagonLayout(Orientation.FLAT, 150, 129.9038, 75, new Point(0, 0));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-157, -121)));
        layout = new HexagonLayout(Orientation.FLAT, 150, 129.9038, 75, new Point(100, 100));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-57, -21)));
    }

    @Test
    void testPointyLayoutPosition() {
        var hexagon = new Hexagon(-2, 1, 1);
        var layout = new HexagonLayout(Orientation.POINTY, 150, 129.9038, 75, new Point(0, 0));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-163, 139)));
        layout = new HexagonLayout(Orientation.POINTY, 150, 129.9038, 75, new Point(100, 100));
        assertEquals(hexagon, layout.getBoundingHexagon(new Point(-63, 239)));
    }


}

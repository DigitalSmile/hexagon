package org.digitalsmile.hexgrid;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Orientation;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalBounds;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleBounds;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("HexagonGridTest")
public class HexagonGridTest {

    @Test
    void testCreateGridFlat() {
        var hexagonGrid = new HexagonGrid.HexagonGridBuilder()
                .orientation(Orientation.FLAT)
                .hexagonWidth(150)
                .hexagonShape(new HexagonalBounds(3))
                .offsetX(10)
                .offsetY(10)
                .hexagonMetaObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(150, hexagonGrid.getHexagonLayout().getHexagonWidth());

        hexagonGrid = new HexagonGrid.HexagonGridBuilder()
                .orientation(Orientation.FLAT)
                .hexagonHeight(150)
                .hexagonShape(new HexagonalBounds(3))
                .offsetX(10)
                .offsetY(10)
                .hexagonMetaObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(150, hexagonGrid.getHexagonLayout().getHexagonHeight());

        hexagonGrid = new HexagonGrid.HexagonGridBuilder()
                .orientation(Orientation.FLAT)
                .side(75)
                .hexagonShape(new HexagonalBounds(3))
                .offsetX(10)
                .offsetY(10)
                .hexagonMetaObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(75, hexagonGrid.getHexagonLayout().getSide());
    }

    @Test
    void testCreateGridPointy() {
        var hexagonGrid = new HexagonGrid.HexagonGridBuilder()
                .orientation(Orientation.POINTY)
                .hexagonWidth(150)
                .hexagonShape(new HexagonalBounds(3))
                .offsetX(10)
                .offsetY(10)
                .hexagonMetaObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(150, hexagonGrid.getHexagonLayout().getHexagonWidth());

        hexagonGrid = new HexagonGrid.HexagonGridBuilder()
                .orientation(Orientation.POINTY)
                .hexagonHeight(150)
                .hexagonShape(new HexagonalBounds(3))
                .offsetX(10)
                .offsetY(10)
                .hexagonMetaObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(150, hexagonGrid.getHexagonLayout().getHexagonHeight());

        hexagonGrid = new HexagonGrid.HexagonGridBuilder()
                .orientation(Orientation.POINTY)
                .side(75)
                .hexagonShape(new HexagonalBounds(3))
                .offsetX(10)
                .offsetY(10)
                .hexagonMetaObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(75, hexagonGrid.getHexagonLayout().getSide());
    }

    @Test
    void testCreateGridFailed() {
        assertThrows(IllegalArgumentException.class, () -> new HexagonGrid.HexagonGridBuilder().build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .hexagonShape(new HexagonalBounds(5))
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .hexagonShape(null)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .rectangleShape(new RectangleBounds(1,1))
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .rectangleShape(null)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .rectangleShape(new RectangleBounds(1,1))
                        .hexagonWidth(1)
                        .hexagonHeight(0)
                        .side(1)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .rectangleShape(new RectangleBounds(1,1))
                        .hexagonWidth(0)
                        .hexagonHeight(1)
                        .side(1)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .rectangleShape(new RectangleBounds(1,1))
                        .hexagonHeight(1)
                        .hexagonWidth(0)
                        .side(1)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .rectangleShape(new RectangleBounds(1,1))
                        .hexagonWidth(1)
                        .hexagonHeight(1)
                        .side(0)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .rectangleShape(new RectangleBounds(1,1))
                        .hexagonWidth(0)
                        .hexagonHeight(0)
                        .side(0)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder()
                        .orientation(Orientation.FLAT)
                        .rectangleShape(new RectangleBounds(1,1))
                        .hexagonWidth(1)
                        .hexagonHeight(1)
                        .side(1)
                        .build());
    }
}

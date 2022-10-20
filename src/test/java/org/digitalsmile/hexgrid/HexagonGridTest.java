package org.digitalsmile.hexgrid;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShape;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShape;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("HexagonGridTest")
class HexagonGridTest {

    @Test
    void testCreateGridFlat() {
        var hexagonGrid = new HexagonGrid.HexagonGridBuilder<>()
                .hexagonWidth(150)
                .shape(new HexagonalShape(3), Orientation.FLAT)
                .offsetX(10)
                .offsetY(10)
                .hexagonDataObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(150, hexagonGrid.getGridLayout().getHexagonLayout().getHexagonWidth());

        hexagonGrid = new HexagonGrid.HexagonGridBuilder<>()
                .hexagonHeight(150)
                .shape(new HexagonalShape(3), Orientation.FLAT)
                .offsetX(10)
                .offsetY(10)
                .hexagonDataObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(150, hexagonGrid.getGridLayout().getHexagonLayout().getHexagonHeight());

        hexagonGrid = new HexagonGrid.HexagonGridBuilder<>()
                .side(75)
                .shape(new HexagonalShape(3), Orientation.FLAT)
                .offsetX(10)
                .offsetY(10)
                .hexagonDataObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(75, hexagonGrid.getGridLayout().getHexagonLayout().getSide());
    }

    @Test
    void testCreateGridPointy() {
        var hexagonGrid = new HexagonGrid.HexagonGridBuilder<>()
                .hexagonWidth(150)
                .shape(new HexagonalShape(3), Orientation.POINTY)
                .offsetX(10)
                .offsetY(10)
                .hexagonDataObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(150, hexagonGrid.getGridLayout().getHexagonLayout().getHexagonWidth());

        hexagonGrid = new HexagonGrid.HexagonGridBuilder<>()
                .hexagonHeight(150)
                .shape(new HexagonalShape(3), Orientation.POINTY)
                .offsetX(10)
                .offsetY(10)
                .hexagonDataObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(150, hexagonGrid.getGridLayout().getHexagonLayout().getHexagonHeight());

        hexagonGrid = new HexagonGrid.HexagonGridBuilder<>()
                .side(75)
                .shape(new HexagonalShape(3), Orientation.POINTY)
                .offsetX(10)
                .offsetY(10)
                .hexagonDataObjectHook(Hexagon::toString)
                .build();
        hexagonGrid.generateHexagons();
        assertEquals(37, hexagonGrid.getHexagons().size());
        assertEquals("(0, 0, 0)", hexagonGrid.getHexagonDataObject(new Hexagon(0,0,0)));
        assertEquals(75, hexagonGrid.getGridLayout().getHexagonLayout().getSide());
    }

    @Test
    void testCreateGridFailed() {
        assertThrows(IllegalArgumentException.class, () -> new HexagonGrid.HexagonGridBuilder<>().build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .shape(new HexagonalShape(5), Orientation.FLAT)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .shape(null, Orientation.FLAT)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .shape(new RectangleShape(1,1), Orientation.FLAT)
                        .hexagonWidth(1)
                        .hexagonHeight(0)
                        .side(1)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .shape(new RectangleShape(1,1), Orientation.FLAT)
                        .hexagonWidth(0)
                        .hexagonHeight(1)
                        .side(1)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .shape(new RectangleShape(1,1), Orientation.FLAT)
                        .hexagonHeight(1)
                        .hexagonWidth(0)
                        .side(1)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .shape(new RectangleShape(1,1), Orientation.FLAT)
                        .hexagonWidth(1)
                        .hexagonHeight(1)
                        .side(0)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .shape(new RectangleShape(1,1), Orientation.FLAT)
                        .hexagonWidth(0)
                        .hexagonHeight(0)
                        .side(0)
                        .build());
        assertThrows(IllegalArgumentException.class,
                () -> new HexagonGrid.HexagonGridBuilder<>()
                        .shape(new RectangleShape(1,1), Orientation.FLAT)
                        .hexagonWidth(1)
                        .hexagonHeight(1)
                        .side(1)
                        .build());
    }
}

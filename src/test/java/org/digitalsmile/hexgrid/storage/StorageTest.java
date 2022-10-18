package org.digitalsmile.hexgrid.storage;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleIndexProcessor;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShape;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("StorageTest")
class StorageTest {

    @Test
    void testAddAndGetStorage() {
        var indexProcessor = new RectangleIndexProcessor(new RectangleShape(2,2), Orientation.FLAT);
        var dataStorage = new HexagonStorage<>(4, indexProcessor, null);
        dataStorage.addHexagon(new Hexagon(1, -1, 0));
        dataStorage.addHexagon(new Hexagon(1, 1, -2));

        assertEquals(4, dataStorage.getHexagons().size());
        assertTrue(dataStorage.getHexagons().contains(new Hexagon(1, -1, 0)));
        assertTrue(dataStorage.getHexagons().contains(new Hexagon(1, 1, -2)));

        dataStorage = new HexagonStorage<>(4, indexProcessor, Hexagon::toString);
        dataStorage.addHexagon(new Hexagon(1, -1, 0));
        dataStorage.addHexagon(new Hexagon(1, 1, -2));

        assertEquals(4, dataStorage.getHexagons().size());
        assertTrue(dataStorage.getHexagons().contains(new Hexagon(1, -1, 0)));
        assertTrue(dataStorage.getHexagons().contains(new Hexagon(1, 1, -2)));

        assertEquals(4, dataStorage.getHexagonDataObjects().size());
        assertEquals("(1, -1, 0)", dataStorage.getHexagonDataObject(new Hexagon(1, -1, 0)));
        assertEquals("(1, 1, -2)", dataStorage.getHexagonDataObject(new Hexagon(1, 1, -2)));
    }

    @Test
    void testClearStorage() {
        var indexProcessor = new RectangleIndexProcessor(new RectangleShape(2,2), Orientation.FLAT);
        var dataStorage = new HexagonStorage<>(4, indexProcessor, Hexagon::toString);
        dataStorage.addHexagon(new Hexagon(1, -1, 0));
        dataStorage.addHexagon(new Hexagon(1, 1, -2));

        assertEquals(4, dataStorage.getHexagons().size());
        dataStorage.clear();
        assertEquals(0, dataStorage.getHexagons().size());
        assertEquals(0, dataStorage.getHexagonDataObjects().size());
    }
}

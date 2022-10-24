package org.digitalsmile.hexgrid.storage;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleIndexProcessor;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShape;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("StorageTest")
class StorageTest {

    @Test
    void testAddAndGetStorage() {
        var indexProcessor = new RectangleIndexProcessor(new RectangleShape(2,2), Orientation.FLAT);
        var dataStorage = new HexagonStorage<>(4, indexProcessor, null);
        dataStorage.addHexagon(new Hexagon(1, -1, 0));
        dataStorage.addHexagon(new Hexagon(1, 1, -2));

        var list = List.of(
                new Hexagon(0, 2, -2),
                new Hexagon(1, 1, -2),
                new Hexagon(2, 1, -3),
                new Hexagon(3, 0, -3),
                new Hexagon(3, 1, -4),
                new Hexagon(3, 2, -5),
                new Hexagon(2, 2, -4),
                new Hexagon(0, 3, -3)
        );

        HexagonStorage<Object> finalDataStorage = dataStorage;
        assertThrows(IllegalArgumentException.class, () ->  finalDataStorage.addHexagon(new Hexagon(10, 20, -30)));
        assertEquals(4, dataStorage.getHexagons().size());
        assertTrue(dataStorage.containsHexagon(new Hexagon(1, -1, 0)));
        assertTrue(dataStorage.containsHexagon(new Hexagon(1, 1, -2)));
        assertFalse(dataStorage.containsHexagon(new Hexagon(10, 20, -30)));
        assertEquals(List.of(new Hexagon(1,1,-2)), dataStorage.getHexagons(list));
        assertEquals(List.of(new Hexagon(1,1,-2)), dataStorage.getHexagons(list, true));

        dataStorage = new HexagonStorage<>(4, indexProcessor, Hexagon::toString);
        dataStorage.addHexagon(new Hexagon(1, -1, 0));
        dataStorage.addHexagon(new Hexagon(1, 1, -2));

        assertEquals(4, dataStorage.getHexagons().size());
        assertTrue(dataStorage.containsHexagon(new Hexagon(1, -1, 0)));
        assertTrue(dataStorage.containsHexagon(new Hexagon(1, 1, -2)));

        assertEquals(4, dataStorage.getHexagonDataObjects().size());
        assertEquals("(1, -1, 0)", dataStorage.getHexagonDataObject(new Hexagon(1, -1, 0)));
        assertEquals("(1, 1, -2)", dataStorage.getHexagonDataObject(new Hexagon(1, 1, -2)));
        assertNull(dataStorage.getHexagonDataObject(new Hexagon(10, 20, -30)));
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

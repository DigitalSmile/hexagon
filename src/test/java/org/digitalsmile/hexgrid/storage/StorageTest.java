package org.digitalsmile.hexgrid.storage;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("StorageTest")
public class StorageTest {

    @Test
    void testAddAndGetStorage() {
        var dataStorage = new HexagonMetaObjectStorage<>();
        dataStorage.addHexagon(new Hexagon(1, -1, 0));
        dataStorage.addHexagon(new Hexagon(1, 1, -2));

        assertEquals(2, dataStorage.getHexagons().size());
        assertTrue(dataStorage.getHexagons().contains(new Hexagon(1, -1, 0)));
        assertTrue(dataStorage.getHexagons().contains(new Hexagon(1, 1, -2)));

        dataStorage = new HexagonMetaObjectStorage<>(Hexagon::toString);
        dataStorage.addHexagon(new Hexagon(1, -1, 0));
        dataStorage.addHexagon(new Hexagon(1, 1, -2));

        assertEquals(2, dataStorage.getHexagons().size());
        assertTrue(dataStorage.getHexagons().contains(new Hexagon(1, -1, 0)));
        assertTrue(dataStorage.getHexagons().contains(new Hexagon(1, 1, -2)));

        assertEquals(2, dataStorage.getHexagonDataObjects().size());
        assertEquals("(1, -1, 0)", dataStorage.getHexagonDataObject(new Hexagon(1, -1, 0)));
        assertEquals("(1, 1, -2)", dataStorage.getHexagonDataObject(new Hexagon(1, 1, -2)));
    }

    @Test
    void testClearSorage() {
        var dataStorage = new HexagonMetaObjectStorage<>(Hexagon::toString);
        dataStorage.addHexagon(new Hexagon(1, -1, 0));
        dataStorage.addHexagon(new Hexagon(1, 1, -2));

        assertEquals(2, dataStorage.getHexagons().size());
        dataStorage.clear();
        assertEquals(0, dataStorage.getHexagons().size());
        assertEquals(0, dataStorage.getHexagonDataObjects().size());
    }
}

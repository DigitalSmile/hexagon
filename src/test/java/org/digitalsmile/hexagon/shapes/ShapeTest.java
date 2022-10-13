package org.digitalsmile.hexagon.shapes;

import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.shapes.hexagonal.HexagonalBounds;
import org.digitalsmile.hexagon.shapes.rectangle.RectangleBounds;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("ShapeTest")
public class ShapeTest {

    @Test
    void testCreateShapeHexagonal() {
        var hexagonalBounds = new HexagonalBounds(3);
        var dataStorage = new HexagonMetaObjectStorage<>();
        Shape.HEXAGONAL.createShape(hexagonalBounds, dataStorage, Orientation.POINTY);
        assertEquals(37, dataStorage.getHexagons().size());
        dataStorage.clearHexagons();
        Shape.HEXAGONAL.createShape(hexagonalBounds, dataStorage, Orientation.FLAT);
        assertEquals(37, dataStorage.getHexagons().size());
    }

    @Test
    void testCreateShapeRectangle() {
        var rectangleBounds = new RectangleBounds(3, 3);
        var dataStorage = new HexagonMetaObjectStorage<>();
        Shape.RECTANGLE.createShape(rectangleBounds, dataStorage, Orientation.POINTY);
        assertEquals(9, dataStorage.getHexagons().size());
        dataStorage.clearHexagons();
        Shape.RECTANGLE.createShape(rectangleBounds, dataStorage, Orientation.FLAT);
        assertEquals(9, dataStorage.getHexagons().size());
        dataStorage.clearHexagons();

        rectangleBounds = new RectangleBounds(1, 3 , 1, 1);
        Shape.RECTANGLE.createShape(rectangleBounds, dataStorage, Orientation.POINTY);
        assertEquals(3, dataStorage.getHexagons().size());
        dataStorage.clearHexagons();
        Shape.RECTANGLE.createShape(rectangleBounds, dataStorage, Orientation.FLAT);
        assertEquals(3, dataStorage.getHexagons().size());
    }
}

package org.digitalsmile.hexgrid.shapes;

import org.digitalsmile.hexgrid.hexagon.Orientation;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalBounds;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleBounds;
import org.digitalsmile.hexgrid.storage.HexagonMetaObjectStorage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("ShapeTest")
class ShapeTest {

    @Test
    void testCreateShapeHexagonal() {
        var hexagonalBounds = new HexagonalBounds(3);
        var dataStorage = new HexagonMetaObjectStorage<>();
        Shape.HEXAGONAL.createShape(hexagonalBounds, dataStorage, Orientation.POINTY);
        assertEquals(37, dataStorage.getHexagons().size());
        dataStorage.clear();
        Shape.HEXAGONAL.createShape(hexagonalBounds, dataStorage, Orientation.FLAT);
        assertEquals(37, dataStorage.getHexagons().size());
    }

    @Test
    void testCreateShapeRectangle() {
        var rectangleBounds = new RectangleBounds(3, 3);
        var dataStorage = new HexagonMetaObjectStorage<>();
        Shape.RECTANGLE.createShape(rectangleBounds, dataStorage, Orientation.POINTY);
        assertEquals(9, dataStorage.getHexagons().size());
        dataStorage.clear();
        Shape.RECTANGLE.createShape(rectangleBounds, dataStorage, Orientation.FLAT);
        assertEquals(9, dataStorage.getHexagons().size());
        dataStorage.clear();

        rectangleBounds = new RectangleBounds(1, 3 , 1, 1);
        Shape.RECTANGLE.createShape(rectangleBounds, dataStorage, Orientation.POINTY);
        assertEquals(3, dataStorage.getHexagons().size());
        dataStorage.clear();
        Shape.RECTANGLE.createShape(rectangleBounds, dataStorage, Orientation.FLAT);
        assertEquals(3, dataStorage.getHexagons().size());
    }
}

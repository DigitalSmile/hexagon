package org.digitalsmile.hexgrid.shapes;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Point;
import org.digitalsmile.hexgrid.layout.GridLayout;
import org.digitalsmile.hexgrid.layout.HexagonLayout;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalIndexProcessor;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShape;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShapeBuilder;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleIndexProcessor;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShape;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShapeBuilder;
import org.digitalsmile.hexgrid.storage.HexagonStorage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("ShapeTest")
class ShapeTest {

    @Test
    void testCreateShapeHexagonal() {
        var hexagonalShape = new HexagonalShape(3);
        var indexProcessor = new HexagonalIndexProcessor(hexagonalShape);
        var dataStorage = new HexagonStorage<>(hexagonalShape.getGridSize(), indexProcessor, null);
        var gridLayout = new GridLayout<>(hexagonalShape,
                new HexagonLayout(Orientation.POINTY, 150, 0 ,0, new Point(0,0)));
        var shapeBuilder = new HexagonalShapeBuilder();
        shapeBuilder.createShape(gridLayout, dataStorage);
        assertEquals(37, dataStorage.getHexagons().size());
        assertEquals(0, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(0,0,0)));
        assertEquals(35, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(3,2,-5)));

        dataStorage.clear();

        dataStorage = new HexagonStorage<>(hexagonalShape.getGridSize(), indexProcessor, null);
        gridLayout = new GridLayout<>(hexagonalShape,
                new HexagonLayout(Orientation.FLAT, 150, 0 ,0, new Point(0,0)));
        shapeBuilder = new HexagonalShapeBuilder();
        shapeBuilder.createShape(gridLayout, dataStorage);
        assertEquals(37, dataStorage.getHexagons().size());
        assertEquals(0, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(0,0,0)));
        assertEquals(35, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(3,2,-5)));
    }

    @Test
    void testCreateShapeRectangle() {
        var rectangleBounds = new RectangleShape(3, 3);
        var indexProcessor = new RectangleIndexProcessor(rectangleBounds, Orientation.POINTY);
        var dataStorage = new HexagonStorage<>(rectangleBounds.getGridSize(), indexProcessor, null);
        var gridLayout = new GridLayout<>(rectangleBounds,
                new HexagonLayout(Orientation.POINTY, 150, 0 ,0, new Point(0,0)));
        var shapeBuilder = new RectangleShapeBuilder();
        shapeBuilder.createShape(gridLayout, dataStorage);
        assertEquals(9, dataStorage.getHexagons().size());
        assertEquals(0, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(0,0,0)));
        assertEquals(8, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(1,2,-3)));
        assertEquals(-1, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(10,20,-30)));

        dataStorage.clear();

        indexProcessor = new RectangleIndexProcessor(rectangleBounds, Orientation.FLAT);
        dataStorage = new HexagonStorage<>(rectangleBounds.getGridSize(), indexProcessor, null);
        gridLayout = new GridLayout<>(rectangleBounds,
                new HexagonLayout(Orientation.FLAT, 150, 0 ,0, new Point(0,0)));
        shapeBuilder = new RectangleShapeBuilder();
        shapeBuilder.createShape(gridLayout, dataStorage);
        assertEquals(9, dataStorage.getHexagons().size());
        assertEquals(0, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(0,0,0)));
        assertEquals(5, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(1,2,-3)));
        assertEquals(-1, shapeBuilder.getIndexProcessor(gridLayout).getIndex(new Hexagon(10,20,-30)));
        dataStorage.clear();

        //TODO: Get storage with irregular shape
//        rectangleBounds = new RectangleShape(1, 3 , 1, 5);
//        indexProcessor = new RectangleIndexProcessor(rectangleBounds, Orientation.POINTY);
//        dataStorage = new HexagonStorage<>(rectangleBounds.getGridSize(), indexProcessor, null);
//        gridLayout = new GridLayout<>(rectangleBounds,
//                new HexagonLayout(Orientation.POINTY, 150, 0 ,0, new Point(0,0)));
//        new RectangleShapeBuilder().createShape(gridLayout, dataStorage);
//        assertEquals(15, dataStorage.getHexagons().size());
//        dataStorage.clear();
//        indexProcessor = new RectangleIndexProcessor(rectangleBounds, Orientation.FLAT);
//        dataStorage = new HexagonStorage<>(rectangleBounds.getGridSize(), indexProcessor, null);
//        gridLayout = new GridLayout<>(rectangleBounds,
//                new HexagonLayout(Orientation.FLAT, 150, 0 ,0, new Point(0,0)));
//        new RectangleShapeBuilder().createShape(gridLayout, dataStorage);
//        assertEquals(3, dataStorage.getHexagons().size());
    }
}

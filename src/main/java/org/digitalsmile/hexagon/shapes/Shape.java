package org.digitalsmile.hexagon.shapes;

import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.shapes.hexagonal.HexagonalShapeCreator;
import org.digitalsmile.hexagon.shapes.rectangle.RectangleShapeCreator;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;

public enum Shape {
    /*PARALLELOGRAM, PARALLELOGRAM_LEFT, PARALLELOGRAM_RIGHT, TRIANGLE_TOP, TRIANGLE_BOTTOM, TRIANGLE_LEFT, TRIANGLE_RIGHT, */
    HEXAGON(new HexagonalShapeCreator()),
    RECTANGLE(new RectangleShapeCreator());

    private final ShapeCreator<Bounds> shapeCreator;
    Shape(ShapeCreator shapeCreator) {
        this.shapeCreator = shapeCreator;
    }

    public <T> void  createShape(Bounds bounds, HexagonMetaObjectStorage<T> dataStorage, Orientation
            orientation) {
        shapeCreator.createShape(bounds, dataStorage, orientation);
    }
}

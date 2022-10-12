package org.digitalsmile.hexagon.shapes;

import org.digitalsmile.hexagon.layout.Hexagon;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;
import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.shapes.hexagon.HexagonShapeCreator;
import org.digitalsmile.hexagon.shapes.rectangle.RectangleShapeCreator;

import java.util.List;

public enum Shape {
    /*PARALLELOGRAM, PARALLELOGRAM_LEFT, PARALLELOGRAM_RIGHT, TRIANGLE_TOP, TRIANGLE_BOTTOM, TRIANGLE_LEFT, TRIANGLE_RIGHT, */
    HEXAGON(new HexagonShapeCreator()),
    RECTANGLE(new RectangleShapeCreator());

    private final ShapeCreator<Bounds> shapeCreator;
    Shape(ShapeCreator shapeCreator) {
        this.shapeCreator = shapeCreator;
    }

    public <T> List<Hexagon>  createShape(Bounds bounds, HexagonMetaObjectStorage<T> dataStorage, Orientation
            orientation) {
        return shapeCreator.createShape(bounds, dataStorage, orientation);
    }
}

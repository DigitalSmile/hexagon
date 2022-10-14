package org.digitalsmile.hexagon.shapes;

import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.shapes.hexagonal.HexagonalShapeCreator;
import org.digitalsmile.hexagon.shapes.rectangle.RectangleShapeCreator;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;

/**
 * Class tha represents shape of the grid.
 */
public enum Shape {
    /*PARALLELOGRAM, PARALLELOGRAM_LEFT, PARALLELOGRAM_RIGHT, TRIANGLE_TOP, TRIANGLE_BOTTOM, TRIANGLE_LEFT, TRIANGLE_RIGHT, */
    HEXAGONAL(new HexagonalShapeCreator()),
    RECTANGLE(new RectangleShapeCreator());

    private final ShapeCreator<Bounds> shapeCreator;

    Shape(ShapeCreator shapeCreator) {
        this.shapeCreator = shapeCreator;
    }

    /**
     * Creates shape by giving bounds and orientation.
     *
     * @param bounds      bounds of created shape
     * @param dataStorage storage for hexagons and companion objects
     * @param orientation orientation of hexagon
     * @param <T>         type of companion objects in data storage
     */
    public <T> void createShape(Bounds bounds, HexagonMetaObjectStorage<T> dataStorage, Orientation
            orientation) {
        shapeCreator.createShape(bounds, dataStorage, orientation);
    }
}

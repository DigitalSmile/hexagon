package org.digitalsmile.hexgrid.shapes;

import org.digitalsmile.hexgrid.hexagon.Orientation;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShapeCreator;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShapeCreator;
import org.digitalsmile.hexgrid.storage.HexagonMetaObjectStorage;

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

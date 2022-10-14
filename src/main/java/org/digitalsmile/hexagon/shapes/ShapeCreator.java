package org.digitalsmile.hexagon.shapes;

import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;

/**
 * Interface for creating a desired grid shape with bounds and data storage.
 *
 * @param <B> bound type of created grid shape
 */
public interface ShapeCreator<B extends Bounds> {
    /**
     * Creates shape by giving bounds and orientation.
     *
     * @param bounds      bounds of created shape
     * @param dataStorage storage for hexagons and companion objects
     * @param orientation orientation of hexagon
     * @param <T>         type of companion objects in data storage
     */
    <T> void createShape(B bounds, HexagonMetaObjectStorage<T> dataStorage, Orientation orientation);
}

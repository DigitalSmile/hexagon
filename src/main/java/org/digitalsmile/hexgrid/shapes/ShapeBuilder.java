package org.digitalsmile.hexgrid.shapes;

import org.digitalsmile.hexgrid.layout.GridLayout;
import org.digitalsmile.hexgrid.storage.HexagonStorage;

/**
 * Interface for creating a desired grid shape with bounds and data storage.
 */
public interface ShapeBuilder<S extends Shape> {
    /**
     * Creates shape by giving grid layout.
     *
     * @param gridLayout  grid layout
     * @param dataStorage storage for hexagons and companion objects
     * @param <T>         type of companion objects in data storage
     */
    <T> void createShape(GridLayout<S> gridLayout, HexagonStorage<T> dataStorage);

    IndexProcessor getIndexProcessor(GridLayout<S> gridLayout);

}

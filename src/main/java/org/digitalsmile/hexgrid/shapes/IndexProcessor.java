package org.digitalsmile.hexgrid.shapes;

import org.digitalsmile.hexgrid.hexagon.Hexagon;

/**
 * Interface that represents the index processor for specific shape.
 * Used by data storage to provide O(1) complexity for most of the operations.
 */
public interface IndexProcessor {
    /**
     * Gets index of hexagon for data storage.
     *
     * @param hexagon hexagon to create index
     * @return index of hexagon to be used in data storage
     */
    int getIndex(Hexagon hexagon);
}

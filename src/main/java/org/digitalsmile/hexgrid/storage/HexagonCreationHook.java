package org.digitalsmile.hexgrid.storage;

import org.digitalsmile.hexgrid.hexagon.Hexagon;

/**
 * Interface for a hook of creating meta objects for hexagon.
 *
 * @param <T> type of meta object to be created
 */
public interface HexagonCreationHook<T> {
    /**
     * Creates a meta object paired to hexagon.
     *
     * @param hexagon hexagon to be paired with
     * @return meta object
     */
    T onHexagonCreate(Hexagon hexagon);
}

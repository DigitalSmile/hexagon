package org.digitalsmile.hexagon.storage;

import org.digitalsmile.hexagon.layout.Hexagon;

/**
 * Interface for a hook of creating meta objects for hexagon.
 *
 * @param <T> - type of meta object to be created
 */
public interface HexagonMetaObjectHook<T> {
    /**
     * Creates a meta object paired to hexagon.
     *
     * @param hexagon - hexagon to be paired with
     * @return meta object
     */
    T onHexagonCreate(Hexagon hexagon);
}

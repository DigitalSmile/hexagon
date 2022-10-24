package org.digitalsmile.hexgrid.storage;

import org.digitalsmile.hexgrid.hexagon.Hexagon;

import java.util.List;

/**
 * Interface for access the hexagon storage.
 *
 * @param <T> type of meta objects stored
 */
public interface Storage<T> {
    /**
     * Gets list of hexagons.
     *
     * @return list of hexagons from storage.
     */
    List<Hexagon> getHexagons();

    /**
     * Gets list of hexagons, that are bounded by provided polygon.
     *
     * @param boundingPolygon   list of hexagons, that forms a bounded polygon
     * @param useParallelStream flag to use parallel streams in computing
     * @return list of bounded hexagons from storage.
     */
    List<Hexagon> getHexagons(List<Hexagon> boundingPolygon, boolean useParallelStream);

    /**
     * Gets list of hexagons, that are bounded by provided polygon.
     *
     * @param boundingPolygon list of hexagons, that forms a bounded polygon
     * @return list of bounded hexagons from storage.
     */
    List<Hexagon> getHexagons(List<Hexagon> boundingPolygon);

    /**
     * Checks if hexagon is present in data storage.
     *
     * @param hexagon hexagon to be checked
     * @return true if hexagon is in data storage, false otherwise
     */
    boolean containsHexagon(Hexagon hexagon);

    /**
     * Gets meta object bound to hexagon.
     *
     * @param hexagon hexagon provided
     * @return meta object bound to hexagon
     */
    T getHexagonDataObject(Hexagon hexagon);

    /**
     * Gets all meta objects as list.
     *
     * @return list of all meta objects
     */
    List<T> getHexagonDataObjects();
}

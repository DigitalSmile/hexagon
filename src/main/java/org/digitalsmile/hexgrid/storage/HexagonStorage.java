package org.digitalsmile.hexgrid.storage;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.shapes.IndexProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main hexagon storage with optional meta objects.
 * Adding and getting new hexagon / meta object are O(1).
 *
 * @param <T> type of meta objects
 */
public class HexagonStorage<T> {
    private final HexagonCreationHook<T> hook;
    private final IndexProcessor indexProcessor;

    private final List<Hexagon> hexagonList;
    private final List<T> metaObjectsList;

    /**
     * Creates data storage with meta object hook.
     *
     * @param hook hook to create meta object
     */
    public HexagonStorage(int hexagonGridSize, IndexProcessor indexProcessor, HexagonCreationHook<T> hook) {
        this.hook = hook;
        this.hexagonList = new ArrayList<>(Collections.nCopies(hexagonGridSize, null));
        this.metaObjectsList = (hook == null) ? new ArrayList<>() : new ArrayList<>(Collections.nCopies(hexagonGridSize, null));
        this.indexProcessor = indexProcessor;
    }

    /**
     * Adds hexagon to data storage.
     * Optionally, if hook is not null, calls fpr meta object creation.
     *
     * @param hexagon hexagon to be added
     */
    public void addHexagon(Hexagon hexagon) {
        var index = indexProcessor.getIndex(hexagon);
        hexagonList.set(index, hexagon);
        if (hook != null) {
            metaObjectsList.set(index, hook.onHexagonCreate(hexagon));
        }
    }

    /**
     * Gets list of hexagons.
     *
     * @return list of hexagons from storage.
     */
    public List<Hexagon> getHexagons() {
        return hexagonList;
    }

    /**
     * Gets meta object bound to hexagon.
     *
     * @param hexagon hexagon provided
     * @return meta object bound to hexagon
     */
    public T getHexagonDataObject(Hexagon hexagon) {
        return metaObjectsList.get(indexProcessor.getIndex(hexagon));
    }


    /**
     * Gets all meta objects as list.
     *
     * @return list of all meta objects
     */
    public List<T> getHexagonDataObjects() {
        return metaObjectsList;
    }

    /**
     * Clears data storage and all hexagons/meta objects.
     */
    public void clear() {
        hexagonList.clear();
        metaObjectsList.clear();
    }
}

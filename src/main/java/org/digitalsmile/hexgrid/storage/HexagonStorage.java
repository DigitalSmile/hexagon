package org.digitalsmile.hexgrid.storage;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.shapes.IndexProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Main hexagon storage with optional meta objects.
 * Add, get, contains of hexagon / meta object are O(1).
 *
 * @param <T> type of meta objects
 */
public class HexagonStorage<T> implements Storage<T> {
    private final HexagonCreationHook<T> hook;
    private final IndexProcessor indexProcessor;

    private final List<Hexagon> hexagonList;
    private final List<T> metaObjectsList;

    /**
     * Creates data storage.
     *
     * @param hexagonGridSize size of the grid
     * @param indexProcessor  index processor to calculate index for specific grid shape
     * @param hook            hook to create meta object
     */
    public HexagonStorage(int hexagonGridSize, IndexProcessor indexProcessor, HexagonCreationHook<T> hook) {
        this.hook = hook;
        this.hexagonList = new ArrayList<>(Collections.nCopies(hexagonGridSize, null));
        this.metaObjectsList = (hook == null) ? new ArrayList<>() : new ArrayList<>(Collections.nCopies(hexagonGridSize, null));
        this.indexProcessor = indexProcessor;
    }

    /**
     * Adds hexagon to data storage.
     * Optionally, if hook is not null, calls for meta object creation.
     *
     * @param hexagon hexagon to be added
     */
    public void addHexagon(Hexagon hexagon) {
        var index = indexProcessor.getIndex(hexagon);
        if (index == -1) {
            throw new IllegalArgumentException("Cannot add hexagon with coordinates " + hexagon + ". It is out of grid bounds.");
        }
        hexagonList.set(index, hexagon);
        if (hook != null) {
            metaObjectsList.set(index, hook.onHexagonCreate(hexagon));
        }
    }

    /**
     * Clears data storage and all hexagons/meta objects.
     */
    public void clear() {
        hexagonList.clear();
        metaObjectsList.clear();
    }

    @Override
    public boolean containsHexagon(Hexagon hexagon) {
        var index = indexProcessor.getIndex(hexagon);
        return index >= 0 && index < hexagonList.size() && hexagon.equals(hexagonList.get(index));
    }

    @Override
    public List<Hexagon> getHexagons() {
        return Collections.unmodifiableList(hexagonList);
    }

    @Override
    public List<Hexagon> getHexagons(List<Hexagon> boundingPolygon, boolean useParallelStream) {
        var stream = boundingPolygon.stream();
        if (useParallelStream) {
            stream = stream.parallel();
        }
        return stream
                .flatMap(hexagon -> Stream.ofNullable(containsHexagon(hexagon) ? hexagon : null))
                .toList();
    }

    @Override
    public List<Hexagon> getHexagons(List<Hexagon> boundingPolygon) {
        return getHexagons(boundingPolygon, false);
    }

    @Override
    public T getHexagonDataObject(Hexagon hexagon) {
        var index = indexProcessor.getIndex(hexagon);
        if (index == -1) {
            return null;
        }
        return metaObjectsList.get(index);
    }

    @Override
    public List<T> getHexagonDataObjects() {
        return metaObjectsList;
    }
}

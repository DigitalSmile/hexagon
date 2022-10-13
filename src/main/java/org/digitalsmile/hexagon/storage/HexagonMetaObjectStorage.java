package org.digitalsmile.hexagon.storage;

import org.digitalsmile.hexagon.layout.Hexagon;

import java.util.*;

/**
 * Main hexagon storage with optional meta objects.
 *
 * @param <T> type of meta objects
 */
public class HexagonMetaObjectStorage<T> {
    private final HexagonMetaObjectHook<T> hook;
    private final Map<Hexagon, T> dataMap = new HashMap<>();

    /**
     * Creates data storage with meta object hook.
     *
     * @param hook - hook to create meta object
     */
    public HexagonMetaObjectStorage(HexagonMetaObjectHook<T> hook) {
        this.hook = hook;
    }

    /**
     * Creates data storage.
     */
    public HexagonMetaObjectStorage() {
        this.hook = null;
    }

    /**
     * Adds hexagon to data storage.
     * Optionally, if hook is not null, calls fpr meta object creation.
     *
     * @param hexagon - hexagon to be added
     */
    public void addHexagon(Hexagon hexagon) {
        if (hook == null) {
            dataMap.put(hexagon, null);
            return;
        }
        var object = hook.onHexagonCreate(hexagon);
        dataMap.put(hexagon, object);
    }

    /**
     * Gets list of hexagons.
     *
     * @return list of hexagons from storage.
     */
    public List<Hexagon> getHexagons() {
        return new ArrayList<>(dataMap.keySet());
    }

    /**
     * Gets meta object bound to hexagon.
     *
     * @param hexagon - hexagon provided
     * @return meta object bound to hexagon
     */
    public T getHexagonDataObject(Hexagon hexagon) {
        return dataMap.get(hexagon);
    }

    /**
     * Gets all meta objects as list.
     *
     * @return list of all meta objects
     */
    public List<T> getHexagonDataObjects() {
        return new ArrayList<>(dataMap.values());
    }

    /**
     * Clears data storage and all hexagons/meta objects.
     */
    public void clear() {
        dataMap.clear();
    }
}

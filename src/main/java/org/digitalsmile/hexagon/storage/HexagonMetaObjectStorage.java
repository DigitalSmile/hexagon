package org.digitalsmile.hexagon.storage;

import org.digitalsmile.hexagon.layout.Hexagon;

import java.util.*;

public class HexagonMetaObjectStorage<T> {
    private final HexagonMetaObjectHook<T> hook;
    private final Map<Hexagon, T> dataMap = new HashMap<>();
    public HexagonMetaObjectStorage(HexagonMetaObjectHook<T> hook) {
        this.hook = hook;
    }

    public void hexagonObjectCreated(Hexagon hexagon) {
        if (hook == null) {
            return;
        }
        var object = hook.onHexagonCreate(hexagon);
        dataMap.put(hexagon, object);
    }

    public List<Hexagon> getHexagons() {
        return new ArrayList<>(dataMap.keySet());
    }

    public T getHexagonDataObject(Hexagon hexagon) {
        return dataMap.get(hexagon);
    }

    public List<T> getHexagonDataObjects() {
        return new ArrayList<>(dataMap.values());
    }
}

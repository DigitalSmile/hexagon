package org.digitalsmile.hexagon.storage;

import org.digitalsmile.hexagon.layout.Hexagon;

public interface HexagonMetaObjectHook<T> {
     T onHexagonCreate(Hexagon hexagon);
}

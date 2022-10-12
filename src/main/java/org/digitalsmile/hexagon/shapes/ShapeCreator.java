package org.digitalsmile.hexagon.shapes;

import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;

public interface ShapeCreator<B extends Bounds> {
    <T> void createShape(B bounds, HexagonMetaObjectStorage<T> dataStorage, Orientation orientation);
}

package org.digitalsmile.hexagon.shapes;

import org.digitalsmile.hexagon.layout.Hexagon;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;
import org.digitalsmile.hexagon.layout.Orientation;

import java.util.List;

public interface ShapeCreator<B extends Bounds> {
    <T> List<Hexagon> createShape(B bounds, HexagonMetaObjectStorage<T> dataStorage, Orientation orientation);
}

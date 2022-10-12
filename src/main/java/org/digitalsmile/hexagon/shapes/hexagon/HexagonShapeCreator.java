package org.digitalsmile.hexagon.shapes.hexagon;

import org.digitalsmile.hexagon.layout.Hexagon;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;
import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.shapes.ShapeCreator;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class HexagonShapeCreator implements ShapeCreator<HexagonBounds> {
    @Override
    public <T> List<Hexagon> createShape(HexagonBounds hexagonBounds, HexagonMetaObjectStorage<T> dataStorage, Orientation orientation) {
        return IntStream.rangeClosed(-hexagonBounds.mapRadius(), hexagonBounds.mapRadius())
                .mapToObj(q -> {
                    int r1 = Math.max(-hexagonBounds.mapRadius(), -q - hexagonBounds.mapRadius());
                    int r2 = Math.min(hexagonBounds.mapRadius(), -q + hexagonBounds.mapRadius());
                    return IntStream.rangeClosed(r1, r2)
                            .mapToObj(r -> {
                                var hexagon = new Hexagon(q, r, -q - r);
                                dataStorage.hexagonObjectCreated(hexagon);
                                return hexagon;
                            })
                            .toList();
                })
                .flatMap(Collection::stream)
                .toList();
    }
}

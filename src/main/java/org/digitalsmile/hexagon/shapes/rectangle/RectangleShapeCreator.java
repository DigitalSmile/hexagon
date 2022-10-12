package org.digitalsmile.hexagon.shapes.rectangle;

import org.digitalsmile.hexagon.layout.Hexagon;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;
import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.shapes.ShapeCreator;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class RectangleShapeCreator implements ShapeCreator<RectangleBounds> {

    @Override
    public <T> List<Hexagon> createShape(RectangleBounds rectangleBounds, HexagonMetaObjectStorage<T> dataStorage, Orientation orientation) {
        switch (orientation) {
            case FLAT -> {
                return IntStream.rangeClosed(rectangleBounds.left(), rectangleBounds.right())
                        .mapToObj(q -> {
                            int qOffset = (int) Math.floor((q) / 2.0);
                            return IntStream.rangeClosed(rectangleBounds.top() - qOffset, rectangleBounds.bottom() - qOffset)
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
            case POINTY -> {
                return IntStream.rangeClosed(rectangleBounds.top(), rectangleBounds.bottom())
                        .mapToObj(r -> {
                            int rOffset = (int) Math.floor((r) / 2.0);
                            return IntStream.rangeClosed(rectangleBounds.left() - rOffset, rectangleBounds.right() - rOffset)
                                    .mapToObj(q -> {
                                        var hexagon = new Hexagon(q, r, -q - r);
                                        dataStorage.hexagonObjectCreated(hexagon);
                                        return hexagon;
                                    })
                                    .toList();
                        })
                        .flatMap(Collection::stream)
                        .toList();
            }
            default -> throw new IllegalStateException("Unexpected value: " + orientation);
        }
    }
}

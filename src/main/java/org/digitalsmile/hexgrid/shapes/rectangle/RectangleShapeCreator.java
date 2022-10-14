package org.digitalsmile.hexgrid.shapes.rectangle;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Orientation;
import org.digitalsmile.hexgrid.shapes.ShapeCreator;
import org.digitalsmile.hexgrid.storage.HexagonMetaObjectStorage;

import java.util.stream.IntStream;

/**
 * Creates Rectangle shape of the grid.
 */
public class RectangleShapeCreator implements ShapeCreator<RectangleBounds> {

    @Override
    public <T> void createShape(RectangleBounds rectangleBounds, HexagonMetaObjectStorage<T> dataStorage, Orientation orientation) {
        switch (orientation) {
            case FLAT -> IntStream.rangeClosed(rectangleBounds.left(), rectangleBounds.right())
                    .forEach(q -> {
                        int qOffset = (int) Math.floor((q) / 2.0);
                        IntStream.rangeClosed(rectangleBounds.top() - qOffset, rectangleBounds.bottom() - qOffset)
                                .forEach(r -> {
                                    var hexagon = new Hexagon(q, r, -q - r);
                                    dataStorage.addHexagon(hexagon);
                                });
                    });
            case POINTY -> IntStream.rangeClosed(rectangleBounds.top(), rectangleBounds.bottom())
                    .forEach(r -> {
                        int rOffset = (int) Math.floor((r) / 2.0);
                        IntStream.rangeClosed(rectangleBounds.left() - rOffset, rectangleBounds.right() - rOffset)
                                .forEach(q -> {
                                    var hexagon = new Hexagon(q, r, -q - r);
                                    dataStorage.addHexagon(hexagon);
                                });
                    });
            default -> throw new IllegalStateException("Unexpected value: " + orientation);
        }
    }
}

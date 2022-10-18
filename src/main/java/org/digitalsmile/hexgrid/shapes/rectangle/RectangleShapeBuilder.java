package org.digitalsmile.hexgrid.shapes.rectangle;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.GridLayout;
import org.digitalsmile.hexgrid.shapes.IndexProcessor;
import org.digitalsmile.hexgrid.shapes.ShapeBuilder;
import org.digitalsmile.hexgrid.storage.HexagonStorage;

import java.util.stream.IntStream;

/**
 * Creates Rectangle shape of the grid.
 */
public class RectangleShapeBuilder implements ShapeBuilder<RectangleShape> {

    @Override
    public <T> void createShape(GridLayout<RectangleShape> gridLayout, HexagonStorage<T> dataStorage) {
        var rectangleBounds = gridLayout.getShape();
        var orientation = gridLayout.getHexagonLayout().getOrientation();
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

    @Override
    public IndexProcessor getIndexProcessor(GridLayout<RectangleShape> gridLayout) {
        return new RectangleIndexProcessor( gridLayout.getShape(), gridLayout.getHexagonLayout().getOrientation());
    }
}

package org.digitalsmile.hexgrid.shapes.hexagonal;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Orientation;
import org.digitalsmile.hexgrid.shapes.ShapeCreator;
import org.digitalsmile.hexgrid.storage.HexagonMetaObjectStorage;

import java.util.stream.IntStream;

/**
 * Creates Hexagonal shape of the grid.
 */
public class HexagonalShapeCreator implements ShapeCreator<HexagonalBounds> {
    @Override
    public <T> void createShape(HexagonalBounds hexagonalBounds, HexagonMetaObjectStorage<T> dataStorage, Orientation orientation) {
        IntStream.rangeClosed(-hexagonalBounds.mapRadius(), hexagonalBounds.mapRadius())
                .forEach(q -> {
                    int r1 = Math.max(-hexagonalBounds.mapRadius(), -q - hexagonalBounds.mapRadius());
                    int r2 = Math.min(hexagonalBounds.mapRadius(), -q + hexagonalBounds.mapRadius());
                    IntStream.rangeClosed(r1, r2)
                            .forEach(r -> {
                                var hexagon = new Hexagon(q, r, -q - r);
                                dataStorage.addHexagon(hexagon);
                            });
                });
    }
}

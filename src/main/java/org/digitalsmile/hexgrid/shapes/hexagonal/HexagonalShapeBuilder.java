package org.digitalsmile.hexgrid.shapes.hexagonal;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.GridLayout;
import org.digitalsmile.hexgrid.shapes.IndexProcessor;
import org.digitalsmile.hexgrid.shapes.ShapeBuilder;
import org.digitalsmile.hexgrid.storage.HexagonStorage;

import java.util.stream.IntStream;

/**
 * Creates Hexagonal shape of the grid.
 */
public class HexagonalShapeBuilder implements ShapeBuilder<HexagonalShape> {

    @Override
    public <T> void createShape(GridLayout<HexagonalShape> gridLayout, HexagonStorage<T> dataStorage) {
        var hexagonalShape = gridLayout.getShape();
        IntStream.rangeClosed(-hexagonalShape.mapRadius(), hexagonalShape.mapRadius())
                .forEach(q -> {
                    var r1 = Math.max(-hexagonalShape.mapRadius(), -q - hexagonalShape.mapRadius());
                    var r2 = Math.min(hexagonalShape.mapRadius(), -q + hexagonalShape.mapRadius());
                    IntStream.rangeClosed(r1, r2)
                            .forEach(r -> {
                                var hexagon = new Hexagon(q, r, -q - r);
                                dataStorage.addHexagon(hexagon);
                            });
                });
    }

    @Override
    public IndexProcessor getIndexProcessor(GridLayout<HexagonalShape> gridLayout) {
        return new HexagonalIndexProcessor(gridLayout.getShape());
    }
}

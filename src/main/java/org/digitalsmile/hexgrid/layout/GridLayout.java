package org.digitalsmile.hexgrid.layout;

import org.digitalsmile.hexgrid.shapes.IndexProcessor;
import org.digitalsmile.hexgrid.shapes.Shape;
import org.digitalsmile.hexgrid.shapes.ShapeBuilder;
import org.digitalsmile.hexgrid.shapes.ShapeBuilderFactory;
import org.digitalsmile.hexgrid.storage.HexagonStorage;

public class GridLayout<S extends Shape> {
    private final S gridShape;
    private final HexagonLayout hexagonLayout;
    private final ShapeBuilder<S> shapeBuilder;

    public GridLayout(S gridShape, HexagonLayout hexagonLayout) {
        this.gridShape = gridShape;
        this.hexagonLayout = hexagonLayout;
        this.shapeBuilder = ShapeBuilderFactory.createShapeCreator(gridShape.getClass());
    }

    public HexagonLayout getHexagonLayout() {
        return hexagonLayout;
    }

    public S getShape() {
        return gridShape;
    }

    public void createShape(HexagonStorage<?> dataStorage) {
        shapeBuilder.createShape(this, dataStorage);
    }

    public IndexProcessor getIndexProcessor() {
        return shapeBuilder.getIndexProcessor(this);
    }
}

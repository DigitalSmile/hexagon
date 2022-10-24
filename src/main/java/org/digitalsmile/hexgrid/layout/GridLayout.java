package org.digitalsmile.hexgrid.layout;

import org.digitalsmile.hexgrid.shapes.IndexProcessor;
import org.digitalsmile.hexgrid.shapes.Shape;
import org.digitalsmile.hexgrid.shapes.ShapeBuilder;
import org.digitalsmile.hexgrid.shapes.ShapeBuilderFactory;
import org.digitalsmile.hexgrid.storage.HexagonStorage;

/**
 * Class holder of parameters of creating grid (shape, hexagon layout, etc.).
 *
 * @param <S> shape of the grid
 */
public class GridLayout<S extends Shape> {
    private final S gridShape;
    private final HexagonLayout hexagonLayout;
    private final ShapeBuilder<S> shapeBuilder;

    /**
     * Creates grid layout with given shape and hexagon layout.
     *
     * @param gridShape     shape of the grid
     * @param hexagonLayout hexagon layout
     */
    public GridLayout(S gridShape, HexagonLayout hexagonLayout) {
        this.gridShape = gridShape;
        this.hexagonLayout = hexagonLayout;
        this.shapeBuilder = ShapeBuilderFactory.createShapeBuilder(gridShape.getClass());
    }

    /**
     * Gets hexagon layout.
     *
     * @return hexagon layout
     */
    public HexagonLayout getHexagonLayout() {
        return hexagonLayout;
    }

    /**
     * Gets grid shape.
     *
     * @return shape of the grid
     */
    public S getShape() {
        return gridShape;
    }

    /**
     * Creates grid shape with given data storage.
     *
     * @param dataStorage data storage for holding hexagons and metao bjects
     */
    public void createShape(HexagonStorage<?> dataStorage) {
        shapeBuilder.createShape(this, dataStorage);
    }

    /**
     * Gets index processor, tied with shape.
     *
     * @return index processor
     */
    public IndexProcessor getIndexProcessor() {
        return shapeBuilder.getIndexProcessor(this);
    }
}

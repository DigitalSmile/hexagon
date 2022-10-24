package org.digitalsmile.hexgrid.shapes.rectangle;

import org.digitalsmile.hexgrid.coordinates.OffsetCoordinates;
import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.IndexProcessor;

/**
 * Index processor for rectangular grid shape.
 */
public class RectangleIndexProcessor implements IndexProcessor {
    private final Orientation orientation;
    private final RectangleShape rectangleShape;

    /**
     * Creates index processor for rectangular shape props with given orientation.
     *
     * @param rectangleShape rectangular shape props
     * @param orientation    hexagon orientation
     */
    public RectangleIndexProcessor(RectangleShape rectangleShape, Orientation orientation) {
        this.orientation = orientation;
        this.rectangleShape = rectangleShape;
    }

    @Override
    public int getIndex(Hexagon hexagon) {
        var offsetCoordinates = OffsetCoordinates.fromCube(orientation, hexagon);
        if (offsetCoordinates.col() > rectangleShape.getCols() ||
                offsetCoordinates.row() > rectangleShape.getRows()) {
            return -1;
        }
        return switch (orientation) {
            case FLAT -> offsetCoordinates.col() * rectangleShape.getRows() + offsetCoordinates.row();
            case POINTY -> offsetCoordinates.row() * rectangleShape.getCols() + offsetCoordinates.col();
        };
    }
}

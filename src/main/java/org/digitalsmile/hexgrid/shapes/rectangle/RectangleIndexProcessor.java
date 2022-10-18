package org.digitalsmile.hexgrid.shapes.rectangle;

import org.digitalsmile.hexgrid.coordinates.OffsetCoordinates;
import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.IndexProcessor;

public class RectangleIndexProcessor implements IndexProcessor {
    private final Orientation orientation;
    private final RectangleShape rectangleShape;

    public RectangleIndexProcessor(RectangleShape rectangleShape, Orientation orientation) {
        this.orientation = orientation;
        this.rectangleShape = rectangleShape;
    }

    @Override
    public int getIndex(Hexagon hexagon) {
        var offsetCoordinates = OffsetCoordinates.fromCube(orientation, hexagon);
        if (offsetCoordinates.col() > rectangleShape.getCols() ||
                offsetCoordinates.row() > rectangleShape.getRows()) {
            throw new IllegalArgumentException("Hexagon with coordinates " + offsetCoordinates + " is out of grid bounds " + rectangleShape);
        }
        return switch (orientation) {
            case FLAT -> offsetCoordinates.col() * rectangleShape.getRows() + offsetCoordinates.row();
            case POINTY -> offsetCoordinates.row() * rectangleShape.getCols() + offsetCoordinates.col();
        };
    }
}

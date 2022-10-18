package org.digitalsmile.hexgrid.layout;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Point;
import org.digitalsmile.hexgrid.operations.HexagonOperations;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Class that represents computation of layout props by provided {@link Orientation}.
 */
public class HexagonLayout {
    private final Orientation orientation;
    private final double hexagonWidth;
    private final double hexagonHeight;
    private final double side;
    private final Point offset;

    /**
     * Creates layout by provided props of hexagon.
     *
     * @param orientation   - {@link Orientation} of hexagon
     * @param hexagonWidth  - width of hexagon
     * @param hexagonHeight - height of hexagon
     * @param side          - side of hexagon
     * @param offset        - offset coordinates (x and y) of the first hexagon
     */
    public HexagonLayout(Orientation orientation, double hexagonWidth, double hexagonHeight, double side, Point offset) {
        this.orientation = orientation;
        this.hexagonWidth = hexagonWidth;
        this.hexagonHeight = hexagonHeight;
        this.side = side;
        this.offset = offset;
    }

    /**
     * Gets the center of hexagon with X and Y coordinates.
     *
     * @param hexagon provided hexagon to calculate
     * @return X and Y representation of center point of hexagon
     */
    public Point getHexagonCenterPoint(Hexagon hexagon) {
        var x = orientation.pixelCoordinateX(hexagon, side) + hexagonWidth / 2d;
        var y = orientation.pixelCoordinateY(hexagon, side) + hexagonHeight / 2d;
        return new Point(x + offset.x(), y + offset.y());
    }

    /**
     * Gets the hexagon by provided X and Y coordinates.
     *
     * @param point X and Y coordinates to get hexagon
     * @return hexagon underneath X and Y coordinates
     */
    public Hexagon getBoundingHexagon(Point point) {
        var adjustedPoint = new Point((point.x() - offset.x()) / side, (point.y() - offset.y()) / side);
        var q = orientation.hexagonCoordinateQ(adjustedPoint);
        var r = orientation.hexagonCoordinateR(adjustedPoint);
        return HexagonOperations.fuzzyToHexagon(q, r, -q - r);
    }

    private Point getHexagonCornerPoint(int corner) {
        return new Point(side * orientation.getCosCorner(corner), side * orientation.getSinCorner(corner));
    }

    /**
     * Gets X and Y coordinates of each corner of provided hexagon with layout offset.
     * Can be used to render hexagons.
     *
     * @param hexagon provided hexagon
     * @return list of X and Y corner coordinates of provided hexagon
     */
    public List<Point> getHexagonCornerPoints(Hexagon hexagon) {
        var center = getHexagonCenterPoint(hexagon);
        return IntStream.range(0, 6).mapToObj(value -> {
            var cornerPoint = getHexagonCornerPoint(value);
            return new Point(center.x() + cornerPoint.x(), center.y() + cornerPoint.y());
        }).toList();
    }

    /**
     * Gets hexagon {@link Orientation} in current layout.
     *
     * @return layout orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Gets hexagon width in current layout.
     *
     * @return width of hexagon
     */
    public double getHexagonWidth() {
        return hexagonWidth;
    }

    /**
     * Gets hexagon height in current layout.
     *
     * @return height of hexagon
     */
    public double getHexagonHeight() {
        return hexagonHeight;
    }

    /**
     * Gets hexagon side in current layout.
     *
     * @return sid of hexagon
     */
    public double getSide() {
        return side;
    }

    /**
     * Gets layout offset in X and Y coordinates.
     *
     * @return X and Y coordinates of offset
     */
    public Point getOffset() {
        return offset;
    }
}

package org.digitalsmile.hexagon.layout;

import java.util.List;
import java.util.stream.IntStream;

public class Layout {
    private final Orientation orientation;
    private final double hexagonWidth;
    private final double hexagonHeight;
    private final double side;
    private final Point offset;

    public Layout(Orientation orientation, double hexagonWidth, double hexagonHeight, double side, Point offset) {
        this.orientation = orientation;
        this.offset = offset;

        switch (orientation) {
            case FLAT -> {
                if (hexagonWidth != 0) {
                    this.hexagonWidth = hexagonWidth;
                    this.side = hexagonWidth / 2d;
                    this.hexagonHeight = this.side * Math.sqrt(3.0);
                } else if (hexagonHeight != 0) {
                    this.hexagonHeight = hexagonHeight;
                    this.side = hexagonHeight / Math.sqrt(3.0);
                    this.hexagonWidth = this.side * 2d;
                } else {
                    this.side = side;
                    this.hexagonWidth = this.side * 2d;
                    this.hexagonHeight = this.side * Math.sqrt(3.0);
                }
            }
            case POINTY -> {
                if (hexagonWidth != 0) {
                    this.hexagonWidth = hexagonWidth;
                    this.side = hexagonWidth / Math.sqrt(3.0);
                    this.hexagonHeight = this.side * 2d;
                } else if (hexagonHeight != 0) {
                    this.hexagonHeight = hexagonHeight;
                    this.side = hexagonHeight / 2d;
                    this.hexagonWidth = this.side * Math.sqrt(3.0);
                } else {
                    this.side = side;
                    this.hexagonWidth = this.side * Math.sqrt(3.0);
                    this.hexagonHeight = this.side * 2d;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + orientation);
        }
    }

    public Point getHexagonCenterPoint(Hexagon hexagon) {
        var x = orientation.pixelCoordinateX(hexagon, side) + hexagonWidth / 2d;
        var y = orientation.pixelCoordinateY(hexagon, side) + hexagonHeight / 2d;
        return new Point(x + offset.x(), y + offset.y());
    }

    public Hexagon getBoundingHexagon(Point point) {
        var adjustedPoint = new Point((point.x() - offset.x()) / side, (point.y() - offset.y()) / side);
        var q = orientation.hexagonCoordinateQ(adjustedPoint);
        var r = orientation.hexagonCoordinateR(adjustedPoint);
        return new FractionalHexagon(q, r, -q - r).roundToHexagon();
    }

    public Point getHexagonCornerPoint(int corner) {
        return new Point(side * orientation.getCosCorner(corner), side * orientation.getSinCorner(corner));
    }

    public List<Point> getHexagonPoints(Hexagon hexagon) {
        var center = getHexagonCenterPoint(hexagon);
        return IntStream.range(0, 6).mapToObj(value -> {
            var cornerPoint = getHexagonCornerPoint(value);
            return new Point(center.x() + cornerPoint.x(), center.y() + cornerPoint.y());
        }).toList();
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public double getHexagonWidth() {
        return hexagonWidth;
    }

    public double getHexagonHeight() {
        return hexagonHeight;
    }

    public Point getOffset() {
        return offset;
    }

    public double getSide() {
        return side;
    }
}

package org.digitalsmile.hexgrid.operations.pole;

import org.digitalsmile.hexgrid.hexagon.Point;

import java.util.List;

/**
 * Helper class, that represents a cell for finding center of polygon. Please, see link below
 *
 * @link <a href="https://github.com/FreshLlamanade/polylabel-java">poly-label</a>
 */
class Cell implements Comparable<Cell> {

    private final double x;
    private final double y;

    private final double max;
    private final double distance;
    private final double half;

    /**
     * Creates a cell.
     *
     * @param x       x-axis coordinate
     * @param y       y-axis coordinate
     * @param half    half of distance
     * @param polygon list of polygon points
     */
    Cell(double x, double y, double half, List<Point> polygon) {
        this.x = x;
        this.y = y;
        this.half = half;
        this.distance = pointToPolygonDist(x, y, polygon); // distance from cell center to polygon
        this.max = distance + half * Math.sqrt(2.0); // max distance to polygon within a cell
    }

    // signed distance from point to polygon outline (negative if point is outside)
    private static double pointToPolygonDist(double x, double y, List<Point> polygon) {
        var inside = false;
        var minDistSq = Double.MAX_VALUE;
        var len = polygon.size();

        for (int i = 0; i < polygon.size(); i++) {
            var current = polygon.get((i + 1) % len);
            var prev = polygon.get(i);

            if ((current.y() > y != prev.y() > y)
                    && (x < (prev.x() - current.x()) * (y - current.y())
                    / (prev.y() - current.y()) + current.x()))
                inside = !inside;

            var distSq = getSegDistSq(x, y, current, prev);
            if (distSq < minDistSq)
                minDistSq = distSq;
        }

        return minDistSq == 0 ? 0 : (inside ? 1 : -1) * Math.sqrt(minDistSq);
    }

    // get squared distance from a point to a segment
    private static double getSegDistSq(double px, double py, Point a, Point b) {

        var x = a.x();
        var y = a.y();
        var dx = b.x() - x;
        var dy = b.y() - y;

        if (dx != 0 || dy != 0) {

            var t = ((px - x) * dx + (py - y) * dy) / (dx * dx + dy * dy);

            if (t > 1) {
                x = b.x();
                y = b.y();

            } else if (t > 0) {
                x += dx * t;
                y += dy * t;
            }
        }

        dx = px - x;
        dy = py - y;

        return dx * dx + dy * dy;
    }

    /**
     * Gets max value of cell.
     *
     * @return max value
     */
    double getMax() {
        return max;
    }

    /**
     * Gets distance from center.
     *
     * @return distance from center
     */
    double getDistance() {
        return distance;
    }

    /**
     * Gets half of distance.
     *
     * @return half of distance
     */
    double getHalf() {
        return half;
    }

    /**
     * Gets x coordinate of cell.
     *
     * @return x coordinate
     */
    double getX() {
        return x;
    }

    /**
     * Gets y coordinate of cell.
     *
     * @return y coordinate
     */
    double getY() {
        return y;
    }

    @Override
    public int compareTo(Cell cell) {
        return Double.compare(cell.max, this.max);
    }
}

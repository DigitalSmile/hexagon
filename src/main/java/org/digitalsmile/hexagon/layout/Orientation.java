package org.digitalsmile.hexagon.layout;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Class that represents hexagon orientation.
 *       /\       __
 *      | |     /   \
 *      \/      \__/
 *    Pointy    Flat
 *
 * All conversion constants are hardcoded and computed on the very start.
 * @see <a href="https://www.redblobgames.com/grids/hexagons/#hex-to-pixel">Hex to Pixel</a>
 * @see <a href="https://www.redblobgames.com/grids/hexagons/#pixel-to-hex">Pixel to Hex</a>
 */
public enum Orientation {
    POINTY(Math.sqrt(3.0d), Math.sqrt(3.0d) / 2.0d, 0.0d, 3.0d / 2.0d, Math.sqrt(3.0d) / 3.0d, -1.0d / 3.0d, 0.0d, 2.0d / 3.0d, 0.5d),
    FLAT(3.0d / 2.0d, 0.0d, Math.sqrt(3.0d) / 2.0d, Math.sqrt(3.0d), 2.0d / 3.0d, 0.0d, -1.0d / 3.0d, Math.sqrt(3.0d) / 3.0d, 0d);

    private final double f0;
    private final double f1;
    private final double f2;
    private final double f3;
    private final double b0;
    private final double b1;
    private final double b2;
    private final double b3;
    private final List<Double> sinCornerList;
    private final List<Double> cosCornerList;

    Orientation(double f0, double f1, double f2, double f3, double b0, double b1, double b2, double b3, double startAngle) {
        this.f0 = f0;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.sinCornerList = IntStream.range(0, 6)
                .mapToObj(value -> Math.sin(2d * Math.PI * (startAngle + value) / 6d))
                .toList();
        this.cosCornerList = IntStream.range(0, 6)
                .mapToObj(value -> Math.cos(2d * Math.PI * (startAngle + value) / 6d))
                .toList();
    }

    /**
     * Converts hexagon q and r cube coordinates to X coordinate in 2D representation.
     * @param hexagon - provided hexagon
     * @param side - side size of hexagon
     * @return X-axis pixel representation of hexagon
     */
    double pixelCoordinateX(Hexagon hexagon, double side) {
        return (f0 * hexagon.q() + f1 * hexagon.r()) * side;
    }

    /**
     * Converts hexagon q and r cube coordinates to Y coordinate in 2D representation.
     * @param hexagon - provided hexagon
     * @param side - side size of hexagon
     * @return Y-axis pixel representation of hexagon
     */
    double pixelCoordinateY(Hexagon hexagon, double side) {
        return (f2 * hexagon.q() + f3 * hexagon.r()) * side;
    }

    /**
     * Converts X and Y pixel coordinate to q coordinate fo hexagon.
     * @param point - x and y coordinate
     * @return q coordinate of hexagon in a point
     */
    double hexagonCoordinateQ(Point point) {
        return b0 * point.x() + b1 * point.y();
    }

    /**
     * Converts X and Y pixel coordinate to r coordinate fo hexagon.
     * @param point - x and y coordinate
     * @return r coordinate of hexagon in a point
     */
    double hexagonCoordinateR(Point point) {
        return b2 * point.x() + b3 * point.y();
    }

    /**
     * Gets the sine value of the corner (0 to 5) of hexagon. The value is precomputed.
     * @param corner - corner value of hexagon (0 to 5)
     * @return sinus value of hexagon corner
     */
    double getSinCorner(int corner) {
        return sinCornerList.get(corner);
    }

    /**
     * Gets the cosine value of the corner (0 to 5) of hexagon. The value is precomputed.
     * @param corner - corner value of hexagon (0 to 5)
     * @return cosine value of hexagon corner
     */
    double getCosCorner(int corner) {
        return cosCornerList.get(corner);
    }
}
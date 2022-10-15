package org.digitalsmile.hexgrid.operations;

import org.digitalsmile.hexgrid.hexagon.Hexagon;

import java.util.List;
import java.util.stream.IntStream;

public class HexagonOperations {
    private static final double EPSILON_1 = 1e-06;
    private static final double EPSILON_2 = 2e-06;

    /**
     * Creates list of hexagons, that form a line between provided hexagons.
     *
     * @param hexagon1 hexagon start from
     * @param hexagon2 hexagon end to
     * @return list of hexagons, that form a line
     */
    public static List<Hexagon> hexagonLinePath(Hexagon hexagon1, Hexagon hexagon2) {
        var distance = hexagon1.distance(hexagon2);
        var hexagon1Nudge = new FractionalHexagon(hexagon1.q() + EPSILON_1, hexagon1.r() + EPSILON_1, hexagon1.s() - EPSILON_2);
        var hexagon2Nudge = new FractionalHexagon(hexagon2.q() + EPSILON_1, hexagon2.r() + EPSILON_1, hexagon2.s() - EPSILON_2);

        var step = 1.0 / Math.max(distance, 1);
        return IntStream.rangeClosed(0, distance)
                .mapToObj(value -> hexagon1Nudge.interpolate(hexagon2Nudge, step * value).roundToHexagon())
                .toList();
    }

    public static Hexagon fuzzyToHexagon(double q, double r, double s) {
        return new FractionalHexagon(q, r, -q - r).roundToHexagon();
    }

    public static Hexagon reflectQ90(Hexagon hexagon) {
        return hexagon.reflectQ().negate();
    }

    public static Hexagon reflectQ45(Hexagon hexagon) {
        return hexagon.negate();
    }

    public static Hexagon reflectR90(Hexagon hexagon) {
        return hexagon.reflectR().negate();
    }

    public static Hexagon reflectR45(Hexagon hexagon) {
        return hexagon.negate();
    }

    public static Hexagon reflectS90(Hexagon hexagon) {
        return hexagon.reflectS().negate();
    }

    public static Hexagon reflectS45(Hexagon hexagon) {
        return hexagon.negate();
    }
}

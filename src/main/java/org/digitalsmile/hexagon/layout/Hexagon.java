package org.digitalsmile.hexagon.layout;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public record Hexagon(int q, int r, int s) {

    public Hexagon {
        if (q + r + s != 0) {
            throw new IllegalArgumentException("q + r + s must be 0");
        }

    }

    public Hexagon add(Hexagon hexagon) {
        return new Hexagon(q + hexagon.q(), r + hexagon.r(), s + hexagon.s());
    }

    public Hexagon subtract(Hexagon hexagon) {
        return new Hexagon(q - hexagon.q(), r - hexagon.r(), s - hexagon.s());
    }

    public Hexagon scale(int unitScale) {
        return new Hexagon(q * unitScale, r * unitScale, s * unitScale);
    }

    public Hexagon rotateLeft() {
        return new Hexagon(-s, -q, -r);
    }

    public Hexagon rotateRight() {
        return new Hexagon(-r, -s, -q);
    }

    public HexagonDirection direction(Hexagon testedHexagon) {
        return Arrays.stream(HexagonDirection.values())
                .filter(direction -> add(direction.getDeltaHexagon()).equals(testedHexagon))
                .findFirst().orElseThrow();
    }

    public Hexagon neighbor(HexagonDirection direction) {
        return add(direction.getDeltaHexagon());
    }

    private static final List<Hexagon> DIAGONALS = List.of(
            new Hexagon(2, -1, -1), new Hexagon(1, -2, 1), new Hexagon(-1, -1, 2),
            new Hexagon(-2, 1, 1), new Hexagon(-1, 2, -1), new Hexagon(1, 1, -2)
    );

    public Hexagon diagonalNeighbor(int direction) {
        return add(DIAGONALS.get(direction));
    }

    public int distance(Hexagon hexagon) {
        return subtract(hexagon).length();
    }

    private int length() {
        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }

    private static final double EPSILON_1 = 1e-06;
    private static final double EPSILON_2 = 2e-06;

    public static List<Hexagon> hexagonLinePath(Hexagon hexagon1, Hexagon hexagon2) {
        var distance = hexagon1.distance(hexagon2);
        var hexagon1Nudge = new FractionalHexagon(hexagon1.q() + EPSILON_1, hexagon1.r() + EPSILON_1, hexagon1.s() - EPSILON_2);
        var hexagon2Nudge = new FractionalHexagon(hexagon2.q() + EPSILON_1, hexagon2.r() + EPSILON_1, hexagon2.s() - EPSILON_2);

        var step = 1.0 / Math.max(distance, 1);
        return IntStream.rangeClosed(0, distance)
                .mapToObj(value -> hexagon1Nudge.interpolate(hexagon2Nudge, step * value).roundToHexagon())
                .toList();
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + q;
//        result = prime * result + r;
//        result = prime * result + s;
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null) return false;
//        if (!getClass().isAssignableFrom(obj.getClass())) return false;
//        Hexagon other = (Hexagon) obj;
//        if (q != other.q()) return false;
//        if (r != other.r()) return false;
//        if (s != other.s()) return false;
//        return true;
//    }

    @Override
    public String toString() {
        return "(" + q + ", " + r + ", " + s + ")";
    }
}

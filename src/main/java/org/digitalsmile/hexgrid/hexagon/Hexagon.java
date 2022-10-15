package org.digitalsmile.hexgrid.hexagon;

import java.util.Arrays;
import java.util.List;

/**
 * Main record class that represents hexagon in cube coordinates. o learn more about cube coordinates, visit <a href="https://www.redblobgames.com/grids/hexagons/#coordinates-cube">@redblobgames</a>
 * You can perform add, subtract, scale (multiply), rotation operations on it.
 * Also, there are operations for finding direction, getting neighbour and calculating distance.
 * NOTE: since it is a record class, equals of existing and new instance with same q, r and s coordinates will <b>always return true</b>.
 *
 * @param q q coordinate of hexagon
 * @param r q coordinate of hexagon
 * @param s q coordinate of hexagon
 */
public record Hexagon(int q, int r, int s) {

    public Hexagon {
        if (q + r + s != 0) {
            throw new IllegalArgumentException("q + r + s must be 0");
        }
    }

    /**
     * Sums current hexagon with provided.
     *
     * @param hexagon provided hexagon to add
     * @return the sum of two hexagons
     */
    public Hexagon add(Hexagon hexagon) {
        return new Hexagon(q + hexagon.q(), r + hexagon.r(), s + hexagon.s());
    }

    /**
     * Subtracts current hexagon from hexagon provided.
     *
     * @param hexagon provided hexagon to subtract
     * @return the subtraction of two hexagons
     */
    public Hexagon subtract(Hexagon hexagon) {
        return new Hexagon(q - hexagon.q(), r - hexagon.r(), s - hexagon.s());
    }

    /**
     * Scales current hexagon by the amount provided.
     *
     * @param unitScale amount to scale
     * @return scaled hexagon
     */
    public Hexagon scale(int unitScale) {
        return new Hexagon(q * unitScale, r * unitScale, s * unitScale);
    }

    /**
     * Rotates hexagon to the left.
     *
     * @return left rotated hexagon
     */
    public Hexagon rotateLeft() {
        return new Hexagon(-s, -q, -r);
    }

    /**
     * Rotates hexagon to the right.
     *
     * @return right rotated hexagon
     */
    public Hexagon rotateRight() {
        return new Hexagon(-r, -s, -q);
    }

    public Hexagon reflectQ() {
        return new Hexagon(q, s, r);
    }

    public Hexagon reflectR() {
        return new Hexagon(s, r, q);
    }

    public Hexagon reflectS() {
        return new Hexagon(r, q, s);
    }

    public Hexagon negate() {
        return new Hexagon(-q, -r, -s);
    }

    /**
     * Gets hexagon direction relative to current hexagon.
     *
     * @param hexagon hexagon to be checked
     * @return hexagon direction
     */
    public HexagonDirection direction(Hexagon hexagon) {
        return Arrays.stream(HexagonDirection.values())
                .filter(direction -> add(direction.getDeltaHexagon()).equals(hexagon))
                .findFirst().orElseThrow();
    }

    /**
     * Gets neighbour hexagon by direction provided.
     *
     * @param direction hexagon direction to check
     * @return hexagon at provided direction
     */
    public Hexagon neighbor(HexagonDirection direction) {
        return add(direction.getDeltaHexagon());
    }

    private static final List<Hexagon> DIAGONALS = List.of(
            new Hexagon(2, -1, -1), new Hexagon(1, -2, 1), new Hexagon(-1, -1, 2),
            new Hexagon(-2, 1, 1), new Hexagon(-1, 2, -1), new Hexagon(1, 1, -2)
    );

    /**
     * Gets diagonal neighbour hexagon by direction provided.
     *
     * @param direction hexagon direction to check
     * @return hexagon at provided direction
     */
    public Hexagon diagonalNeighbor(int direction) {
        return add(DIAGONALS.get(direction));
    }

    /**
     * Calculates distance from current hexagon to hexagon provided.
     *
     * @param hexagon provided hexagon to calculate distance
     * @return number of hexagons between current and provided hexagons (distance)
     */
    public int distance(Hexagon hexagon) {
        return subtract(hexagon).length();
    }

    private int length() {
        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }

    @Override
    public String toString() {
        return "(" + q + ", " + r + ", " + s + ")";
    }
}

package org.digitalsmile.hexagon.layout;

import java.util.Arrays;

/**
 * Provides values for determine direction of hexagon side with relative rotation in degrees.
 * Direction is started by North (upper top) with index 0. Direction follows counterclockwise rule, so do the indexing.
 */
public enum HexagonDirection {
    NORTH(0, 0f, new Hexagon(0, -1, 1)),
    NORTH_WEST(1, 60f, new Hexagon(-1, 0, 1)),
    SOUTH_WEST(2, 120f, new Hexagon(-1, 1, 0)),
    SOUTH(3, 180f, new Hexagon(0, 1, -1)),
    SOUTH_EAST(4, 240f, new Hexagon(1, 0, -1)),
    NORTH_EAST(5, 300f, new Hexagon(1, -1, 0));

    private final int index;
    private final float rotation;
    private final Hexagon deltaHexagon;

    HexagonDirection(int index, float rotation, Hexagon deltaHexagon) {
        this.index = index;
        this.rotation = rotation;
        this.deltaHexagon = deltaHexagon;
    }

    /**
     * Gets next counterclockwise direction by given direction.
     *
     * @param hexagonDirection provided hexagon direction
     * @return next counterclockwise direction
     */
    public static HexagonDirection nextCounterClockWiseDirection(HexagonDirection hexagonDirection) {
        var index = hexagonDirection.getIndex() - 1 < 0 ? 5 : hexagonDirection.getIndex() - 1;
        return Arrays.stream(values()).filter(direction -> direction.getIndex() == index).findFirst().orElseThrow();
    }

    /**
     * Gets next clockwise direction by given direction.
     *
     * @param hexagonDirection provided hexagon direction
     * @return next clockwise direction
     */
    public static HexagonDirection nextClockWiseDirection(HexagonDirection hexagonDirection) {
        var index = hexagonDirection.getIndex() + 1 > 5 ? 0 : hexagonDirection.getIndex() + 1;
        return Arrays.stream(values()).filter(direction -> direction.getIndex() == index).findFirst().orElseThrow();
    }

    /**
     * Gets direction by given angle in degrees.
     *
     * @param degree provided degree, cannot be negative
     * @return hexagon direction that is within range by provided angle or null if angle is negative
     */
    public static HexagonDirection getDirection(float degree) {
        if (degree < 0) {
            return null;
        }
        if (betweenExclusive(degree, 330f, 30f)) {
            return NORTH;
        } else if (betweenExclusive(degree, 30f, 90f)) {
            return NORTH_WEST;
        } else if (betweenExclusive(degree, 90f, 150f)) {
            return SOUTH_WEST;
        } else if (betweenExclusive(degree, 150f, 210f)) {
            return SOUTH;
        } else if (betweenExclusive(degree, 210f, 270f)) {
            return SOUTH_EAST;
        } else if (betweenExclusive(degree, 270f, 330f)) {
            return NORTH_EAST;
        }
        return null;
    }

    private static boolean betweenExclusive(float test, float start, float end) {
        end = (end - start) < 0.0f ? end - start + 360.0f : end - start;
        test = (test - start) < 0.0f ? test - start + 360.0f : test - start;
        return (test < end);
    }

    /**
     * Gets relative rotation in degree of current direction.
     *
     * @return degree of relative rotation
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * Gets index of current direction (0-5).
     *
     * @return index of current direction (0-5)
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets relative hexagon that is next to given direction.
     * Can be used to quick calculation of actual hexagon coordinates followed by direction
     *
     * @return relative hexagon
     */
    public Hexagon getDeltaHexagon() {
        return deltaHexagon;
    }
}

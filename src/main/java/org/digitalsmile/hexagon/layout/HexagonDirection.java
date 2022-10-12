package org.digitalsmile.hexagon.layout;

import java.util.Arrays;

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

    public static HexagonDirection nextCounterClockWiseDirection(HexagonDirection currentHexagonDirection) {
        var index = currentHexagonDirection.getIndex() - 1 < 0 ? 5 : currentHexagonDirection.getIndex() - 1;
        return Arrays.stream(values()).filter(direction -> direction.getIndex() == index).findFirst().orElseThrow();
    }

    public static HexagonDirection nextClockWiseDirection(HexagonDirection currentHexagonDirection) {
        var index = currentHexagonDirection.getIndex() + 1 > 5 ? 0 : currentHexagonDirection.getIndex() + 1;
        return Arrays.stream(values()).filter(direction -> direction.getIndex() == index).findFirst().orElseThrow();
    }

    public static HexagonDirection getDirection(float degree) {
        if (degree < 0) {
            return null;
        }
        if (betweenExclusive(degree, 330, 30)) {
            return NORTH;
        } else if (betweenExclusive(degree, 30, 90)) {
            return NORTH_WEST;
        } else if (betweenExclusive(degree, 90, 150)) {
            return SOUTH_WEST;
        } else if (betweenExclusive(degree, 150, 210)) {
            return SOUTH;
        } else if (betweenExclusive(degree, 210, 270)) {
            return SOUTH_EAST;
        } else if (betweenExclusive(degree, 270, 330)) {
            return NORTH_EAST;
        }
        return null;
    }

    private static boolean betweenExclusive(float test, float start, float end) {
        end = (end - start) < 0.0f ? end - start + 360.0f : end - start;
        test = (test - start) < 0.0f ? test - start + 360.0f : test - start;
        return (test < end);
    }

    public float getRotation() {
        return rotation;
    }

    public int getIndex() {
        return index;
    }

    public Hexagon getDeltaHexagon() {
        return deltaHexagon;
    }
}

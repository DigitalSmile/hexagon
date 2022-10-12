package org.digitalsmile.hexagon.layout;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Tag("HexagonDirectionTest")
class HexagonDirectionTest {

    @Test
    void testNextCounterClockWiseDirection() {
        assertEquals(HexagonDirection.NORTH_EAST, HexagonDirection.nextCounterClockWiseDirection(HexagonDirection.NORTH));
        assertEquals(HexagonDirection.SOUTH_EAST, HexagonDirection.nextCounterClockWiseDirection(HexagonDirection.NORTH_EAST));
    }

    @Test
    void testNextClockWiseDirection() {
        assertEquals(HexagonDirection.NORTH_WEST, HexagonDirection.nextClockWiseDirection(HexagonDirection.NORTH));
        assertEquals(HexagonDirection.NORTH, HexagonDirection.nextClockWiseDirection(HexagonDirection.NORTH_EAST));
    }

    @Test
    void testHexagonDirectionGetDirection() {
        assertEquals(HexagonDirection.NORTH, HexagonDirection.getDirection(20));
        assertEquals(HexagonDirection.NORTH_WEST, HexagonDirection.getDirection(55));
        assertEquals(HexagonDirection.SOUTH_WEST, HexagonDirection.getDirection(110));
        assertEquals(HexagonDirection.SOUTH, HexagonDirection.getDirection(199));
        assertEquals(HexagonDirection.SOUTH_EAST, HexagonDirection.getDirection(247));
        assertEquals(HexagonDirection.NORTH_EAST, HexagonDirection.getDirection(271));

        assertNull(HexagonDirection.getDirection(1232141));
        assertNull(HexagonDirection.getDirection(-1232141));
    }

}

package org.digitalsmile.hexgrid.hexagon;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("FractionalHexagonTest")
class FractionalHexagonTest {

    @Test
    void testFractionalHexagonThrows() {
        assertThrows(IllegalArgumentException.class, () -> new FractionalHexagon(1d, 2d, 3d));
    }
}

package org.digitalsmile.hexgrid.operations;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("FractionalHexagonTest")
class FractionalHexagonTest {

    @Test
    void testFractionalHexagonThrows() {
        assertThrows(IllegalArgumentException.class, () -> new FractionalHexagon(1d, 2d, 3d));
    }

    @Test
    void testHexagonRound() {
        var a = new FractionalHexagon(0.0, 0.0, 0.0);
        var b = new FractionalHexagon(1.0, -1.0, 0.0);
        var c = new FractionalHexagon(0.0, -1.0, 1.0);

        assertEquals(new Hexagon(5, -10, 5), new FractionalHexagon(0.0, 0.0, 0.0).interpolate(new FractionalHexagon(10.0, -20.0, 10.0), 0.5).roundToHexagon());
        assertEquals(a.roundToHexagon(), a.interpolate(b, 0.499).roundToHexagon());
        assertEquals(b.roundToHexagon(), a.interpolate(b, 0.501).roundToHexagon());
        assertEquals(a.roundToHexagon(), new FractionalHexagon(a.q() * 0.4 + b.q() * 0.3 + c.q() * 0.3, a.r() * 0.4 + b.r() * 0.3 + c.r() * 0.3, a.s() * 0.4 + b.s() * 0.3 + c.s() * 0.3).roundToHexagon());
        assertEquals(c.roundToHexagon(), new FractionalHexagon(a.q() * 0.3 + b.q() * 0.3 + c.q() * 0.4, a.r() * 0.3 + b.r() * 0.3 + c.r() * 0.4, a.s() * 0.3 + b.s() * 0.3 + c.s() * 0.4).roundToHexagon());
    }
}

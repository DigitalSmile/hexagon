package org.digitalsmile.hexgrid.hexagon;

import org.digitalsmile.hexgrid.HexagonGrid;

/**
 * Utility hexagon class for interpolating and rounding physical coordinates (e.g. pixels) to exact hexagon on the {@link HexagonGrid}.
 *
 * @param q fuzzy q coordinates of hexagon
 * @param r fuzzy r coordinates of hexagon
 * @param s fuzzy s coordinates of hexagon
 */
record FractionalHexagon(double q, double r, double s) {

    FractionalHexagon {
        if (Math.round(q + r + s) != 0) {
            throw new IllegalArgumentException("q + r + s must be 0");
        }
    }

    /**
     * Rounds FractionalHexagon to the hexagon on the {@link HexagonGrid}.
     *
     * @return hexagon with exact coordinates
     */
    public Hexagon roundToHexagon() {
        // Since Math.round(double) returns long,
        // we need Math.toIntExact to ensure we did not pass any int overflowing numbers
        var roundedQ = Math.toIntExact(Math.round(q));
        var roundedR = Math.toIntExact(Math.round(r));
        var roundedS = Math.toIntExact(Math.round(s));
        var qDiff = Math.abs(roundedQ - q);
        var rDiff = Math.abs(roundedR - r);
        var sDiff = Math.abs(roundedS - s);
        if (qDiff > rDiff && qDiff > sDiff) {
            roundedQ = -roundedR - roundedS;
        } else if (rDiff > sDiff) {
            roundedR = -roundedQ - roundedS;
        } else {
            roundedS = -roundedQ - roundedR;
        }
        return new Hexagon(roundedQ, roundedR, roundedS);
    }

    /**
     * Interpolation function for coordinate determination.
     *
     * @param fractionalHexagon provided "near" hexagon
     * @param step              step of interpolation
     * @return new FractionalHexagon with more precise coordinates
     */
    public FractionalHexagon interpolate(FractionalHexagon fractionalHexagon, double step) {
        var q = q() * (1.0 - step) + fractionalHexagon.q() * step;
        var r = r() * (1.0 - step) + fractionalHexagon.r() * step;
        var s = s() * (1.0 - step) + fractionalHexagon.s() * step;
        return new FractionalHexagon(q, r, s);
    }


}

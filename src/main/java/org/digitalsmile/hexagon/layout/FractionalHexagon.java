package org.digitalsmile.hexagon.layout;

record FractionalHexagon(double q, double r, double s) {

    FractionalHexagon {
        if (Math.round(q + r + s) != 0) {
            throw new IllegalArgumentException("q + r + s must be 0");
        }
    }

    public Hexagon roundToHexagon() {
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


    public FractionalHexagon interpolate(FractionalHexagon fractionalHexagon, double step) {
        var q = q() * (1.0 - step) + fractionalHexagon.q() * step;
        var r = r() * (1.0 - step) + fractionalHexagon.r() * step;
        var s = s() * (1.0 - step) + fractionalHexagon.s() * step;
        return new FractionalHexagon(q, r, s);
    }


}

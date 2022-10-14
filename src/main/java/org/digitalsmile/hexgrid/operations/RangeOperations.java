package org.digitalsmile.hexgrid.operations;

import org.digitalsmile.hexgrid.hexagon.Hexagon;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class that provides range operations for hexagon.
 */
public class RangeOperations {
    /**
     * Gets the list of hexagon in a specific range starting from provided center.
     *
     * @param centerHexagon provided center hexagon
     * @param range         range in hexagon numbers
     * @return list of hexagon in range
     */
    public static List<Hexagon> getRange(Hexagon centerHexagon, int range) {
        return IntStream.rangeClosed(-range, range)
                .mapToObj(q -> {
                    var max = Math.max(-range, -q - range);
                    var min = Math.min(range, -q + range);
                    return IntStream.rangeClosed(max, min)
                            .mapToObj(r -> {
                                var s = -q - r;
                                var hex = new Hexagon(q, r, s);
                                return centerHexagon.add(hex);
                            }).toList();
                })
                .flatMap(Collection::stream)
                .toList();
    }
}

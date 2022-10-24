package org.digitalsmile.hexgrid.operations;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.HexagonDirection;
import org.digitalsmile.hexgrid.hexagon.Point;
import org.digitalsmile.hexgrid.layout.GridLayout;
import org.digitalsmile.hexgrid.operations.pole.PoleDetection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class that provides range operations for hexagon.
 */
public class RangeOperations {

    private RangeOperations() {
    }

    /**
     * Gets the list of hexagon in a specific range starting from provided center.
     * Set useParallelStream flag to true to force computation in parallel.
     * Please note, enabling flag on small amounts of data may degrade performance, use with caution
     * and always test.
     *
     * @param centerHexagon     provided center hexagon
     * @param range             range in hexagon numbers
     * @param useParallelStream flag for using parallel stream for computing
     * @return list of hexagon in range
     */
    public static List<Hexagon> getRange(Hexagon centerHexagon, int range, boolean useParallelStream) {
        var stream = IntStream.rangeClosed(-range, range);
        if (useParallelStream) {
            stream = stream.parallel();
        }
        return stream.mapToObj(q -> {
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

    /**
     * Gets the list of hexagon in a specific range starting from provided center.
     *
     * @param centerHexagon provided center hexagon
     * @param range         range in hexagon numbers
     * @return list of hexagon in range
     */
    public static List<Hexagon> getRange(Hexagon centerHexagon, int range) {
        return getRange(centerHexagon, range, false);
    }

    /**
     * Gets a list og hexagons bounded by provided polygon with physical coordinates for a specific grid.
     *
     * @param boundingPolygon        list of physical coordinates of bounded polygon
     * @param gridLayout             grid layout for hexagon lookup
     * @param includeBoundingHexagon flag to include the bordered hexagon within bounded polygon
     * @return list of bounded hexagons for specific grid
     */
    public static List<Hexagon> getBoundingHexagon(List<Point> boundingPolygon, GridLayout<?> gridLayout, boolean includeBoundingHexagon) {
        var hexagonLayout = gridLayout.getHexagonLayout();
        var centerPoint = PoleDetection.detectCenter(boundingPolygon, 1.0);
        var centerHexagon = hexagonLayout.getBoundingHexagon(centerPoint);

        var len = boundingPolygon.size();
        List<Hexagon> boundingHexagons = new ArrayList<>();
        for (int i = 0; i < boundingPolygon.size(); i++) {
            var current = boundingPolygon.get((i + 1) % len);
            var prev = boundingPolygon.get(i);
            boundingHexagons.addAll(
                    HexagonOperations.hexagonLinePath(
                            hexagonLayout.getBoundingHexagon(prev),
                            hexagonLayout.getBoundingHexagon(current)
                    )
            );
        }
        return getBoundingHexagons(new ArrayList<>(), boundingHexagons, centerHexagon, includeBoundingHexagon);
    }

    private static List<Hexagon> getBoundingHexagons(List<Hexagon> hexagons, List<Hexagon> boundingPolygon, Hexagon centerHexagon, boolean includeBoundingHexagon) {
        if (hexagons.contains(centerHexagon)) {
            return hexagons;
        }
        hexagons.add(centerHexagon);
        for (HexagonDirection direction : HexagonDirection.values()) {
            var neighbour = centerHexagon.neighbor(direction);
            if (boundingPolygon.contains(neighbour)) {
                if (includeBoundingHexagon) {
                    hexagons.add(neighbour);
                }
                continue;
            }
            hexagons = getBoundingHexagons(hexagons, boundingPolygon, neighbour, includeBoundingHexagon);
        }
        return hexagons;
    }
}

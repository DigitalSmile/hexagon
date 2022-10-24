package org.digitalsmile.hexgrid.operations.pole;

import org.digitalsmile.hexgrid.hexagon.Point;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Class to detect the center of any polygon. See link below for explanations.
 *
 * @link <a href="https://github.com/FreshLlamanade/polylabel-java">poly-label</a>
 */
public class PoleDetection {

    /**
     * Detects center of polygon by providing list of physical coordinates and precision.
     *
     * @param polygon   list of physical polygon coordinates
     * @param precision precision of finding center in double from 0 to 1.0
     * @return a point, that represents the center of polygon
     */
    public static Point detectCenter(List<Point> polygon, double precision) {

        // find the bounding box of the outer ring
        var minX = polygon.stream().mapToDouble(Point::x).min().orElseThrow();
        var maxX = polygon.stream().mapToDouble(Point::x).max().orElseThrow();
        var minY = polygon.stream().mapToDouble(Point::y).min().orElseThrow();
        var maxY = polygon.stream().mapToDouble(Point::y).max().orElseThrow();

        var width = maxX - minX;
        var height = maxY - minY;
        var cellSize = Math.min(width, height);
        var half = cellSize / 2;

        if (cellSize == 0) {
            return new Point(minX, minY);
        }

        // a priority queue of cells in order of their "potential" (max distance to polygon)
        var cellQueue = new PriorityQueue<>(Cell::compareTo);

        for (double x = minX; x < maxX; x += cellSize)
            for (double y = minY; y < maxY; y += cellSize)
                cellQueue.add(new Cell(x + half, y + half, half, polygon));

        // take centroid as the first best guess
        var bestCell = getCentroidCell(polygon);

        // special case for rectangular polygons
        var rectangularCell = new Cell(minX + width / 2, minY + height / 2, 0, polygon);
        if (rectangularCell.getDistance() > bestCell.getDistance())
            bestCell = rectangularCell;

        var numProbes = cellQueue.size();

        while (cellQueue.size() > 0) {
            // pick the most promising cell from the queue
            var cell = cellQueue.poll();

            // update the best cell if we found a better one
            if (cell.getDistance() > bestCell.getDistance()) {
                bestCell = cell;
            }

            // do not drill down further if there's no chance of a better solution
            if (cell.getMax() - bestCell.getDistance() <= precision)
                continue;

            // split the cell into four cells
            half = cell.getHalf() / 2;
            cellQueue.add(new Cell(cell.getX() - half, cell.getY() - half, half, polygon));
            cellQueue.add(new Cell(cell.getX() + half, cell.getY() - half, half, polygon));
            cellQueue.add(new Cell(cell.getX() - half, cell.getY() + half, half, polygon));
            cellQueue.add(new Cell(cell.getX() + half, cell.getY() + half, half, polygon));
            numProbes += 4;
        }

        return new Point(bestCell.getX(), bestCell.getY());
    }

    // get polygon centroid
    private static Cell getCentroidCell(List<Point> polygon) {
        var area = 0d;
        var x = 0d;
        var y = 0d;

        for (int i = 0, len = polygon.size() - 1, j = len - 1; i < len; j = i++) {
            var a0 = polygon.get(i).x();
            var a1 = polygon.get(i).y();
            var b0 = polygon.get(j).x();
            var b1 = polygon.get(j).y();
            var diff = a0 * b1 - b0 * a1;
            x += (a0 + b0) * diff;
            y += (a1 + b1) * diff;
            area += diff * 3;
        }
        if (area == 0)
            return new Cell(polygon.get(0).x(), polygon.get(0).y(), 0, polygon);
        return new Cell(x / area, y / area, 0, polygon);
    }

}

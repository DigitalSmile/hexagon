package org.digitalsmile.hexgrid.shapes.hexagonal;

import org.digitalsmile.hexgrid.shapes.Bounds;

/**
 * Represents props for creating Hexagonal shape.
 * @param mapRadius radius of hexagonal view
 */
public record HexagonalBounds(int mapRadius) implements Bounds {
}

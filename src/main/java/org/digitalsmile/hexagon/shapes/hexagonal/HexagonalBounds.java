package org.digitalsmile.hexagon.shapes.hexagonal;

import org.digitalsmile.hexagon.shapes.Bounds;

/**
 * Represents props for creating Hexagonal shape.
 * @param mapRadius radius of hexagonal view
 */
public record HexagonalBounds(int mapRadius) implements Bounds {
}

package org.digitalsmile.hexagon;

import org.digitalsmile.hexagon.layout.Hexagon;
import org.digitalsmile.hexagon.layout.Layout;
import org.digitalsmile.hexagon.layout.Orientation;
import org.digitalsmile.hexagon.layout.Point;
import org.digitalsmile.hexagon.shapes.Bounds;
import org.digitalsmile.hexagon.shapes.Shape;
import org.digitalsmile.hexagon.shapes.hexagonal.HexagonalBounds;
import org.digitalsmile.hexagon.shapes.rectangle.RectangleBounds;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectHook;
import org.digitalsmile.hexagon.storage.HexagonMetaObjectStorage;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Main class that represents grid of hexagons with
 */
public class HexagonGrid {
    private final Shape shape;
    private final Bounds bounds;
    private final HexagonMetaObjectStorage<?> dataStorage;
    private final Layout layout;

    private HexagonGrid(HexagonGridBuilder builder) {
        this.bounds = builder.bounds;
        this.shape = builder.shape;
        this.dataStorage = new HexagonMetaObjectStorage<>(builder.objectCreationHook);

        var layoutProps = calculateLayoutProps(builder);
        this.layout = new Layout(builder.orientation,
                layoutProps.hexagonWidth(), layoutProps.hexagonHeight(), layoutProps.side(),
                new Point(builder.offsetX, builder.offsetY));
    }

    private record LayoutProps(double hexagonWidth, double hexagonHeight, double side) {
    }

    private LayoutProps calculateLayoutProps(HexagonGridBuilder builder) {
        double hexagonWidth;
        double hexagonHeight;
        double side;
        switch (builder.orientation) {
            case FLAT -> {
                if (builder.hexagonWidth != 0) {
                    hexagonWidth = builder.hexagonWidth;
                    side = builder.hexagonWidth / 2d;
                    hexagonHeight = side * Math.sqrt(3.0);
                } else if (builder.hexagonHeight != 0) {
                    hexagonHeight = builder.hexagonHeight;
                    side = builder.hexagonHeight / Math.sqrt(3.0);
                    hexagonWidth = side * 2d;
                } else {
                    side = builder.side;
                    hexagonWidth = side * 2d;
                    hexagonHeight = side * Math.sqrt(3.0);
                }
            }
            case POINTY -> {
                if (builder.hexagonWidth != 0) {
                    hexagonWidth = builder.hexagonWidth;
                    side = builder.hexagonWidth / Math.sqrt(3.0);
                    hexagonHeight = side * 2d;
                } else if (builder.hexagonHeight != 0) {
                    hexagonHeight = builder.hexagonHeight;
                    side = builder.hexagonHeight / 2d;
                    hexagonWidth = side * Math.sqrt(3.0);
                } else {
                    side = builder.side;
                    hexagonWidth = side * Math.sqrt(3.0);
                    hexagonHeight = side * 2d;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + builder.orientation);
        }
        return new LayoutProps(hexagonWidth, hexagonHeight, side);
    }

    /**
     * Generates hexagon grid with props provided earlier.
     */
    public void generateHexagons() {
        shape.createShape(bounds, dataStorage, layout.getOrientation());
    }

    /**
     * Gets list of hexagons. The list is unmodifiable.
     * @return unmodifiable list of hexagons
     */
    public List<Hexagon> getHexagons() {
        return Collections.unmodifiableList(dataStorage.getHexagons());
    }

    /**
     * Gets meta objects by provided hexagon.
     * @param hexagon provided hexagon
     * @return meta object
     * @param <T> type of meta object
     */
    @SuppressWarnings("unchecked")
    public <T> T getHexagonDataObject(Hexagon hexagon) {
        return (T) dataStorage.getHexagonDataObject(hexagon);
    }

    /**
     * Gets list of all meta objects.
     * @return list of meta objects
     * @param <T> type of meta objects
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getHexagonDataObjects() {
        return (List<T>) dataStorage.getHexagonDataObjects();
    }

    /**
     * Gets layout of grid creates.
     * @return grid layout object
     */
    public Layout getLayout() {
        return layout;
    }

//    public double getWidth() {
//        return layout.getHexagonWidth() * 3 / 4 * cols + layout.getHexagonWidth();
//    }
//
//    public double getHeight() {
//        return layout.getHexagonHeight() * (rows + 1);
//    }


    public List<Hexagon> getRange(Hexagon centerHexagon, int range) {
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

    /**
     * Builder for creating hexagon grid.
     */
    public static class HexagonGridBuilder {
        private Orientation orientation;
        private Shape shape;
        private Bounds bounds;
        private double offsetX;
        private double offsetY;
        private double hexagonWidth;
        private double hexagonHeight;
        private double side;

        private HexagonMetaObjectHook<?> objectCreationHook;

        private static final String SIZE_ERROR = "You should specify width, height or side of hexagon, but not combination!";

        public HexagonGridBuilder orientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public HexagonGridBuilder rectangleShape(RectangleBounds rectangleBounds) {
            this.shape = Shape.RECTANGLE;
            this.bounds = rectangleBounds;
            return this;
        }

        public HexagonGridBuilder hexagonShape(HexagonalBounds hexagonalBounds) {
            this.shape = Shape.HEXAGONAL;
            this.bounds = hexagonalBounds;
            return this;
        }

        public HexagonGridBuilder offsetX(double offsetX) {
            this.offsetX = offsetX;
            return this;
        }

        public HexagonGridBuilder offsetY(double offsetY) {
            this.offsetY = offsetY;
            return this;
        }

        public HexagonGridBuilder hexagonWidth(double hexagonWidth) {
            if (this.hexagonHeight != 0 || this.side != 0) {
                throw new IllegalArgumentException(SIZE_ERROR);
            }
            this.hexagonWidth = hexagonWidth;
            return this;
        }

        public HexagonGridBuilder hexagonHeight(double hexagonHeight) {
            if (this.hexagonWidth != 0 || this.side != 0) {
                throw new IllegalArgumentException(SIZE_ERROR);
            }
            this.hexagonHeight = hexagonHeight;
            return this;
        }

        public HexagonGridBuilder side(double side) {
            if (this.hexagonWidth != 0 || this.hexagonHeight != 0) {
                throw new IllegalArgumentException(SIZE_ERROR);
            }
            this.side = side;
            return this;
        }

        public HexagonGridBuilder hexagonMetaObjectHook(HexagonMetaObjectHook<?> objectCreationHook) {
            this.objectCreationHook = objectCreationHook;
            return this;
        }

        public HexagonGrid build() {
            if (Objects.isNull(orientation)) {
                throw new IllegalArgumentException("You have to define Orientation for hexagon in order to create a grid!");
            }
            if (Objects.isNull(shape) || Objects.isNull(bounds)) {
                throw new IllegalArgumentException("You have to define Shape and Bounds for a grid!");
            }
            var emptyProps = Stream.of(hexagonWidth, hexagonHeight, side).filter(value -> value == 0).count();
            if (emptyProps == 3) {
                throw new IllegalArgumentException("You have to define at least one of third props - hexagonWidth/hexagonHeight/side - to create a grid!");
            }
            return new HexagonGrid(this);
        }
    }
}

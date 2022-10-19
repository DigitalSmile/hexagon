package org.digitalsmile.hexgrid;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.GridLayout;
import org.digitalsmile.hexgrid.layout.HexagonLayout;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.hexagon.Point;
import org.digitalsmile.hexgrid.shapes.Shape;
import org.digitalsmile.hexgrid.storage.HexagonCreationHook;
import org.digitalsmile.hexgrid.storage.HexagonStorage;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Main class that represents grid of hexagons with
 */
public class HexagonGrid<S extends Shape> {
    private final GridLayout<S> gridLayout;
    private final HexagonStorage<?> dataStorage;

    private HexagonGrid(HexagonGridBuilder<S> builder) {
        var layoutProps = calculateLayoutProps(builder);
        var hexagonLayout = new HexagonLayout(builder.orientation,
                layoutProps.hexagonWidth(), layoutProps.hexagonHeight(), layoutProps.side(),
                new Point(builder.offsetX, builder.offsetY));
        this.gridLayout = new GridLayout<>(builder.shape, hexagonLayout);
        this.dataStorage = new HexagonStorage<>(gridLayout.getShape().getGridSize(),
                gridLayout.getIndexProcessor(), builder.objectCreationHook);
    }

    /**
     * Generates hexagon grid with props provided earlier.
     */
    public void generateHexagons() {
        gridLayout.createShape(dataStorage);
    }

    /**
     * Gets list of hexagons. The list is unmodifiable.
     *
     * @return unmodifiable list of hexagons
     */
    public List<Hexagon> getHexagons() {
        return Collections.unmodifiableList(dataStorage.getHexagons());
    }

    /**
     * Gets meta objects by provided hexagon.
     *
     * @param hexagon provided hexagon
     * @param <T>     type of meta object
     * @return meta object
     */
    @SuppressWarnings("unchecked")
    public <T> T getHexagonDataObject(Hexagon hexagon) {
        return (T) dataStorage.getHexagonDataObject(hexagon);
    }

    /**
     * Gets layout of grid creates.
     *
     * @return grid layout object
     */
    public HexagonLayout getHexagonLayout() {
        return gridLayout.getHexagonLayout();
    }

    //    public double getWidth() {
//        return layout.getHexagonWidth() * 3 / 4 * cols + layout.getHexagonWidth();
//    }
//
//    public double getHeight() {
//        return layout.getHexagonHeight() * (rows + 1);
//    }
    private record LayoutProps(double hexagonWidth, double hexagonHeight, double side) {
    }

    private LayoutProps calculateLayoutProps(HexagonGridBuilder<S> builder) {
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
     * Builder for creating hexagon grid.
     */
    public static class HexagonGridBuilder<S extends Shape> {
        private Orientation orientation;
        private S shape;
        private double offsetX;
        private double offsetY;
        private double hexagonWidth;
        private double hexagonHeight;
        private double side;

        private HexagonCreationHook<?> objectCreationHook;

        private static final String SIZE_ERROR = "You should specify width, height or side of hexagon, but not combination!";

        public  HexagonGridBuilder<S> shape(S shape, Orientation orientation) {
            this.shape = shape;
            this.orientation = orientation;
            return this;
        }

        public HexagonGridBuilder<S> offsetX(double offsetX) {
            this.offsetX = offsetX;
            return this;
        }

        public HexagonGridBuilder<S> offsetY(double offsetY) {
            this.offsetY = offsetY;
            return this;
        }

        public HexagonGridBuilder<S> hexagonWidth(double hexagonWidth) {
            if (this.hexagonHeight != 0 || this.side != 0) {
                throw new IllegalArgumentException(SIZE_ERROR);
            }
            this.hexagonWidth = hexagonWidth;
            return this;
        }

        public HexagonGridBuilder<S> hexagonHeight(double hexagonHeight) {
            if (this.hexagonWidth != 0 || this.side != 0) {
                throw new IllegalArgumentException(SIZE_ERROR);
            }
            this.hexagonHeight = hexagonHeight;
            return this;
        }

        public HexagonGridBuilder<S> side(double side) {
            if (this.hexagonWidth != 0 || this.hexagonHeight != 0) {
                throw new IllegalArgumentException(SIZE_ERROR);
            }
            this.side = side;
            return this;
        }

        public HexagonGridBuilder<S> hexagonDataObjectHook(HexagonCreationHook<?> objectCreationHook) {
            this.objectCreationHook = objectCreationHook;
            return this;
        }

        public HexagonGrid<S> build() {
            if (Objects.isNull(orientation)) {
                throw new IllegalArgumentException("You have to define Orientation for hexagon in order to create a grid!");
            }
            if (Objects.isNull(shape)) {
                throw new IllegalArgumentException("You have to define Shape and Bounds for a grid!");
            }
            var emptyProps = Stream.of(hexagonWidth, hexagonHeight, side).filter(value -> value == 0).count();
            if (emptyProps == 3) {
                throw new IllegalArgumentException("You have to define at least one of third props - hexagonWidth/hexagonHeight/side - to create a grid!");
            }
            return new HexagonGrid<>(this);
        }
    }
}

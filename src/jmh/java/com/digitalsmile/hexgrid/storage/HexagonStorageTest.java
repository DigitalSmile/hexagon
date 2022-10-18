package com.digitalsmile.hexgrid.storage;

import org.digitalsmile.hexgrid.HexagonGrid;
import org.digitalsmile.hexgrid.coordinates.OffsetCoordinates;
import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShape;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class HexagonStorageTest {
    private HexagonGrid<?> grid;
    private Hexagon hexagonToFind;

    @Setup
    public void setup() {
        grid = new HexagonGrid.HexagonGridBuilder<>()
                .hexagonWidth(150)
                .shape(new RectangleShape(1000, 1000), Orientation.FLAT)
                .hexagonMetaObjectHook(Hexagon::toString)
                .build();
        grid.generateHexagons();
        var randomX = new Random().nextInt(1000);
        var randomY = new Random().nextInt(1000);
        hexagonToFind = OffsetCoordinates.toCube(Orientation.FLAT, new OffsetCoordinates(randomX, randomY));
    }


    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testStorageGetMetaObjectByIndex(Blackhole blackhole) {
        var object = grid.getHexagonDataObject(hexagonToFind);
        blackhole.consume(object);
    }

}

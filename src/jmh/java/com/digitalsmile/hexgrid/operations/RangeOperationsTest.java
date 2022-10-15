package com.digitalsmile.hexgrid.operations;

import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.operations.RangeOperations;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class RangeOperationsTest {

    @State(Scope.Thread)
    public static class Input {
        public Hexagon hexagon = new Hexagon(0,0,0);
        public int range = 100;
    }

    @Benchmark
    public void testGetRangeNoParallelStream(Input i, Blackhole blackhole) {
        var list = RangeOperations.getRange(i.hexagon, i.range);
        blackhole.consume(list);
    }

}

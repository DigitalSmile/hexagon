package org.digitalsmile.hexgrid.shapes;

import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShape;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShapeBuilder;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShape;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShapeBuilder;

import java.util.Map;

public class ShapeBuilderFactory {

    private static final Map<Class<? extends Shape>, ShapeBuilder<? extends Shape>> map = Map.of(
            RectangleShape.class, new RectangleShapeBuilder(),
            HexagonalShape.class, new HexagonalShapeBuilder()
    );

    private ShapeBuilderFactory(){}

    @SuppressWarnings("unchecked")
    public static <S extends Shape> ShapeBuilder<S> createShapeCreator(Class<? extends Shape> clazz) {
        return (ShapeBuilder<S>) map.get(clazz);
    }

}

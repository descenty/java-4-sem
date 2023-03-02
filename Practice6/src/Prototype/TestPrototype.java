package Prototype;

import java.util.stream.IntStream;
import Prototype.Shapes.Shape;

public class TestPrototype {
    public static void main(String[] args) {
        ShapeCache.loadCache();
        IntStream.range(0, 5).forEach((i) -> {
            Shape clonedShape = ShapeCache.getShape("1");
            clonedShape.draw();
        });
        IntStream.range(0, 5).forEach((i) -> {
            Shape clonedShape = ShapeCache.getShape("2");
            clonedShape.draw();
        });
    }
}

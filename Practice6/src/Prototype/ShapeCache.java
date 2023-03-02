package Prototype;

import java.util.Map;

import Prototype.Shapes.Circle;
import Prototype.Shapes.Rectangle;
import Prototype.Shapes.Shape;
import java.util.HashMap;

public class ShapeCache {
    private static Map<String, Shape> shapeMap = new HashMap<>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    public static void loadCache() {
        Rectangle rectangle = new Rectangle(1.0f, 2.0f);
        rectangle.setId("1");
        shapeMap.put(rectangle.getId(), rectangle);

        Circle circle = new Circle(1.0f);
        circle.setId("2");
        shapeMap.put(circle.getId(), circle);
    }
}

package Decorator;

import Prototype.Shapes.Shape;

public class BoxShadowDecorator extends ShapeDecorator {
    public BoxShadowDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    public void draw() {
        decoratedShape.draw();
        setBoxShadow(decoratedShape);
    }

    private void setBoxShadow(Shape decoratedShape) {
        System.out.println("Box Shadow: 10px 10px 5px #888888");
    }
}

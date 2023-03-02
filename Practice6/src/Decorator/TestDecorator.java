package Decorator;

import Prototype.Shapes.Circle;
import Prototype.Shapes.Rectangle;
import Prototype.Shapes.Shape;

public class TestDecorator {
    public static void main(String[] args) {
        Shape circle = new Circle(1.0f);
        Shape redBorderCircle = new RedBorderDecorator(new Circle(3.0f));
        Shape redShadowRectangle = new BoxShadowDecorator(new RedBorderDecorator(new Rectangle(1.0f, 2.0f)));
        circle.draw();
        System.out.println();
        redBorderCircle.draw();
        System.out.println();
        redShadowRectangle.draw();
    }
}

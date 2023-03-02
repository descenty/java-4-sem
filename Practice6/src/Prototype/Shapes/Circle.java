package Prototype.Shapes;

public class Circle extends Shape {
    private float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println(String.format("Drawing a circle with id: %s and radius: %f", getId(), radius));
    }
}

package Prototype.Shapes;

public class Circle extends Shape {
    private float size;

    public Circle(float size) {
        this.size = size;
    }

    @Override
    public void draw() {
        System.out.println(String.format("Drawing a circle with id: %s and size: %f", getId(), size));
    }
}

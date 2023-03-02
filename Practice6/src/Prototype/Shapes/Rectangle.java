package Prototype.Shapes;

public class Rectangle extends Shape {
    private float width;
    private float height;

    public Rectangle(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println(
                String.format("Drawing a rectangle with id: %s and width: %f and height: %f", getId(), width, height));
    }
}

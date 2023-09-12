package ru.astondevs.course.shapes;

public class Rectangle extends AbstractShape {
    private double side1;
    private double side2;

    public Rectangle(double side1, double side2, String fillColor, String borderColor) {
        super(fillColor, borderColor);
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public double calculateArea() {
        return side1 * side2;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (side1 + side2);
    }

    @Override
    public String toString() {
        return super.getShapeCharacteristics("Прямоугольник");
    }
}

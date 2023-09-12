package ru.astondevs.course.shapes;

public class Triangle extends AbstractShape {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3, String fillColor, String borderColor) {
        super(fillColor, borderColor);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double calculateArea() {
        var semiP = calculatePerimeter() / 2;
        return Math.sqrt(semiP * (semiP - side1) * (semiP - side2) * (semiP - side3));
    }

    @Override
    public double calculatePerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return super.getShapeCharacteristics("Треугольник");
    }
}

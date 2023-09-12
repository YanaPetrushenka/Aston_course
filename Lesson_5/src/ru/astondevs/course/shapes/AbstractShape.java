package ru.astondevs.course.shapes;

import ru.astondevs.course.shapes.interfaces.Area;
import ru.astondevs.course.shapes.interfaces.Perimeter;

public abstract class AbstractShape implements Perimeter, Area {
    private String fillColor;
    private String borderColor;

    public AbstractShape(String fillColor, String borderColor) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    protected String getShapeCharacteristics(String shapeName) {
        return String.format("%s: цвет заливки = %s, цвет границы = %s, площадь = %.2f, периметр = %.2f",
                shapeName, fillColor, borderColor, calculateArea(), calculatePerimeter()
        );
    }
}

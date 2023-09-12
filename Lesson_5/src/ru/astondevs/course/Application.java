package ru.astondevs.course;

import ru.astondevs.course.animals.Animal;
import ru.astondevs.course.animals.Cat;
import ru.astondevs.course.animals.Dog;
import ru.astondevs.course.animals.Plate;
import ru.astondevs.course.shapes.Circle;
import ru.astondevs.course.shapes.Rectangle;
import ru.astondevs.course.shapes.Triangle;

import java.util.Arrays;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {

//        Animals
        var cat1 = new Cat();
        var dog1 = new Dog();
        var dog2 = new Dog();
        var plate1 = new Plate();

        cat1.run(10);
        cat1.swim(16);
        dog1.run(150);
        dog2.run(501);
        cat1.eat(plate1);

        var cats = Stream.generate(Cat::new)
                .limit(10)
                .toArray(Cat[]::new);

        Plate plate2 = new Plate();

        Arrays.stream(cats)
                .forEach(
                        cat -> {
                            cat.eat(plate2);
                            System.out.println("Cat isFed: " + cat.isFed());
                        }
                );

        System.out.printf("Создано обьектов Cat: %d%n", Cat.getCatCount());
        System.out.printf("Создано обьектов Dog: %d%n", Dog.getDogCount());
        System.out.printf("Создано обьектов Animal: %d%n", Animal.getAnimalCount());

//        Shapes
        Circle circle = new Circle(15, "Белый", "Черный");
        System.out.println(circle);

        Rectangle rectangle = new Rectangle(5, 10, "Зеленый", "Синий");
        System.out.println(rectangle);

        Triangle triangle = new Triangle(30, 30, 50, "Желтый", "Красный");
        System.out.println(triangle);
    }
}



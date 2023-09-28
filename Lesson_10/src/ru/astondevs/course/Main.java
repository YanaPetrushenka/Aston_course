package ru.astondevs.course;

import ru.astondevs.course.entities.Apple;
import ru.astondevs.course.entities.Box;
import ru.astondevs.course.entities.Orange;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

//        Fill boxes
        Stream.generate(Apple::new)
                .limit(100)
                .forEach(appleBox::addFruit);

        Stream.generate(Orange::new)
                .limit(50)
                .forEach(orangeBox::addFruit);

        System.out.println("Масса коробки с яблоками: " + appleBox.getWeight());
        System.out.println("Масса коробки с апельсинами: " + orangeBox.getWeight());

//        Compare boxes weight
        System.out.println("Массы коробок равны: " + appleBox.compare(orangeBox));

//        Put fruits to another box
        Box<Apple> destinationAppleBox = new Box<>();

        Stream.generate(Apple::new)
                .limit(5)
                .forEach(destinationAppleBox::addFruit);

        appleBox.putTo(destinationAppleBox);
        System.out.println("Масса первой коробки с яблоками после пересыпки: " + appleBox.getWeight());
        System.out.println("Масса второй коробки с яблоками после пересыпки: " + destinationAppleBox.getWeight());
    }
}
package ru.astondevs.course;

import java.util.List;

import static ru.astondevs.course.constants.Constants.*;

public class Lesson2 {

    public static void main(String[] args) {
        var app = new Lesson2();
        app.checkSumSing();
        app.printColor();
        app.compareNumbers();
    }

    private void printThreeWords() {
        var fruits = List.of("Orange", "Banana", "Apple");
        fruits.forEach(System.out::println);
    }

    private void checkSumSing() {
        int a = 0;
        int b = -1;
        int c = a + b;

        if (c >= 0) {
            System.out.println(POSITIVE_SUM_MESSAGE);
        } else {
            System.out.println(NEGATIVE_SUM_MESSAGE);
        }
    }

    private void printColor() {
        int value = 1000;

        if (value <= RED_UP_LIMIT) {
            System.out.println(RED_KEY);
        } else if (value > YELLOW_LOW_LIMIT && value <= YELLOW_UP_LIMIT) {
            System.out.println(YELLOW_KEY);
        } else if (value > GREEN_LOW_LIMIT) {
            System.out.println(GREEN_KEY);
        }
    }

    private void compareNumbers() {
        int a = 5;
        int b = 3;

        if (a >= b) {
            System.out.println(A_GREATER_OR_EQUALS_B_MESSAGE);
        } else {
            System.out.println(A_LESS_B_MESSAGE);
        }
    }
}

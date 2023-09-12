package ru.astondevs.course.animals;

import java.util.Random;

public class Cat extends Animal {
    private static final int MAX_HUNGER = 20;
    private static final int RUN_LIMIT = 200;

    private static int catCounter;

    private boolean isFed;
    private int hungerLevel;

    public Cat() {
        hungerLevel = Math.abs(new Random().nextInt(MAX_HUNGER));
        catCounter++;
    }

    @Override
    public void run(int meters) {
        var message = meters <= RUN_LIMIT ?
                String.format("Кот пробежал %d м", meters) :
                String.format("Кот не может бежать больше %d м", RUN_LIMIT);

        System.out.println(message);
    }

    @Override
    public void swim(int meters) {
        System.out.println("Кот не умеет плавать");
    }

    public void eat(Plate plate) {
        var foodRemainder = plate.takeFood(hungerLevel);

        if (foodRemainder >= 0) {
            hungerLevel = 0;
            isFed = true;
        }
    }

    public static int getCatCount() {
        return catCounter;
    }

    public boolean isFed() {
        return isFed;
    }
}

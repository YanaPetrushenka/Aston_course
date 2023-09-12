package ru.astondevs.course.animals;

public abstract class Animal {

    private static int animalCounter;

    public Animal() {
        animalCounter++;
    }

    public abstract void run(int meters);

    public abstract void swim(int meters);

    public static int getAnimalCount() {
        return animalCounter;
    }
}


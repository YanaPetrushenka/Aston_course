package ru.astondevs.course.animals;

public class Dog extends Animal {
    private static final int RUN_LIMIT = 500;
    private static final int SWIM_LIMIT = 10;
    private static int dogCounter;

    public Dog() {
        dogCounter++;
    }

    @Override
    public void run(int meters) {
        var message = meters <= RUN_LIMIT ?
                String.format("Собака пробежала %d м", meters) :
                String.format("Собака не может бежать больше %d м", RUN_LIMIT);

        System.out.println(message);
    }

    @Override
    public void swim(int meters) {
        var message = meters <= SWIM_LIMIT ?
                String.format("Собака проплыла %d м", meters) :
                String.format("Собака не может плыть больше %d м", SWIM_LIMIT);

        System.out.println(message);
    }

    public static int getDogCount() {
        return dogCounter;
    }
}
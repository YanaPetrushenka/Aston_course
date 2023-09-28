package ru.astondevs.course.entities;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<>();

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        return fruits.isEmpty() ? 0 : fruits.size() * fruits.get(0).getWeight();
    }

    public boolean compare(Box<? extends Fruit> box) {
        return this.getWeight() == box.getWeight();
    }

    public void putTo(Box<T> box) {
        fruits.forEach(box::addFruit);
        fruits.clear();
    }
}

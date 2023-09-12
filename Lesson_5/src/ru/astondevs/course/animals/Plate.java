package ru.astondevs.course.animals;

import java.util.Random;

public class Plate {
    private int foodLevel;

    public Plate(){
        foodLevel = Math.abs(new Random().nextInt(200));
    }

    public void putFood(int foodQuantity) {
        foodLevel += foodQuantity;
    }

    public int takeFood(int hungerLevel) {
        var foodReminder = foodLevel - hungerLevel;

        if (foodReminder >= 0) {
            foodLevel = foodReminder;
        }
        return foodReminder;
    }
}

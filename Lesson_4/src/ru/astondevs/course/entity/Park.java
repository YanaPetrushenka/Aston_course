package ru.astondevs.course.entity;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Park {

    private Attraction orbitAttr = new Attraction("Orbit", BigDecimal.valueOf(9),
            LocalTime.of(8, 0), LocalTime.of(20,0));
    private Attraction rollerAttr = new Attraction("Roller Coaster", BigDecimal.valueOf(10),
            LocalTime.of(10, 0), LocalTime.of(22,0));

    private class Attraction {
        private String name;
        private BigDecimal price;
        private LocalTime openingTime;
        private LocalTime closingTime;

        private Attraction(String name, BigDecimal price, LocalTime openingTime, LocalTime closingTime) {
            this.name = name;
            this.price = price;
            this.openingTime = openingTime;
            this.closingTime = closingTime;
        }
    }
}





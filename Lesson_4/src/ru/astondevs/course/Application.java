package ru.astondevs.course;

import ru.astondevs.course.entity.Employee;

import java.math.BigDecimal;
import java.util.Arrays;

public class Application {
    private static final int AGE = 40;

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Dostoevsky Fedor", "Writer", "tutu@gmail.com",
                333, BigDecimal.valueOf(600), 22);

        employees[1] = new Employee("Dali Salvador", "Artist", "dud@gmail.com",
                444, BigDecimal.valueOf(650), 84);

        employees[2] = new Employee("Tesla Nicola", "Inventor", "сосо@gmail.com",
                222, BigDecimal.valueOf(700), 33);

        employees[3] = new Employee("Nietzsche Friedrich", "Philosopher", "not@gmail.com",
                555, BigDecimal.valueOf(700), 44);

        employees[4] = new Employee("Tchaikovsky Petr", "Composer", "cha@gmail.com",
                111, BigDecimal.valueOf(750), 53);

        Application app = new Application();
        app.printOlderThan(employees, AGE);
    }

    private void printOlderThan(Employee[] employees, int age) {
        Arrays.stream(employees)
                .filter(employee -> employee.getAge() > age)
                .forEach(System.out::println);
    }
}

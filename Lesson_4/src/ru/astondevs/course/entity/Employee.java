package ru.astondevs.course.entity;

import java.math.BigDecimal;

public class Employee {
    private String Name;
    private String position;
    private String email;
    private int telephone;
    private BigDecimal salary;
    private int age;

    public Employee(
            String Name, String position, String email, int telephone, BigDecimal salary, int age) {
        this.Name = Name;
        this.position = position;
        this.email = email;
        this.telephone = telephone;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{Name:" + Name + ", " +"Position:" + position + ", " + "Email:" + email + ", " + "Tel.:" +
                telephone + ", " + "Salary:" + salary + ", " + "Age:" + age + "}";
    }

    public int getAge(){
        return age;
    }
}



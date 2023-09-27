package ru.astondevs.courses.task4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class StudentsData {

    Collection<Student> students = Arrays.asList(
            new Student("Дмитрий", 17, Gender.MAN),
            new Student("Максим", 20, Gender.MAN),
            new Student("Екатерина", 20, Gender.WOMAN),
            new Student("Михаил", 28, Gender.MAN)
    );

    public static void main(String[] args) {
        StudentsData studentsData = new StudentsData();

//        Task 4.1
        var manAverageAge = studentsData.students.stream()
                .filter(student -> Gender.MAN.equals(student.getGender()))
                .mapToInt(Student::getAge)
                .average()
                .orElse(0L);
        System.out.printf("Средний возраст студентов мужского пола: %.1f " , manAverageAge);

//        Task 4.2
        studentsData.students.stream()
                .filter(student -> Gender.MAN.equals(student.getGender()))
                .filter(student -> student.getAge() >= 18)
                .filter(student -> student.getAge() <= 27)
                .forEach(System.out::println);
    }

    private enum Gender {
        MAN,
        WOMAN
    }

    private static class Student {
        private final String name;
        private final Integer age;
        private final Gender gender;


        public Student(String name, Integer age, Gender gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Gender getGender() {
            return gender;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender=" + gender +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student student = (Student) o;
            return Objects.equals(name, student.name) &&
                    Objects.equals(age, student.age) &&
                    Objects.equals(gender, student.gender);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, gender);
        }
    }
}

package ru.astondevs.courses.task1_3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Application {
    private static final int RANDOM_NUMBERS_LIST_SIZE = 100;

    public static void main(String[] args) {
        Application app = new Application();

//        Task 1
        var evenCount = app.generateRandomInts().stream()
                .filter(number -> (number % 2) == 0)
                .count();
        System.out.println("Количество четных чисел: " + evenCount);

//        Task 2
        var words = List.of("Highload", "High", "Load", "Highload");

//        2.1
        var wordCount = words.stream()
                .filter("High"::equals)
                .count();
        System.out.println("'High' встречается " + wordCount + " раз");

//        2.2
        var firstItem = words.stream()
                .findFirst()
                .orElse("0");
        System.out.println("Первый элемент в коллекции: " + firstItem);

//        2.3
        var reversedWords = new ArrayList<>(words);
        Collections.reverse(reversedWords);
        var lastItem = reversedWords.stream()
                .findFirst()
                .orElse("0");
        System.out.println("Последний элемент в коллекции: " + lastItem);

//        Task 3
        var items = List.of("f10", "f15", "f2", "f4", "f4");
        var sortedItems = items.stream()
                .sorted()
                .toArray(String[]::new);
        System.out.println("Остортированные в алфавитном порядке элементы: " + Arrays.toString(sortedItems));
    }

    private List<Integer> generateRandomInts() {
        var random = new Random();
        return IntStream.generate(random::nextInt)
                .limit(RANDOM_NUMBERS_LIST_SIZE)
                .boxed()
                .collect(Collectors.toList());
    }
}

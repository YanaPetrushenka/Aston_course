package ru.astondevs.course;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        List<String> wordsList = new ArrayList<>();
        wordsList.add("фикус");
        wordsList.add("молочай");
        wordsList.add("калатея");
        wordsList.add("папоротник");
        wordsList.add("строманта");
        wordsList.add("кактус");
        wordsList.add("фикус");
        wordsList.add("кактус");
        wordsList.add("кактус");
        wordsList.add("кактус");

//        Print unique elements
        Set<String> worsdSet = new HashSet<>(wordsList);
        System.out.println(worsdSet);

//        Count words
        var wordCountMap = wordsList.stream()
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
        System.out.println(wordCountMap);

//        PhoneBook
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "999");
        phoneBook.add("Иванов", "444");
        phoneBook.add("Иванов", "777");
        phoneBook.add("Сидоров", "555");
        phoneBook.add("Петров", "666");
        var phoneNumbers1 = phoneBook.get("Иванов");
        var phoneNumbers2 = phoneBook.get("Сидоров");
        System.out.println(phoneNumbers1);
        System.out.println(phoneNumbers2);
    }

}

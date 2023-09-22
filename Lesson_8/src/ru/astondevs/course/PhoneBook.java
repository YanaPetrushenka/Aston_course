package ru.astondevs.course;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> phoneBookMap;

    public PhoneBook() {
        phoneBookMap = new HashMap<>();
    }

    public void add(String name, String phoneNumber) {
        if (!phoneBookMap.containsKey(name)) {
            phoneBookMap.put(name, new HashSet<>());
        }
        phoneBookMap.get(name).add(phoneNumber);
    }

    public Set<String> get(String name) {
        var phonesNumbers = phoneBookMap.get(name);
        return phonesNumbers;
    }
}


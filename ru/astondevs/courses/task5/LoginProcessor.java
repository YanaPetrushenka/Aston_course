package ru.astondevs.courses.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginProcessor {

    public static void main(String[] args) {
        List<String> logins = new ArrayList<>();
        var scanner = new Scanner(System.in);

        while(true){
            var login = scanner.nextLine();

            if ("".equals(login)){
                break;
            }

            logins.add(login);
        }

        logins.stream()
                .filter(login -> login.startsWith("f"))
                .forEach(System.out::println);
    }
}

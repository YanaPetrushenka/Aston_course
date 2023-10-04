package org.aston;

public class Main {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        var f = factorial.calculateFactorial(3);
        System.out.println(f);
    }
}
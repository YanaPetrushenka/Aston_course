package org.aston;

public class Factorial {
    public long calculateFactorial(long number) {
        if (number < 0 || number > 20) {
            throw new IllegalArgumentException("Valid number range is from 0 to 20");
        }
        if (number == 0) {
            return 1;
        }
        return number * calculateFactorial(number - 1);
    }
}
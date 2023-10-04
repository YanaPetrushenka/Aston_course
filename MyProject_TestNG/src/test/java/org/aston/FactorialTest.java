package org.aston;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialTest {

    private Factorial factorial;

    @BeforeMethod
    void init() {
        factorial = new Factorial();
    }

    @Test
    void shouldCalculateFactorialOfZero() {
        assertEquals(factorial.calculateFactorial(0), 1);
    }

    @Test
    void shouldCalculateFactorialOfTen() {
        assertEquals(factorial.calculateFactorial(10), 3628800);
    }

    @Test
    void shouldCalculateFactorialOfTwenty() {
        assertEquals(factorial.calculateFactorial(20), 2432902008176640000L);
    }

    @Test
    void shouldThrowExceptionWhenNegativeArgument() {
        assertThrows(IllegalArgumentException.class, () -> factorial.calculateFactorial(-1));
    }

    @Test
    void shouldThrowExceptionWhenArgumentGreaterThanTwenty() {
        assertThrows(IllegalArgumentException.class, () -> factorial.calculateFactorial(21));
    }
}
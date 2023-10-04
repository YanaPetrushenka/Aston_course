package org.aston;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    private Factorial factorial;

    @BeforeEach
    void init() {
        factorial = new Factorial();
    }

    @Test
    void shouldCalculateFactorialOfZero() {
        assertEquals(1, factorial.calculateFactorial(0));
    }

    @Test
    void shouldCalculateFactorialOfTen() {
        assertEquals(3628800, factorial.calculateFactorial(10));
    }

    @Test
    void shouldCalculateFactorialOfTwenty() {
        assertEquals(2432902008176640000L, factorial.calculateFactorial(20));
    }

    @Test
    void shouldThrowExceptionWhenNegativeArgument() {
        var exception = assertThrows(IllegalArgumentException.class, () -> factorial.calculateFactorial(-1));
        assertEquals("Valid number range is from 0 to 20", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenArgumentGreaterThanTwenty() {
        var exception = assertThrows(IllegalArgumentException.class, () -> factorial.calculateFactorial(21));
        assertEquals("Valid number range is from 0 to 20", exception.getMessage());
    }
}
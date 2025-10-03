package com.mello.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CalculatorServiceTest")
class CalculatorTest {

    @Test
    @DisplayName("Should correctly add two numbers")
    void shouldAddTwoNumbers() {
        int a = 1;
        int b = 2;

        int result = CalculatorService.add(a, b);

        Assertions.assertEquals(a + b, result);
    }

    @Test
    @DisplayName("Should correctly subtract two numbers")
    void  shouldSubtractTwoNumbers() {
        int a = 1;
        int b = 2;

        int result = CalculatorService.sub(a, b);

        Assertions.assertEquals(a - b, result);
    }

    @Test
    @DisplayName("Should correctly multiply two numbers")
    void shouldMultiplyTwoNumbers() {
        int a = 6;
        int b = 7;

        int result = CalculatorService.mul(a, b);

        Assertions.assertEquals(42, result, "The result of 6 * 7 should be 42");
    }

    @Test
    @DisplayName("Should correctly divide two numbers")
    void shouldDivideTwoNumbers() {
        int a = 20;
        int b = 4;

        int result = CalculatorService.div(a, b);

        Assertions.assertEquals(5, result, "The result of 20 / 4 should be 5");
    }

    @Test
    @DisplayName("Should throw exception when dividing by zero")
    void shouldThrowExceptionWhenDivideByZero() {
        int a = 3;
        int b = 0;

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> CalculatorService.div(a, b));

        Assertions.assertEquals("Division by zero is not allowed", exception.getMessage());
    }
}

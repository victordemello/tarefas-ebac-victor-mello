package com.mello.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FibonacciCalculatorTest")
class FibonacciCalculatorTest {
    private FibonacciCalculatorService fibonacciCalculatorService;

    @BeforeEach
    void setUp() {
        this.fibonacciCalculatorService = new FibonacciCalculatorService();
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when input is negative instead of recursing")
    void shouldValidateIfNumberIsNegative(){
        int number = -10;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> fibonacciCalculatorService.fibonacci(number));

        Assertions.assertEquals("number must be non-negative", exception.getMessage());
    }

    @Test
    @DisplayName("Should return 0 when input is 0")
    void shouldReturnZero_whenInputIsZero() {
        FibonacciCalculatorService calculator = new FibonacciCalculatorService();
        long result = calculator.fibonacci(0);
        Assertions.assertEquals(0, result, "Expected Fibonacci(0) to return 0");
    }

    @Test
    @DisplayName("Should return 1 when input is 1")
    void shouldReturnOne_whenInputIsOne() {
        FibonacciCalculatorService calculator = new FibonacciCalculatorService();
        long result = calculator.fibonacci(1);
        Assertions.assertEquals(1, result, "Expected Fibonacci(1) to return 1");
    }

    @Test
    @DisplayName("Should memoize computed value after first calculation")
    void shouldMemoizeValue_whenFibonacciCalledFirstTime() {
        int n = 10;
        long result = fibonacciCalculatorService.fibonacci(n);

        // Verifica comportamento correto
        Assertions.assertEquals(result, fibonacciCalculatorService.fibonacci(n),
                "Result of fibonacci(n) should remain consistent");

        // Verifica se o valor foi armazenado no memo
        Assertions.assertTrue(fibonacciCalculatorService.isMemoized(n),
                "Expected value for n to be memoized");

        Long cached = fibonacciCalculatorService.getMemoizedValue(n);
        Assertions.assertNotNull(cached, "Memoized value should not be null");
        Assertions.assertEquals(result, cached.longValue(),
                "Memoized value should match computed result");
    }

    @Test
    @DisplayName("Should compute and cache value when not already memoized")
    void shouldComputeAndCacheValue_whenNotMemoized() {
        int n = 6;
        // Antes de chamar, deverá **não** estar memoizado
        Assertions.assertFalse(fibonacciCalculatorService.isMemoized(n),
                "Value n should not be memoized before first invocation");

        // Chama o método para computar
        long computed = fibonacciCalculatorService.fibonacci(n);

        // Agora deve estar no cache
        Assertions.assertTrue(fibonacciCalculatorService.isMemoized(n), "Value n should be memoized after invocation");

        // Verifica que o valor no cache é igual ao resultado retornado
        Long cached = fibonacciCalculatorService.getMemoizedValue(n);
        Assertions.assertNotNull(cached, "Cached value should not be null");
        Assertions.assertEquals(computed, cached.longValue(),
                "Cached value should match computed result");
    }

    @Test
    @DisplayName("Should return correct Fibonacci value for a given n")
    void shouldReturnCorrectFibonacciValue_forGivenN() {
        int n = 10;
        long expected = 55;  // os primeiros termos de Fibonacci são: 0,1,1,2,3,5,8,13,21,34,55,...

        long actual = fibonacciCalculatorService.fibonacci(n);

        Assertions.assertEquals(expected, actual,
                String.format("Expected Fibonacci(%d) to be %d, but was %d", n, expected, actual));
    }
}

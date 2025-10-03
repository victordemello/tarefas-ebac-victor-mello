package com.mello.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Serviço responsável pelo cálculo da sequência de Fibonacci, utilizando
 * técnica de memoização para otimizar desempenho e evitar recomputações.
 * <p>
 * Essa classe armazena resultados já calculados internamente para acelerar chamadas subsequentes.
 * </p>
 *
 * @author Victor
 * @since 1.0
 */
public class FibonacciCalculatorService {

    /**
     * Cache interno (memoização) para armazenar resultados já computados de Fibonacci.
     * A chave é o valor de {@code n} e o valor é o resultado {@code fibonacci(n)}.
     */
    private final Map<Integer, Long> memo = new HashMap<>();

    /**
     * Calcula o valor de Fibonacci para o índice {@code n}, aplicando recursão com memoização.
     * <p>
     * Faz validação de que {@code n} não seja negativo, trata os casos base (0 e 1),
     * verifica se já existe valor no cache e, se não existir, calcula recursivamente
     * e armazena o resultado no cache.
     * </p>
     *
     * @param n o índice da sequência de Fibonacci (não negativo)
     * @return o valor de {@code Fibonacci(n)}
     * @throws IllegalArgumentException se {@code n} for negativo
     */
    public long fibonacci(int n) {
        validateNonNegative(n);

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // Verifica se o valor já foi computado e está em cache
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Computa recursivamente e armazena no cache
        long result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result);

        return result;
    }

    /**
     * Verifica se o valor para {@code n} já está armazenado no cache (memoizado).
     * Este método é útil principalmente para fins de teste ou inspeção.
     *
     * @param n o índice que se deseja verificar no cache
     * @return {@code true} se {@code fibonacci(n)} já estiver no cache, {@code false} caso contrário
     */
    boolean isMemoized(int n) {
        return memo.containsKey(n);
    }

    /**
     * Retorna o valor memoizado para {@code n}, se existir no cache.
     * Caso não exista, retorna {@code null}.
     * Este método é útil para fins de teste ou inspeção interna.
     *
     * @param n o índice cujo valor memoizado se deseja obter
     * @return o valor memoizado de {@code fibonacci(n)}, ou {@code null} se não estiver em cache
     */
    Long getMemoizedValue(int n) {
        return memo.get(n);
    }

    /**
     * Valida se o número fornecido é não negativo. Se negativo, lança exceção.
     *
     * @param number o valor que será validado
     * @throws IllegalArgumentException se {@code number} for negativo
     */
    private void validateNonNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number must be non-negative");
        }
    }

    /**
     * Método principal para execução independente.
     * Demonstra o uso do serviço imprimindo o resultado de Fibonacci para um termo fixo.
     *
     * @param args argumentos de linha de comando (não usados)
     */
    public static void main(String[] args) {
        FibonacciCalculatorService calculator = new FibonacciCalculatorService();

        int termo = 3; // Termo desejado da sequência
        long resultado = calculator.fibonacci(termo);
        System.out.printf("Fibonacci(%d) = %d%n", termo, resultado);
    }
}

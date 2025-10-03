package com.mello.entities;

/**
 * Serviço utilitário que oferece operações aritméticas básicas
 * (soma, subtração, multiplicação e divisão) sobre dois números inteiros.
 * <p>
 * Todos os métodos são estáticos e esta classe não deve ser instanciada.
 * </p>
 *
 * @author Victor Mello
 * @since 1.0
 */
public class CalculatorService {

    /**
     * Soma dois inteiros.
     *
     * @param a o primeiro operando
     * @param b o segundo operando
     * @return a soma de {@code a} e {@code b}
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtrai um inteiro de outro.
     *
     * @param a o minuendo (valor de onde será subtraído)
     * @param b o subtraendo (valor que será subtraído)
     * @return o resultado de {@code a} menos {@code b}
     */
    public static int sub(int a, int b) {
        return a - b;
    }

    /**
     * Multiplica dois inteiros.
     *
     * @param a o primeiro fator
     * @param b o segundo fator
     * @return o produto de {@code a} por {@code b}
     */
    public static int mul(int a, int b) {
        return a * b;
    }

    /**
     * Divide um inteiro por outro.
     *
     * @param a o dividendo
     * @param b o divisor
     * @return o quociente inteiro de {@code a} dividido por {@code b}
     * @throws ArithmeticException se {@code b} for zero (divisão por zero)
     */
    public static int div(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    /**
     * Construtor privado para evitar instanciação da classe utilitária.
     */
    private CalculatorService() {
        throw new UnsupportedOperationException("Classe utilitária — não instanciar");
    }
}

package com.mello.mod04;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Exercicio1 {
    /**
     * Exercício 1 - Resolva o problema usando backtracking.
     *
     * Dado um conjunto S de elementos únicos, calcule todos os seus subconjuntos de n elementos.
     *
     * Exemplos:
     *
     * Entrada: S = [1, 2, 3], n = 2
     * Saída: [1, 2], [1, 3], [2, 3]
     *
     * Entrada: S= [1, 2, 3, 4], n = 1
     * Saída: [1], [2], [3] e [4]
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Quantos elementos deseja digitar?");
        int amount = sc.nextInt();
        int n;

        do {
            System.out.println("Digite o tamanho do subconjunto (não pode ser maior que " + amount + "): ");
            n = sc.nextInt();
        }
        while (n > amount);

        int[] elements = new int[amount];

        for (int i = 0; i < elements.length; i++) {
            System.out.printf("Digite o valor do elemento %d: ", i + 1);
            elements[i] = sc.nextInt();
        }

        System.out.println("\nSubconjuntos de tamanho " + n + ":");
        generateCombinations(elements, n);
        sc.close();
    }

    public static void generateCombinations(int[] elements, int n) {
        List<Integer> current = new ArrayList<>();
        backtrack(elements, n, 0, current);
    }

    private static void backtrack(int[] elements, int n, int start, List<Integer> current) {
        // Caso base: já montamos um subconjunto de tamanho n
        if (current.size() == n) {
            System.out.println(current);
            return;
        }

        for (int i = start; i < elements.length; i++) {
            // Escolhe elemento
            current.add(elements[i]);

            // Continua explorando a partir do próximo índice
            backtrack(elements, n, i + 1, current);

            // Desfaz a escolha (backtracking)
            current.remove(current.size() - 1);
        }
    }
}


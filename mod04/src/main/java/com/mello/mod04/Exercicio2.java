package com.mello.mod04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Quantia a devolver
        System.out.print("Digite a quantia: ");
        int valor = sc.nextInt();

        // Moedas disponíveis
        System.out.print("Digite quantos tipos de moedas existem: ");
        int qtdMoedas = sc.nextInt();

        int[] moedas = new int[qtdMoedas];
        for (int i = 0; i < qtdMoedas; i++) {
            System.out.print("Digite o valor da moeda " + (i + 1) + ": ");
            moedas[i] = sc.nextInt();
        }

        // Ordena as moedas em ordem decrescente (para o guloso funcionar)
        Arrays.sort(moedas);
        for (int i = 0, j = moedas.length - 1; i < j; i++, j--) {
            int temp = moedas[i];
            moedas[i] = moedas[j];
            moedas[j] = temp;
        }

        // Algoritmo guloso
        List<Integer> resultado = new ArrayList<>();
        int restante = valor;

        for (int moeda : moedas) {
            while (restante >= moeda) {
                restante -= moeda;
                resultado.add(moeda);
            }
        }

        System.out.println("\nTroco para " + valor + ": " + resultado);
        System.out.println("Número de moedas: " + resultado.size());

        sc.close();
    }
}

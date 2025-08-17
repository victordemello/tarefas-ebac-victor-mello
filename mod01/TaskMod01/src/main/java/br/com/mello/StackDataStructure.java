package br.com.mello;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackDataStructure {

    private int[] values;
    private int count = 0;

    public void push(int value) {
        if (count > 0){
            int[] valuesCopy = values;
            count++;
            this.values = new int[count];
            this.values[0] = value;

            for (int i = 0; i < valuesCopy.length; i++){
                this.values[i + 1] = valuesCopy[i];
            }

        } else {
            this.values = new int[1];
            this.values[0] = value;
            count++;
        }
    }

    public int pop() {
        if (count == 0) {
            throw new EmptyStackException();
        }

        int[] valuesCopy = values;
        int poppedValue = values[0]; // Armazena o valor que será "removido"
        count--;

        this.values = new int[count];
        for (int i = 0; i < count; i++) {
            this.values[i] = valuesCopy[i + 1];
        }

        return poppedValue;
    }

    public int top(){
        return values[0];
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public int size(){
        return this.count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("== TOP ==\n");

        for (int i = count - 1; i >= 0; i--) {
            sb.append("|  ").append(values[i]).append("  |\n");
        }

        sb.append("== BASE ==\n");

        return sb.toString();
    }
}

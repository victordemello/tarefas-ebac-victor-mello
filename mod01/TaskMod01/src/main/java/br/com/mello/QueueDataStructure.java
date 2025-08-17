package br.com.mello;

import java.util.NoSuchElementException;

public class QueueDataStructure {
    private int[] values;
    private int count = 0;

    public void enqueue(int value) {
        if (count > 0){
            int[] valuesCopy = values;
            count++;
            this.values = new int[count];
            this.values[count - 1] = value;

            for (int i = 0; i < valuesCopy.length; i++){
                this.values[i] = valuesCopy[i];
            }

        } else {
            this.values = new int[1];
            this.values[0] = value;
            count++;
        }
    }

    public void dequeue() {
        if (count == 0) {
            throw new NoSuchElementException();
        }

        int[] valuesCopy = values;
        count--;

        this.values = new int[count];
        for (int i = 0; i < count; i++) {
            this.values[i] = valuesCopy[i + 1];
        }
    }

    public int rear(){
        return this.values[count - 1];
    }

    public int front(){
        return this.values[0];
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    @Override
    public String toString() {
        if (count == 0 || values == null) {
            return "Queue is empty.";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Queue front -> [");

        for (int i = 0; i < count; i++) {
            builder.append(values[i]);
            if (i < count - 1) {
                builder.append(" | ");
            }
        }

        builder.append("] <- rear");
        return builder.toString();
    }

}

package br.com.mello;

public class ListDataStructure {
    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Node head;
    private int count = 0;

    // Adds a node to the end of the list
    public void push(Node node) {
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        count++;
    }

    // Removes the last node and returns it
    public Node pop() {
        if (head == null) return null;

        if (head.next == null) {
            Node temp = head;
            head = null;
            count--;
            return temp;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        Node removed = current.next;
        current.next = null;
        count--;
        return removed;
    }

    // Inserts a node at a given index
    public void insert(int index, Node node) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            node.next = head;
            head = node;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }

        count++;
    }

    // Removes a node at a given index
    public void remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
        }

        count--;
    }

    // Returns the node at a given index
    public Node elementAt(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    // Returns the size of the list
    public int size() {
        return count;
    }

    // Prints a string representation of the list
    public void printList() {
        Node current = head;
        StringBuilder result = new StringBuilder();

        while (current != null) {
            result.append(current.value).append(" -> ");
            current = current.next;
        }

        result.append("null");
        System.out.println(result.toString());
    }
}



package br.com.mello;

public class Main {
    public static void main(String[] args) {

        StackDataStructure data = new StackDataStructure();
        data.push(5);
        data.push(6);
        data.push(7);
        data.push(8);

        int element = data.pop();

        System.out.println(data.toString());
        System.out.printf("O número %d foi removido...\n", element);
        System.out.printf("O elemento no topo da pilha é o: %d \n", data.top());
        System.out.println("A pilha está vazia? " + data.isEmpty());
        System.out.println("Total de elementos na pilha = " + data.size());

        QueueDataStructure data2 = new QueueDataStructure();
        data2.enqueue(2);
        data2.enqueue(5);
        data2.enqueue(10);
        data2.enqueue(8);

        data2.dequeue();

        System.out.println(data2.toString());

        System.out.println(data2.front());
        System.out.println(data2.rear());
        System.out.println(data2.size());
        System.out.println(data2.isEmpty());

        ListDataStructure data3 = new ListDataStructure();

        data3.push(new ListDataStructure.Node(10));
        data3.push(new ListDataStructure.Node(8));

        data3.printList();

    }
}
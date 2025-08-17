package br.com.mello;

import java.util.NoSuchElementException;

public class HashMapDataStructure {

    // Classe interna para representar um par chave-valor
    private static class Entry {
        int key;
        int value;
        Entry next; // Para tratamento de colisões usando encadeamento

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "{" + key + "=" + value + "}";
        }
    }

    private static final int CAPACITY = 10; // Tamanho fixo conforme especificação
    private Entry[] buckets; // Array de buckets para armazenar as entradas
    private int size; // Número atual de elementos no mapa

    public HashMapDataStructure() {
        this.buckets = new Entry[CAPACITY];
        this.size = 0;
    }

    /**
     * Função hash simples usando módulo
     * Escolhi esta função porque:
     * 1. É eficiente - O(1)
     * 2. Garante que o índice esteja sempre dentro do range [0, CAPACITY-1]
     * 3. Para números inteiros pequenos, oferece distribuição razoável
     * 4. É fácil de entender e implementar
     *
     * Limitações: Pode gerar muitas colisões se as chaves seguirem padrões
     * específicos (ex: múltiplos de 10 em um array de tamanho 10)
     */
    private int hash(int key) {
        // Usa Math.abs para garantir que chaves negativas também funcionem
        return Math.abs(key) % CAPACITY;
    }

    /**
     * Adiciona o par chave/valor ao mapa
     * Se a chave já existe, atualiza o valor
     */
    public void put(int key, int value) {
        int index = hash(key);
        Entry current = buckets[index];

        // Verifica se a chave já existe no bucket
        while (current != null) {
            if (current.key == key) {
                // Chave encontrada, atualiza o valor
                current.value = value;
                return;
            }
            current = current.next;
        }

        // Chave não existe, cria nova entrada no início da lista ligada
        Entry newEntry = new Entry(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }

    /**
     * Retorna o valor associado à chave passada via parâmetro
     * Lança exceção se a chave não for encontrada
     */
    public int get(int key) {
        int index = hash(key);
        Entry current = buckets[index];

        // Percorre a lista ligada do bucket procurando a chave
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }

        // Chave não encontrada
        throw new NoSuchElementException("Chave " + key + " não encontrada no mapa");
    }

    /**
     * Remove o par chave/valor associado à chave
     * Retorna true se removeu, false se a chave não existia
     */
    public boolean delete(int key) {
        int index = hash(key);
        Entry current = buckets[index];
        Entry previous = null;

        // Percorre a lista ligada procurando a chave
        while (current != null) {
            if (current.key == key) {
                // Chave encontrada, remove da lista
                if (previous == null) {
                    // É o primeiro elemento do bucket
                    buckets[index] = current.next;
                } else {
                    // Remove do meio ou fim da lista
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        // Chave não encontrada
        return false;
    }

    /**
     * Remove todos os elementos do mapa
     */
    public void clear() {
        for (int i = 0; i < CAPACITY; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    /**
     * Retorna o número de elementos no mapa
     */
    public int size() {
        return size;
    }

    /**
     * Verifica se o mapa está vazio
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Verifica se uma chave existe no mapa
     */
    public boolean containsKey(int key) {
        try {
            get(key);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Retorna uma representação em string do mapa para debug
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HashMap {size=").append(size).append("}\n");

        for (int i = 0; i < CAPACITY; i++) {
            sb.append("Bucket[").append(i).append("]: ");
            Entry current = buckets[i];

            if (current == null) {
                sb.append("null");
            } else {
                while (current != null) {
                    sb.append(current);
                    if (current.next != null) {
                        sb.append(" -> ");
                    }
                    current = current.next;
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Método para análise de distribuição das chaves (útil para debug)
     */
    public void printDistribution() {
        System.out.println("Distribuição das chaves nos buckets:");
        for (int i = 0; i < CAPACITY; i++) {
            int count = 0;
            Entry current = buckets[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elemento(s)");
        }
        System.out.println("Fator de carga: " + (double) size / CAPACITY);
    }
}
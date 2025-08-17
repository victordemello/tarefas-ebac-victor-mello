# Análise de Complexidade Assintótica das Estruturas de Dados

## 1. Pilha (StackDataStructure)

### Complexidade de Tempo:

**push(int value): O(n)**
- Justificativa: A cada inserção, você cria um novo array com tamanho `count + 1` e copia todos os elementos existentes para o novo array. Isso resulta em O(n) operações de cópia, onde n é o número atual de elementos na pilha.

**pop(): O(n)**
- Justificativa: Similar ao push, você cria um novo array menor e copia todos os elementos restantes (exceto o primeiro). Isso também resulta em O(n) operações.

### Complexidade de Espaço:
- **Espaço auxiliar por operação**: O(n) - devido à criação de arrays temporários durante push/pop
- **Espaço total da estrutura**: O(n) - onde n é o número de elementos armazenados

---

## 2. Fila (QueueDataStructure)

### Complexidade de Tempo:

**enqueue(int value): O(n)**
- Justificativa: Cria um novo array maior e copia todos os elementos existentes, adicionando o novo elemento no final. Requer n operações de cópia.

**dequeue(): O(n)**
- Justificativa: Cria um novo array menor e copia todos os elementos (exceto o primeiro), deslocando-os uma posição para a esquerda. Requer n-1 operações de cópia.

**rear(): O(1)**
- Justificativa: Acesso direto ao último elemento através do índice `count - 1`.

**front(): O(1)**
- Justificativa: Acesso direto ao primeiro elemento através do índice 0.

### Complexidade de Espaço:
- **Espaço auxiliar por operação**: O(n) - devido à criação de arrays temporários
- **Espaço total da estrutura**: O(n) - onde n é o número de elementos armazenados

---

## 3. Lista Encadeada (ListDataStructure)

### Complexidade de Tempo:

**push(Node node): O(n)**
- Justificativa: Precisa percorrer toda a lista até encontrar o último nó para adicionar o novo elemento no final. No pior caso, percorre n elementos.

**pop(): O(n)**
- Justificativa: Precisa percorrer até o penúltimo nó para remover o último elemento, resultando em n-1 traversals no pior caso.

**insert(int index, Node node): O(n)**
- Justificativa: No pior caso (inserção no final), precisa percorrer até a posição index, que pode ser O(n).

**remove(int index): O(n)**
- Justificativa: Similar ao insert, precisa percorrer até a posição index-1 para fazer a remoção.

**elementAt(int index): O(n)**
- Justificativa: Percorre a lista desde o início até a posição desejada, resultando em até n traversals.

### Complexidade de Espaço:
- **Espaço auxiliar por operação**: O(1) - operações usam apenas algumas variáveis temporárias
- **Espaço total da estrutura**: O(n) - cada elemento requer um nó com valor e ponteiro

---

## Resumo das Complexidades

| Estrutura | Operação | Tempo | Espaço Auxiliar |
|-----------|----------|-------|-----------------|
| **Pilha** | push() | O(n) | O(n) |
| **Pilha** | pop() | O(n) | O(n) |
| **Fila** | enqueue() | O(n) | O(n) |
| **Fila** | dequeue() | O(n) | O(n) |
| **Fila** | rear() | O(1) | O(1) |
| **Fila** | front() | O(1) | O(1) |
| **Lista** | push() | O(n) | O(1) |
| **Lista** | pop() | O(n) | O(1) |
| **Lista** | insert() | O(n) | O(1) |
| **Lista** | remove() | O(n) | O(1) |
| **Lista** | elementAt() | O(n) | O(1) |

**Espaço total de todas as estruturas**: O(n) onde n é o número de elementos armazenados.

# Desafio HashMap

## Justificativa da Função Hash Escolhida

**Função Hash Utilizada**: `Math.abs(key) % CAPACITY`

## Por que escolhi esta função:

1. **Simplicidade e Eficiência**:
    * Operação O(1) constante
    * Fácil de entender e implementar
    * Computacionalmente barata

2. **Garantia de Range**:
    * Sempre retorna valores entre 0 e CAPACITY-1 (0 a 9)
    * O `Math.abs()` garante que chaves negativas também funcionem corretamente

3. **Adequada para o Contexto**:
    * Para um mapa de tamanho fixo pequeno (10 elementos)
    * Funciona bem para chaves inteiras aleatórias

## Limitações da Função Hash:

1. **Padrões de Colisão**:
    * Chaves que são múltiplas de 10 vão colidir (10, 20, 30, etc.)
    * Chaves com mesmo resto na divisão por 10 colidem

2. **Distribuição**:
    * Pode não ser ideal se as chaves seguirem padrões específicos
    * Para dados reais, uma função hash mais sofisticada seria melhor

## Tratamento de Colisões:

Implementei **encadeamento separado** (separate chaining) usando listas ligadas:
* Cada bucket é uma lista ligada de entradas
* Colisões são resolvidas adicionando elementos na mesma posição
* Busca, inserção e remoção percorrem a lista do bucket correspondente

## Complexidades da Implementação:

* **put()**: O(1) médio, O(n) pior caso (muitas colisões no mesmo bucket)
* **get()**: O(1) médio, O(n) pior caso
* **delete()**: O(1) médio, O(n) pior caso
* **clear()**: O(1)
* **Espaço**: O(n) onde n é o número de elementos inseridos
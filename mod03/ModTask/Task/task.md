# Exercicio 1

## Problema: crie um código que calcule o fatorial de um número

Exemplos:

Entrada: 3
Saída: 6

Entrada: 7
Saída: 5040

Consegue calcular o fatorial de um número acima de 100 com uma solução recursiva? Justifique sua resposta.

### RESPOSTA:

A princípio o algoritmo foi executado resultando em 101! = 0 
Isso ocorreu devido ao overflow causado no long, ou seja, o retorno
foi um número tão grande, que não é suportado pelo tipo primitivo long

Uma alternativa a esse problema seria usar o BigInteger do Java.
Seguindo essa estratégia obtive o seguinte resultado:
- 101! = 9425947759838359420851623124482936749562312794702543768327889353416977599316221476503087861591808346911623490003549599583369706302603264000000000000000000000000

# Exercício 2

Problema: resolva o exercício 2 com programação dinâmica. Crie um algoritmo com abordagem top down e outro com bottom up e explique as diferenças entre a solução do exercício 2 e a do exercício 3.

Exemplos:

Entrada: 3
Saída: 6

Entrada: 7
Saída: 5040

Consegue calcular o fatorial de um número acima de 100 com uma solução recursiva? Justifique sua resposta.

Resposta: Sim foi possível devido ao uso do BigInteger

# Exercício 3

Problema: calcule a complexidade de tempo das soluções da série de Fibonacci apresentadas no módulo e compare as duas.

Por que a solução feita com programação dinâmica é melhor?

### Resposta

SEM programação dinâmica
- Complexidade de Tempo: O(2ⁿ) - EXPONENCIAL 💥
- Complexidade de Espaço: O(n) - profundidade da pilha

COM programação dinâmica
Complexidade de Tempo: O(n) - LINEAR
Complexidade de Espaço: O(n) - array de cache

Justificativa: A ação de reacalcular elementos anteriores
faz com que o algoritmo sem a programação dinâmica tenha um
desempenho muito pior comparado ao algoritmo que implementa a
programação dinâmica.

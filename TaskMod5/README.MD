# 📐 Calculadora e Fibonacci com Testes Unitários

Este projeto em Java implementa duas funcionalidades principais com foco em **boas práticas de codificação** e **testes automatizados** utilizando **JUnit 5**:

- Uma **calculadora aritmética** com operações básicas (`+`, `-`, `*`, `/`);
- Um serviço para cálculo da **sequência de Fibonacci com memoização**, otimizando desempenho.

O projeto faz parte de exercícios práticos de testes unitários e documentação de código.

---

## 📁 Estrutura do Projeto

```
com.mello.entities
├── CalculatorService.java            # Classe utilitária com operações aritméticas
├── CalculatorTest.java              # Testes unitários para a calculadora
├── FibonacciCalculatorService.java  # Fibonacci com memoização
└── FibonacciCalculatorTest.java     # Testes unitários do Fibonacci
```

---

## 📌 Exercícios

### ✔️ Exercício 1 – Testes Unitários (Calculadora)

Implementação da classe `CalculatorService`, com os métodos:

```java
public static int add(int a, int b);
public static int sub(int a, int b);
public static int mul(int a, int b);
public static int div(int a, int b);
```

Todos os métodos foram devidamente testados em `CalculatorTest.java`, com atenção especial ao método de divisão para garantir tratamento de divisões por zero (`ArithmeticException`).

---

### ✔️ Exercício 2 – Testes com Fibonacci Recursivo

A classe `FibonacciCalculatorService` implementa a sequência de Fibonacci com **memoização** (armazenamento em cache dos resultados já calculados). Isso evita recomputações e melhora o desempenho em chamadas subsequentes.

A classe de testes cobre:

- Casos base (`Fibonacci(0)` e `Fibonacci(1)`)
- Verificação de exceção para entradas negativas
- Memoização de valores
- Retorno correto para valores intermediários como `Fibonacci(10) == 55`

---

### ✔️ Exercício 3 – Documentação

Ambas as classes (`CalculatorService` e `FibonacciCalculatorService`) estão **completamente documentadas com Javadoc**:

- Explicações detalhadas dos métodos
- Parâmetros e exceções
- Observações sobre uso e comportamento esperado

**Pergunta**: *É possível entender o código sem documentação?*

> Sim, em parte. O código é simples e segue boas práticas como nomes claros de métodos e estrutura limpa. No entanto, a documentação melhora a **manutenibilidade**, ajuda novos desenvolvedores a entenderem o comportamento esperado e detalha exceções e decisões de implementação, como o uso de cache no Fibonacci.

---

## 🧪 Executando os Testes

Este projeto utiliza **JUnit 5**. Para executar os testes:

### Usando Maven:

```bash
mvn test
```

Ou diretamente pela sua IDE (IntelliJ, Eclipse, VS Code etc).

---

## 🛠️ Tecnologias e Conceitos Utilizados

- Java 17+ (recomendado)
- JUnit 5
- Testes unitários com `@Test`, `@BeforeEach`, `Assertions`
- Tratamento de exceções
- Design orientado a objetos
- Javadoc
- Memoização (Caching)

---

## 📃 Licença

Este projeto foi desenvolvido como parte de estudos. Sinta-se livre para utilizar como base para seus próprios projetos ou estudos.

---

## 👨‍💻 Autor

Victor Mello  
[LinkedIn](https://www.linkedin.com/) | [GitHub](https://github.com/)

# Programação Funcional em Java

![Java](https://img.shields.io/badge/Java-21-blue) ![Functional](https://img.shields.io/badge/Estilo-Funcional-green)

A Programação Funcional (PF) em Java ganhou força a partir do Java 8 com a introdução de **Lambda Expressions**, **interfaces funcionais**, **Stream API** e **Optional**. Ela permite escrever código mais **declarativo**, **modular** e **expressivo**, favorecendo a composição e a imutabilidade.

---

## 📚 Sumário

- [Conceitos Fundamentais](#conceitos-fundamentais)
- [Interfaces Funcionais](#interfaces-funcionais)
- [Lambda Expressions](#lambda-expressions)
- [Optional](#optional)
- [Composição de Funções](#composição-de-funções)
- [Uso com Stream API](#uso-com-stream-api)
- [Exercícios Práticos](#exercícios-práticos)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Fundamentais

| Conceito                  | Descrição                                                |
| ------------------------- | -------------------------------------------------------- |
| **Imutabilidade**         | Dados não mudam após serem criados.                      |
| **Funções Puras**         | Mesmo input → mesmo output, sem efeitos colaterais.      |
| **First-class functions** | Funções podem ser passadas como parâmetros e retornadas. |
| **Composição**            | Combinar funções simples em operações maiores.           |
| **Lazy evaluation**       | Avaliação sob demanda (como em streams).                 |

---

## Interfaces Funcionais

| Interface           | Método Único          | Descrição                           |
| ------------------- | --------------------- | ----------------------------------- |
| `Function<T,R>`     | `R apply(T t)`        | Transforma um valor em outro.       |
| `Predicate<T>`      | `boolean test(T t)`   | Avaliação booleana.                 |
| `Consumer<T>`       | `void accept(T t)`    | Executa ação com efeito colateral.  |
| `Supplier<T>`       | `T get()`             | Fornece valor sem entrada.          |
| `UnaryOperator<T>`  | `T apply(T t)`        | Como Function, entrada = saída.     |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | Combina dois valores do mesmo tipo. |

---

## Lambda Expressions

```java
// Forma tradicional
Function<String, Integer> tamanho = new Function<>() {
    public Integer apply(String s) {
        return s.length();
    }
};

// Lambda
Function<String, Integer> tamanho2 = s -> s.length();
Predicate<Integer> par = x -> x % 2 == 0;
Consumer<String> print = System.out::println;
Supplier<Double> aleatorio = () -> Math.random();
```

---

## Optional

Evita `NullPointerException` com estilo funcional:

```java
Optional<String> nome = Optional.of("João");

nome
  .map(String::toUpperCase)
  .filter(n -> n.startsWith("J"))
  .ifPresent(System.out::println);
```

---

## Composição de Funções

```java
Function<Integer, Integer> dobro = x -> x * 2;
Function<Integer, Integer> soma3 = x -> x + 3;

Function<Integer, Integer> composicao = dobro.compose(soma3); // (x + 3) * 2
Function<Integer, Integer> encadeamento = dobro.andThen(soma3); // (x * 2) + 3
```

---

## Uso com Stream API

```java
List<String> nomes = List.of("Ana", "Bruno", "Carlos");

nomes.stream()
  .filter(n -> n.length() > 4)
  .map(String::toUpperCase)
  .forEach(System.out::println);
```

---

## Exercícios Práticos

| Arquivo                                         | Descrição                               |
| ----------------------------------------------- | --------------------------------------- |
| [`LabFunctionalToDo`](./LabFunctionalToDo.java) | Classe com exercícios a implementar     |
| [`LabFunctionalDone`](./LabFunctionalDone.java) | Classe com soluções completas           |
| [`exercise.en-pt.md`](./exercise.en-pt.md)      | Lista de exercícios bilíngue (EN/PT-BR) |

---

## Recursos Adicionais

- [Documentação Oracle - Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Guia Baeldung - Functional Programming in Java](https://www.baeldung.com/java-functional-programming)
- [Optional no Java 8+](https://www.devmedia.com.br/java-8-optional-evitando-nullpointerexception/34487)
- [Blog da Oracle - Lambda e Streams](https://blogs.oracle.com/javamagazine/pt/uso-de-lambdas-e-streams-em-java)

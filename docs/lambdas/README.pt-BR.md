# Java Lambda Expressions

![Java](https://img.shields.io/badge/Java-21-blue) ![Lambda](https://img.shields.io/badge/Lambda-Functions-orange)

As **expressões lambda**, introduzidas no Java 8, trouxeram a **programação funcional** para o Java, permitindo a criação de código mais conciso e expressivo. Elas são usadas principalmente para fornecer implementações curtas para **interfaces funcionais**.

---

## 📚 Sumário

- [Conceitos Chave](#conceitos-chave)
- [Sintaxe da Lambda](#sintaxe-da-lambda)
- [Interfaces Funcionais](#interfaces-funcionais)
- [Captura de Variáveis](#captura-de-variáveis)
- [Composição de Funções](#composição-de-funções)
- [Exemplos de Uso](#exemplos-de-uso)
  - [Exemplo 1: Consumer](#exemplo-1-consumer)
  - [Exemplo 2: Predicate](#exemplo-2-predicate)
  - [Exemplo 3: Function](#exemplo-3-function)
  - [Exemplo 4: Usando lambdas com `forEach`](#exemplo-4-usando-lambdas-com-foreach)
  - [Exemplo 5: Lista de lambdas](#exemplo-5-lista-de-lambdas)
- [Exercícios Práticos](#exercícios-práticos)
- [Quando Usar Lambdas?](#quando-usar-lambdas)
- [Testes](#testes)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Chave

* **Interface Funcional:** Uma interface com exatamente um método abstrato.
* **Lambda:** Uma função anônima que implementa uma interface funcional.
* **Funções como dados:** Você pode armazenar lambdas em variáveis, passá-las como argumentos e retorná-las de métodos.
* **First-class citizens:** No Java, as lambdas permitem que funções sejam tratadas como objetos de primeira classe.

---

## Sintaxe da Lambda

```java
// (argumentos) -> { corpo }
(String s) -> System.out.println(s);

// Inferência de tipos
s -> System.out.println(s);

// Vários argumentos
(a, b) -> a + b;

// Sem argumento
() -> System.out.println("Hello");
```

---

## Interfaces Funcionais

Java fornece várias interfaces funcionais na biblioteca `java.util.function`. As principais são:

| Interface           | Descrição                                  | Exemplo                               |
| ------------------- | ------------------------------------------ | ------------------------------------- |
| `Runnable`          | Não recebe argumento e não retorna         | `() -> System.out.println("Running")` |
| `Consumer<T>`       | Recebe um argumento, sem retorno           | `x -> System.out.println(x)`          |
| `Supplier<T>`       | Não recebe argumento, retorna um valor     | `() -> Math.random()`                 |
| `Function<T, R>`    | Recebe um argumento e retorna um valor     | `s -> s.length()`                     |
| `Predicate<T>`      | Recebe argumento e retorna boolean         | `n -> n % 2 == 0`                     |
| `BiFunction<T,U,R>` | Recebe dois argumentos e retorna um        | `(a, b) -> a + b`                     |
| `BinaryOperator<T>` | Dois argumentos do mesmo tipo → mesmo tipo | `(x, y) -> x * y`                     |

---

## Captura de Variáveis

Lambdas podem acessar variáveis do escopo externo **desde que sejam efetivamente finais**:

```java
int base = 10;
Function<Integer, Integer> adder = x -> x + base;
System.out.println(adder.apply(5)); // 15
```

---

## Composição de Funções

Funções podem ser **encadeadas**:

```java
Function<String, String> toUpper = String::toUpperCase;
Function<String, String> addExclamation = s -> s + "!";
Function<String, String> shout = toUpper.andThen(addExclamation);

System.out.println(shout.apply("java")); // JAVA!
```

---

## Exemplos de Uso

### Exemplo 1: Consumer

```java
Consumer<String> printer = s -> System.out.println("Olá " + s);
printer.accept("Lambda");
```

---

### Exemplo 2: Predicate

```java
Predicate<Integer> isPositive = x -> x > 0;
System.out.println(isPositive.test(3)); // true
```

---

### Exemplo 3: Function

```java
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Java")); // 4
```

---

### Exemplo 4: Usando lambdas com `forEach`

```java
List<String> list = List.of("a", "b", "c");
list.forEach(item -> System.out.println(item));
```

---

### Exemplo 5: Lista de lambdas

```java
List<Supplier<String>> actions = List.of(
    () -> "Hello",
    () -> "Lambda",
    () -> "World"
);
actions.forEach(a -> System.out.println(a.get()));
```

---

## Exercícios Práticos

| Arquivo                                    | Descrição                                                  |
| ------------------------------------------ | ---------------------------------------------------------- |
| [`LabLambdaToDo`](./LabLambdaToDo.java)    | Classe com os métodos dos exercícios para você implementar |
| [`LabLambdaDone`](./LabLambdaDone.java)    | Classe com todos os exercícios já resolvidos               |
| [`exercise.en-pt.md`](./exercise.en-pt.md) | Lista de exercícios bilíngue (EN/PT-BR)                    |

---

## Quando Usar Lambdas?

* Ao implementar interfaces com **apenas um método abstrato**.
* Para simplificar **código com callbacks, filtros, mapeamentos ou ordenações**.
* Em APIs que usam **Streams** ou qualquer funcionalidade do pacote `java.util.function`.
* Para tornar o código mais **limpo, legível e expressivo**.

---

## Testes

Você pode encontrar testes unitários para esses exemplos em `../../tests/LambdaTest.java`.

---

## Recursos Adicionais

* [Documentação Oficial da Oracle: Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
* [Guia Baeldung: Java Lambda Expressions](https://www.baeldung.com/java-lambda-expressions)
* [Java 8 Lambdas - Guia completo (DevMedia)](https://www.devmedia.com.br/lambda-expressions-em-java-8/31823)
* [Funções Lambda em Java explicadas](https://www.dio.me/articles/funcao-lambda-em-java-explicada)

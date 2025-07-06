# Java Streams API

![Java](https://img.shields.io/badge/Java-21-blue) ![Stream API](https://img.shields.io/badge/Stream--API-Java%208%2B-yellow)

A Stream API, introduzida no Java 8, oferece uma nova maneira de processar coleções de dados de forma declarativa e funcional. Ela permite que você escreva código mais conciso e legível para operações como filtragem, mapeamento e redução.

---

## 📚 Sumário

- [Conceitos Chave](#conceitos-chave)
- [Ordem de Execução](#ordem-de-execução)
- [Criação do Stream](#criação-do-stream)
- [Stream em Paralelo](#stream-em-paralelo)
- [Exemplos de Uso](#exemplos-de-uso)
- [Exercícios Práticos](#exercícios-práticos)
- [Quando Usar Streams?](#quando-usar-streams)
- [Testes](#testes)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Chave

* **Fonte de Dados:** Pode ser uma coleção, um array, um gerador ou um recurso de I/O.
* **Operações Intermediárias:** Processam a stream e retornam uma nova stream. São *lazy* (não executam até uma operação terminal). Exemplos: `filter()`, `map()`, `flatMap()`, `sorted()`, `distinct()`, `peek()`, `limit()`, `skip()`.
* **Operações Terminais:** Consomem a stream e produzem um resultado ou um efeito colateral. São *eager* (iniciam o processamento da stream). Exemplos: `forEach()`, `collect()`, `reduce()`, `count()`, `min()`, `max()`, `anyMatch()`, `allMatch()`, `noneMatch()`, `findFirst()`, `findAny()`.
---

## Ordem de execução
* Do ponto de vista da performance, a ordem correta é um dos aspectos mais importantes em operações em cadeia na `stream pipeline`.
* Com isso, operações intermediárias que reduzem o tamanho da stream devem ser colocadas antes de operações que são aplicadas a cada elemento, portanto, métodos como `skip()`, `filter()`, e `distinct()` são colocados no topo.

## Criação do Stream

Existem muitas formas de criar uma instância stream de diferentes fontes, uma vez criado a instância não modifica o objeto!

### Stream de Collection
```java
Collection<String> collection = Arrays.asList("a", "b", "c");
Stream<String> streamOfCollection = collection.stream();
```

### Stream de Array
```java
Stream<String> streamOfArray = Stream.of("a", "b", "c");

String[] arr = new String[]{"a", "b", "c"};
Stream<String> streamOfArrayFull = Arrays.stream(arr);
Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);
```

### Stream.builder()
Sendo importante especificar o tipo do objeto `Stream<T>.builder()`
```java
Stream<String> streamBuilder =
  Stream.<String>builder().add("a").add("b").add("c").build();
```

### Stream de tipos primitivos
Como Stream<T> é uma interface genérica, não tem como usar dado primitivo como parâmetro de genéricos, com isso as interfaces `IntStream`, `LongStream` e `DoubleStream` foram criadas.
```java
// range(int startInclusive, int endExclusive) 
IntStream intStream = IntStream.range(1, 3);
// rangeClosed(int startInclusive, int endInclusive) 
LongStream longStream = LongStream.rangeClosed(1, 3);
Random random = new Random();
DoubleStream doubleStream = random.doubles(3);
```

---

## Stream em paralelo

* Java 8 introduziu uma maneira de usar o paralelismo de uma maneira funcional
* Se o Stream é uma ``Collection`` ou `array` usa-se o `parallelStream()`, se não `parallel()`

```java
// parallelStream()
Stream<Product> streamOfCollection = productList.parallelStream();
boolean isParallel = streamOfCollection.isParallel();
boolean bigPrice = streamOfCollection
  .map(product -> product.getPrice() * 12)
  .anyMatch(price -> price > 200);

// parallel()
IntStream intStreamParallel = IntStream.range(1, 150).parallel();
boolean isParallel = intStreamParallel.isParallel();
```

---

## Exemplos de Uso

### Filtrando e Mapeando Elementos

Este exemplo demonstra como filtrar números pares e mapeá-los para seus quadrados.

```java
// Exemplo: FilterMapReduce.java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterMapReduce {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> squaredEvenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0) // Operação intermediária: filtra números pares
            .map(n -> n * n)         // Operação intermediária: mapeia para o quadrado
            .collect(Collectors.toList()); // Operação terminal: coleta para uma nova lista

        System.out.println("Números pares ao quadrado: " + squaredEvenNumbers); // Saída: [4, 16, 36, 64, 100]
    }
}
```

### Usando `reduce()` para Somar Elementos

O método `reduce()` permite combinar elementos de uma stream em um único resultado.

```java
// Exemplo: ReduceExample.java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Soma todos os números na lista
        Optional<Integer> sum = numbers.stream()
            .reduce((a, b) -> a + b);

        sum.ifPresent(s -> System.out.println("Soma total: " + s)); // Saída: 15
    }
}
```

-----

## Exercícios Práticos

| Arquivo                     | Descrição                                                                 |
|----------------------------|---------------------------------------------------------------------------|
| [`LabStreamToDo`](./LabStreamToDo.java)   | Classe com os métodos dos exercícios para você implementar |
| [`LabStreamDone`](./LabStreamDone.java)   | Classe com todos os exercícios já resolvidos                |
| [`exercise.en-pt.md`](./exercise.en-pt.md) | Lista de exercícios bilíngue (EN/PT-BR)                      |


-----

## Quando Usar Streams?

* Processamento de coleções grandes de dados.
* Quando a lógica de processamento é complexa e envolve múltiplas operações (filtrar, mapear, etc.).
* Para escrever código mais declarativo e menos imperativo, focando "o que" fazer em vez de "como" fazer.
* Para facilitar a paralelização de operações (usando `parallelStream()`).

-----

## Testes

Você pode encontrar testes unitários para esses exemplos em `../../tests/StreamsTest.java`.

-----

## Recursos Adicionais

* [Documentação Oficial da Oracle: Package `java.util.stream`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/package-summary.html)
* [Guia Baeldung: Java 8 Stream API Tutorial](https://www.baeldung.com/java-8-streams)
* [Java Streams na Prática: Exemplos e Boas Práticas](https://www.dio.me/articles/java-streams-na-pratica-exemplos-e-boas-praticas)
* [Java Streams API: manipulando coleções de forma eficiente](https://www.devmedia.com.br/java-streams-api-manipulando-colecoes-de-forma-eficiente/37630)

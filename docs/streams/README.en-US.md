# Java Streams API

![Java](https://img.shields.io/badge/Java-21-blue) ![Stream API](https://img.shields.io/badge/Stream--API-Java%208%2B-yellow)

The Stream API, introduced in Java 8, provides a new way to process collections of data in a declarative and functional way. It allows you to write more concise and readable code for operations such as filtering, mapping, and reducing.

---

## 📚 Summary

- [Key Concepts](#key-concepts)
- [Execution order](#execution-order)
- [Creating a Stream](#creating-a-stream)
  - [Collection Stream](#collection-stream)
  - [Array Stream](#array-stream)
  - [Stream.builder()](#streambuilder)
  - [Stream of primitive types](#stream-of-primitive-types)
- [Stream in parallel](#stream-in-parallel)
- [Usage Examples](#usage-examples)
  - [Filtering and Mapping Elements](#filtering-and-mapping-elements)
  - [Using `reduce()` to Sum Elements](#using-reduce-to-sum-elements)
- [Practical Exercises](#practical-exercises)
- [When to Use Streams?](#when-to-use-streams)
- [Tests](#tests)
- [Additional Resources](#additional-resources)

---

## Key Concepts

* **Data Source:** Can be a collection, an array, a generator, or an I/O resource.
* **Intermediate Operations:** Process the stream and return a new stream. They are *lazy* (they do not execute until a terminal operation). Examples: `filter()`, `map()`, `flatMap()`, `sorted()`, `distinct()`, `peek()`, `limit()`, `skip()`.
* **Terminal Operations:** They consume the stream and produce a result or a side effect. They are *eager* (they start processing the stream). Examples: `forEach()`, `collect()`, `reduce()`, `count()`, `min()`, `max()`, `anyMatch()`, `allMatch()`, `noneMatch()`, `findFirst()`, `findAny()`.
---

## Execution order
* From a performance point of view, the correct order is one of the most important aspects in chained operations in the `stream pipeline`. * With this, intermediate operations that reduce the size of the stream must be placed before operations that are applied to each element, so methods like `skip()`, `filter()`, and `distinct()` are placed at the top.

## Creating a Stream

There are many ways to create a stream instance from different sources, once created the instance does not modify the object!

### Collection Stream
```java
Collection<String> collection = Arrays.asList("a", "b", "c");
Stream<String> streamOfCollection = collection.stream();
```

### Array Stream
```java
Stream<String> streamOfArray = Stream.of("a", "b", "c");

String[] arr = new String[]{"a", "b", "c"};
Stream<String> streamOfArrayFull = Arrays.stream(arr); Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);
```

### Stream.builder()
It is important to specify the type of the object `Stream<T>.builder()`
```java
Stream<String> streamBuilder =
Stream.<String>builder().add("a").add("b").add("c").build();
```

### Stream of primitive types
Since Stream<T> is a generic interface, it is not possible to use primitive data as a generic parameter, so the `IntStream`, `LongStream` and `DoubleStream` interfaces were created.
```java
// range(int startInclusive, int endExclusive)
IntStream intStream = IntStream.range(1, 3); // rangeClosed(int startInclusive, int endInclusive)
LongStream longStream = LongStream.rangeClosed(1, 3);
Random random = new Random();
DoubleStream doubleStream = random.doubles(3);
```

---

## Stream in parallel

* Java 8 introduced a way to use parallelism in a functional way
* If the Stream is a ``Collection`` or `array` use `parallelStream()`, if not `parallel()`

```java
// parallelStream()
Stream<Product> streamOfCollection = productList.parallelStream();
boolean isParallel = streamOfCollection.isParallel(); boolean bigPrice = streamOfCollection
.map(product -> product.getPrice() * 12)
.anyMatch(price -> price > 200);

// parallel()
IntStream intStreamParallel = IntStream.range(1, 150).parallel();
boolean isParallel = intStreamParallel.isParallel();
```

---

## Usage Examples

### Filtering and Mapping Elements

This example demonstrates how to filter even numbers and map them to their squares.

```java
// Example: FilterMapReduce.java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterMapReduce {
  public static void main(String[] args) {
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

  List<Integer> squaredEvenNumbers = numbers.stream()
  .filter(n -> n % 2 == 0) // Intermediate operation: filter even numbers
  .map(n -> n * n) // Intermediate operation: map to squared
  .collect(Collectors.toList()); // Terminal operation: collect to a new list

  System.out.println("Even numbers squared: " + squaredEvenNumbers); // Output: [4, 16, 36, 64, 100]
  }
}
```

### Using `reduce()` to Sum Elements

The `reduce()` method allows you to combine elements of a stream into a single result.

```java
// Example: ReduceExample.java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceExample {
  public static void main(String[] args) {
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

  // Sum all numbers in the list
  Optional<Integer> sum = numbers.stream()
  .reduce((a, b) -> a + b);

  sum.ifPresent(s -> System.out.println("Total sum: " + s)); // Output: 15
  }
}
```

-----

## Practical Exercises

| File | Description |
|----------------------------|---------------------------------------------------------------------------|
| [`LabStreamToDo`](./LabStreamToDo.java) | Class with the exercise methods for you to implement |
| [`LabStreamDone`](./LabStreamDone.java) | Class with all the exercises already solved |
| [`exercise.en-pt.md`](./exercise.en-pt.md) | Bilingual exercise list (EN/PT-BR) |

-----

## When to Use Streams?

* Processing large collections of data.
* When the processing logic is complex and involves multiple operations (filtering, mapping, etc.).
* To write more declarative and less imperative code, focusing on "what" to do instead of "how" to do it.
* To facilitate parallelization of operations (using `parallelStream()`).

-----

## Tests

You can find unit tests for these examples in `../../tests/StreamsTest.java`.

-----

## Additional Resources

* [Official Oracle Documentation: Package `java.util.stream`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/package-summary.html)
* [Baeldung Guide: Java 8 Stream API Tutorial](https://www.baeldung.com/java-8-streams)
* [Java Streams na Prática: Exemplos e Boas Práticas](https://www.dio.me/articles/java-streams-na-pratica-exemplos-e-boas-praticas)
* [Java Streams API: manipulando coleções de forma eficiente](https://www.devmedia.com.br/java-streams-api-manipulando-colecoes-de-forma-eficiente/37630)

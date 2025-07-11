# Functional Programming in Java

![Java](https://img.shields.io/badge/Java-21-blue) ![Functional](https://img.shields.io/badge/Style-Functional-green)

Functional Programming (FP) in Java gained momentum starting with Java 8, with the introduction of **Lambda Expressions**, **functional interfaces**, **Stream API**, and **Optional**. It enables writing more **declarative**, **modular**, and **expressive** code, favoring composition and immutability.

---

## 📚 Summary

- [Fundamental Concepts](#fundamental-concepts)
- [Functional Programming vs. Object-Oriented Programming (OOP)](#functional-programming-vs-object-oriented-programming-oop)
  - [Object-Oriented Programming (OOP) 🧱](#object-oriented-programming-oop)
  - [Functional Programming (FP) ⚛️](#functional-programming-fp)
  - [Key Differences and Characteristics](#key-differences-and-characteristics)
  - [When to Use Each (and Together!)](#when-to-use-each-and-together)
- [Functional Interfaces](#functional-interfaces)
- [Lambda Expressions](#lambda-expressions)
- [Optional](#optional)
- [Function Composition](#function-composition)
- [Usage with Stream API](#usage-with-stream-api)
- [🤔 Wouldn't it be overkill to create a function that performs a sum?](#wouldnt-it-be-overkill-to-create-a-function-that-performs-a-sum)
  - [Example](#example)
    - [How Reduction Works (Step-by-Step)](#how-reduction-works-step-by-step)
    - [The Value of This Example ✨](#the-value-of-this-example)
- [Practical Exercises](#practical-exercises)
- [Additional Resources](#additional-resources)

---

## Fundamental Concepts

| Concept                   | Description                                         |
| :------------------------ | :-------------------------------------------------- |
| **Immutability**          | Data doesn't change after creation.                 |
| **Pure Functions**        | Same input → same output, no side effects.          |
| **First-class functions** | Functions can be passed as parameters and returned. |
| **Composition**           | Combining simple functions into larger operations.  |
| **Lazy evaluation**       | On-demand evaluation (as in streams).               |

---

## Functional Programming vs. Object-Oriented Programming (OOP)

Functional Programming (FP) and Object-Oriented Programming (OOP) are two distinct **programming paradigms**, each with its own philosophy on how to structure and organize code to solve problems. They are not direct competitors but rather complementary approaches that can (and often should) be used together in the same project.

### Object-Oriented Programming (OOP) 🧱

OOP focuses on **modeling the real world** through **objects**. An object is an instance of a **class**, which combines related **data (attributes)** and **behaviors (methods)**.

- **Core Pillars:** Objects, classes, inheritance, polymorphism, encapsulation, and abstraction.
- **Mutable State:** Objects in OOP typically have **state** that can change over time. For example, a `BankAccount` object has a balance that changes after deposits and withdrawals.
- **Focus:** "How do things _interact_ and _change state_?"
- **Examples:** Java, C++, C#, Python (with OOP aspects), Ruby.

### Functional Programming (FP) ⚛️

FP treats computation as the **evaluation of mathematical functions**, avoiding mutable state and changing data. It focuses on "what" (the desired result) rather than "how" (the exact steps to achieve it).

- **Core Pillars:** **Pure functions**, **immutability**, **minimal or no side effects**, and **first-class functions** (functions can be passed as arguments, returned by other functions, etc.).
- **Immutable State:** Data is treated as **immutable**. Instead of modifying an existing object, a function returns a new object with the desired modifications.
- **Focus:** "How do things _transform_ from an input to an output?"
- **Examples:** Haskell, Lisp, Scala (with FP aspects), JavaScript (with FP aspects), Java (from Java 8 onwards with Lambdas and Streams).

---

### Key Differences and Characteristics

| Characteristic       | Object-Oriented Programming (OOP)                                           | Functional Programming (FP)                                                   |
| :------------------- | :-------------------------------------------------------------------------- | :---------------------------------------------------------------------------- |
| **Central Paradigm** | Objects and Classes                                                         | Functions and Immutability                                                    |
| **State**            | **Mutable** (objects can change state)                                      | **Immutable** (functions avoid changing existing data, creating new)          |
| **Focus**            | Verbs (actions) and Nouns (data); modeling entities and their interactions. | Functions as building blocks; transforming data.                              |
| **Side Effects**     | Common and generally acceptable (changing object state).                    | Avoided or minimized; pure functions cause no external effects.               |
| **Concurrency**      | More challenging due to shared, mutable state (requires locks, etc.).       | Easier to manage, as immutability reduces synchronization issues.             |
| **Testability**      | Can be more complex to test units that depend on state.                     | Pure functions are easier to test (fixed inputs = fixed outputs).             |
| **Java Example**     | Create a `Car` instance, call `car.accelerate()`.                           | Use `stream.map(s -> s.toUpperCase())` or `list.filter(num -> num % 2 == 0)`. |
| **Abstraction**      | Encapsulation of data and methods within objects.                           | Functions as first-class citizens; behavior abstraction.                      |

---

### When to Use Each (and Together!)

- **OOP:** Excellent for modeling complex systems with many entities and relationships where object state and lifecycle are important (e.g., management systems, business applications, graphical interfaces).
- **FP:** Shines in data transformation scenarios, collection processing, stateless logic, mathematical algorithms, and where concurrency is a primary concern (e.g., data analysis, big data processing, data transformation pipelines, stateless REST APIs).

In Java (from Java 8 onwards), OOP and FP are not mutually exclusive. You can have code that uses classes and objects (OOP) and, within these objects, leverages lambdas and Streams to process data functionally. This combination gets the best of both worlds: the **structure and modeling of OOP** with the **conciseness, parallelism, and testability of FP**.

---

## Functional Interfaces

| Interface           | Single Method         | Description                            |
| :------------------ | :-------------------- | :------------------------------------- |
| `Function<T,R>`     | `R apply(T t)`        | Transforms one value into another.     |
| `Predicate<T>`      | `boolean test(T t)`   | Boolean evaluation.                    |
| `Consumer<T>`       | `void accept(T t)`    | Performs an action with a side effect. |
| `Supplier<T>`       | `T get()`             | Supplies a value without input.        |
| `UnaryOperator<T>`  | `T apply(T t)`        | Like Function, input = output type.    |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | Combines two values of the same type.  |

---

## Lambda Expressions

```java
// Traditional way
Function<String, Integer> length = new Function<>() {
    public Integer apply(String s) {
        return s.length();
    }
};

// Lambda
Function<String, Integer> length2 = s -> s.length();
Predicate<Integer> isEven = x -> x % 2 == 0;
Consumer<String> print = System.out::println;
Supplier<Double> random = () -> Math.random();
UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
BinaryOperator<Integer> sum = (num1, num2) -> num1 + num2;
BiFunction<T, U, R> combine = (s, i) -> Double.parseDouble(s) + i; // (input: String, Integer; output: Double)
```

---

## Optional

Avoids `NullPointerException` with a functional style:

```java
Optional<String> name = Optional.of("João");

name
  .map(String::toUpperCase)
  .filter(n -> n.startsWith("J"))
  .ifPresent(System.out::println);
```

---

## Function Composition

```java
Function<Integer, Integer> doubled = x -> x * 2;
Function<Integer, Integer> plus3 = x -> x + 3;

Function<Integer, Integer> composition = doubled.compose(plus3); // (x + 3) * 2
Function<Integer, Integer> chaining = doubled.andThen(plus3); // (x * 2) + 3
```

---

## Usage with Stream API

```java
List<String> names = List.of("Ana", "Bruno", "Carlos");

names.stream()
  .filter(n -> n.length() > 4)
  .map(String::toUpperCase)
  .forEach(System.out::println);
```

---

## 🤔 Wouldn't it be overkill to create a function that performs a sum?

If it's possible to directly return the sum as `num1 + num2`, why implement an interface, as in the case of `BinaryOperator<Integer> sum = (num1, num2) -> num1 + num2;`?

The beauty of `functional interfaces` and `lambdas` lies in abstracting behaviors so they can be passed as arguments, stored in variables, returned by other methods, and reused flexibly, especially in operations with collections (`Streams`).

With this:

- **Behavior Reusability:** Define once and reuse in other places;
- **Abstraction and Flexibility:** In libraries and frameworks (like Java's Streams API), you don't say how to sum, but what to do (sum). The library then applies this "sum function" where needed.
- **Chaining (Pipelines):** Functions and operators are building blocks that can be chained in data processing pipelines, such as `stream.map(...).filter(...).reduce(...)`.

#### Example

```java
List<Integer> transactions = List.of(100, -20, 50, -30, 80);
int initialBalance = 500;

BinaryOperator<Integer> binarySum = (num1, num2) -> num1 + num2;
int finalBalance = transactions.stream()
                               .reduce(initialBalance, binarySum);
```

- **`BinaryOperator<Integer> binarySum = (num1, num2) -> num1 + num2;`**:
  - Here you define the **binary operation** to be applied. It takes two `Integer`s and returns an `Integer` (their sum).
  - This `BinaryOperator` represents the **summing behavior**, which can be reused in various contexts.
- **`int finalBalance = transactions.stream().reduce(initialBalance, binarySum);`**:
  - **`transactions.stream()`**: Creates a `Stream` from your list of transactions. Streams are the heart of functional collection processing in Java.
  - **`.reduce(initialBalance, binarySum)`**: This is the reduction operation.
    - `initialBalance`: This is the **identity value (or initial value)**. It's the starting point for the reduction and also the value returned if the stream is empty.
    - `binarySum`: This is the **accumulator**. This `BinaryOperator` is applied repeatedly to the stream's elements. The first argument (`num1`) receives the result of the previous operation (or `initialBalance` for the first time), and the second argument (`num2`) receives the next element from the stream.

##### How Reduction Works (Step-by-Step)

Let's simulate how `reduce` operates with your values:

1.  **Start:** `accumulator` = `initialBalance` (500)
2.  **First Transaction (100):** `binarySum.apply(500, 100)` $\\rightarrow$ 600. `accumulator` is now 600.
3.  **Second Transaction (-20):** `binarySum.apply(600, -20)` $\\rightarrow$ 580. `accumulator` is now 580.
4.  **Third Transaction (50):** `binarySum.apply(580, 50)` $\\rightarrow$ 630. `accumulator` is now 630.
5.  **Fourth Transaction (-30):** `binarySum.apply(630, -30)` $\\rightarrow$ 600. `accumulator` is now 600.
6.  **Fifth Transaction (80):** `binarySum.apply(600, 80)` $\\rightarrow$ 680. `accumulator` is now 680.
7.  **End of Stream:** The final reduction value is 680.

The reduce operation takes a stream of elements and combines them into a single result, using a binary function (like the BinaryOperator).

---

##### The Value of This Example ✨

This is an **extremely common and powerful** use case for `BinaryOperator` and `Stream.reduce()`. Instead of writing a manual `for` loop to sum, you:

1.  **Declare Intent:** You express _what_ you want to do (`binarySum`), not _how_ (detailed loop steps).
2.  **Reuse Behavior:** `binarySum` can be used in any other reduction operation where you need to sum `Integer`s.
3.  **Fluid Chaining:** This fits perfectly into chains of Stream operations (`.filter().map().reduce()`), making the code more readable and maintainable.

---

## Practical Exercises

| File                                                                            | Description                        |
| ------------------------------------------------------------------------------- | ---------------------------------- |
| [`LabFunctionalToDo`](https://www.google.com/search?q=./LabFunctionalToDo.java) | Class with exercises to implement  |
| [`LabFunctionalDone`](https://www.google.com/search?q=./LabFunctionalDone.java) | Class with complete solutions      |
| [`exercise.en-pt.md`](https://www.google.com/search?q=./exercise.en-pt.md)      | Bilingual exercise list (EN/PT-BR) |

---

## Additional Resources

- [Oracle Documentation - Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Baeldung Guide - Functional Programming in Java](https://www.baeldung.com/java-functional-programming)
- [Optional in Java 8+](https://www.devmedia.com.br/java-8-optional-evitando-nullpointerexception/34487)
- [Oracle Blog - Lambdas and Streams](https://blogs.oracle.com/javamagazine/pt/uso-de-lambdas-e-streams-em-java)

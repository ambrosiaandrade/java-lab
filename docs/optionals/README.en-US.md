# Java Optional API

![Java](https://img.shields.io/badge/Java-21-blue) ![Optional API](https://img.shields.io/badge/Optional--API-Java%208%2B-yellow)

The `Optional` API, introduced in Java 8, provides an elegant way to handle values that may be absent (`null`), helping to avoid common errors like `NullPointerException`. This API offers functional-style methods to safely and clearly manipulate optional values.

---

## 📚 Table of Contents

- [Key Concepts](#key-concepts)
- [Creating Optionals](#creating-optionals)
- [Checking for Value Presence](#checking-for-value-presence)
- [Fallback and Default Values](#fallback-and-default-values)
- [Transformations and Filters](#transformations-and-filters)
- [Primitives Optionals](#primitives-optionals)
- [Usage Examples](#usage-examples)
- [Practical Exercises](#practical-exercises)
- [When Should You Use Optional?](#when-should-you-use-optional)
- [Performance](#performance)
- [Tests](#tests)
- [Additional Resources](#additional-resources)

---

## Key Concepts

- **Optional:** A container that may or may not contain a non-null value.
- **Avoids NullPointerException:** Forces explicit handling of absent values.
- **Functional Methods:** `map()`, `filter()`, `orElse()`, `orElseGet()`, `orElseThrow()`, `ifPresent()`.
- **Primitive Optionals:** `OptionalInt`, `OptionalLong`, `OptionalDouble` to avoid unnecessary boxing.

---

## Creating Optionals

```java
Optional<String> opt1 = Optional.of("value");         // Does not allow null
Optional<String> opt2 = Optional.empty();             // Empty Optional
Optional<String> opt3 = Optional.ofNullable(value);   // Accepts null
```

Use `Optional.ofNullable()` whenever the source might return `null`, and use Optional-safe methods to process or fallback.

---

## Checking for Value Presence

```java
if (opt.isPresent()) {
    System.out.println(opt.get());
}

opt.ifPresent(value -> System.out.println(value));
```

---

## Fallback and Default Values

```java
String value = opt.orElse("default value");
String lazyValue = opt.orElseGet(() -> "generated value");
String errorValue = opt.orElseThrow(() -> new IllegalStateException("Missing"));
```

### `orElse(value)`: Eager

This method **always evaluates** the argument, even if the `Optional` contains a value.

**It's like saying:** "Always prepare the backup dish, just in case."

**Example:**

```java
public String getDefault() {
    System.out.println("Generating expensive default...");
    return "Default";
}

Optional<String> opt = Optional.of("Real Value");
String result = opt.orElse(getDefault()); // Default is created anyway!
System.out.println(result); // Prints "Real Value"
```

**Use when:** The default value is cheap and readily available (e.g., a constant, literal, simple object).

---

### `orElseGet(() -> ...)`: Lazy

This method takes a `Supplier` and **only executes it if the `Optional` is empty**.

**It's like saying:** "Give me the recipe, but I’ll only cook it if needed."

**Example:**

```java
public String getDefault() {
    System.out.println("Generating expensive default...");
    return "Default";
}

Optional<String> opt = Optional.of("Real Value");
String result = opt.orElseGet(this::getDefault); // Default is NOT created
System.out.println(result); // Prints "Real Value"

Optional<String> emptyOpt = Optional.empty();
String fallback = emptyOpt.orElseGet(this::getDefault); // Default IS created
System.out.println(fallback); // Prints "Default"
```

### Summary

- **`orElse()`**: Always evaluated. Good for cheap, ready values.
- **`orElseGet()`**: Lazy execution. Better for expensive operations.

---

## Transformations and Filters

```java
Optional<String> name = Optional.of("Java");
Optional<Integer> length = name.map(String::length);
Optional<String> filtered = name.filter(n -> n.startsWith("J"));
```

---

## Primitives Optionals

```java
OptionalInt oi = OptionalInt.of(10);
int val1 = oi.orElse(0);

OptionalLong ol = OptionalLong.empty();
long val2 = ol.orElseGet(() -> 100L);

OptionalDouble od = OptionalDouble.of(3.14);
double val3 = od.orElseThrow();
```

---

## Usage Examples

```java
Optional<String> optName = Optional.ofNullable("Ambrósia");

String upper = optName
    .map(String::toUpperCase)
    .orElse("UNKNOWN");

System.out.println(upper);
```

---

## Practical Exercises

| File                                             | Description                       |
| ------------------------------------------------ | --------------------------------- |
| [`LabOptionalToDo.java`](./LabOptionalToDo.java) | Class with exercises to implement |
| [`LabOptionalDone.java`](./LabOptionalDone.java) | Class with completed solutions    |
| [`exercise.en-pt.md`](./exercise.en-pt.md)       | Bilingual exercise list           |

---

## When Should You Use Optional?

- When returning values that may be absent instead of `null`.
- To make code more expressive and robust.
- For modern APIs that explicitly express the possibility of missing values.

---

## Performance

### The Problem: `Optional<Integer>` and Boxing

Java has primitive types (`int`, `long`, etc.) and wrapper objects (`Integer`, `Long`, etc.).

- **Boxing:** Converts a primitive to its object wrapper.
- **Unboxing:** The reverse—extracting the primitive from its wrapper.

Using `Optional<Integer>` causes boxing and unboxing overhead:

```java
Optional<Integer> boxed = Optional.of(42);

if (boxed.isPresent()) {
    int value = boxed.get(); // Implicit unboxing
    System.out.println(value + 8);
}
```

### The Solution: `OptionalInt`

`OptionalInt` was introduced in Java 8 to avoid boxing by storing `int` directly.

```java
OptionalInt unboxed = OptionalInt.of(42);

if (unboxed.isPresent()) {
    int value = unboxed.getAsInt(); // No unboxing
    System.out.println(value + 8);
}
```

### Why Is `OptionalInt` Faster?

1. **Less Memory Allocation:** No wrapper objects → faster memory use.
2. **Reduced Garbage Collection:** Fewer objects → less GC activity → lower latency.

### When Should You Use `OptionalInt`?

> **Whenever you’re wrapping a primitive that may be absent, prefer `OptionalInt`, `OptionalLong`, or `OptionalDouble`.**

Especially in:

- **Streams** with large data volumes
- **High-performance** systems

#### Practical Stream Example

Inefficient approach:

```java
List<Integer> nums = List.of(1, 3, 5, 8, 10);

Optional<Integer> even = nums.stream()
                             .filter(n -> n % 2 == 0)
                             .findFirst();
```

Efficient approach:

```java
List<Integer> nums = List.of(1, 3, 5, 8, 10);

OptionalInt even = nums.stream()
                       .mapToInt(Integer::intValue)
                       .filter(n -> n % 2 == 0)
                       .findFirst();
```

### Conclusion

**Use `OptionalInt`, `OptionalLong`, and `OptionalDouble` for better performance.**
It’s a sign of modern, optimized Java code, especially for data-intensive operations.

---

## Tests

Tests are located at:
`../../tests/OptionalTest.java`

---

## Additional Resources

- [Official Oracle Documentation – Optional](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Optional.html)
- [Baeldung Guide: Java Optional Tutorial](https://www.baeldung.com/java-optional)
- [Oracle Java Tutorials: Optional](https://docs.oracle.com/javase/tutorial/java/javaOO/optional.html)

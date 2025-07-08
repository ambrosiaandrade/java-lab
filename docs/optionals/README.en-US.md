# Java Optional API

![Java](https://img.shields.io/badge/Java-21-blue) ![Optional API](https://img.shields.io/badge/Optional--API-Java%208%2B-yellow)

The `Optional` API, introduced in Java 8, provides an elegant way to handle values that may or may not be present (null), thus helping to avoid common errors such as `NullPointerException`. This API offers functional methods to manipulate optional values safely and readably.

---

## 📚 Table of Contents

- [Key Concepts](#key-concepts)
- [Creating Optionals](#creating-optionals)
- [Checking Value Presence](#checking-value-presence)
- [Replacement and Fallback](#replacement-and-fallback)
- [Transformations and Filters](#transformations-and-filters)
- [Primitive Optionals](#primitive-optionals)
- [Usage Examples](#usage-examples)
- [Practical Exercises](#practical-exercises)
- [When to Use Optional?](#when-to-use-optional)
- [Tests](#tests)
- [Additional Resources](#additional-resources)

---

## Key Concepts

- **Optional:** Container that may or may not hold a non-null value.
- **Avoid NullPointerException:** Forces explicit handling of absent values.
- **Functional Methods:** `map()`, `filter()`, `orElse()`, `orElseGet()`, `orElseThrow()`, `ifPresent()`.
- **Primitive Optionals:** `OptionalInt`, `OptionalLong`, `OptionalDouble` avoid unnecessary boxing.

---

## Creating Optionals

```java
Optional<String> opt1 = Optional.of("value");         // Does not accept null
Optional<String> opt2 = Optional.empty();             // Empty Optional
Optional<String> opt3 = Optional.ofNullable(value);   // Accepts null value
```

---

## Checking Value Presence

```java
if (opt.isPresent()) {
    System.out.println(opt.get());
}

opt.ifPresent(value -> System.out.println(value));
```

---

## Replacement and Fallback

```java
String value = opt.orElse("default value");
String lazyValue = opt.orElseGet(() -> "generated value");
String exceptionValue = opt.orElseThrow(() -> new IllegalStateException("Absent"));
```

_Tip:_ Prefer `orElseGet()` when the default value is costly to create, because `orElse()` always evaluates its argument.

---

## Transformations and Filters

```java
Optional<String> name = Optional.of("Java");
Optional<Integer> length = name.map(String::length);
Optional<String> filtered = name.filter(n -> n.startsWith("J"));
```

---

## Primitive Optionals

```java
OptionalInt oi = OptionalInt.of(10);
int val = oi.orElse(0);

OptionalLong ol = OptionalLong.empty();
long val2 = ol.orElseGet(() -> 100L);

OptionalDouble od = OptionalDouble.of(3.14);
double val3 = od.orElseThrow();
```

---

## Usage Examples

```java
Optional<String> optName = Optional.ofNullable("Ambrosia");

String upperName = optName
    .map(String::toUpperCase)
    .orElse("UNKNOWN");

System.out.println(upperName);
```

---

## Practical Exercises

| File                                             | Description                           |
| ------------------------------------------------ | ------------------------------------- |
| [`LabOptionalToDo.java`](./LabOptionalToDo.java) | Class to implement Optional exercises |
| [`LabOptionalDone.java`](./LabOptionalDone.java) | Class with complete solutions         |
| [`exercise.en-pt.md`](./exercise.en-pt.md)       | Bilingual list of exercises           |

---

## When to Use Optional?

- When returning values that may be absent, avoiding `null`.
- To make code more expressive and safer.
- In modern APIs to explicitly denote optional absence of value.

---

## Tests

Tests for this lab are located at:
`../../tests/OptionalTest.java`

---

## Additional Resources

- [Oracle Official Documentation - Optional](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Optional.html)
- [Baeldung Guide: Java Optional Tutorial](https://www.baeldung.com/java-optional)
- [Oracle Java Tutorials: Optional](https://docs.oracle.com/javase/tutorial/java/javaOO/optional.html)

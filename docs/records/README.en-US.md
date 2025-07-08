# Java Records

![Java](https://img.shields.io/badge/Java-16%2B-blue) ![Feature](https://img.shields.io/badge/Feature-record-lightgrey)

Java `record` is a special kind of class introduced in Java 14 (preview) and officially released in Java 16 to simplify the creation of data-carrier classes.

---

## 📚 Summary

- [Key Concepts](#key-concepts)
- [Basic Syntax](#basic-syntax)
- [Immutability](#immutability)
- [Compact Constructor and Validation](#compact-constructor-and-validation)
- [Methods in Records](#methods-in-records)
- [Records and Interfaces](#records-and-annotations)
- [Practical Exercises](#practical-exercises)
- [When to Use Records?](#when-to-use-records)
- [Additional Resources](#additional-resources)

---

## Key Concepts

- `record` provides a **concise syntax** for immutable data classes
- Auto-generates:
  - Constructor
  - Accessor methods (instead of traditional `getters`)
  - `equals()`, `hashCode()`, and `toString()`
- Always `final` and immutable
- Cannot extend another class, but can implement interfaces

---

## Basic Syntax

```java
public record Person(String name, int age) {}
```

---

## Immutability

Once created, record instances cannot have their values changed. All fields are `private final`.

---

## Compact Constructor and Validation

You can validate input directly in a compact constructor:

```java
public record Product(String name, double price) {
    public Product {
        if (price < 0)
            throw new IllegalArgumentException("Invalid price");
    }
}
```

---

## Methods in Records

Records can define methods like any normal class:

```java
public record Rectangle(double width, double height) {
    public double area() {
        return width * height;
    }
}
```

---

## Records and Annotations

You can annotate record components:

```java
public record User(@NotNull String username, int level) {}
```

---

## Practical Exercises

| Java Class                                | Description (EN)                                  |
| ----------------------------------------- | ------------------------------------------------- |
| [`LabRecordsToDo`](./LabRecordsToDo.java) | Java class for you to implement the exercises     |
| [`LabRecordsDone`](./LabRecordsDone.java) | Java class with all exercises already implemented |

For the full list of exercises, see [`exercise.en-pt.md`](./exercise.en-pt.md)

---

## When to Use Records?

* For DTOs (Data Transfer Objects)
* As immutable API response models
* When using objects as map keys
* For simple data encapsulation

---

## Additional Resources

* [Official Java Docs: Records](https://docs.oracle.com/en/java/javase/21/language/records.html)
* [Baeldung: A Guide to Java Records](https://www.baeldung.com/java-record-keyword)
* [InfoQ: Understanding Java Records](https://www.infoq.com/articles/java-records/)

```

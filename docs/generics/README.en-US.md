# 📚 Generics in Java

Generics, introduced in Java 5, are a powerful feature that allows you to write classes, interfaces, and methods that operate with objects of **different types**, while ensuring **type safety at compile-time**. They solve the problem of `ClassCastException` at runtime, which was common when working with collections and classes that handled `Object`.

---

## 📚 Table of Contents

- [Why Use Generics?](#why-use-generics)
- [Basic Generics Terminology](#basic-generics-terminology)
- [Using Generics](#using-generics)
  - [Generic Classes](#generic-classes)
  - [Generic Interfaces](#generic-interfaces)
  - [Generic Methods](#generic-methods)
- [Bounded Type Parameters](#bounded-type-parameters)
- [Wildcards (`?`)](#wildcards)
  - [PECS (Producer Extends, Consumer Super)](#pecs-producer-extends-consumer-super)
- [Type Erasure](#type-erasure)
- [Practical Usage Example](#practical-usage-example)
- [💡 Class vs. Method Type Parameters](#-class-vs-method-type-parameters)
  - [Class Type Parameter](#class-type-parameter)
  - [Method Type Parameter](#method-type-parameter)
  - [Practical Example](#practical-example)
- [Why `extends` and Not `implements` in Type Parameters?](#why-extends-and-not-implements-in-type-parameters)
- [Summary of Wildcards and PECS](#summary-of-wildcards-and-pecs)
  - [`List<?>` (Unbounded Wildcard)](#list-unbounded-wildcard)
  - [`List<? extends T>` (Upper Bounded Wildcard)](#list-extends-t-upper-bounded-wildcard)
  - [`List<? super T>` (Lower Bounded Wildcard)](#list-super-t-lower-bounded-wildcard)
  - [PECS Summary](#pecs-summary)

---

## Why Use Generics?

Before Generics, Java collections (like `ArrayList` or `HashMap`) operated with the `Object` type. This led to two main problems:

1.  **Lack of Type Safety:** You could add any type of object to a collection. There was no compile-time guarantee that the collection would only contain the expected type of object.

    ```java
    List myList = new ArrayList(); // List of Objects
    myList.add("String 1");
    myList.add(123); // No one stops you from adding an Integer
    String s = (String) myList.get(1); // Runtime error: ClassCastException!
    ```

2.  **Need for Explicit Casts:** Explicit _casts_ were required when retrieving objects from a collection, which made the code more verbose and error-prone.

    ```java
    List myList = new ArrayList();
    myList.add("Hello");
    String s = (String) myList.get(0); // Cast necessary
    ```

**Generics solve this by allowing you to specify the type of objects a class or method will handle, ensuring type safety at compile-time and eliminating the need for explicit casts.**

```java
List<String> myStringList = new ArrayList<>(); // Now it's a List OF Strings
myStringList.add("String 1");
// myStringList.add(123); // Compile-time error! Does not allow adding an Integer
String s = myStringList.get(0); // No cast needed!
```

---

## Basic Generics Terminology

- **Parameterized Type:** A class or interface that is generic. E.g.: `List<String>`, `Map<Integer, String>`.
- **Type Parameter:** The "placeholder" for a type, usually represented by a single uppercase letter (e.g.: `<E>`, `<T>`, `<K>`, `<V>`).
  - `E` - Element (extensively used by the Java Collections Framework)
  - `K` - Key
  - `N` - Number
  - `T` - Type
  - `V` - Value
  - `S`, `U`, `V` - Second, third, and fourth types
- **Type Argument:** The actual type you provide when instantiating a parameterized type. E.g.: in `List<String>`, `String` is the type argument.
- **Diamond Operator (`<>`):** Introduced in Java 7, it allows the compiler to infer the type argument, making the code more concise.
  ```java
  List<String> myList = new ArrayList<>(); // Compiler infers <String>
  ```

---

## Using Generics

You can use Generics in:

### Generic Classes

A class can be generic by declaring one or more type parameters within `< >` after the class name.

```java
// Generic 'Box' class that can hold any type of object
public class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("Hello Generics!");
        System.out.println("String Box Content: " + stringBox.getContent()); // Output: Hello Generics!

        Box<Integer> integerBox = new Box<>(123);
        System.out.println("Integer Box Content: " + integerBox.getContent()); // Output: 123

        // Box<String> wrongBox = new Box<>(456); // Compile-time error!
    }
}
```

### Generic Interfaces

Like classes, interfaces can also have type parameters.

```java
// Generic interface for a converter
public interface Converter<S, T> {
    T convert(S source);
}

// Implementation of a String to Integer converter
public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.parseInt(source);
    }

    public static void main(String[] args) {
        Converter<String, Integer> converter = new StringToIntegerConverter();
        Integer number = converter.convert("100");
        System.out.println("String '100' converted to Integer: " + number); // Output: 100
    }
}
```

### Generic Methods

You can declare a method as generic regardless of whether the class it's in is generic or not. The type parameter is placed before the method's return type.

```java
public class GenericMethods {

    // Generic method to print elements of an array of any type
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    // Generic method to find the maximum of two comparables
    public static <T extends Comparable<T>> T findMax(T x, T y) {
        if (x.compareTo(y) > 0) {
            return x;
        } else {
            return y;
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.print("Integer Array: ");
        printArray(intArray); // Type inference: <Integer>

        System.out.print("Double Array: ");
        printArray(doubleArray); // Type inference: <Double>

        System.out.print("Character Array: ");
        printArray(charArray); // Type inference: <Character>

        System.out.println("Max between 5 and 10: " + findMax(5, 10)); // Type inference: <Integer>
        System.out.println("Max between 'apple' and 'banana': " + findMax("apple", "banana")); // Type inference: <String>
    }
}
```

---

## Bounded Type Parameters

Sometimes, you want to restrict the types that can be used as type parameters. This is done using `extends` to specify an upper class or an interface that the type must implement.

- `<T extends Comparable<T>>`: `T` must be a type that implements the `Comparable` interface. This allows calling `Comparable` methods (like `compareTo()`) on type `T`.
- `<T extends Number>`: `T` must be a subclass of `Number` (Integer, Double, Float, etc.).

<!-- end list -->

```java
// Only accepts types that are subclasses of Number
public class NumericBox<T extends Number> {
    private T number;

    public NumericBox(T number) {
        this.number = number;
    }

    public double doubleValue() {
        return number.doubleValue(); // We can call Number's methods
    }

    public static void main(String[] args) {
        NumericBox<Integer> intBox = new NumericBox<>(10);
        System.out.println("Double value of Integer: " + intBox.doubleValue());

        NumericBox<Double> doubleBox = new NumericBox<>(25.5);
        System.out.println("Double value of Double: " + doubleBox.doubleValue());

        // NumericBox<String> stringBox = new NumericBox<>("abc"); // Compile-time error! String is not a Number
    }
}
```

---

## Wildcards (`?`)

Wildcards are used in type arguments (in method calls or variable declarations) to express flexibility regarding the types that can be used.

- **`?` (Unbounded Wildcard):** Represents an unknown type. It's equivalent to `<?>`.
  ```java
  public void printList(List<?> list) { // Accepts List<String>, List<Integer>, etc.
      for (Object o : list) {
          System.out.println(o);
      }
  }
  ```
- **`? extends T` (Upper Bounded Wildcard):** Represents an unknown type that is `T` or a **subclass** of `T`. Used when you want to **read** data from a generic structure ( **PECS principle: Producer Extends**).
  ```java
  public void addNumbers(List<? extends Number> list) { // Accepts List<Integer>, List<Double>, etc.
      // list.add(new Integer(1)); // Compile-time error! Cannot add (unless it's null)
      Number num = list.get(0); // Can read as Number
  }
  ```
- **`? super T` (Lower Bounded Wildcard):** Represents an unknown type that is `T` or a **superclass** of `T`. Used when you want to **write** data to a generic structure ( **PECS principle: Consumer Super**).
  ```java
  public void addIntegers(List<? super Integer> list) { // Accepts List<Integer>, List<Number>, List<Object>
      list.add(10);    // Can add Integer
      list.add(20);    // Can add Integer
      // Number num = (Number) list.get(0); // Requires cast and is not guaranteed to be a Number
      Object obj = list.get(0); // Can read as Object
  }
  ```

### PECS (Producer Extends, Consumer Super)

A useful mnemonic rule for wildcards:

- If you are going to **read** (produce) items from a generic collection, use `? extends T`.
- If you are going to **write** (consume) items to a generic collection, use `? super T`.

---

## Type Erasure

A crucial concept in Generics is **Type Erasure**. Java implements Generics using type erasure, meaning that generic type information (like `<T>` or `<String>`) is **removed at compile-time**. The compiler uses this information to check for type safety, but in the resulting _bytecode_, all generic types are replaced by their bounds (or `Object` if there is no bound).

- **Implications:**
  - You cannot use generic type information at runtime (e.g.: `T.class`, `instanceof T`).
  - You cannot create arrays of generic types directly (e.g.: `new T[10]`).
  - `List<String>` and `List<Integer>` are the same at runtime for the JVM (`List`).

This is important for **backward compatibility** with older Java versions, but it means that Generics are primarily a **compile-time** feature.

---

## Practical Usage Example

```java
import java.util.ArrayList;
import java.util.List;

public class GenericsExample {

    // Generic method to copy elements from one list to another
    // PECS: source is a Producer (extends), dest is a Consumer (super)
    public static <T> void copyList(List<? extends T> source, List<? super T> dest) {
        for (T element : source) {
            dest.add(element);
        }
    }

    public static void main(String[] args) {
        List<Integer> sourceInts = new ArrayList<>();
        sourceInts.add(1);
        sourceInts.add(2);

        List<Number> destNums = new ArrayList<>(); // Can accept Integers
        copyList(sourceInts, destNums);
        System.out.println("Numbers List after copy: " + destNums); // Output: [1, 2]

        List<Object> destObjects = new ArrayList<>(); // Can accept Integers
        copyList(sourceInts, destObjects);
        System.out.println("Objects List after copy: " + destObjects); // Output: [1, 2]

        List<Double> sourceDoubles = new ArrayList<>();
        sourceDoubles.add(3.14);

        // copyList(sourceDoubles, destInts); // Compile-time error: Double is not a subtype of Integer for 'super'
        copyList(sourceDoubles, destNums); // Works: Double is a subtype of Number, Number is a supertype of Double
        System.out.println("Numbers List after adding Doubles: " + destNums); // Output: [1, 2, 3.14]
    }
}
```

---

## 💡 Class vs. Method Type Parameters

When working with Generics, it's crucial to understand the difference between a **type parameter defined in the class** and a **type parameter defined in the method**.

### Class Type Parameter

- **Where it's defined:** After the class name (e.g.: `MyClass<T>`).
- **Scope:** The type `T` is valid throughout the **entire class**. All fields, constructors, and methods of that class can use and depend on this `T`.
- **Purpose:** Defines the type of data that the **entire instance of the class** will handle. Once you create `MyClass<String>`, `T` _is_ `String` for that instance.

### Method Type Parameter

- **Where it's defined:** **Before the method's return type** (e.g.: `public <U> U myMethod(...)`).
- **Scope:** The type `U` is valid **only within that specific method**. It is independent of any class type parameters.
- **Purpose:** Allows the **individual method** to be generic, operating with a type that may be different from the class's type, or introducing a new generic type in a non-generic class.

#### Practical Example:

```java
public class Box<T> { // T is the CLASS's type
    private T item;

    public Box(T item) {
        this.item = item;
    }

    // Uses the CLASS's T: Returns the type of item this Box contains
    public T getItem() {
        return item;
    }

    // <U> introduces a NEW generic type U, valid only for this METHOD
    public <U> void printAndSet(U newValue) {
        System.out.println("New value type: " + newValue.getClass().getName());
        // item = (T) newValue; // This would cause a compile-time error or ClassCastException!
        // 'U' is not necessarily compatible with 'T'.
    }
}
```

In the example above, `Box<T>` defines the type of the `item` the box stores. The `printAndSet<U>` method, on the other hand, is generic by itself, allowing you to pass _any_ type `U` to it, regardless of the `T` of the `Box`.

Understanding this distinction ensures you use type parameters in the correct place, avoiding compilation errors and guaranteeing the type safety that Generics offer.

---

## Why `extends` and Not `implements` in Type Parameters?

This is a very common and important question\!

In Java, when declaring **generic type parameters** (like `<T extends Comparable<T>>`), we use the keyword **`extends`** for both cases:

1.  When we want the type `T` to be a **subclass** of a particular class.
2.  When we want the type `T` to **implement** a particular interface.

**It's a peculiarity of Java's Generics syntax.**

**Think of it this way:**

- **`T extends MyBaseClass`**: Means "T must be `MyBaseClass` or one of its subclasses."
- **`T extends MyInterface`**: Means "T must implement `MyInterface`."

Even though `Comparable<T>` is an `interface`, the syntax for type bounding in Generics uses `extends` to indicate that type `T` must "extend" (or conform to) the behaviors defined by `Comparable<T>`.

If you tried to use `implements` (`<T implements Comparable<T>>`), the compiler would throw a syntax error.

---

## Summary of Wildcards and PECS

Imagine you have a **box (a `List`)** and you want it to be flexible enough to work with different types of "items" safely.

### `List<?>` (Unbounded Wildcard)

- **What it is:** A list of an **unknown type**.
- **Analogy:** Think of it as a **mystery box**. You know there are items inside, but you don't know what they are.
- **What you can do:**
  - **Read:** You can get items from it, but they will always come out as `Object`, because you're unsure what you're getting.
  - **Cannot write (almost never):** You cannot put any specific item (other than `null`) into it, because if the box is, for example, for `String`s, you can't put an `Integer` in it. Since you don't know the type, the compiler prevents you from putting almost anything to ensure safety.
- **When to use:** When you only need to **read** the elements from the list, and the specific type doesn't matter for the logic (e.g., printing all elements).

### `List<? extends T>` (Upper Bounded Wildcard)

- **What it is:** A list of `T` or of **any subtype of `T`**.
- **Analogy:** Think of it as a **box that only accepts fruits (or more specific types of fruits, like apples, bananas)**. `T` here would be "Fruit." The box can contain apples, bananas, or anything that _is_ a fruit.
- **What you can do:**
  - **Read (Producer):** You can get items from it and treat them as `T` (or a more generic type than `T`). If the box has apples, you can take them out and call them "fruit." **It's a `Producer` because you are `producing` (obtaining) values from it.**
  - **Cannot write (Consumer - Restricted):** You cannot put new items into it (except `null`). If the box was declared as `List<? extends Apple>`, it might be holding a list of `FujiApple` or `GalaApple`. If you try to add a `GreenApple`, the compiler has no way of knowing if `GreenApple` is compatible with the exact type of the list that was passed (`FujiApple` or `GalaApple`). Therefore, the general rule is: `extends` means you can _read_, but not _write_.
- **When to use (PECS: Producer Extends):** When you need to **read** (produce) items from a collection, and these items must be of a specific type or a subtype of that type. "Extends" indicates that the list's type is a "producer" of items of type `T` or its subtypes.

### `List<? super T>` (Lower Bounded Wildcard)

- **What it is:** A list of `T` or of **any supertype of `T`**.
- **Analogy:** Think of it as a **box that can receive apples (T), or more generic boxes that can hold apples, like a box of fruits or a box of edibles**.
- **What you can do:**
  - **Write (Consumer):** You can put items of type `T` (and its subtypes) into it. If the box is `List<? super Apple>`, you can put an `Apple` in it, or a `FujiApple`, because you are sure that any supertype of `Apple` will be able to store an `Apple` or its subtypes. **It's a `Consumer` because you are `consuming` (adding) values to it.**
  - **Cannot read (Producer - Restricted):** You can get items from it, but they will come out as `Object`. If the box is `List<? super Apple>`, it could be a `List<Fruit>` or `List<Object>`. If you get an item, you can't be sure it's an `Apple` without a cast, so the compiler forces you to treat it as `Object`.
- **When to use (PECS: Consumer Super):** When you need to **write** (consume) items into a collection, and these items are of a specific type or a supertype. "Super" indicates that the list is a "consumer" of items of type `T` or its subtypes.

---

### PECS Summary

**PECS** stands for:

- **P**roducer **E**xtends
- **C**onsumer **S**uper

It's a mnemonic rule to help you remember which wildcard to use:

- If the list is **producing** values for you (you are _reading_ from it), use `? extends T`.
- If the list is **consuming** values you are giving to it (you are _writing_ to it), use `? super T`.

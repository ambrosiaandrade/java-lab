# Java Collections Framework

![Java](https://img.shields.io/badge/Java-21-blue) ![Collections API](https://img.shields.io/badge/Collections--API-Java%20SE-yellow)

The **Java Collections Framework (JCF)** is a unified set of interfaces, implementations, and algorithms for representing and manipulating data collections. It standardizes how groups of objects are managed, offering robust and efficient data structures for various storage, access, and manipulation needs. By using the JCF, you benefit from cleaner, more reusable, and optimized code, avoiding the need to implement complex structures from scratch.

---

## 📚 Summary

- [Key Concepts](#key-concepts)
- [Main Interfaces](#main-interfaces)
- [Common Implementations](#common-implementations)
- [Generics and Type Safety](#generics-and-type-safety)
- [Stream API Integration](#stream-api-integration)
- [Performance Considerations](#performance-considerations)
- [Thread Safety](#thread-safety)
- [Basic Operations](#basic-operations)
- [Immutable Collections](#immutable-collections)
- [Usage Examples](#usage-examples)
- [Practical Exercises](#practical-exercises)
- [When to Use Collections?](#when-to-use-collections)
- [Tests](#tests)
- [Additional Resources](#additional-resources)

---

## Key Concepts

- **Collection:** Root interface of the framework, representing a group of objects. Serves as the base for interfaces like `List`, `Set`, and `Queue`.
- **Map:** A data structure that associates **unique keys with values**. It's important to note that `Map` **does not extend** the `Collection` interface, but is an integral part of the Collections Framework.
- **List:** An **ordered** collection (insertion order is maintained) that **allows duplicate elements**. Allows access to elements by index.
- **Set:** A collection that **does not allow duplicate elements**. Its main function is to ensure the uniqueness of elements. The order of elements is generally not guaranteed.
- **Queue:** A collection designed to hold elements prior to processing. Generally follows the **FIFO** (First-In, First-Out) principle.
- **Iterator:** An object that allows traversing a collection and safely removing elements during iteration.

---

## Main Interfaces

| Interface | Description | `Collection<E>` | Base interface for all collection types (List, Set, Queue). |
| `List<E>` | Ordered list that maintains insertion order and allows duplicates. |
| `Set<E>` | Set of unique elements, with no guaranteed order (except `LinkedHashSet` and `TreeSet`). |
| `Queue<E>` | Queue of elements, generally following FIFO order. |
| `Deque<E>` | Double-ended Queue, allowing insertion and removal at both ends (useful for stacks and queues). |
| `Map<K,V>` | Key-value mapping, where each key is unique. Does not inherit from `Collection`. |
| `Map.Entry<K,V>` | Nested interface within `Map` that represents a single key-value pair. |
| `Comparable<T>` | Interface to define the **natural ordering** of objects (e.g., alphabetical order for Strings). |
| `Comparator<T>` | Interface to define **custom orderings** of objects, typically passed to constructors of ordered collections (e.g., `TreeSet`, `PriorityQueue`). |

---

## Common Implementations

| Implementation | Description ```

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicOperations {
    public static void main(String[] args) {
        // Example of List (ArrayList)
        System.out.println("--- List (ArrayList) ---");
        List<String> names = new ArrayList<>();
        names.add("Java");        // Adds element
        names.add("Collections");
        names.add("Java");        // Allows duplicates
        System.out.println("List: " + names);
        System.out.println("Contains 'Java'? " + names.contains("Java")); // Checks existence
        names.remove("Collections"); // Removes the first occurrence
        System.out.println("List after removing 'Collections': " + names);
        System.out.println("Element at index 0: " + names.get(0)); // Access by index

        // Iteration with Enhanced For Loop
        System.out.println("\nIterating over the list:");
        for(String s : names) {
            System.out.println("- " + s);
        }

        // Example of Set (HashSet)
        System.out.println("\n--- Set (HashSet) ---");
        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(1); // Does not add duplicate
        System.out.println("Set: " + numbers); // Order not guaranteed
        System.out.println("Contains '2'? " + numbers.contains(2));
        numbers.remove(1);
        System.out.println("Set after removing '1': " + numbers);

        // Example of Map (HashMap)
        System.out.println("\n--- Map (HashMap) ---");
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 30);  // Adds key-value pair
        ages.put("Bob", 25);
        ages.put("Alice", 31);  // Updates the value for key 'Alice'
        System.out.println("Map: " + ages);
        System.out.println("Bob's age: " + ages.get("Bob")); // Accesses value by key
        ages.remove("Bob");     // Removes key-value pair
        System.out.println("Map after removing Bob: " + ages);

        // Iteration over Map with forEach (Java 8+)
        System.out.println("\nIterating over the map:");
        ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old."));
    }
}
```

---

## Immutable Collections

Starting from Java 9, it's possible to create immutable collections concisely and efficiently using the static `of()` methods of interfaces. This is useful to ensure that a collection will not be modified after its creation, which improves code safety and predictability.

```java
import java.util.List;
import java.util.Set;
import java.util.Map;

public class ImmutableCollections {
    public static void main(String[] args) {
        List<String> immutableList = List.of("A", "B", "C");
        System.out.println("Immutable List: " + immutableList);
        // immutableList.add("D"); // This would throw UnsupportedOperationException

        Set<Integer> immutableSet = Set.of(1, 2, 3);
        System.out.println("Immutable Set: " + immutableSet);

        Map<String, String> immutableMap = Map.of("key1", "value1", "key2", "value2");
        System.out.println("Immutable Map: " + immutableMap);
    }
}
```

---

## Usage Examples

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UsageExamples {
    public static void main(String[] args) {
        // Example 1: Sorting a list of strings (natural order)
        System.out.println("--- Sorting a list ---");
        List<String> names = new ArrayList<>(Arrays.asList("Maria", "João", "Ana", "Carlos"));
        Collections.sort(names); // Uses natural order (alphabetical for String)
        System.out.println("Sorted names: " + names); // Output: [Ana, Carlos, João, Maria]

        // Example 2: Using PriorityQueue with custom Comparator
        System.out.println("\n--- PriorityQueue with Comparator ---");
        // Prioritizes strings by shortest length
        PriorityQueue<String> queueByLength = new PriorityQueue<>(
            Comparator.comparingInt(String::length)
        );
        queueByLength.add("Apple");     // 5
        queueByLength.add("Banana");    // 6
        queueByLength.add("Grape");     // 5
        queueByLength.add("Peach");     // 5

        System.out.println("Elements from PriorityQueue (removing one by one by priority):");
        while (!queueByLength.isEmpty()) {
            System.out.println(queueByLength.poll());
        }
        // Expected output (order for same length might vary): Grape, Apple, Peach, Banana
        // Note: For "Maçã", "Uva", "Pêssego" in Portuguese, lengths are 4, 3, 7.
        // For "Apple", "Grape", "Peach" in English, lengths are 5, 5, 5.
        // The output for same lengths depends on internal tie-breaking.
        // If original Portuguese words are used: Maçã (4), Banana (6), Uva (3), Pêssego (7)
        // Output: Uva, Maçã, Banana, Pêssego
    }
}
```

---

## Practical Exercises

| File                                                                                                                                                                                              | Description                                                                                             |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | :------------------------------------------------------------------------------------------------------ |
| [`LabCollectionsToDo.java`](<https://www.google.com/search?q=%5Bhttps://www.google.com/search%3Fq%3D./LabCollectionsToDo.java%5D(https://www.google.com/search%3Fq%3D./LabCollectionsToDo.java)>) | Class to implement the proposed exercises on using and manipulating Collections.                        |
| [`LabCollectionsDone.java`](<https://www.google.com/search?q=%5Bhttps://www.google.com/search%3Fq%3D./LabCollectionsDone.java%5D(https://www.google.com/search%3Fq%3D./LabCollectionsDone.java)>) | Class containing complete and commented solutions for the exercises, serving as a reference.            |
| [`exercise.en-pt.md`](<https://www.google.com/search?q=%5Bhttps://www.google.com/search%3Fq%3D./exercise.en-pt.md%5D(https://www.google.com/search%3Fq%3D./exercise.en-pt.md)>)                   | Bilingual (English-Portuguese) list of exercises, with clear instructions and objectives for each task. |

---

## When to Use Collections?

- To **store and manage groups of objects** in an organized and efficient manner.
- When the need for a specific **data structure** (linear, unique, key-value, priority) is an important factor.
- To perform **common operations** such as adding, removing, searching, sorting, filtering, and transforming data.
- To **simplify development** and increase code readability, by using tested and optimized implementations.
- When dealing with **dynamic data** whose size may vary during program execution.

---

## Extra

It's more common to use **`ArrayList`** and **`HashMap`** in daily development. This is because they cover a wide range of scenarios, offering a good balance between performance and simplicity for quick access by index or by key, respectively.

However, the other Collections Framework implementations are not there by chance\! They shine in specific situations where their unique characteristics, such as **order guarantee**, **priority**, or **optimized performance for certain operations**, become crucial.

Let's explore examples and scenarios for each of them, showing when they are the ideal choice.

---

### When Order Matters (or is Special)

#### 1\. `LinkedList` (when order matters and you frequently modify the beginning/middle)

You've seen that `LinkedList` is efficient for insertions and removals at the beginning or in the middle. Think of systems where elements are constantly added and removed from the ends:

- **System waiting queue:** Imagine an online ordering system. New orders arrive at the end of the queue (`addLast`), and older orders are processed and removed from the beginning (`removeFirst`). A `LinkedList` (used as a `Queue` or `Deque`) is perfect for this, as these operations are very efficient.
- **Navigation history/undoable actions (Undo/Redo):** If you have a list of actions in a text editor, and the most recent actions are added while older ones are removed to limit the history, a `LinkedList` can be more performant than an `ArrayList` for managing these additions and removals at the "top" or "bottom" of the history.

---

#### 2\. `LinkedHashSet` (unique elements with insertion order)

Normally, a `HashSet` doesn't care about order. But what if you need **unique** elements and also want them to be retrieved in the **order they were added**?

- **Recently viewed items cache:** In an e-commerce site, you might want to show "last viewed products," but without duplicating products if the user views them multiple times. A `LinkedHashSet` would be ideal: it ensures each product appears only once and in the order it was first viewed.
- **Maintaining the order of selected options:** If a user selects several options from a list, and you need to process them in a specific order (the order of selection), but without allowing them to select the same option twice.

---

#### 3\. `TreeSet` (unique and always sorted elements)

`TreeSet` ensures that elements are **always in order** (alphabetical, numerical, or custom), in addition to being unique.

- **Alphabetical contact list:** If you need a unique list of names that is always presented in alphabetical order. Adding or removing a name automatically inserts it in the correct place.
- **Game score ranking:** Maintaining a ranking of the highest scores, where each score (if it's an object that implements `Comparable` or uses a `Comparator`) is unique and the set needs to be always sorted.
- **Remove duplicates and sort at the same time:** If you have a `List` with many unsorted and duplicate items and need a final list with unique and sorted items, converting to a `TreeSet` and then back to a `List` is an efficient way to do this in a single operation.

---

#### 4\. `TreeMap` (Maps where keys need to be ordered)

Like `TreeSet`, `TreeMap` maintains **keys in order**. This is crucial when you need to iterate over the map in a predictable key sequence or find elements within a **key range**.

- **Dictionary or index:** A dictionary (key=word, value=definition) can be stored in a `TreeMap` so that words are always in alphabetical order, facilitating navigation or searching for word ranges (e.g., "all words between 'house' and 'car'").
- **Temporal data:** If keys are dates/times and you need to access or iterate data chronologically. For example, bank transaction records by date, where you want to see all transactions for a specific month.
- **Value ranges:** If you need to find the first key greater than X, or the last key less than Y. `TreeMap` offers specific methods for this (`lowerKey()`, `higherKey()`, `subMap()`), which are not available in `HashMap`.

---

#### 5\. `PriorityQueue` (Processing order is by priority, not by insertion)

This is the "emergency queue" we discussed. Elements are dequeued in **highest priority** order, not in the order they were added.

- **Task Scheduler:** An operating system might use a `PriorityQueue` to schedule processes. "High" priority tasks are executed before "low" priority tasks, regardless of when they were submitted.
- **Discrete event simulations:** In simulations (e.g., of a bank queue or network events), events are processed not in the order they were created, but in the order of the time they are supposed to occur. A `PriorityQueue` stores the events and always returns the next event to be processed (the one with the lowest time).
- **Shortest path algorithms (Dijkstra):** Graph algorithms that always need to visit the most "promising" node (the one with the lowest accumulated cost so far) use a priority queue to store the nodes to be explored.

---

## Tests

The tests for this lab are located at:
`../../tests/CollectionsTest.java`
They are essential for verifying the correctness of implementations and ensuring understanding of concepts.

---

## Additional Resources

- [Official Oracle Documentation - Collections Framework](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Collection.html) - The definitive source for detailed information.
- [Baeldung Guide: Java Collections Framework](https://www.baeldung.com/java-collections) - A comprehensive guide with many practical examples.
- [Oracle Tutorial: Collections](https://docs.oracle.com/javase/tutorial/collections/overview/index.html) - A good starting point for beginners.
- [Video: Java Collections Framework Explained](https://www.google.com/search?q=https://www.youtube.com/watch%3Fv%3DJjV2Q6moYhM) (Example external resource, adapt if you have a specific one)

<!-- end list -->

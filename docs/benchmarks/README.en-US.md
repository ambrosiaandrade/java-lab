# Benchmarking in Java

![Java](https://img.shields.io/badge/Java-21-blue) ![Benchmark](https://img.shields.io/badge/Benchmark-Java%208%2B-brightgreen)

Benchmarking is an essential technique to measure the performance of code snippets, identify bottlenecks, and compare different implementations. In Java, you can use simple approaches like `System.nanoTime()` or specialized frameworks like JMH (Java Microbenchmark Harness).

---

## 📚 Table of Contents

- [Basic Concepts](#basic-concepts)
- [Measuring with System.nanoTime()](#measuring-with-systemnanotime)
- [Measuring with System.currentTimeMillis()](#measuring-with-systemcurrenttimemillis)
- [Creating Generic Benchmark Methods](#creating-generic-benchmark-methods)
- [Introduction to JMH](#introduction-to-jmh)
- [Tips for Reliable Benchmarks](#tips-for-reliable-benchmarks)
- [Practical Exercises](#practical-exercises)
- [Additional Resources](#additional-resources)

---

## Basic Concepts

Benchmarking measures the execution time or resource usage of an operation to understand its performance. It’s crucial for optimizations and technical decisions.

---

## Measuring with System.nanoTime()

The most used method for measuring short times, with high resolution.

```java
long start = System.nanoTime();
// code to measure
long end = System.nanoTime();
System.out.println("Time: " + (end - start) + " ns");
```

---

## Measuring with System.currentTimeMillis()

Useful for longer operations, measuring time in milliseconds.

```java
long start = System.currentTimeMillis();
// code to measure
long end = System.currentTimeMillis();
System.out.println("Time: " + (end - start) + " ms");
```

---

## Creating Generic Benchmark Methods

Encapsulating measurement facilitates reuse and organization.

```java
public static void benchmark(Runnable task, String name) {
    long start = System.nanoTime();
    task.run();
    long end = System.nanoTime();
    System.out.println(name + " took " + (end - start) / 1_000_000 + " ms");
}
```

---

## Introduction to JMH

JMH is the official framework for microbenchmarks in Java. It handles warm-up, JVM optimizations, and precise data collection.

- Supports multiple modes (Throughput, Latency, etc.)
- Uses annotations to define benchmarks
- Integrates with Maven/Gradle

---

## Tips for Reliable Benchmarks

- Run multiple iterations and use averages
- Include warm-up runs so JVM can optimize code
- Avoid interference from Garbage Collector
- Clearly separate measured code from setup/other code
- Use specialized tools for complex benchmarks (e.g., JMH)

---

## Additional Resources

- [Oracle Documentation on System.nanoTime](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#nanoTime--)
- [JMH - Java Microbenchmark Harness](https://openjdk.org/projects/code-tools/jmh/)
- [Baeldung Article on Java Benchmarking](https://www.baeldung.com/java-microbenchmark-harness)
- [Best Practices in Java Benchmarking](https://shipilev.net/blog/2014/jmh-and-jvm-performance/)

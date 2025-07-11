# Concurrency (`java.util.concurrent`)

The `java.util.concurrent` package, introduced extensively in Java 5 and continuously enhanced, provides a powerful and robust framework for developing multithreaded applications. Concurrency is crucial for building responsive, scalable, and efficient systems, allowing programs to perform multiple tasks simultaneously.

This section explores the fundamental concepts and key components of Java's modern concurrency API, moving beyond basic `Thread` and `Runnable` to more sophisticated tools that simplify parallel programming and help avoid common pitfalls like deadlocks, race conditions, and inconsistent data.

---

## 📚 Summary

- [Why Concurrency?](#why-concurrency)
- [Core Concepts](#core-concepts)
- [Executors and Thread Pools](#executors-and-thread-pools)
- [Synchronizers](#synchronizers)
- [Concurrent Collections](#concurrent-collections)
- [Atomic Operations](#atomic-operations)
- [CompletableFuture](#completablefuture)
- [Best Practices & Pitfalls](#best-practices-pitfalls)
- [Practical Examples](#practical-examples)
  - [1\. `ThreadCreation.java`](#1-threadcreationjava)
  - [2\. `RaceConditionDemo.java`](#2-raceconditiondemojava)
  - [3\. `SynchronizedCounter.java`](#3-synchronizedcounterjava)
  - [4\. `ThreadPoolExample.java`](#4-threadpoolexamplejava)
  - [5\. `ProducerConsumerQueue.java`](#5-producerconsumerqueuejava)
  - [6\. `CountdownLatchExample.java`](#6-countdownlatchexamplejava)
  - [7\. `CompletableFutureChain.java`](#7-completablefuturechainjava)
  - [8\. `DiningPhilosophers.java`](#8-diningphilosophersjava)
- [Additional Resources](#additional-resources)

---

## Why Concurrency?

Modern applications often need to handle multiple operations at once:

- **Responsiveness:** Keeping the UI responsive while performing background tasks.
- **Performance:** Utilizing multi-core processors to speed up computations.
- **Scalability:** Handling more users or requests concurrently.
- **Resource Utilization:** Efficiently using I/O-bound operations (e.g., network requests, database calls) without blocking.

---

## Core Concepts

- **Thread:** The basic unit of execution. Java processes typically run with multiple threads.
- **Runnable/Callable:** Interfaces defining tasks to be executed by threads. `Runnable` doesn't return a value or throw checked exceptions, `Callable` does.
- **Race Condition:** When multiple threads access and modify shared data, leading to unpredictable results depending on the timing of their execution.
- **Synchronization:** Mechanisms to control access to shared resources to prevent race conditions and ensure data consistency.
- **Deadlock:** A situation where two or more threads are blocked indefinitely, waiting for each other to release a resource.
- **Liveness:** Refers to the ability of a program to execute in a timely manner (e.g., freedom from deadlocks, livelocks, starvation).
- **Visibility:** Ensuring that changes made by one thread to shared variables are visible to other threads.
- **Atomicity:** An operation is atomic if it completes entirely or not at all, appearing as a single, indivisible operation to other threads.

---

## Executors and Thread Pools

The `Executor` framework simplifies thread management. Instead of creating threads directly, you submit tasks to an `ExecutorService`, which manages a pool of threads. This reduces overhead and improves performance.

- **`Executor`**: A simple interface for executing `Runnable` tasks.
- **`ExecutorService`**: Extends `Executor`, providing methods for managing termination and producing `Future`s for tracking asynchronous task completion.
- **`Executors` Utility Class**: Factory methods for creating common `ExecutorService` configurations (e.g., `newFixedThreadPool`, `newCachedThreadPool`, `newSingleThreadExecutor`).
- **`Future`**: Represents the result of an asynchronous computation. Provides methods to check if the computation is complete, wait for its completion, and retrieve the result.

<!-- end list -->

```java
import java.util.concurrent.*;

public class ExecutorExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Pool of 2 threads

        Future<String> future1 = executor.submit(() -> {
            Thread.sleep(1000);
            return "Task 1 Completed";
        });

        Future<String> future2 = executor.submit(() -> {
            Thread.sleep(500);
            return "Task 2 Completed";
        });

        System.out.println(future2.get()); // Blocks until Task 2 is done
        System.out.println(future1.get()); // Blocks until Task 1 is done

        executor.shutdown(); // Initiates an orderly shutdown
    }
}
```

---

## Synchronizers

Tools designed to coordinate the execution of threads:

- **`Semaphore`**: Controls the number of threads that can access a resource concurrently.
- **`CountDownLatch`**: Allows one or more threads to wait until a set of operations being performed in other threads completes.
- **`CyclicBarrier`**: Allows a set of threads to all wait for each other to reach a common barrier point.
- **`Phaser`**: A more flexible alternative to `CountDownLatch` and `CyclicBarrier`, allowing dynamic registration of parties and multiple phases.
- **`Exchanger`**: Allows two threads to exchange objects at a synchronization point.

---

## Concurrent Collections

Thread-safe alternatives to standard `java.util` collections, optimized for concurrent access:

- **`ConcurrentHashMap`**: A thread-safe, high-performance alternative to `HashMap`.
- **`CopyOnWriteArrayList` / `CopyOnWriteArraySet`**: Thread-safe lists/sets where all mutative operations (add, set, remove) create a fresh copy of the underlying array. Good for collections that are rarely modified but often iterated.
- **`ConcurrentLinkedQueue`**: A thread-safe, unbounded, non-blocking queue.
- **`BlockingQueue` implementations (e.g., `ArrayBlockingQueue`, `LinkedBlockingQueue`)**: Queues that support operations that wait for the queue to become non-empty when retrieving an element, and wait for space to become available when storing an element. Crucial for producer-consumer patterns.

---

## Atomic Operations

Classes in `java.util.concurrent.atomic` provide thread-safe operations on single variables without using locks, leveraging low-level CPU instructions.

- **`AtomicInteger`**, **`AtomicLong`**, **`AtomicBoolean`**, **`AtomicReference`**: Provide atomic operations (e.g., `incrementAndGet()`, `compareAndSet()`) that ensure atomicity and visibility.

<!-- end list -->

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet(); // Atomically increments and gets the new value
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final counter value: " + counter.get()); // Will correctly be 2000
    }
}
```

---

## CompletableFuture

Introduced in Java 8, `CompletableFuture` is a powerful class for asynchronous programming. It represents a `Future` that can be explicitly completed by setting its value and supports chaining dependent operations.

- **Non-blocking operations:** Allows you to define callbacks that execute when a computation completes.
- **Composition:** Combine multiple asynchronous operations, handle errors, and transform results.
- **Replaces manual callbacks:** A much cleaner way to manage complex asynchronous workflows.

<!-- end list -->

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> greetingFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello";
        });

        CompletableFuture<String> combinedFuture = greetingFuture.thenApply(greeting -> greeting + " World")
                                                                 .thenApply(finalGreeting -> finalGreeting + "!");

        combinedFuture.thenAccept(System.out::println); // Prints "Hello World!" after 1 second

        // Keep main thread alive for the async tasks to complete
        TimeUnit.SECONDS.sleep(2);
    }
}
```

---

## Best Practices & Pitfalls

- **Prefer high-level concurrency utilities:** Use `ExecutorService`, concurrent collections, and `CompletableFuture` over low-level `Thread` and `synchronized` blocks when possible.
- **Minimize shared mutable state:** The less shared state, the fewer synchronization issues. Favor immutability.
- **Use appropriate synchronizers:** Choose the right tool for the job (`Semaphore` vs. `CountDownLatch`, etc.).
- **Understand `volatile` keyword:** Ensures visibility of variable changes across threads, but not atomicity.
- **Avoid deadlocks:** Design your locking order carefully.
- **Handle exceptions in threads:** Uncaught exceptions in threads can terminate them silently. Use `UncaughtExceptionHandler` or `Future.get()`.
- **Properly shut down `ExecutorService`s:** Always call `shutdown()` and/or `shutdownNow()` to release resources.

---

## Practical Examples

Here you'll find practical examples demonstrating various concurrency concepts:

---

### 1\. `ThreadCreation.java`

This example shows the two primary ways to create and run threads in Java: by extending `Thread` and by implementing `Runnable`.

```java
// src/main/java/com/yourlab/concurrency/ThreadCreation.java
package com.yourlab.concurrency;

public class ThreadCreation {

    // Option 1: Extend the Thread class
    static class MyThread extends Thread {
        private String threadName;

        public MyThread(String name) {
            this.threadName = name;
            System.out.println("Creating " + threadName);
        }

        @Override
        public void run() {
            System.out.println("Running " + threadName);
            try {
                for (int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + threadName + ", " + i);
                    // Let the thread sleep for a while.
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread " + threadName + " interrupted.");
            }
            System.out.println("Thread " + threadName + " exiting.");
        }
    }

    // Option 2: Implement the Runnable interface (more common and flexible)
    static class MyRunnable implements Runnable {
        private String threadName;

        public MyRunnable(String name) {
            this.threadName = name;
            System.out.println("Creating " + threadName);
        }

        @Override
        public void run() {
            System.out.println("Running " + threadName);
            try {
                for (int i = 4; i > 0; i--) {
                    System.out.println("Runnable: " + threadName + ", " + i);
                    Thread.sleep(70);
                }
            } catch (InterruptedException e) {
                System.out.println("Runnable " + threadName + " interrupted.");
            }
            System.out.println("Runnable " + threadName + " exiting.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Main thread started.");

        // Using Thread class
        MyThread thread1 = new MyThread("Thread-1");
        thread1.start(); // Calls run() method

        // Using Runnable interface (preferred)
        Thread thread2 = new Thread(new MyRunnable("Runnable-1"));
        thread2.start();

        // Using an anonymous Runnable with a lambda expression (common in modern Java)
        Thread thread3 = new Thread(() -> {
            System.out.println("Running Lambda Thread");
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("Lambda: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Lambda Thread interrupted.");
            }
            System.out.println("Lambda Thread exiting.");
        });
        thread3.start();

        System.out.println("Main thread finished starting other threads.");
    }
}
```

---

### 2\. `RaceConditionDemo.java`

This example demonstrates a **race condition** where multiple threads try to increment a shared counter without proper synchronization, leading to an incorrect final value.

```java
// src/main/java/com/yourlab/concurrency/RaceConditionDemo.java
package com.yourlab.concurrency;

public class RaceConditionDemo {

    private static int counter = 0; // Shared mutable state

    public static void main(String[] args) throws InterruptedException {
        Runnable incrementTask = () -> {
            for (int i = 0; i < 10000; i++) {
                counter++; // This operation is not atomic
            }
        };

        Thread t1 = new Thread(incrementTask, "Thread-1");
        Thread t2 = new Thread(incrementTask, "Thread-2");

        t1.start();
        t2.start();

        // Wait for both threads to finish
        t1.join();
        t2.join();

        // The expected value is 20000, but it will likely be less due to race condition
        System.out.println("Final counter value (expected 20000): " + counter);
    }
}
```

---

### 3\. `SynchronizedCounter.java`

This example shows how to resolve the race condition from `RaceConditionDemo` using **`synchronized`** methods and **`AtomicInteger`** for thread-safe counter increments.

```java
// src/main/java/com/yourlab/concurrency/SynchronizedCounter.java
package com.yourlab.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedCounter {

    // Option 1: Using synchronized method
    static class SynchronizedValue {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    // Option 2: Using AtomicInteger (preferred for single variable atomicity)
    static class AtomicValue {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet(); // Atomic operation
        }

        public int getCount() {
            return count.get();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // --- Demonstrate synchronized method ---
        System.out.println("--- Using Synchronized Method ---");
        SynchronizedValue syncCounter = new SynchronizedValue();
        Runnable syncTask = () -> {
            for (int i = 0; i < 10000; i++) {
                syncCounter.increment();
            }
        };

        Thread syncT1 = new Thread(syncTask, "SyncThread-1");
        Thread syncT2 = new Thread(syncTask, "SyncThread-2");

        syncT1.start();
        syncT2.start();

        syncT1.join();
        syncT2.join();
        System.out.println("Final synchronized counter value (expected 20000): " + syncCounter.getCount());


        // --- Demonstrate AtomicInteger ---
        System.out.println("\n--- Using AtomicInteger ---");
        AtomicValue atomicCounter = new AtomicValue();
        Runnable atomicTask = () -> {
            for (int i = 0; i < 10000; i++) {
                atomicCounter.increment();
            }
        };

        Thread atomicT1 = new Thread(atomicTask, "AtomicThread-1");
        Thread atomicT2 = new Thread(atomicTask, "AtomicThread-2");

        atomicT1.start();
        atomicT2.start();

        atomicT1.join();
        atomicT2.join();
        System.out.println("Final atomic counter value (expected 20000): " + atomicCounter.getCount());
    }
}
```

---

### 4\. `ThreadPoolExample.java`

This example showcases the use of `ExecutorService` and `Executors` to manage a fixed-size thread pool, executing multiple `Runnable` and `Callable` tasks.

```java
// src/main/java/com/yourlab/concurrency/ThreadPoolExample.java
package com.yourlab.concurrency;

import java.util.concurrent.*;

public class ThreadPoolExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Create a fixed-size thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("Submitting tasks...");

        // Submit Runnable tasks (fire and forget, no return value)
        executor.execute(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Runnable Task 1 executed by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                Thread.sleep(500);
                System.out.println("Runnable Task 2 executed by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Submit Callable tasks (returns a value and can throw exceptions)
        Future<String> future1 = executor.submit(() -> {
            System.out.println("Callable Task 3 started by " + Thread.currentThread().getName());
            Thread.sleep(2000);
            return "Result of Task 3";
        });

        Future<Integer> future2 = executor.submit(() -> {
            System.out.println("Callable Task 4 started by " + Thread.currentThread().getName());
            Thread.sleep(1500);
            return 123;
        });

        // Retrieve results from Future objects
        System.out.println("Getting results from futures...");
        try {
            System.out.println("Future 1 Result: " + future1.get()); // Blocks until task 3 completes
            System.out.println("Future 2 Result: " + future2.get()); // Blocks until task 4 completes
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error getting future result: " + e.getMessage());
        }

        // Check if tasks are done without blocking
        if (future1.isDone()) {
            System.out.println("Future 1 is done already!");
        }

        // Attempt to shut down the executor gracefully
        executor.shutdown(); // Prevents new tasks from being submitted
        System.out.println("Executor shutdown initiated.");

        // Wait for all submitted tasks to complete or a timeout occurs
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("ThreadPool did not terminate in time, forcing shutdown...");
                executor.shutdownNow(); // Attempts to stop all actively executing tasks
            }
        } catch (InterruptedException e) {
            System.err.println("Termination interrupted: " + e.getMessage());
            executor.shutdownNow();
        }
        System.out.println("Executor terminated.");
    }
}
```

---

### 5\. `ProducerConsumerQueue.java`

This example implements the classic Producer-Consumer pattern using a `BlockingQueue` to safely transfer data between threads.

```java
// src/main/java/com/yourlab/concurrency/ProducerConsumerQueue.java
package com.yourlab.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerQueue {

    public static void main(String[] args) throws InterruptedException {
        // Create a BlockingQueue with a capacity of 10
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        // Producer thread
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 20; i++) {
                    System.out.println("Producer: Producing item " + i);
                    queue.put(i); // Blocks if the queue is full
                    Thread.sleep(100); // Simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted.");
            }
        };

        // Consumer thread
        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 20; i++) {
                    Integer item = queue.take(); // Blocks if the queue is empty
                    System.out.println("Consumer: Consuming item " + item);
                    Thread.sleep(300); // Simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted.");
            }
        };

        Thread producerThread = new Thread(producer, "ProducerThread");
        Thread consumerThread = new Thread(consumer, "ConsumerThread");

        producerThread.start();
        consumerThread.start();

        producerThread.join(); // Wait for producer to finish
        consumerThread.join(); // Wait for consumer to finish

        System.out.println("Producer-Consumer example finished.");
    }
}
```

---

### 6\. `CountdownLatchExample.java`

This example demonstrates `CountDownLatch`, a synchronizer that allows one or more threads to wait until a set of operations being performed in other threads completes.

```java
// src/main/java/com/yourlab/concurrency/CountdownLatchExample.java
package com.yourlab.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountdownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        // Create a CountDownLatch with a count of 3.
        // The main thread will wait until this count reaches zero.
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Task 1: Simulates loading configuration
        executor.submit(() -> {
            try {
                System.out.println("Task 1: Loading configuration...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Task 1: Configuration loaded.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown(); // Decrement the latch count
            }
        });

        // Task 2: Simulates connecting to database
        executor.submit(() -> {
            try {
                System.out.println("Task 2: Connecting to database...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Task 2: Database connected.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown(); // Decrement the latch count
            }
        });

        // Task 3: Simulates initializing services
        executor.submit(() -> {
            try {
                System.out.println("Task 3: Initializing services...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Task 3: Services initialized.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown(); // Decrement the latch count
            }
        });

        System.out.println("Main thread: Waiting for all startup tasks to complete...");

        // The main thread waits here until the latch's count reaches zero
        latch.await(); // Blocks until countDown() is called 3 times

        System.out.println("Main thread: All startup tasks completed. Application is ready!");

        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
```

---

### 7\. `CompletableFutureChain.java`

This example demonstrates how to chain multiple asynchronous operations using `CompletableFuture`, including transformations and error handling.

```java
// src/main/java/com/yourlab/concurrency/CompletableFutureChain.java
package com.yourlab.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureChain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Starting CompletableFuture chain example...");

        // 1. supplyAsync: Get user ID asynchronously
        CompletableFuture<String> userIdFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Step 1: Fetching User ID in " + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(500); // Simulate network call
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "user123";
        });

        // 2. thenCompose: Use user ID to fetch user details (dependent async operation)
        CompletableFuture<String> userDetailsFuture = userIdFuture.thenCompose(userId -> {
            System.out.println("Step 2: Fetching details for " + userId + " in " + Thread.currentThread().getName());
            return CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(700); // Simulate another network call
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Details for " + userId + ": John Doe, Age 30";
            });
        });

        // 3. thenApply: Transform the details into a welcome message
        CompletableFuture<String> welcomeMessageFuture = userDetailsFuture.thenApply(details -> {
            System.out.println("Step 3: Creating welcome message in " + Thread.currentThread().getName());
            return "Welcome! Here are your details: " + details;
        });

        // 4. thenAccept: Consume the final result (no return value)
        welcomeMessageFuture.thenAccept(message -> {
            System.out.println("Step 4: Displaying final message in " + Thread.currentThread().getName());
            System.out.println(message);
        });

        // 5. exceptionally: Handle errors at any point in the chain
        CompletableFuture<String> errorHandlingFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("\nStep X: Starting task that might fail in " + Thread.currentThread().getName());
            if (true) { // Simulate an error condition
                throw new RuntimeException("Something went wrong during data processing!");
            }
            return "Data processed.";
        }).exceptionally(ex -> {
            System.err.println("Error caught in exceptionally: " + ex.getMessage() + " in " + Thread.currentThread().getName());
            return "Error: Could not process data."; // Return a fallback value
        }).thenApply(result -> {
            // This step will execute with the fallback value if an error occurred
            System.out.println("Step Y: Continuing after error/success: " + result + " in " + Thread.currentThread().getName());
            return result.toUpperCase();
        });


        System.out.println("\nMain thread continues its work while futures run concurrently...");

        // Wait for all futures to complete (or for demonstration purposes)
        // In a real application, you'd likely attach more `then` callbacks
        // or join only at the very end if necessary.
        userIdFuture.join(); // Blocking, just to ensure main thread waits for chain to finish
        userDetailsFuture.join();
        welcomeMessageFuture.join();
        errorHandlingFuture.join();


        System.out.println("Main thread finished.");
    }
}
```

---

### 8\. `DiningPhilosophers.java`

This is a classic problem in concurrency that illustrates **deadlock**. Five philosophers repeatedly think and eat. To eat, a philosopher needs two chopsticks (forks) – one to their left and one to their right. The problem arises when all philosophers pick up their left chopstick simultaneously, then wait indefinitely for their right chopstick, which is held by their neighbor.

This example provides the basic deadlock scenario. To make it a full lab exercise, you'd then add solutions (e.g., ordered resource acquisition, a monitor/mediator, or a `Semaphore`).

```java
// src/main/java/com/yourlab/concurrency/DiningPhilosophers.java
package com.yourlab.concurrency;

import java.util.concurrent.TimeUnit;

public class DiningPhilosophers {

    static class Philosopher implements Runnable {
        private String name;
        private Chopstick leftChopstick;
        private Chopstick rightChopstick;

        public Philosopher(String name, Chopstick left, Chopstick right) {
            this.name = name;
            this.leftChopstick = left;
            this.rightChopstick = right;
        }

        private void think() throws InterruptedException {
            System.out.println(name + " is thinking.");
            TimeUnit.MILLISECONDS.sleep(Math.round(Math.random() * 500) + 100);
        }

        private void eat() throws InterruptedException {
            System.out.println(name + " is eating.");
            TimeUnit.MILLISECONDS.sleep(Math.round(Math.random() * 500) + 100);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    think();

                    // This order often leads to deadlock
                    synchronized (leftChopstick) {
                        System.out.println(name + " picked up left chopstick " + leftChopstick.getId());
                        synchronized (rightChopstick) {
                            System.out.println(name + " picked up right chopstick " + rightChopstick.getId());
                            eat();
                            System.out.println(name + " put down right chopstick " + rightChopstick.getId());
                        }
                        System.out.println(name + " put down left chopstick " + leftChopstick.getId());
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(name + " was interrupted.");
            }
        }
    }

    static class Chopstick {
        private int id;

        public Chopstick(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public static void main(String[] args) {
        int numPhilosophers = 5;
        Chopstick[] chopsticks = new Chopstick[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        Thread[] philosophers = new Thread[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            // Philosopher i takes left chopstick i and right chopstick (i+1)%numPhilosophers
            // This symmetrical acquisition leads to deadlock
            philosophers[i] = new Thread(
                new Philosopher("Philosopher-" + i, chopsticks[i], chopsticks[(i + 1) % numPhilosophers])
            );
            philosophers[i].start();
        }

        System.out.println("Dining Philosophers simulation started. Watch for deadlocks!");
        // You'll likely see output stop or become very sparse, indicating deadlock.
        // Press Ctrl+C to stop the program.
    }
}
```

---

## Additional Resources

- [Oracle Java Concurrency Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)
- [Java Concurrency in Practice (Book by Brian Goetz et al.)](https://www.amazon.com/Java-Concurrency-Practice-Brian-Goetz/dp/0321349601) - A must-read for serious Java concurrency.
- [Baeldung - Guide to Java Concurrency](https://www.baeldung.com/java-concurrency)
- [IBM Developer - Java theory and practice: Concurrency](https://www.google.com/search?q=https://www.ibm.com/developerworks/java/library/j-jtp/)

---

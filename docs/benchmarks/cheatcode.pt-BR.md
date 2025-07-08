# 📊 Cheatsheet — Benchmark em Java

---

## 1. Benchmark Básico com `System.nanoTime()`

```java
long startTime = System.nanoTime();

// Código a ser medido
for (int i = 0; i < 1_000_000; i++) {
    Math.sqrt(i);
}

long endTime = System.nanoTime();

long duration = endTime - startTime; // duração em nanossegundos
System.out.println("Duração (ns): " + duration);
System.out.println("Duração (ms): " + duration / 1_000_000);
```

---

## 2. Benchmark com `System.currentTimeMillis()`

Útil para medições mais longas (milissegundos).

```java
long start = System.currentTimeMillis();

// Código pesado
Thread.sleep(500);

long end = System.currentTimeMillis();
System.out.println("Tempo decorrido: " + (end - start) + " ms");
```

---

## 3. Criando Método Genérico para Benchmark

```java
public static void benchmark(Runnable task, String taskName) {
    long start = System.nanoTime();
    task.run();
    long end = System.nanoTime();
    System.out.println(taskName + " demorou " + (end - start)/1_000_000 + " ms");
}

// Uso:
benchmark(() -> {
    // código que deseja medir
    for (int i = 0; i < 100_000; i++) {
        Math.log(i+1);
    }
}, "Cálculo de logaritmos");
```

---

## 4. Benchmark com JMH (Java Microbenchmark Harness)

Para benchmarks robustos, use o JMH, que é o padrão da indústria.

### Dependência Maven

```xml
<dependency>
    <groupId>org.openjdk.jmh</groupId>
    <artifactId>jmh-core</artifactId>
    <version>1.38</version>
</dependency>
```

### Exemplo básico JMH

```java
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class MeuBenchmark {

    @Benchmark
    public void testeSimples() {
        Math.sqrt(12345);
    }
}
```

_Rodar via plugin Maven/Gradle ou linha de comando._

---

## 5. Dicas para benchmark eficaz

- **Evite otimizações do compilador**: faça operações que o compilador não elimina.
- **Use várias iterações** para média estável.
- **Evite coletor de lixo (GC) durante a medição** (JMH ajuda nisso).
- **Considere “warm-up”** para o JVM otimizar antes da medição.
- **Isolar código a ser medido** em métodos ou lambdas.

---

## 6. Medindo uso de memória (simples)

```java
Runtime runtime = Runtime.getRuntime();

runtime.gc(); // Força coleta de lixo

long beforeMem = runtime.totalMemory() - runtime.freeMemory();

// Código que consome memória

long afterMem = runtime.totalMemory() - runtime.freeMemory();

System.out.println("Memória usada: " + (afterMem - beforeMem) + " bytes");
```

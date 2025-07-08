# Benchmark em Java

![Java](https://img.shields.io/badge/Java-21-blue) ![Benchmark](https://img.shields.io/badge/Benchmark-Java%208%2B-brightgreen)

Benchmarking é uma técnica essencial para medir o desempenho de trechos de código, identificando gargalos e comparando diferentes implementações. Em Java, existem desde abordagens simples com `System.nanoTime()` até frameworks especializados como o JMH (Java Microbenchmark Harness).

---

## 📚 Sumário

- [Conceitos Básicos](#conceitos-básicos)
- [Medição com System.nanoTime()](#medição-com-systemnanotime)
- [Medição com System.currentTimeMillis()](#medição-com-systemcurrenttimemillis)
- [Criando Métodos Genéricos para Benchmark](#criando-métodos-genéricos-para-benchmark)
- [Introdução ao JMH](#introdução-ao-jmh)
- [Dicas para Benchmarks Confiáveis](#dicas-para-benchmarks-confiáveis)
- [Exercícios Práticos](#exercícios-práticos)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Básicos

Benchmarking mede o tempo de execução ou uso de recursos de uma operação para entender seu desempenho. É fundamental para otimizações e escolhas técnicas.

---

## Medição com System.nanoTime()

Método mais usado para medir tempos curtos, com alta resolução.

```java
long start = System.nanoTime();
// código a medir
long end = System.nanoTime();
System.out.println("Tempo: " + (end - start) + " ns");
```

---

## Medição com System.currentTimeMillis()

Útil para operações mais longas, mede tempo em milissegundos.

```java
long start = System.currentTimeMillis();
// código a medir
long end = System.currentTimeMillis();
System.out.println("Tempo: " + (end - start) + " ms");
```

---

## Criando Métodos Genéricos para Benchmark

Encapsular a medição facilita reutilização e organização.

```java
public static void benchmark(Runnable task, String nome) {
    long start = System.nanoTime();
    task.run();
    long end = System.nanoTime();
    System.out.println(nome + " demorou " + (end - start)/1_000_000 + " ms");
}
```

---

## Introdução ao JMH

JMH é o framework oficial para microbenchmarks em Java. Ele cuida de “warm-up”, otimizações da JVM e coleta de dados precisos.

- Suporta múltiplos modos (Throughput, Latency, etc.)
- Usa anotações para definir benchmarks
- Integra-se com Maven/Gradle

---

## Dicas para Benchmarks Confiáveis

- Faça múltiplas execuções e use média
- Inclua “warm-up” para JVM otimizar o código
- Evite interferência do Garbage Collector
- Separe claramente o código medido de setup/outro código
- Use ferramentas especializadas para benchmarks complexos (ex: JMH)

---

## Recursos Adicionais

- [Documentação Oracle sobre System.nanoTime](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#nanoTime--)
- [JMH - Java Microbenchmark Harness](https://openjdk.org/projects/code-tools/jmh/)
- [Artigo Baeldung sobre Benchmark em Java](https://www.baeldung.com/java-microbenchmark-harness)
- [Melhores práticas em benchmarks Java](https://shipilev.net/blog/2014/jmh-and-jvm-performance/)

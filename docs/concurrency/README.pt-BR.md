# Concorrência (`java.util.concurrent`)

O pacote `java.util.concurrent`, introduzido extensivamente no Java 5 e continuamente aprimorado, oferece um framework poderoso e robusto para o desenvolvimento de aplicações multi-threaded. A concorrência é crucial para construir sistemas responsivos, escaláveis e eficientes, permitindo que programas executem múltiplas tarefas simultaneamente.

Esta seção explora os conceitos fundamentais e os componentes chave da moderna API de concorrência do Java, indo além do básico `Thread` e `Runnable` para ferramentas mais sofisticadas que simplificam a programação paralela e ajudam a evitar armadilhas comuns como deadlocks, condições de corrida e dados inconsistentes.

---

## 📚 Sumário

- [Por Que Concorrência?](#por-que-concorrência)
- [Conceitos Essenciais](#conceitos-essenciais)
- [Executors e Pools de Threads](#executors-e-pools-de-threads)
- [Sincronizadores](#sincronizadores)
- [Coleções Concorrentes](#coleções-concorrentes)
- [Operações Atômicas](#operações-atômicas)
- [CompletableFuture](#completablefuture)
- [Boas Práticas e Armadilhas](#boas-práticas-e-armadilhas)
- [Exemplos Práticos](#exemplos-práticos)
  - [1\. `ThreadCreation.java`](#1-threadcreationjava)
  - [2\. `RaceConditionDemo.java`](#2-raceconditiondemojava)
  - [3\. `SynchronizedCounter.java`](#3-synchronizedcounterjava)
  - [4\. `ThreadPoolExample.java`](#4-threadpoolexamplejava)
  - [5\. `ProducerConsumerQueue.java`](#5-producerconsumerqueuejava)
  - [6\. `CountdownLatchExample.java`](#6-countdownlatchexamplejava)
  - [7\. `CompletableFutureChain.java`](#7-completablefuturechainjava)
  - [8\. `DiningPhilosophers.java`](#8-diningphilosophersjava)
- [Recursos Adicionais](#recursos-adicionais)

---

## Por Que Concorrência?

Aplicações modernas frequentemente precisam lidar com múltiplas operações ao mesmo tempo:

- **Responsividade:** Manter a interface do usuário responsiva enquanto executa tarefas em segundo plano.
- **Desempenho:** Utilizar processadores multi-core para acelerar computações.
- **Escalabilidade:** Lidar com mais usuários ou requisições simultaneamente.
- **Utilização de Recursos:** Usar eficientemente operações de I/O (ex: requisições de rede, chamadas de banco de dados) sem bloquear.

---

## Conceitos Essenciais

- **Thread:** A unidade básica de execução. Processos Java tipicamente executam com múltiplas threads.
- **Runnable/Callable:** Interfaces que definem tarefas a serem executadas por threads. `Runnable` não retorna valor nem lança exceções checadas; `Callable` faz ambos.
- **Condição de Corrida (Race Condition):** Ocorre quando múltiplas threads acessam e modificam dados compartilhados, levando a resultados imprevisíveis dependendo do momento de sua execução.
- **Sincronização:** Mecanismos para controlar o acesso a recursos compartilhados para prevenir condições de corrida e garantir a consistência dos dados.
- **Deadlock:** Uma situação onde duas ou mais threads ficam bloqueadas indefinidamente, esperando uma pela outra para liberar um recurso.
- **Vivacidade (Liveness):** Refere-se à capacidade de um programa de executar em tempo hábil (ex: ausência de deadlocks, livelocks, inanição/starvation).
- **Visibilidade:** Garantir que as alterações feitas por uma thread em variáveis compartilhadas sejam visíveis para outras threads.
- **Atomicidade:** Uma operação é atômica se ela é concluída inteiramente ou não é executada, aparecendo como uma única operação indivisível para outras threads.

---

## Executors e Pools de Threads

O framework `Executor` simplifica o gerenciamento de threads. Em vez de criar threads diretamente, você submete tarefas a um `ExecutorService`, que gerencia um pool de threads. Isso reduz a sobrecarga e melhora o desempenho.

- **`Executor`**: Uma interface simples para executar tarefas `Runnable`.
- **`ExecutorService`**: Estende `Executor`, fornecendo métodos para gerenciar o encerramento e produzir `Future`s para rastrear a conclusão de tarefas assíncronas.
- **Classe Utilitária `Executors`**: Métodos de fábrica para criar configurações comuns de `ExecutorService` (ex: `newFixedThreadPool`, `newCachedThreadPool`, `newSingleThreadExecutor`).
- **`Future`**: Representa o resultado de uma computação assíncrona. Fornece métodos para verificar se a computação está completa, esperar por sua conclusão e recuperar o resultado.

<!-- end list -->

```java
import java.util.concurrent.*;

public class ExecutorExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Pool de 2 threads

        Future<String> future1 = executor.submit(() -> {
            Thread.sleep(1000);
            return "Tarefa 1 Concluída";
        });

        Future<String> future2 = executor.submit(() -> {
            Thread.sleep(500);
            return "Tarefa 2 Concluída";
        });

        System.out.println(future2.get()); // Bloqueia até a Tarefa 2 ser concluída
        System.out.println(future1.get()); // Bloqueia até a Tarefa 1 ser concluída

        executor.shutdown(); // Inicia um desligamento ordenado
    }
}
```

---

## Sincronizadores

Ferramentas projetadas para coordenar a execução de threads:

- **`Semaphore`**: Controla o número de threads que podem acessar um recurso simultaneamente.
- **`CountDownLatch`**: Permite que uma ou mais threads esperem até que um conjunto de operações sendo executadas em outras threads seja concluído.
- **`CyclicBarrier`**: Permite que um conjunto de threads espere umas pelas outras para atingir um ponto de barreira comum.
- **`Phaser`**: Uma alternativa mais flexível a `CountDownLatch` e `CyclicBarrier`, permitindo o registro dinâmico de partes e múltiplas fases.
- **`Exchanger`**: Permite que duas threads troquem objetos em um ponto de sincronização.

---

## Coleções Concorrentes

Alternativas thread-safe às coleções padrão de `java.util`, otimizadas para acesso concorrente:

- **`ConcurrentHashMap`**: Uma alternativa thread-safe e de alta performance ao `HashMap`.
- **`CopyOnWriteArrayList` / `CopyOnWriteArraySet`**: Listas/conjuntos thread-safe onde todas as operações de mutação (adicionar, definir, remover) criam uma nova cópia do array subjacente. Bom para coleções que são raramente modificadas, mas frequentemente iteradas.
- **`ConcurrentLinkedQueue`**: Uma fila não bloqueante, ilimitada e thread-safe.
- **Implementações de `BlockingQueue` (ex: `ArrayBlockingQueue`, `LinkedBlockingQueue`)**: Filas que suportam operações que esperam a fila se tornar não-vazia ao recuperar um elemento, e esperam que o espaço fique disponível ao armazenar um elemento. Crucial para padrões produtor-consumidor.

---

## Operações Atômicas

Classes em `java.util.concurrent.atomic` fornecem operações thread-safe em variáveis únicas sem o uso de locks, utilizando instruções de CPU de baixo nível.

- **`AtomicInteger`**, **`AtomicLong`**, **`AtomicBoolean`**, **`AtomicReference`**: Fornecem operações atômicas (ex: `incrementAndGet()`, `compareAndSet()`) que garantem atomicidade e visibilidade.

<!-- end list -->

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet(); // Incrementa atomicamente e obtém o novo valor
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final do contador: " + counter.get()); // Será corretamente 2000
    }
}
```

---

## CompletableFuture

Introduzido no Java 8, `CompletableFuture` é uma classe poderosa para programação assíncrona. Ele representa um `Future` que pode ser explicitamente concluído definindo seu valor e suporta o encadeamento de operações dependentes.

- **Operações não bloqueantes:** Permite definir callbacks que são executados quando uma computação é concluída.
- **Composição:** Combine múltiplas operações assíncronas, lide com erros e transforme resultados.
- **Substitui callbacks manuais:** Uma forma muito mais limpa de gerenciar fluxos de trabalho assíncronos complexos.

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
            return "Olá";
        });

        CompletableFuture<String> combinedFuture = greetingFuture.thenApply(greeting -> greeting + " Mundo")
                                                                 .thenApply(finalGreeting -> finalGreeting + "!");

        combinedFuture.thenAccept(System.out::println); // Imprime "Olá Mundo!" após 1 segundo

        // Mantém a thread principal viva para que as tarefas assíncronas sejam concluídas
        TimeUnit.SECONDS.sleep(2);
    }
}
```

---

## Boas Práticas e Armadilhas

- **Prefira utilitários de concorrência de alto nível:** Use `ExecutorService`, coleções concorrentes e `CompletableFuture` em vez de `Thread` de baixo nível e blocos `synchronized` sempre que possível.
- **Minimize o estado mutável compartilhado:** Quanto menos estado compartilhado, menos problemas de sincronização. Favoreça a imutabilidade.
- **Use sincronizadores apropriados:** Escolha a ferramenta certa para o trabalho (`Semaphore` vs. `CountDownLatch`, etc.).
- **Compreenda a palavra-chave `volatile`:** Garante a visibilidade das mudanças de variáveis entre threads, mas não a atomicidade.
- **Evite deadlocks:** Projete a ordem de bloqueio cuidadosamente.
- **Lide com exceções em threads:** Exceções não capturadas em threads podem encerrá-las silenciosamente. Use `UncaughtExceptionHandler` ou `Future.get()`.
- **Desligue corretamente os `ExecutorService`s:** Sempre chame `shutdown()` e/ou `shutdownNow()` para liberar recursos.

---

## Exemplos Práticos

Aqui você encontrará exemplos práticos demonstrando vários conceitos de concorrência:

---

### 1\. `ThreadCreation.java`

Este exemplo mostra as duas principais formas de criar e executar threads em Java: estendendo `Thread` e implementando `Runnable`.

```java
// src/main/java/com/yourlab/concurrency/ThreadCreation.java
package com.yourlab.concurrency;

public class ThreadCreation {

    // Opção 1: Estender a classe Thread
    static class MyThread extends Thread {
        private String threadName;

        public MyThread(String name) {
            this.threadName = name;
            System.out.println("Criando " + threadName);
        }

        @Override
        public void run() {
            System.out.println("Executando " + threadName);
            try {
                for (int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + threadName + ", " + i);
                    // Deixa a thread dormir por um tempo.
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread " + threadName + " interrompida.");
            }
            System.out.println("Thread " + threadName + " saindo.");
        }
    }

    // Opção 2: Implementar a interface Runnable (mais comum e flexível)
    static class MyRunnable implements Runnable {
        private String threadName;

        public MyRunnable(String name) {
            this.threadName = name;
            System.out.println("Criando " + threadName);
        }

        @Override
        public void run() {
            System.out.println("Executando " + threadName);
            try {
                for (int i = 4; i > 0; i--) {
                    System.out.println("Runnable: " + threadName + ", " + i);
                    Thread.sleep(70);
                }
            } catch (InterruptedException e) {
                System.out.println("Runnable " + threadName + " interrompida.");
            }
            System.out.println("Runnable " + threadName + " saindo.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Thread principal iniciada.");

        // Usando a classe Thread
        MyThread thread1 = new MyThread("Thread-1");
        thread1.start(); // Chama o método run()

        // Usando a interface Runnable (preferencial)
        Thread thread2 = new Thread(new MyRunnable("Runnable-1"));
        thread2.start();

        // Usando um Runnable anônimo com uma expressão lambda (comum no Java moderno)
        Thread thread3 = new Thread(() -> {
            System.out.println("Executando Thread Lambda");
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println("Lambda: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread Lambda interrompida.");
            }
            System.out.println("Thread Lambda saindo.");
        });
        thread3.start();

        System.out.println("Thread principal terminou de iniciar outras threads.");
    }
}
```

---

### 2\. `RaceConditionDemo.java`

Este exemplo demonstra uma **condição de corrida** onde múltiplas threads tentam incrementar um contador compartilhado sem sincronização adequada, levando a um valor final incorreto.

```java
// src/main/java/com/yourlab/concurrency/RaceConditionDemo.java
package com.yourlab.concurrency;

public class RaceConditionDemo {

    private static int counter = 0; // Estado mutável compartilhado

    public static void main(String[] args) throws InterruptedException {
        Runnable incrementTask = () -> {
            for (int i = 0; i < 10000; i++) {
                counter++; // Esta operação não é atômica
            }
        };

        Thread t1 = new Thread(incrementTask, "Thread-1");
        Thread t2 = new Thread(incrementTask, "Thread-2");

        t1.start();
        t2.start();

        // Espera ambas as threads terminarem
        t1.join();
        t2.join();

        // O valor esperado é 20000, mas provavelmente será menor devido à condição de corrida
        System.out.println("Valor final do contador (esperado 20000): " + counter);
    }
}
```

---

### 3\. `SynchronizedCounter.java`

Este exemplo mostra como resolver a condição de corrida de `RaceConditionDemo` usando métodos **`synchronized`** e **`AtomicInteger`** para incrementos de contador thread-safe.

```java
// src/main/java/com/yourlab/concurrency/SynchronizedCounter.java
package com.yourlab.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedCounter {

    // Opção 1: Usando método synchronized
    static class SynchronizedValue {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    // Opção 2: Usando AtomicInteger (preferencial para atomicidade de variável única)
    static class AtomicValue {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet(); // Operação atômica
        }

        public int getCount() {
            return count.get();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // --- Demonstração de método synchronized ---
        System.out.println("--- Usando Método Sincronizado ---");
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
        System.out.println("Valor final do contador sincronizado (esperado 20000): " + syncCounter.getCount());


        // --- Demonstração de AtomicInteger ---
        System.out.println("\n--- Usando AtomicInteger ---");
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
        System.out.println("Valor final do contador atômico (esperado 20000): " + atomicCounter.getCount());
    }
}
```

---

### 4\. `ThreadPoolExample.java`

Este exemplo demonstra o uso de `ExecutorService` e `Executors` para gerenciar um pool de threads de tamanho fixo, executando múltiplas tarefas `Runnable` e `Callable`.

```java
// src/main/java/com/yourlab/concurrency/ThreadPoolExample.java
package com.yourlab.concurrency;

import java.util.concurrent.*;

public class ThreadPoolExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Cria um pool de threads de tamanho fixo com 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("Submetendo tarefas...");

        // Submete tarefas Runnable (dispara e esquece, sem valor de retorno)
        executor.execute(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Tarefa Runnable 1 executada por " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                Thread.sleep(500);
                System.out.println("Tarefa Runnable 2 executada por " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Submete tarefas Callable (retorna um valor e pode lançar exceções)
        Future<String> future1 = executor.submit(() -> {
            System.out.println("Tarefa Callable 3 iniciada por " + Thread.currentThread().getName());
            Thread.sleep(2000);
            return "Resultado da Tarefa 3";
        });

        Future<Integer> future2 = executor.submit(() -> {
            System.out.println("Tarefa Callable 4 iniciada por " + Thread.currentThread().getName());
            Thread.sleep(1500);
            return 123;
        });

        // Recupera resultados de objetos Future
        System.out.println("Obtendo resultados dos futures...");
        try {
            System.out.println("Resultado do Future 1: " + future1.get()); // Bloqueia até a tarefa 3 ser concluída
            System.out.println("Resultado do Future 2: " + future2.get()); // Bloqueia até a tarefa 4 ser concluída
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erro ao obter resultado do future: " + e.getMessage());
        }

        // Verifica se as tarefas foram concluídas sem bloquear
        if (future1.isDone()) {
            System.out.println("Future 1 já está concluído!");
        }

        // Tenta desligar o executor graciosamente
        executor.shutdown(); // Impede que novas tarefas sejam submetidas
        System.out.println("Desligamento do Executor iniciado.");

        // Espera todas as tarefas submetidas serem concluídas ou um timeout ocorrer
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("ThreadPool não terminou a tempo, forçando o desligamento...");
                executor.shutdownNow(); // Tenta parar todas as tarefas em execução ativa
            }
        } catch (InterruptedException e) {
            System.err.println("Terminação interrompida: " + e.getMessage());
            executor.shutdownNow();
        }
        System.out.println("Executor terminado.");
    }
}
```

---

### 5\. `ProducerConsumerQueue.java`

Este exemplo implementa o clássico padrão Produtor-Consumidor usando uma `BlockingQueue` para transferir dados de forma segura entre threads.

```java
// src/main/java/com/yourlab/concurrency/ProducerConsumerQueue.java
package com.yourlab.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerQueue {

    public static void main(String[] args) throws InterruptedException {
        // Cria uma BlockingQueue com capacidade de 10
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        // Thread Produtora
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 20; i++) {
                    System.out.println("Produtor: Produzindo item " + i);
                    queue.put(i); // Bloqueia se a fila estiver cheia
                    Thread.sleep(100); // Simula trabalho
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Produtor interrompido.");
            }
        };

        // Thread Consumidora
        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 20; i++) {
                    Integer item = queue.take(); // Bloqueia se a fila estiver vazia
                    System.out.println("Consumidor: Consumindo item " + item);
                    Thread.sleep(300); // Simula trabalho
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumidor interrompido.");
            }
        };

        Thread producerThread = new Thread(producer, "ProducerThread");
        Thread consumerThread = new Thread(consumer, "ConsumerThread");

        producerThread.start();
        consumerThread.start();

        producerThread.join(); // Espera o produtor terminar
        consumerThread.join(); // Espera o consumidor terminar

        System.out.println("Exemplo Produtor-Consumidor finalizado.");
    }
}
```

---

### 6\. `CountdownLatchExample.java`

Este exemplo demonstra `CountDownLatch`, um sincronizador que permite que uma ou mais threads esperem até que um conjunto de operações sendo executadas em outras threads seja concluído.

```java
// src/main/java/com/yourlab/concurrency/CountdownLatchExample.java
package com.yourlab.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountdownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        // Cria um CountDownLatch com uma contagem de 3.
        // A thread principal esperará até que esta contagem chegue a zero.
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Tarefa 1: Simula o carregamento da configuração
        executor.submit(() -> {
            try {
                System.out.println("Tarefa 1: Carregando configuração...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Tarefa 1: Configuração carregada.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown(); // Decrementa a contagem do latch
            }
        });

        // Tarefa 2: Simula a conexão ao banco de dados
        executor.submit(() -> {
            try {
                System.out.println("Tarefa 2: Conectando ao banco de dados...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Tarefa 2: Banco de dados conectado.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown(); // Decrementa a contagem do latch
            }
        });

        // Tarefa 3: Simula a inicialização de serviços
        executor.submit(() -> {
            try {
                System.out.println("Tarefa 3: Inicializando serviços...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Tarefa 3: Serviços inicializados.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown(); // Decrementa a contagem do latch
            }
        });

        System.out.println("Thread principal: Esperando todas as tarefas de inicialização serem concluídas...");

        // A thread principal espera aqui até que a contagem do latch chegue a zero
        latch.await(); // Bloqueia até que countDown() seja chamado 3 vezes

        System.out.println("Thread principal: Todas as tarefas de inicialização concluídas. Aplicação está pronta!");

        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
```

---

### 7\. `CompletableFutureChain.java`

Este exemplo demonstra como encadear múltiplas operações assíncronas usando `CompletableFuture`, incluindo transformações e tratamento de erros.

```java
// src/main/java/com/yourlab/concurrency/CompletableFutureChain.java
package com.yourlab.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureChain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Iniciando exemplo de encadeamento CompletableFuture...");

        // 1. supplyAsync: Obtém o ID do usuário assincronamente
        CompletableFuture<String> userIdFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Passo 1: Buscando ID do Usuário em " + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(500); // Simula chamada de rede
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "user123";
        });

        // 2. thenCompose: Usa o ID do usuário para buscar detalhes do usuário (operação assíncrona dependente)
        CompletableFuture<String> userDetailsFuture = userIdFuture.thenCompose(userId -> {
            System.out.println("Passo 2: Buscando detalhes para " + userId + " em " + Thread.currentThread().getName());
            return CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(700); // Simula outra chamada de rede
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Detalhes para " + userId + ": João Silva, Idade 30";
            });
        });

        // 3. thenApply: Transforma os detalhes em uma mensagem de boas-vindas
        CompletableFuture<String> welcomeMessageFuture = userDetailsFuture.thenApply(details -> {
            System.out.println("Passo 3: Criando mensagem de boas-vindas em " + Thread.currentThread().getName());
            return "Bem-vindo(a)! Aqui estão seus detalhes: " + details;
        });

        // 4. thenAccept: Consome o resultado final (sem valor de retorno)
        welcomeMessageFuture.thenAccept(message -> {
            System.out.println("Passo 4: Exibindo mensagem final em " + Thread.currentThread().getName());
            System.out.println(message);
        });

        // 5. exceptionally: Lida com erros em qualquer ponto da cadeia
        CompletableFuture<String> errorHandlingFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("\nPasso X: Iniciando tarefa que pode falhar em " + Thread.currentThread().getName());
            if (true) { // Simula uma condição de erro
                throw new RuntimeException("Algo deu errado durante o processamento dos dados!");
            }
            return "Dados processados.";
        }).exceptionally(ex -> {
            System.err.println("Erro capturado em exceptionally: " + ex.getMessage() + " em " + Thread.currentThread().getName());
            return "Erro: Não foi possível processar os dados."; // Retorna um valor de fallback
        }).thenApply(result -> {
            // Este passo será executado com o valor de fallback se um erro ocorreu
            System.out.println("Passo Y: Continuando após erro/sucesso: " + result + " em " + Thread.currentThread().getName());
            return result.toUpperCase();
        });


        System.out.println("\nThread principal continua seu trabalho enquanto os futures executam concorrentemente...");

        // Espera todos os futures serem concluídos (ou para fins de demonstração)
        // Em uma aplicação real, você provavelmente anexaria mais callbacks `then`
        // ou faria o join apenas no final, se necessário.
        userIdFuture.join(); // Bloqueando, apenas para garantir que a thread principal espere a cadeia terminar
        userDetailsFuture.join();
        welcomeMessageFuture.join();
        errorHandlingFuture.join();


        System.out.println("Thread principal finalizada.");
    }
}
```

---

### 8\. `DiningPhilosophers.java`

Este é um problema clássico em concorrência que ilustra o **deadlock**. Cinco filósofos repetidamente pensam e comem. Para comer, um filósofo precisa de dois hashis (garfos) – um à sua esquerda e outro à sua direita. O problema surge quando todos os filósofos pegam seu hashi esquerdo simultaneamente, e então esperam indefinidamente por seu hashi direito, que está sendo segurado por seu vizinho.

Este exemplo fornece o cenário básico de deadlock. Para torná-lo um exercício de laboratório completo, você adicionaria soluções (ex: aquisição ordenada de recursos, um monitor/mediador ou um `Semaphore`).

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
            System.out.println(name + " está pensando.");
            TimeUnit.MILLISECONDS.sleep(Math.round(Math.random() * 500) + 100);
        }

        private void eat() throws InterruptedException {
            System.out.println(name + " está comendo.");
            TimeUnit.MILLISECONDS.sleep(Math.round(Math.random() * 500) + 100);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    think();

                    // Esta ordem frequentemente leva a deadlock
                    synchronized (leftChopstick) {
                        System.out.println(name + " pegou o hashi esquerdo " + leftChopstick.getId());
                        synchronized (rightChopstick) {
                            System.out.println(name + " pegou o hashi direito " + rightChopstick.getId());
                            eat();
                            System.out.println(name + " largou o hashi direito " + rightChopstick.getId());
                        }
                        System.out.println(name + " largou o hashi esquerdo " + leftChopstick.getId());
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(name + " foi interrompido.");
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
            // Filósofo i pega o hashi esquerdo i e o hashi direito (i+1)%numPhilosophers
            // Esta aquisição simétrica leva ao deadlock
            philosophers[i] = new Thread(
                new Philosopher("Filósofo-" + i, chopsticks[i], chopsticks[(i + 1) % numPhilosophers])
            );
            philosophers[i].start();
        }

        System.out.println("Simulação dos Filósofos Comilões iniciada. Cuidado com os deadlocks!");
        // Você provavelmente verá a saída parar ou se tornar muito esparsa, indicando deadlock.
        // Pressione Ctrl+C para parar o programa.
    }
}
```

---

## Recursos Adicionais

- [Tutorial de Concorrência Java da Oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)
- [Java Concurrency in Practice (Livro de Brian Goetz et al.)](https://www.amazon.com/Java-Concurrency-Practice-Brian-Goetz/dp/0321349601) - Leitura obrigatória para concorrência Java séria.
- [Baeldung - Guia para Concorrência em Java](https://www.baeldung.com/java-concurrency)
- [IBM Developer - Teoria e prática Java: Concorrência](https://www.google.com/search?q=https://www.ibm.com/developerworks/java/library/j-jtp/)

---

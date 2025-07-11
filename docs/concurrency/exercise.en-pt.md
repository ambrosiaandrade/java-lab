# Exercícios de Concorrência (Concurrency Exercises)

- [Exercícios em Português](#exercícios-em-português)
- [Exercises in English](#exercises-in-english)

## Exercícios em Português

**Objetivo:** Praticar a criação e gerenciamento básico de threads, identificando e resolvendo condições de corrida.

**Exercício 1: Criação e Início de Threads**

- Crie duas threads diferentes: uma estendendo a classe `Thread` e outra implementando a interface `Runnable`.
- Faça cada thread imprimir seu nome e uma mensagem simples 5 vezes, com um pequeno atraso (ex: 100ms) entre as impressões.
- Na thread principal, inicie ambas as threads.

**Exercício 2: Condição de Corrida e `synchronized`**

- Crie uma classe `Contador` com um método `incrementar()` que aumenta uma variável `int` privada.
- Crie 10 threads, cada uma chamando `incrementar()` 1000 vezes.
- Execute as threads e imprima o valor final do contador. Você deve observar um valor incorreto (condição de corrida).
- Modifique a classe `Contador` para usar a palavra-chave `synchronized` no método `incrementar()` e execute novamente. Observe a correção do resultado.

**Exercício 3: `AtomicInteger` para Contagem Segura**

- Repita o Exercício 2, mas em vez de usar `synchronized`, utilize a classe `java.util.concurrent.atomic.AtomicInteger` para a variável do contador.
- Compare a simplicidade e o desempenho em relação à solução com `synchronized`.

**Objetivo:** Explorar o framework `ExecutorService` para gerenciamento de pools de threads.

**Exercício 4: `ExecutorService` com `Runnable`**

- Crie um `ExecutorService` usando `Executors.newFixedThreadPool(3)`.
- Crie 5 tarefas `Runnable`, onde cada tarefa imprime uma mensagem e simula um trabalho (ex: `Thread.sleep(500ms)`).
- Submeta todas as 5 tarefas ao `ExecutorService`.
- Após submeter as tarefas, inicie o desligamento gracioso do `ExecutorService` e espere por sua terminação.

**Exercício 5: `ExecutorService` com `Callable` e `Future`**

- Usando o mesmo `ExecutorService` (ou um novo), crie 3 tarefas `Callable<Integer>`. Cada `Callable` deve retornar um número aleatório após um pequeno atraso.
- Submeta essas tarefas e colete os objetos `Future` retornados.
- Na thread principal, use `future.get()` para recuperar os resultados e imprima-os. Note que `get()` é bloqueante.

**Objetivo:** Utilizar sincronizadores para coordenar o fluxo de execução entre threads.

**Exercício 6: `CountDownLatch` para Sincronização de Início**

- Simule o início de uma aplicação onde a thread principal precisa esperar que 3 serviços (cada um em sua própria thread) sejam inicializados.
- Use um `CountDownLatch` para coordenar isso. Cada serviço, ao finalizar sua inicialização, deve decrementar o latch. A thread principal deve esperar no latch.

**Exercício 7: `Semaphore` para Limitar Acesso**

- Simule um pool de conexões de banco de dados que só permite 3 conexões ativas simultaneamente.
- Crie 5 threads que tentam adquirir uma conexão, usá-la (simule com `sleep`), e depois liberá-la.
- Use um `Semaphore` para controlar o acesso às "conexões".

**Objetivo:** Trabalhar com operações assíncronas e encadeamento usando `CompletableFuture`.

**Exercício 8: Encadeamento Básico com `CompletableFuture`**

- Crie um `CompletableFuture` que simule a busca de um nome de usuário (ex: retorna "Alice" após 1 segundo).
- Encadeie uma segunda operação que use o nome de usuário para buscar o email correspondente (ex: "alice@example.com" após mais 1 segundo).
- Finalmente, encadeie uma terceira operação que imprima a mensagem "Bem-vindo(a), [nome]! Seu email é [email]."
- Use `thenApply` e `thenAccept`.

**Exercício 9: Tratamento de Erros com `CompletableFuture`**

- Crie um `CompletableFuture` que simule uma operação que pode falhar (ex: lançar uma `RuntimeException` 50% das vezes).
- Use `exceptionally()` para fornecer um valor de fallback caso a operação falhe. Imprima uma mensagem indicando se a operação foi bem-sucedida ou se um erro ocorreu e o fallback foi usado.

**Exercício 10: Combinando Múltiplos `CompletableFuture`**

- Crie dois `CompletableFuture`s independentes: um que busca o preço de um produto e outro que busca a quantidade em estoque.
- Use `thenCombine()` para combinar os resultados e calcular o valor total disponível do produto (preço \* quantidade). Imprima o valor final.

---

## Exercises in English

**Objective:** Practice basic thread creation and management, identifying and resolving race conditions.

**Exercise 1: Thread Creation and Start**

- Create two different threads: one by extending the `Thread` class and another by implementing the `Runnable` interface.
- Have each thread print its name and a simple message 5 times, with a small delay (e.g., 100ms) between prints.
- In the main thread, start both threads.

**Exercise 2: Race Condition and `synchronized`**

- Create a `Counter` class with an `increment()` method that increases a private `int` variable.
- Create 10 threads, each calling `increment()` 1000 times.
- Run the threads and print the final counter value. You should observe an incorrect value (race condition).
- Modify the `Counter` class to use the `synchronized` keyword on the `increment()` method and run again. Observe the correct result.

**Exercise 3: `AtomicInteger` for Safe Counting**

- Repeat Exercise 2, but instead of using `synchronized`, use the `java.util.concurrent.atomic.AtomicInteger` class for the counter variable.
- Compare the simplicity and performance relative to the `synchronized` solution.

**Objective:** Explore the `ExecutorService` framework for thread pool management.

**Exercise 4: `ExecutorService` with `Runnable`**

- Create an `ExecutorService` using `Executors.newFixedThreadPool(3)`.
- Create 5 `Runnable` tasks, where each task prints a message and simulates some work (e.g., `Thread.sleep(500ms)`).
- Submit all 5 tasks to the `ExecutorService`.
- After submitting the tasks, initiate a graceful shutdown of the `ExecutorService` and wait for its termination.

**Exercise 5: `ExecutorService` with `Callable` and `Future`**

- Using the same `ExecutorService` (or a new one), create 3 `Callable<Integer>` tasks. Each `Callable` should return a random integer after a small delay.
- Submit these tasks and collect the `Future` objects returned.
- In the main thread, use `future.get()` to retrieve the results and print them. Note that `get()` is blocking.

**Objective:** Utilize synchronizers to coordinate execution flow between threads.

**Exercise 6: `CountDownLatch` for Startup Synchronization**

- Simulate an application startup where the main thread needs to wait for 3 services (each in its own thread) to be initialized.
- Use a `CountDownLatch` to coordinate this. Each service, upon finishing its initialization, should decrement the latch. The main thread should wait on the latch.

**Exercise 7: `Semaphore` to Limit Access**

- Simulate a database connection pool that only allows 3 active connections simultaneously.
- Create 5 threads that attempt to acquire a connection, use it (simulate with `sleep`), and then release it.
- Use a `Semaphore` to control access to the "connections".

**Objective:** Work with asynchronous operations and chaining using `CompletableFuture`.

**Exercise 8: Basic Chaining with `CompletableFuture`**

- Create a `CompletableFuture` that simulates fetching a username (e.g., returns "Alice" after 1 second).
- Chain a second operation that uses the username to fetch the corresponding email (e.g., "alice@example.com" after another 1 second).
- Finally, chain a third operation that prints the message "Welcome, [name]! Your email is [email]."
- Use `thenApply` and `thenAccept`.

**Exercise 9: Error Handling with `CompletableFuture`**

- Create a `CompletableFuture` that simulates an operation that might fail (e.g., throws a `RuntimeException` 50% of the time).
- Use `exceptionally()` to provide a fallback value if the operation fails. Print a message indicating whether the operation succeeded or if an error occurred and the fallback was used.

**Exercise 10: Combining Multiple `CompletableFuture`s**

- Create two independent `CompletableFuture`s: one that fetches a product's price and another that fetches the quantity in stock.
- Use `thenCombine()` to combine the results and calculate the total available value of the product (price \* quantity). Print the final value.

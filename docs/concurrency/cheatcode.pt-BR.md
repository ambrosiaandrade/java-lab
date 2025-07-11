# 📚 Cheat Sheet de Concorrência em Java

Este cheat sheet resume os pontos essenciais da API `java.util.concurrent`, que é fundamental para construir aplicações Java eficientes e escaláveis.

---

## 📚 Tabela de conteúdos

- [🚀 Conceitos Fundamentais](#conceitos-fundamentais)
- [⚙️ Gerenciamento de Threads (Executors)](#gerenciamento-de-threads-executors)
- [🤝 Sincronizadores Comuns](#sincronizadores-comuns)
- [🔒 Operações Atômicas (`java.util.concurrent.atomic`)](#operações-atômicas-javautilconcurrentatomic)
- [⚡ Programação Assíncrona (`CompletableFuture`)](#programação-assíncrona-completablefuture)
- [📚 Coleções Concorrentes](#coleções-concorrentes)
- [✅ Boas Práticas](#boas-práticas)

---

## 🚀 Conceitos Fundamentais

- **Thread**: Unidade básica de execução.
- **Runnable**: Interface para definir tarefas que não retornam valor nem lançam exceções checadas.
  - **Lambda:** `() -> { /* código */ }`
- **Callable**: Interface para definir tarefas que retornam valor e podem lançar exceções.
  - **Lambda:** `() -> { /* código */; return resultado; }`
- **Condição de Corrida (Race Condition)**: Múltiplas threads acessam/modificam dados compartilhados sem controle, levando a resultados imprevisíveis.
- **Deadlock**: Duas ou mais threads se bloqueiam mutuamente, esperando por recursos que a outra possui.
- **Atomicidade**: Uma operação que é indivisível e ocorre por completo ou não ocorre.
- **Visibilidade**: Garantia de que as mudanças de uma thread em variáveis são vistas por outras.

---

## ⚙️ Gerenciamento de Threads (Executors)

- **`ExecutorService`**: Gerencia um pool de threads, evitando a criação e destruição excessiva de threads.
  - **Criar um Pool:**
    - `Executors.newFixedThreadPool(int n)`: Pool com `n` threads fixas.
    - `Executors.newCachedThreadPool()`: Pool dinâmico, cria threads conforme necessário e as reutiliza.
    - `Executors.newSingleThreadExecutor()`: Pool com uma única thread, garantindo execução sequencial.
  - **Submeter Tarefas:**
    - `executor.execute(Runnable task)`: Executa um `Runnable` (fire-and-forget).
    - `Future<V> future = executor.submit(Callable<V> task)`: Executa um `Callable`, retorna um `Future` para o resultado.
  - **Encerrar o Pool:**
    - `executor.shutdown()`: Inicia um desligamento gracioso; não aceita novas tarefas, mas conclui as existentes.
    - `executor.shutdownNow()`: Tenta parar todas as tarefas imediatamente (interrompendo threads).
    - `executor.awaitTermination(long timeout, TimeUnit unit)`: Espera pelo término do pool ou por um timeout.

---

## 🤝 Sincronizadores Comuns

- **`synchronized`**: Palavra-chave para garantir que apenas uma thread por vez execute um bloco de código ou método.
  - **Método:** `public synchronized void meuMetodo() { ... }`
  - **Bloco:** `synchronized (this) { ... }` ou `synchronized (algumObjetoLock) { ... }`
- **`CountDownLatch`**: Uma thread espera que N outras threads concluam suas operações.
  - `CountDownLatch latch = new CountDownLatch(N);`
  - Threads trabalhadoras: `latch.countDown();` (decrementa N)
  - Thread principal: `latch.await();` (bloqueia até N chegar a zero)
- **`Semaphore`**: Controla o número de threads que podem acessar um recurso simultaneamente.
  - `Semaphore semaphore = new Semaphore(int permits);`
  - Adquirir: `semaphore.acquire();`
  - Liberar: `semaphore.release();`

---

## 🔒 Operações Atômicas (`java.util.concurrent.atomic`)

Classes para operações atômicas em variáveis, sem necessidade de locks explícitos, usando instruções de CPU.

- **`AtomicInteger`**: Para operações atômicas em `int`.
  - `AtomicInteger counter = new AtomicInteger(0);`
  - Incrementar: `counter.incrementAndGet();`
  - Comparar e Trocar: `counter.compareAndSet(expectedValue, newValue);`
- **`AtomicLong`**, **`AtomicBoolean`**, **`AtomicReference`**: Variações para `long`, `boolean` e referências de objeto, respectivamente.

---

## ⚡ Programação Assíncrona (`CompletableFuture`)

Introduzido no Java 8, para encadear e compor operações assíncronas de forma não bloqueante.

- **Criar:**
  - `CompletableFuture.supplyAsync(() -> resultado)`: Para operações que produzem um resultado.
  - `CompletableFuture.runAsync(() -> { /* ação */ })`: Para operações que não produzem resultado.
- **Encadear Operações:**
  - `.thenApply(resultadoAnterior -> novoResultado)`: Transforma o resultado (executa quando a anterior completa).
  - `.thenAccept(resultado -> { /* consome resultado */ })`: Consome o resultado (não retorna nada).
  - `.thenCompose(resultadoAnterior -> outroFuture)`: Encadeia outro `CompletableFuture` (para operações assíncronas dependentes).
  - `.thenCombine(outroFuture, (res1, res2) -> combinado)`: Combina resultados de dois `CompletableFuture`s.
- **Tratamento de Erros:**
  - `.exceptionally(Throwable ex -> valorDeFallback)`: Fornece um valor de fallback se houver uma exceção.
  - `.handle(resultado, ex -> { /* lida com sucesso ou erro */ })`: Executa após a conclusão, com ou sem exceção.
- **Obter Resultado (Bloqueante):**
  - `future.get()`: Bloqueia até o resultado estar disponível (lança `ExecutionException`).
  - `future.join()`: Semelhante a `get()`, mas lança `CompletionException` (unchecked).

---

## 📚 Coleções Concorrentes

Alternativas thread-safe às coleções padrão, otimizadas para ambientes multi-threaded.

- **`ConcurrentHashMap`**: Mapa thread-safe, de alta performance.
- **`BlockingQueue`**: Interface para filas que suportam operações de `put` (colocar) e `take` (retirar) bloqueantes.
  - `ArrayBlockingQueue`: Fila com capacidade fixa.
  - `LinkedBlockingQueue`: Fila com capacidade opcionalmente limitada.
- **`CopyOnWriteArrayList` / `CopyOnWriteArraySet`**: Versões thread-safe de `ArrayList` / `HashSet` para cenários de poucas modificações e muitas leituras.

---

## ✅ Boas Práticas

- **Prefira o `java.util.concurrent`**: Use as ferramentas de alto nível (`ExecutorService`, `CompletableFuture`, coleções concorrentes) em vez de `Thread` e `synchronized` diretamente.
- **Minimize o Estado Mutável Compartilhado**: Quanto menos dados compartilhados e mutáveis, menos problemas de sincronização.
- **Favoreça a Imutabilidade**: Objetos imutáveis são inerentemente thread-safe.
- **Entenda `volatile`**: Garante visibilidade, mas não atomicidade.
- **Gerenciamento de Recursos**: Sempre encerre seus `ExecutorService`s com `shutdown()` e `awaitTermination()`.

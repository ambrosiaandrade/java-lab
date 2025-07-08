# Cheatsheet Java Collections

---

## Principais Interfaces e Implementações

| Interface/Classe   | Descrição                                                    |
| ------------------ | ------------------------------------------------------------ |
| `Collection<E>`    | Raiz das coleções: lista, conjunto, fila                     |
| `List<E>`          | Coleção ordenada, permite duplicatas                         |
| `Set<E>`           | Coleção sem elementos duplicados                             |
| `Queue<E>`         | Fila, FIFO geralmente                                        |
| `Deque<E>`         | Fila dupla, permite inserção/removação em ambas extremidades |
| `ArrayList<E>`     | Lista baseada em array, rápida leitura                       |
| `LinkedList<E>`    | Lista ligada, eficiente inserção/removação                   |
| `HashSet<E>`       | Conjunto baseado em hash                                     |
| `TreeSet<E>`       | Conjunto ordenado (árvore vermelha-preta)                    |
| `HashMap<K,V>`     | Mapa chave-valor baseado em hash                             |
| `TreeMap<K,V>`     | Mapa ordenado por chave                                      |
| `PriorityQueue<E>` | Fila de prioridade                                           |

---

## Criação de coleções

```java
Collection<String> coll = new ArrayList<>();
List<Integer> list = new LinkedList<>();
Set<String> set = new HashSet<>();
Queue<Integer> queue = new PriorityQueue<>();
Deque<String> deque = new LinkedList<>();
Map<String, Integer> map = new HashMap<>();
```

---

## Métodos comuns das interfaces Collection, List, Set, Queue, Deque

| Método                                  | Descrição                                |
| --------------------------------------- | ---------------------------------------- |
| `add(E e)`                              | Adiciona elemento                        |
| `addAll(Collection<? extends E> c)`     | Adiciona todos elementos da coleção      |
| `remove(Object o)`                      | Remove elemento                          |
| `removeIf(Predicate<? super E> filter)` | Remove elementos que satisfazem condição |
| `contains(Object o)`                    | Verifica se contém elemento              |
| `size()`                                | Tamanho da coleção                       |
| `isEmpty()`                             | Verifica se está vazia                   |
| `clear()`                               | Limpa todos os elementos                 |
| `iterator()`                            | Retorna iterator para percorrer          |

---

## Métodos específicos de List

| Método                                | Descrição                     |
| ------------------------------------- | ----------------------------- |
| `get(int index)`                      | Retorna elemento na posição   |
| `set(int index, E element)`           | Substitui elemento na posição |
| `add(int index, E element)`           | Insere elemento na posição    |
| `remove(int index)`                   | Remove elemento na posição    |
| `indexOf(Object o)`                   | Índice da primeira ocorrência |
| `lastIndexOf(Object o)`               | Índice da última ocorrência   |
| `subList(int fromIndex, int toIndex)` | Sublista de elementos         |

---

## Métodos específicos de Set

- Não possui métodos além dos de Collection (não permite duplicatas)
- Implementações:

  - `HashSet` — não ordenado, rápido
  - `TreeSet` — ordenado, baseado em árvore (implementa `NavigableSet`)

---

## Métodos específicos de Queue e Deque

| Método          | Descrição                                        |
| --------------- | ------------------------------------------------ |
| `offer(E e)`    | Insere elemento na fila, retorna false se falhar |
| `poll()`        | Remove e retorna o próximo elemento (ou null)    |
| `peek()`        | Retorna próximo elemento sem remover (ou null)   |
| `addFirst(E e)` | (Deque) Insere no início                         |
| `addLast(E e)`  | (Deque) Insere no fim                            |
| `removeFirst()` | (Deque) Remove do início                         |
| `removeLast()`  | (Deque) Remove do fim                            |
| `getFirst()`    | (Deque) Retorna primeiro elemento                |
| `getLast()`     | (Deque) Retorna último elemento                  |

---

## Métodos úteis de Map

| Método                             | Descrição                                         |
| ---------------------------------- | ------------------------------------------------- |
| `put(K key, V value)`              | Adiciona/atualiza valor para a chave              |
| `get(Object key)`                  | Retorna valor para chave (ou null se não existir) |
| `remove(Object key)`               | Remove entrada pela chave                         |
| `containsKey(Object key)`          | Verifica se contém chave                          |
| `containsValue(Object value)`      | Verifica se contém valor                          |
| `keySet()`                         | Retorna conjunto de chaves                        |
| `values()`                         | Retorna coleção de valores                        |
| `entrySet()`                       | Retorna conjunto de pares chave-valor             |
| `putIfAbsent(K key, V value)`      | Insere valor se chave não existir                 |
| `computeIfAbsent(K key, Function)` | Insere valor calculado se chave não existir       |

---

## Exemplos rápidos

```java
// Adicionando elementos
list.add("Java");
set.add("Java");
queue.offer(10);
deque.addFirst("Start");
map.put("idade", 30);

// Iterando Collection
for (String s : coll) System.out.println(s);

// Iterando Map
for (Map.Entry<String, Integer> e : map.entrySet()) {
    System.out.println(e.getKey() + ": " + e.getValue());
}

// Removendo elementos com Predicate
list.removeIf(s -> s.startsWith("J"));
```

---

## Coleções Imutáveis (Java 9+)

```java
List<String> imutavel = List.of("A", "B", "C");
Set<Integer> imutavelSet = Set.of(1, 2, 3);
Map<String, String> imutavelMap = Map.of("chave1", "valor1", "chave2", "valor2");
```

---

# Guia Avançado: Queue, Deque e PriorityQueue em Java

---

## 1. Queue<E>

### O que é?

- `Queue<E>` é uma interface que representa uma **fila** (estrutura FIFO — First In, First Out).
- Elementos são inseridos no final e removidos do início.
- Usada para controlar tarefas em ordem sequencial, processamento assíncrono, buffer, etc.

### Implementações comuns

- `LinkedList<E>` (implementa Queue)
- `PriorityQueue<E>` (implementa Queue mas com ordenação especial)

### Métodos mais usados

| Método       | Descrição                                                |
| ------------ | -------------------------------------------------------- |
| `offer(E e)` | Insere elemento na fila. Retorna false se não puder.     |
| `poll()`     | Remove e retorna o elemento do início (ou null se vazia) |
| `peek()`     | Retorna, sem remover, o elemento do início (ou null)     |
| `add(E e)`   | Insere elemento, lança exceção se falhar                 |
| `remove()`   | Remove elemento, lança exceção se vazia                  |

### Quando usar?

- Controle de tarefas em ordem FIFO (ex: fila de impressão, tarefas em background).
- Buffer temporário de dados.
- Quando a ordem de processamento é importante.

---

## 2. Deque<E> (Double Ended Queue)

### O que é?

- `Deque<E>` é uma fila dupla, permite inserção e remoção **nos dois extremos**.
- Combina características de fila (FIFO) e pilha (LIFO).

### Implementações comuns

- `LinkedList<E>` (implementa Deque)
- `ArrayDeque<E>` (implementação eficiente de deque em array)

### Métodos importantes

| Método            | Descrição                                        |
| ----------------- | ------------------------------------------------ |
| `addFirst(E e)`   | Insere no início                                 |
| `addLast(E e)`    | Insere no fim                                    |
| `offerFirst(E e)` | Insere no início, retorna false se falhar        |
| `offerLast(E e)`  | Insere no fim, retorna false se falhar           |
| `removeFirst()`   | Remove e retorna do início                       |
| `removeLast()`    | Remove e retorna do fim                          |
| `pollFirst()`     | Remove do início ou retorna null                 |
| `pollLast()`      | Remove do fim ou retorna null                    |
| `getFirst()`      | Retorna do início sem remover (exceção se vazio) |
| `getLast()`       | Retorna do fim sem remover (exceção se vazio)    |
| `peekFirst()`     | Retorna do início sem remover ou null            |
| `peekLast()`      | Retorna do fim sem remover ou null               |

### Quando usar?

- Quando precisar de uma estrutura que funcione como fila e pilha ao mesmo tempo.
- Exemplos:

  - Histórico de navegação (push/pop nas extremidades)
  - Buffer circular
  - Algoritmos de busca (BFS, DFS combinados)

---

## 3. PriorityQueue<E>

### O que é?

- `PriorityQueue<E>` é uma fila com prioridade, os elementos são ordenados por um comparador natural ou personalizado.
- Diferente de uma fila FIFO, o elemento com **maior prioridade** (menor valor comparado) sai primeiro.

### Como funciona?

- Implementada internamente como uma heap binária (min-heap).
- Operações principais (inserir, remover) têm complexidade O(log n).

### Criação

```java
// Prioridade natural (ex: inteiros, strings)
PriorityQueue<Integer> pq = new PriorityQueue<>();

// Com comparador personalizado (ex: ordem reversa)
PriorityQueue<String> pqCustom = new PriorityQueue<>(Comparator.reverseOrder());
```

### Métodos importantes

| Método             | Descrição                                                      |
| ------------------ | -------------------------------------------------------------- |
| `offer(E e)`       | Adiciona elemento à fila                                       |
| `poll()`           | Remove e retorna o elemento de maior prioridade (ou null)      |
| `peek()`           | Retorna, sem remover, o elemento de maior prioridade (ou null) |
| `remove(Object o)` | Remove elemento específico                                     |

### Quando usar?

- Para processar tarefas ou eventos por ordem de prioridade, não apenas por chegada.
- Exemplos:

  - Agendadores de tarefas (executar tarefas mais urgentes primeiro)
  - Algoritmos como Dijkstra, A\* (filas de prioridade para caminhos mínimos)
  - Sistemas de filas com prioridades (ex: suporte, impressão)

---

## Resumo e quando usar cada uma

| Estrutura         | Ordem      | Inserção/remoção                       | Uso típico                                |
| ----------------- | ---------- | -------------------------------------- | ----------------------------------------- |
| **Queue**         | FIFO       | Inserção no fim, remoção no início     | Filas tradicionais, buffers, tasks queue  |
| **Deque**         | FIFO/LIFO  | Inserção/removal nas duas pontas       | Pilhas, filas duplas, buffers flexíveis   |
| **PriorityQueue** | Prioridade | Remove o elemento com maior prioridade | Agendamento, processamento por prioridade |

---

## Exemplos práticos

```java
// Queue com LinkedList
Queue<String> queue = new LinkedList<>();
queue.offer("A");
queue.offer("B");
System.out.println(queue.poll()); // Imprime "A"

// Deque com ArrayDeque
Deque<String> deque = new ArrayDeque<>();
deque.addFirst("Start");
deque.addLast("End");
System.out.println(deque.removeLast()); // Imprime "End"

// PriorityQueue com números
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(30);
pq.offer(10);
pq.offer(20);
while(!pq.isEmpty()) {
    System.out.println(pq.poll()); // Imprime em ordem: 10, 20, 30
}
```

# Exemplos Práticos dos Principais Métodos do Stream em Java

## Operações Intermediárias

### 1. `filter()`
Filtra elementos que satisfazem um predicado.

```java
List<String> names = List.of("Ana", "Bob", "Carlos");
List<String> filtered = names.stream()
    .filter(name -> name.startsWith("A"))
    .toList(); // [Ana]
```

---

### 2. `map()`

Transforma cada elemento da stream.

```java
List<String> names = List.of("Ana", "Bob", "Carlos");
List<Integer> lengths = names.stream()
    .map(String::length)
    .toList(); // [3, 3, 6]
```

---

### 3. `flatMap()`

Achata streams dentro de streams.

```java
List<List<String>> list = List.of(
    List.of("a", "b"),
    List.of("c", "d")
);
List<String> flat = list.stream()
    .flatMap(Collection::stream)
    .toList(); // [a, b, c, d]
```

---

### 4. `sorted()`

Ordena os elementos.

```java
List<Integer> nums = List.of(5, 3, 9, 1);
List<Integer> sorted = nums.stream()
    .sorted()
    .toList(); // [1, 3, 5, 9]
```

---

### 5. `distinct()`

Remove elementos duplicados.

```java
List<Integer> nums = List.of(1, 2, 2, 3);
List<Integer> distinct = nums.stream()
    .distinct()
    .toList(); // [1, 2, 3]
```

---

### 6. `limit()`

Limita o número de elementos.

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5);
List<Integer> limited = nums.stream()
    .limit(3)
    .toList(); // [1, 2, 3]
```

---

### 7. `skip()`

Pula os primeiros N elementos.

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5);
List<Integer> skipped = nums.stream()
    .skip(2)
    .toList(); // [3, 4, 5]
```

---

## Operações Terminais

### 1. `forEach()`

Executa ação para cada elemento.

```java
List<String> names = List.of("Ana", "Bob");
names.stream()
    .forEach(System.out::println);
```

---

### 2. `collect()`

Agrupa os elementos em uma coleção.

```java
List<String> names = List.of("Ana", "Bob", "Carlos");
Set<String> set = names.stream()
    .collect(Collectors.toSet());

List<Integer> numbers = Stream.of(1, 2, 3, 4, 5)
          .collect(Collectors.toList());
```

---

### 3. `reduce()`

Reduz os elementos a um único valor.

```java
List<Integer> nums = List.of(1, 2, 3, 4);
int sum = nums.stream()
    .reduce(0, Integer::sum); // 10
```

---

### 4. `count()`

Conta o número de elementos.

```java
long count = List.of("a", "b", "c").stream()
    .count(); // 3
```

---

### 5. `anyMatch()`

Verifica se algum elemento satisfaz o predicado.

```java
boolean hasEven = List.of(1, 3, 4).stream()
    .anyMatch(n -> n % 2 == 0); // true
```

---

### 6. `allMatch()`

Verifica se todos satisfazem o predicado.

```java
boolean allEven = List.of(2, 4, 6).stream()
    .allMatch(n -> n % 2 == 0); // true
```

---

### 7. `noneMatch()`

Verifica se nenhum satisfaz o predicado.

```java
boolean noneNegative = List.of(1, 2, 3).stream()
    .noneMatch(n -> n < 0); // true
```

---

### 8. `findFirst()`

Retorna o primeiro elemento como `Optional`.

```java
Optional<String> first = List.of("a", "b").stream()
    .findFirst();
```

---

### 9. `findAny()`

Retorna algum elemento (útil para streams paralelos).

```java
Optional<String> any = List.of("a", "b").stream()
    .findAny();
```

---

### 10. `peek()`

Útil para depurar ou inspecionar o fluxo.

```java
IntStream.range(1, 6)
          .peek(n -> System.out.println("Processando: " + n))
          .map(n -> n * n)
          .forEach(System.out::println);
```
Resultado:
```sh
Processando: 1
1
Processando: 2
4
Processando: 3
9
Processando: 4
16
Processando: 5
25
```

---

# Observações

* Operações intermediárias são *lazy*: só executam quando uma operação terminal é chamada.
* Operações terminais são *eager*: disparam o processamento da stream.

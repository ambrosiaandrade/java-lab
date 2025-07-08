# 🧠 Cheatsheet — Programação Funcional em Java

---

## 📌 Conceitos Fundamentais

| Conceito                     | Descrição                                                        |
| ---------------------------- | ---------------------------------------------------------------- |
| **Imutabilidade**            | Evitar alterar dados; preferir retornar novos valores.           |
| **Funções puras**            | Sempre retornam o mesmo resultado para os mesmos parâmetros.     |
| **Referencial Transparency** | Substituir uma função por seu valor sem alterar o comportamento. |
| **Sem efeitos colaterais**   | Evitar modificar estado fora da função.                          |
| **Composição**               | Combinar funções pequenas em operações maiores.                  |
| **Lazy Evaluation**          | Avaliação apenas quando necessário (Streams aproveitam isso).    |

---

## 🔹 Lambda Expressions

```java
(param) -> expressão
(param1, param2) -> { bloco de código }
```

```java
Function<String, Integer> strLength = s -> s.length();
Predicate<Integer> isEven = n -> n % 2 == 0;
Consumer<String> printer = msg -> System.out.println(msg);
Supplier<String> randomName = () -> "John";
BinaryOperator<Integer> sum = (a, b) -> a + b;
```

---

## 🔹 Interfaces Funcionais Principais

| Interface           | Método Único      | Descrição                           |
| ------------------- | ----------------- | ----------------------------------- |
| `Function<T,R>`     | `R apply(T)`      | Transforma um valor em outro        |
| `Predicate<T>`      | `boolean test(T)` | Teste booleano com base no valor    |
| `Consumer<T>`       | `void accept(T)`  | Consome um valor (efeito colateral) |
| `Supplier<T>`       | `T get()`         | Fornece um valor sem entrada        |
| `UnaryOperator<T>`  | `T apply(T)`      | Como Function, mas entrada = saída  |
| `BinaryOperator<T>` | `T apply(T, T)`   | Combina dois valores do mesmo tipo  |
| `BiFunction<T,U,R>` | `R apply(T,U)`    | Recebe dois valores e retorna outro |

---

## 🔹 Combinando Funções (`Function.compose` / `Function.andThen`)

```java
Function<Integer, Integer> times2 = x -> x * 2;
Function<Integer, Integer> plus3 = x -> x + 3;

Function<Integer, Integer> composed = times2.compose(plus3); // (x + 3) * 2
Function<Integer, Integer> chained  = times2.andThen(plus3); // (x * 2) + 3
```

---

## 🔹 Uso com `Stream API`

```java
List<String> names = List.of("Ana", "Bruno", "Carlos");

List<String> filtered = names.stream()
    .filter(name -> name.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

---

## 🔹 Optional + Lambda

```java
Optional<String> name = Optional.of("Maria");

name
    .map(String::toUpperCase)
    .filter(n -> n.startsWith("M"))
    .ifPresent(System.out::println);
```

---

## 🔹 Currying (Funções que retornam funções)

```java
Function<Integer, Function<Integer, Integer>> sumCurried = a -> b -> a + b;
int result = sumCurried.apply(5).apply(3); // 8
```

---

## 🔹 Métodos de Referência

```java
// Equivalente a x -> System.out.println(x)
Consumer<String> printer = System.out::println;

Function<String, Integer> length = String::length;
```

---

## 🧪 Exemplos Rápidos

### ➕ Soma com `BinaryOperator`

```java
BinaryOperator<Integer> soma = Integer::sum;
System.out.println(soma.apply(10, 5)); // 15
```

### 🔍 Verificação com `Predicate`

```java
Predicate<String> startsWithA = s -> s.startsWith("A");
System.out.println(startsWithA.test("Ana")); // true
```

### 🧾 Consumidor de mensagens

```java
Consumer<String> printMsg = msg -> System.out.println(">> " + msg);
printMsg.accept("Olá mundo!");
```

---

## 🎯 Quando usar programação funcional?

✅ Quando quiser **reduzir boilerplate** (como laços e condicionais)
✅ Ao **processar coleções com transformações encadeadas**
✅ Para **funções reutilizáveis, testáveis e pequenas**
✅ Com APIs como **Streams**, **Optional**, **CompletableFuture**, etc.

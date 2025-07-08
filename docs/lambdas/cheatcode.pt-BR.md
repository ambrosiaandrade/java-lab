# Exemplos Práticos de Expressões Lambda em Java

As **Lambdas** foram introduzidas no Java 8 e permitem criar instâncias de interfaces funcionais de forma concisa e funcional.

---

## Interfaces Funcionais Comuns

### 1. `Runnable`

Executa uma tarefa sem argumentos nem retorno.

```java
Runnable r = () -> System.out.println("Running a task");
r.run(); // Running a task
```

---

### 2. `Consumer<T>`

Recebe um argumento e não retorna nada.

```java
Consumer<String> printer = s -> System.out.println("Hello " + s);
printer.accept("Java"); // Hello Java
```

---

### 3. `Supplier<T>`

Não recebe argumentos, mas retorna um valor.

```java
Supplier<Double> random = () -> Math.random();
System.out.println(random.get()); // ex: 0.7254
```

---

### 4. `Function<T, R>`

Recebe um argumento e retorna outro valor.

```java
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Lambda")); // 6
```

---

### 5. `Predicate<T>`

Recebe um argumento e retorna `true` ou `false`.

```java
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4)); // true
```

---

### 6. `BiFunction<T, U, R>`

Recebe dois argumentos e retorna um valor.

```java
BiFunction<String, String, String> concat = (a, b) -> a + b;
System.out.println(concat.apply("Lambda", " Rocks")); // Lambda Rocks
```

---

### 7. `BinaryOperator<T>`

Versão especializada de `BiFunction` onde os argumentos e retorno são do mesmo tipo.

```java
BinaryOperator<Integer> sum = (a, b) -> a + b;
System.out.println(sum.apply(10, 20)); // 30
```

---

## Exemplos com Uso Real

### 8. Usando lambdas com `List.forEach`

```java
List<String> names = List.of("Ana", "Bruno", "Carlos");
names.forEach(name -> System.out.println("Olá " + name));
```

---

### 9. Usando lambdas com `sort()`

```java
List<String> names = new ArrayList<>(List.of("Carlos", "Ana", "Bruno"));
names.sort((a, b) -> a.compareTo(b));
System.out.println(names); // [Ana, Bruno, Carlos]
```

---

### 10. Capturando variáveis externas (efeito *"closure"*)

```java
int limit = 5;
Predicate<Integer> lessThanLimit = x -> x < limit;
System.out.println(lessThanLimit.test(3)); // true
```

---

### 11. Composição de `Function`

```java
Function<String, String> toUpper = String::toUpperCase;
Function<String, String> addExcl = s -> s + "!";
Function<String, String> composed = toUpper.andThen(addExcl);

System.out.println(composed.apply("java")); // JAVA!
```

---

### 12. Lambdas em Interfaces Personalizadas

```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

Calculator add = (a, b) -> a + b;
System.out.println(add.operate(5, 3)); // 8
```

---

### 13. Lista de lambdas

```java
List<Supplier<String>> actions = List.of(
    () -> "First",
    () -> "Second",
    () -> "Third"
);

actions.forEach(s -> System.out.println(s.get()));
```

---

### 14. Lambda com Regex

```java
Predicate<String> isEmail = s -> s.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
System.out.println(isEmail.test("email@example.com")); // true
```

---

### 15. Lambda com Stream API

```java
List<String> names = List.of("Ana", "Bruno", "Carlos");
List<String> upper = names.stream()
    .filter(n -> n.length() > 3)
    .map(String::toUpperCase)
    .toList(); // [BRUNO, CARLOS]
```

---

## Dicas Importantes

* Lambdas só funcionam com **interfaces funcionais** (interfaces com exatamente **um método abstrato**).
* A anotação `@FunctionalInterface` ajuda a validar isso em tempo de compilação.
* Podem capturar variáveis externas **desde que sejam efetivamente finais**.

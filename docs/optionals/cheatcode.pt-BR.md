# 🧠 Cheatsheet Java - Optional (pt-BR)

Este guia apresenta os principais métodos e padrões de uso da API `Optional` do Java, incluindo suas variações primitivas: `OptionalInt`, `OptionalLong` e `OptionalDouble`.

---

## 📦 Criação de Optional

```java
Optional<String> opt1 = Optional.of("valor");        // Não pode ser null
Optional<String> opt2 = Optional.empty();            // Optional vazio
Optional<String> opt3 = Optional.ofNullable(possivelNulo); // Pode ser null
```

---

## ❓ Verificar presença de valor

```java
if (opt.isPresent()) {
    System.out.println("Valor presente: " + opt.get());
}

opt.ifPresent(valor -> System.out.println("Valor: " + valor));
```

---

## ⚙️ Fornecer valor padrão

```java
String resultado = opt.orElse("valor padrão");            // Usa valor padrão se vazio
String resultado2 = opt.orElseGet(() -> "gerado");        // Usa supplier, só será chamado quando opt é vazio
```

| Método        | Quando usar                         | Avaliação            | Uso típico                 |
| ------------- | ----------------------------------- | -------------------- | -------------------------- |
| `orElse()`    | Valor padrão **leve** ou constante  | Sempre avalia        | `orElse("valor")`          |
| `orElseGet()` | Valor padrão **caro** ou com lógica | Avaliação preguiçosa | `orElseGet(() -> gerar())` |

---

## 💥 Lançar exceção se vazio

```java
String resultado = opt.orElseThrow(); // Lança NoSuchElementException se vazio

String resultado2 = opt.orElseThrow(() ->
    new IllegalArgumentException("Valor ausente"));
```

---

## 🔄 Transformações

```java
Optional<String> nome = Optional.of("Java");
Optional<Integer> tamanho = nome.map(String::length);  // Transforma o valor

Optional<String> filtrado = nome.filter(s -> s.startsWith("J")); // Filtra valor

Optional<String> resultado = nome
    .filter(n -> n.length() > 2)
    .map(String::toUpperCase); // Encadeamento
```

---

## 🔢 Tipos Primitivos

### `OptionalInt`

```java
OptionalInt oi = OptionalInt.of(10);
if (oi.isPresent()) {
    System.out.println("Valor: " + oi.getAsInt());
}
```

### `OptionalLong`

```java
OptionalLong ol = OptionalLong.of(100L);
long valor = ol.orElse(0L);
```

### `OptionalDouble`

```java
OptionalDouble od = OptionalDouble.of(3.14);
double valor = od.orElseGet(Math::random);
```

---

## 🧪 Boas Práticas

- Evite usar `.get()` sem verificar `isPresent()`
- Prefira `orElseGet()` quando o valor padrão for pesado para calcular
- Use `Optional` como retorno de métodos, nunca como parâmetro
- Combine `filter()`, `map()`, `orElse()` para pipelines elegantes
- Para tipos primitivos, use `OptionalInt`, `OptionalDouble`, etc.

---

## 🔚 Finalizando

A API `Optional` ajuda a evitar `NullPointerException` e incentiva um estilo mais funcional de programação. Pratique bastante os métodos e experimente diferentes formas de composição com `map`, `flatMap` e `filter`.

# Java Optional API

![Java](https://img.shields.io/badge/Java-21-blue) ![Optional API](https://img.shields.io/badge/Optional--API-Java%208%2B-yellow)

A API `Optional`, introduzida no Java 8, fornece uma forma elegante de lidar com valores que podem estar ausentes (null), evitando assim erros comuns como o `NullPointerException`. Essa API oferece métodos funcionais para manipular valores opcionais de forma segura e legível.

---

## 📚 Sumário

- [Conceitos Chave](#conceitos-chave)
- [Criação de Optional](#criação-de-optional)
- [Verificação de valor presente](#verificação-de-valor-presente)
- [Substituição e fallback](#substituição-e-fallback)
- [Transformações e filtros](#transformações-e-filtros)
- [Optionals para tipos primitivos](#optionals-para-tipos-primitivos)
- [Exemplos de Uso](#exemplos-de-uso)
- [Exercícios Práticos](#exercícios-práticos)
- [Quando Usar Optional?](#quando-usar-optional)
- [Testes](#testes)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Chave

- **Optional:** Container que pode conter ou não um valor não-nulo.
- **Evita NullPointerException:** Força tratamento explícito da ausência de valor.
- **Métodos Funcionais:** `map()`, `filter()`, `orElse()`, `orElseGet()`, `orElseThrow()`, `ifPresent()`.
- **Optionals Primitivos:** `OptionalInt`, `OptionalLong`, `OptionalDouble` para evitar boxing desnecessário.

---

## Criação de Optional

```java
Optional<String> opt1 = Optional.of("valor");         // Não aceita null
Optional<String> opt2 = Optional.empty();             // Optional vazio
Optional<String> opt3 = Optional.ofNullable(valor);   // Aceita valor null
```

---

## Verificação de valor presente

```java
if (opt.isPresent()) {
    System.out.println(opt.get());
}

opt.ifPresent(valor -> System.out.println(valor));
```

---

## Substituição e fallback

```java
String valor = opt.orElse("valor padrão");
String valorLazy = opt.orElseGet(() -> "valor gerado");
String valorException = opt.orElseThrow(() -> new IllegalStateException("Ausente"));
```

_Dica:_ Prefira `orElseGet()` quando o valor padrão for custoso para criar, pois `orElse()` avalia sempre o argumento.

---

## Transformações e filtros

```java
Optional<String> nome = Optional.of("Java");
Optional<Integer> tamanho = nome.map(String::length);
Optional<String> filtrado = nome.filter(n -> n.startsWith("J"));
```

---

## Optionals para tipos primitivos

```java
OptionalInt oi = OptionalInt.of(10);
int valor = oi.orElse(0);

OptionalLong ol = OptionalLong.empty();
long val2 = ol.orElseGet(() -> 100L);

OptionalDouble od = OptionalDouble.of(3.14);
double val3 = od.orElseThrow();
```

---

## Exemplos de Uso

```java
Optional<String> optNome = Optional.ofNullable("Ambrósia");

String nomeEmMaiusculo = optNome
    .map(String::toUpperCase)
    .orElse("DESCONHECIDO");

System.out.println(nomeEmMaiusculo);
```

---

## Exercícios Práticos

| Arquivo                                          | Descrição                                         |
| ------------------------------------------------ | ------------------------------------------------- |
| [`LabOptionalToDo.java`](./LabOptionalToDo.java) | Classe para implementar os exercícios de Optional |
| [`LabOptionalDone.java`](./LabOptionalDone.java) | Classe com soluções completas                     |
| [`exercise.en-pt.md`](./exercise.en-pt.md)       | Lista bilíngue dos exercícios                     |

---

## Quando Usar Optional?

- Ao retornar valores que podem estar ausentes, evitando `null`.
- Para deixar o código mais expressivo e seguro.
- Em APIs modernas que desejam deixar explícita a possibilidade de ausência de valor.

---

## Testes

Os testes para este laboratório estão localizados em:
`../../tests/OptionalTest.java`

---

## Recursos Adicionais

- [Documentação Oficial Oracle - Optional](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Optional.html)
- [Guia Baeldung: Java Optional Tutorial](https://www.baeldung.com/java-optional)
- [Oracle Java Tutorials: Optional](https://docs.oracle.com/javase/tutorial/java/javaOO/optional.html)

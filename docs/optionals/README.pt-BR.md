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
Use ``Optional.ofNullable()`` sempre que a fonte do seu dado puder potencialmente retornar ``null``, e então use os métodos seguros do ``Optional`` para processar ou fornecer um valor padrão, evitando ``NullPointerExceptions``.

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

### `orElse(valorPronto)`: Ansioso (Eager)

Este método **sempre** avalia o argumento passado para ele, não importa se o `Optional` está vazio ou não. O valor padrão é criado de qualquer forma.

**É como dizer:** "Prepare este prato de qualquer jeito. Se o prato principal não estiver disponível, eu sirvo este que você já preparou."

**Exemplo:**

```java
public String getDefault() {
    System.out.println("Criando valor padrão custoso..."); // Esta linha SEMPRE será executada
    // Imagine uma chamada ao banco de dados ou API aqui
    return "Valor Padrão";
}

// O Optional TEM um valor ("Valor Real")
Optional<String> optionalComValor = Optional.of("Valor Real");
String resultado = optionalComValor.orElse(getDefault()); // "Criando valor padrão..." é impresso mesmo assim!

System.out.println(resultado); // Imprime "Valor Real"
```

**Use quando:** O valor padrão é barato e já existe (ex: uma constante, uma string literal, um objeto simples).

-----

### `orElseGet(funcaoQueCriaOValor)`: Preguiçoso (Lazy)

Este método aceita uma função (`Supplier`) e **só executa essa função** para criar o valor padrão **se, e somente se, o `Optional` estiver vazio**.

**É como dizer:** "Me dê a receita. Eu só vou cozinhar se o prato principal não estiver disponível."

**Exemplo:**

```java
public String getDefault() {
    System.out.println("Criando valor padrão custoso..."); // Esta linha SÓ será executada se necessário
    return "Valor Padrão";
}

// Cenário 1: Optional com valor
Optional<String> optionalComValor = Optional.of("Valor Real");
String r1 = optionalComValor.orElseGet(this::getDefault); // NADA é impresso
System.out.println(r1); // Imprime "Valor Real"

// Cenário 2: Optional vazio
Optional<String> optionalVazio = Optional.empty();
String r2 = optionalVazio.orElseGet(this::getDefault); // "Criando valor padrão..." AGORA é impresso
System.out.println(r2); // Imprime "Valor Padrão"
```

**Use quando:** O valor padrão é **custoso** para criar (ex: uma chamada de rede, uma consulta ao banco de dados, um cálculo complexo).

### Resumo Final

  - **`orElse()`**: Executa sempre. Bom para valores baratos e prontos.
  - **`orElseGet()`**: Executa só se precisar. Essencial para valores caros e que demandam processamento.

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

## Performance

### O Problema: `Optional<Integer>` e o "Boxing"

Primeiro, vamos entender o que é **boxing**. Em Java, temos tipos primitivos (`int`, `double`, `long`, etc.) e seus tipos de "caixa" ou "embrulho" (wrapper objects), que são `Integer`, `Double`, `Long`, etc.

  * **Boxing:** É o processo de converter um tipo primitivo em seu objeto wrapper.
      * `int meuPrimitivo = 10;`
      * `Integer meuObjeto = meuPrimitivo; // <-- Acontece o "autoboxing" aqui`
  * **Unboxing:** É o processo inverso, de objeto wrapper para primitivo.
      * `int outroPrimitivo = meuObjeto; // <-- Acontece o "unboxing" aqui`

O problema do `Optional<T>` genérico é que ele **só pode trabalhar com objetos**. Você não pode escrever `Optional<int>`, pois `int` não é um objeto. Portanto, você é forçado a usar `Optional<Integer>`.

Quando você faz isso, o Java precisa executar o boxing e unboxing constantemente:

1.  Você tem um `int`.
2.  Para colocá-lo dentro de um `Optional`, ele precisa ser "encaixotado" (boxed) em um objeto `Integer`.
3.  Para usar o valor novamente como um `int` em um cálculo, ele precisa ser "desencaixotado" (unboxed).

<!-- end list -->

```java
// Exemplo com Optional<Integer> (MENOS eficiente)

// 1. O valor 42 (primitivo 'int') é "encaixotado" para um objeto 'Integer'
//    para poder ser colocado dentro do Optional.
Optional<Integer> optionalComBoxing = Optional.of(42);

// 2. Para usar o valor, ele é obtido como 'Integer' e depois "desencaixotado"
//    para 'int' para fazer a soma.
if (optionalComBoxing.isPresent()) {
    int valor = optionalComBoxing.get(); // Unboxing implícito
    System.out.println(valor + 8); // Resultado: 50
}
```

### A Solução: `OptionalInt`

Para resolver exatamente esse problema, o Java 8 introduziu as especializações de `Optional` para tipos primitivos:

  * `OptionalInt` para `int`
  * `OptionalLong` para `long`
  * `OptionalDouble` para `double`

`OptionalInt` é um container que foi projetado para segurar um valor `int` **diretamente**, sem a necessidade de um objeto `Integer` como intermediário. Ele evita completamente o ciclo de boxing/unboxing.

```java
// Exemplo com OptionalInt (MAIS eficiente)

// 1. O valor 42 (primitivo 'int') é colocado diretamente dentro do OptionalInt.
//    NENHUM objeto Integer é criado. Não há boxing.
OptionalInt optionalSemBoxing = OptionalInt.of(42);

// 2. Para usar o valor, ele é obtido diretamente como um 'int'.
//    Não há unboxing.
if (optionalSemBoxing.isPresent()) {
    int valor = optionalSemBoxing.getAsInt(); // Método específico e direto
    System.out.println(valor + 8); // Resultado: 50
}
```

### Por que `OptionalInt` Melhora a Performance?

A melhoria de performance vem de dois fatores principais:

1.  **Redução de Alocação de Memória:** Cada vez que você faz boxing (`int` -\> `Integer`), um novo objeto é criado na *heap* da memória (com exceção de um cache para valores pequenos, geralmente de -128 a 127). A criação de objetos é uma operação mais custosa do que a manipulação de tipos primitivos, que geralmente vivem na *stack*, uma área de memória muito mais rápida. Usar `OptionalInt` evita essa alocação de objeto.

2.  **Menor Pressão no Garbage Collector (GC):** Menos objetos criados significa menos trabalho para o Coletor de Lixo (Garbage Collector). O GC precisa periodicamente pausar a aplicação (mesmo que por microssegundos) para limpar objetos que não estão mais em uso. Ao evitar a criação de milhões de objetos `Integer` desnecessários em laços ou streams, você reduz a frequência e a duração desses ciclos de coleta, resultando em uma aplicação mais fluida e com menor latência.

### Quando Usar `OptionalInt`?

A regra é simples:

> **Sempre que você for encapsular um valor `int` que pode ou não estar presente, use `OptionalInt` em vez de `Optional<Integer>`.**

O mesmo vale para `long` (`OptionalLong`) e `double` (`OptionalDouble`).

Isso é especialmente importante em cenários de alta performance e processamento de dados, como:

  * **Streams:** Ao trabalhar com `IntStream`, `LongStream` ou `DoubleStream`, operações terminais como `findFirst()`, `findAny()`, `min()`, `max()` ou `average()` retornam `OptionalInt`, `OptionalLong`, etc. Manter as operações no domínio primitivo é crucial para o desempenho dos Streams.

#### Exemplo Prático com Streams:

Imagine que você quer encontrar o primeiro número par em uma lista de inteiros.

**Abordagem Ineficiente com `Stream<Integer>`:**

```java
List<Integer> numeros = List.of(1, 3, 5, 8, 10);

// Aqui, cada elemento é um objeto Integer.
Optional<Integer> primeiroPar = numeros.stream()
                                     .filter(n -> n % 2 == 0) // Unboxing para a operação de módulo
                                     .findFirst(); // Retorna Optional<Integer>
```

**Abordagem Eficiente com `IntStream`:**

```java
List<Integer> numeros = List.of(1, 3, 5, 8, 10);

// mapToInt evita boxing para cada elemento e cria um fluxo de primitivos
OptionalInt primeiroPar = numeros.stream()
                               .mapToInt(Integer::intValue) // Converte para IntStream
                               .filter(n -> n % 2 == 0) // Operação direto no primitivo
                               .findFirst(); // Retorna OptionalInt
```

### Conclusão

 **Usar `OptionalInt`, `OptionalLong` e `OptionalDouble` é uma otimização de performance importante.** É um sinal de um código Java moderno e consciente, que evita a criação de objetos desnecessários, alivia a pressão sobre o Garbage Collector e tira proveito da velocidade dos tipos primitivos, especialmente em código que processa grandes volumes de dados.

---

## Testes

Os testes para este laboratório estão localizados em:
`../../tests/OptionalTest.java`

---

## Recursos Adicionais

- [Documentação Oficial Oracle - Optional](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Optional.html)
- [Guia Baeldung: Java Optional Tutorial](https://www.baeldung.com/java-optional)
- [Oracle Java Tutorials: Optional](https://docs.oracle.com/javase/tutorial/java/javaOO/optional.html)

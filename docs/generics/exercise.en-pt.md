# 🧪 Exercises

- [Português](#português)
  - [1 Classe Genérica Simples](#1-classe-genérica-simples)
  - [2 Método Genérico com Wildcards](#2-método-genérico-com-wildcards)
  - [3 Classe Genérica com Limites](#3-classe-genérica-com-limites)
  - [4 Cenário Realístico com Interfaces Genéricas](#4-cenário-realístico-com-interfaces-genéricas)
- [English](#english)
  - [1 Basic Generic Class](#1-basic-generic-class)
  - [2 Generic Method with Wildcards](#2-generic-method-with-wildcards)
  - [3 Generic Class with Bounded Types](#3-generic-class-with-bounded-types)
  - [4 Realistic Scenario with Generic Interfaces](#4-realistic-scenario-with-generic-interfaces)

---

## Português

### 1 Classe Genérica Simples

Crie uma **classe genérica** chamada `Pair` que possa armazenar dois objetos de **tipos diferentes**. A classe deve ter:

- Dois campos privados para armazenar os objetos.
- Um construtor que aceite dois objetos para inicializar os campos.
- Métodos `getLeft()` e `getRight()` para acessar os objetos.
- Um método `swap()` que retorne uma nova `Pair` com os objetos trocados.

No método `main`, crie algumas instâncias de `Pair` com diferentes tipos (ex: `Pair<String, Integer>`, `Pair<Double, String>`) e demonstre o uso dos métodos.

---

### 2 Método Genérico com Wildcards

Crie uma classe `ListUtilities` com os seguintes **métodos estáticos genéricos**:

1.  **`printList(List<?> list)`:** Um método que aceita qualquer tipo de `List` e imprime todos os seus elementos.
2.  **`sumOfNumbers(List<? extends Number> numbers)`:** Um método que aceita uma `List` de qualquer tipo que seja uma subclasse de `Number` (ex: `Integer`, `Double`) e retorna a soma de seus valores como um `double`.
3.  **`addNumbersToList(List<? super Integer> list)`:** Um método que adiciona os números inteiros de 1 a 5 a qualquer `List` que possa receber `Integer`s (ou supertipos de `Integer`).

No método `main`, demonstre o uso desses métodos com diferentes tipos de `List`.

---

### 3 Classe Genérica com Limites

Crie uma **classe genérica** chamada `MeasurableItem` que armazene um item de um tipo `T`. Esta classe deve ter um **parâmetro de tipo delimitado** que garanta que `T` seja sempre um tipo que implementa a interface `Comparable<T>`.

A classe `MeasurableItem` deve ter:

- Um campo privado do tipo `T` para o item.
- Um construtor que aceite um item do tipo `T`.
- Um método `getItem()` para obter o item.
- Um método `isGreaterThan(MeasurableItem<T> otherItem)` que retorne `true` se o item desta instância for maior que o item de `otherItem` (usando o método `compareTo()`).

No método `main`, crie instâncias de `MeasurableItem` com tipos como `Integer` ou `String` e teste o método `isGreaterThan`.

---

### 4 Cenário Realístico com Interfaces Genéricas

Imagine que você precisa processar diferentes tipos de `Relatórios`.

1.  Crie uma **interface genérica** chamada `ReportProcessor<T, R>` onde `T` representa o tipo de dado de entrada do relatório e `R` o tipo do resultado do processamento. A interface deve ter um método: `R process(T inputData)`.
2.  Crie duas classes que implementem `ReportProcessor`:
    - `SalesProcessor`: Processa um `List<Double>` (vendas diárias) e retorna um `Double` (total de vendas).
    - `ErrorLogProcessor`: Processa um `List<String>` (linhas de log de erro) e retorna um `Integer` (número total de erros).
3.  No método `main`, demonstre como usar diferentes `ReportProcessor`s para processar dados de vendas e logs de erro, mostrando a flexibilidade das interfaces genéricas.

---

## English

### 1 Basic Generic Class

Create a **generic class** called `Pair` that can store two objects of **different types**. The class should have:

- Two private fields to store the objects.
- A constructor that accepts two objects to initialize the fields.
- `getLeft()` and `getRight()` methods to access the objects.
- A `swap()` method that returns a new `Pair` with the objects swapped.

In the `main` method, create a few `Pair` instances with different types (e.g., `Pair<String, Integer>`, `Pair<Double, String>`) and demonstrate the use of the methods.

### 2 Generic Method with Wildcards

Create a `ListUtilities` class with the following **static generic methods**:

1.  **`printList(List<?> list)`:** A method that accepts any type of `List` and prints all its elements.
2.  **`sumOfNumbers(List<? extends Number> numbers)`:** A method that accepts a `List` of any type that is a subclass of `Number` (e.g., `Integer`, `Double`) and returns the sum of its values as a `double`.
3.  **`addNumbersToList(List<? super Integer> list)`:** A method that adds integers from 1 to 5 to any `List` that can receive `Integer`s (or supertypes of `Integer`).

In the `main` method, demonstrate the use of these methods with different types of `List`.

### 3 Generic Class with Bounded Types

Create a **generic class** called `MeasurableItem` that stores an item of type `T`. This class should have a **bounded type parameter** that ensures `T` is always a type that implements the `Comparable<T>` interface.

The `MeasurableItem` class should have:

- A private field of type `T` for the item.
- A constructor that accepts an item of type `T`.
- A `getItem()` method to retrieve the item.
- An `isGreaterThan(MeasurableItem<T> otherItem)` method that returns `true` if this instance's item is greater than `otherItem`'s item (using the `compareTo()` method).

In the `main` method, create `MeasurableItem` instances with types like `Integer` or `String` and test the `isGreaterThan` method.

### 4 Realistic Scenario with Generic Interfaces

Imagine you need to process different types of `Reports`.

1.  Create a **generic interface** called `ReportProcessor<T, R>` where `T` represents the input data type for the report and `R` represents the processing result type. The interface should have one method: `R process(T inputData)`.
2.  Create two classes that implement `ReportProcessor`:
    - `SalesProcessor`: Processes a `List<Double>` (daily sales) and returns a `Double` (total sales).
    - `ErrorLogProcessor`: Processes a `List<String>` (error log lines) and returns an `Integer` (total number of errors).
3.  In the `main` method, demonstrate how to use different `ReportProcessor`s to process sales data and error logs, showcasing the flexibility of generic interfaces.

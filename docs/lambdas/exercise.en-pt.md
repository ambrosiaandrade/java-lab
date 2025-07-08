# Lambda

## Description | Descrição

| Java Class                              | Description (EN)                                  | Descrição (PT-BR)                               |
| --------------------------------------- | ------------------------------------------------- | ----------------------------------------------- |
| [`LabLambdaToDo`](./LabLambdaToDo.java) | Java class for you to implement the exercises     | Classe Java para você implementar os exercícios |
| [`LabLambdaDone`](./LabLambdaDone.java) | Java class with all exercises already implemented | Classe Java com todos os exercícios resolvidos  |

## Exercise | Exercício

|   # | **English**                                                                                                                     | **Português**                                                                                                                         |
| --: | ------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
|   1 | 🔸 **Create a `Runnable` that prints a message**<br>📌 _Hint:_ `Runnable r = () -> System.out.println("...");`                  | 🔸 **Criar um `Runnable` que imprime uma mensagem**<br>📌 _Dica:_ `Runnable r = () -> System.out.println("...");`                     |
|   2 | 🔸 **Implement `Comparator<Person>` by age using Lambda**<br>📌 _Hint:_ `(p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())` | 🔸 **Implementar `Comparator<Person>` por idade usando Lambda**<br>📌 _Dica:_ `(p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())` |
|   3 | 🔸 **Sort a list of names alphabetically**<br>📌 _Hint:_ `list.sort((a, b) -> a.compareTo(b))`                                  | 🔸 **Ordenar uma lista de nomes em ordem alfabética**<br>📌 _Dica:_ `list.sort((a, b) -> a.compareTo(b))`                             |
|   4 | 🔸 **Create a custom `Predicate<Integer>` that checks if a number is even**<br>📌 _Hint:_ `n -> n % 2 == 0`                     | 🔸 **Criar um `Predicate<Integer>` que verifica se um número é par**<br>📌 _Dica:_ `n -> n % 2 == 0`                                  |
|   5 | 🔸 **Implement `Function<String, Integer>` that returns string length**<br>📌 _Hint:_ `s -> s.length()`                         | 🔸 **Implementar `Function<String, Integer>` que retorna o tamanho da string**<br>📌 _Dica:_ `s -> s.length()`                        |
|   6 | 🔸 **Use `Consumer<Person>` to print each person’s name**<br>📌 _Hint:_ `p -> System.out.println(p.getName())`                  | 🔸 **Usar `Consumer<Person>` para imprimir o nome de cada pessoa**<br>📌 _Dica:_ `p -> System.out.println(p.getName())`               |
|   7 | 🔸 **Create a `Supplier<Double>` that returns a random number**<br>📌 _Hint:_ `() -> Math.random()`                             | 🔸 **Criar um `Supplier<Double>` que retorna um número aleatório**<br>📌 _Dica:_ `() -> Math.random()`                                |
|   8 | 🔸 **Combine two strings using `BinaryOperator<String>`**<br>📌 _Hint:_ `(a, b) -> a + b`                                       | 🔸 **Combinar duas strings usando `BinaryOperator<String>`**<br>📌 _Dica:_ `(a, b) -> a + b`                                          |
|   9 | 🔸 **Pass a lambda to a method that takes a `Predicate`**<br>📌 _Hint:_ `filter(list, p -> p > 10)`                             | 🔸 **Passar uma lambda para um método que recebe `Predicate`**<br>📌 _Dica:_ `filter(list, p -> p > 10)`                              |
|  10 | 🔸 **Use a lambda that captures a variable from outside scope**<br>📌 _Hint:_ `n -> n > threshold`                              | 🔸 **Usar uma lambda que captura variável do escopo externo**<br>📌 _Dica:_ `n -> n > threshold`                                      |
|  11 | 🔸 **Chain two `Function<String, String>` using `andThen()`**<br>📌 _Hint:_ `f1.andThen(f2)`                                    | 🔸 **Encadear duas `Function<String, String>` usando `andThen()`**<br>📌 _Dica:_ `f1.andThen(f2)`                                     |
|  12 | 🔸 **Use a lambda inside a custom interface method**<br>📌 _Hint:_ functional interface + lambda implementation                 | 🔸 **Usar uma lambda dentro de um método de interface personalizada**<br>📌 _Dica:_ Interface funcional + implementação com lambda    |
|  13 | 🔸 **Create a list of lambdas and execute them in a loop**<br>📌 _Hint:_ `List<Runnable>` ou `List<Supplier<?>>`                | 🔸 **Criar uma lista de lambdas e executá-las em um loop**<br>📌 _Dica:_ `List<Runnable>` ou `List<Supplier<?>>`                      |
|  14 | 🔸 **Write a lambda to validate email format (regex)**<br>📌 _Hint:_ `s -> s.matches(...)`                                      | 🔸 **Escrever uma lambda para validar formato de email (regex)**<br>📌 _Dica:_ `s -> s.matches(...)`                                  |
|  15 | 🔸 **Use lambdas with Stream to combine operations**<br>📌 _Hint:_ `.stream().filter(...).map(...)`                             | 🔸 **Usar lambdas com Stream para combinar operações**<br>📌 _Dica:_ `.stream().filter(...).map(...)`                                 |

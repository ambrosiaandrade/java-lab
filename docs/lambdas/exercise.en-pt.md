# Lambda

## Description | Descrição

| Java Class                              | Description (EN)                                  | Descrição (PT-BR)                               |
| --------------------------------------- | ------------------------------------------------- | ----------------------------------------------- |
| [`LabLambdaToDo`](./LabLambdaToDo.java) | Java class for you to implement the exercises     | Classe Java para você implementar os exercícios |
| [`LabLambdaDone`](./LabLambdaDone.java) | Java class with all exercises already implemented | Classe Java com todos os exercícios resolvidos  |

## Exercise | Exercício

|  # | **English**                                                                                                                     | **Português**                                                                                                                         |
| -: | ------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
|  1 | 🔸 **Create a `Runnable` that prints a message**<br>📌 *Hint:* `Runnable r = () -> System.out.println("...");`                  | 🔸 **Criar um `Runnable` que imprime uma mensagem**<br>📌 *Dica:* `Runnable r = () -> System.out.println("...");`                     |
|  2 | 🔸 **Implement `Comparator<Person>` by age using Lambda**<br>📌 *Hint:* `(p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())` | 🔸 **Implementar `Comparator<Person>` por idade usando Lambda**<br>📌 *Dica:* `(p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())` |
|  3 | 🔸 **Sort a list of names alphabetically**<br>📌 *Hint:* `list.sort((a, b) -> a.compareTo(b))`                                  | 🔸 **Ordenar uma lista de nomes em ordem alfabética**<br>📌 *Dica:* `list.sort((a, b) -> a.compareTo(b))`                             |
|  4 | 🔸 **Create a custom `Predicate<Integer>` that checks if a number is even**<br>📌 *Hint:* `n -> n % 2 == 0`                     | 🔸 **Criar um `Predicate<Integer>` que verifica se um número é par**<br>📌 *Dica:* `n -> n % 2 == 0`                                  |
|  5 | 🔸 **Implement `Function<String, Integer>` that returns string length**<br>📌 *Hint:* `s -> s.length()`                         | 🔸 **Implementar `Function<String, Integer>` que retorna o tamanho da string**<br>📌 *Dica:* `s -> s.length()`                        |
|  6 | 🔸 **Use `Consumer<Person>` to print each person’s name**<br>📌 *Hint:* `p -> System.out.println(p.getName())`                  | 🔸 **Usar `Consumer<Person>` para imprimir o nome de cada pessoa**<br>📌 *Dica:* `p -> System.out.println(p.getName())`               |
|  7 | 🔸 **Create a `Supplier<Double>` that returns a random number**<br>📌 *Hint:* `() -> Math.random()`                             | 🔸 **Criar um `Supplier<Double>` que retorna um número aleatório**<br>📌 *Dica:* `() -> Math.random()`                                |
|  8 | 🔸 **Combine two strings using `BinaryOperator<String>`**<br>📌 *Hint:* `(a, b) -> a + b`                                       | 🔸 **Combinar duas strings usando `BinaryOperator<String>`**<br>📌 *Dica:* `(a, b) -> a + b`                                          |
|  9 | 🔸 **Pass a lambda to a method that takes a `Predicate`**<br>📌 *Hint:* `filter(list, p -> p > 10)`                             | 🔸 **Passar uma lambda para um método que recebe `Predicate`**<br>📌 *Dica:* `filter(list, p -> p > 10)`                              |
| 10 | 🔸 **Use a lambda that captures a variable from outside scope**<br>📌 *Hint:* `n -> n > threshold`                              | 🔸 **Usar uma lambda que captura variável do escopo externo**<br>📌 *Dica:* `n -> n > threshold`                                      |
| 11 | 🔸 **Chain two `Function<String, String>` using `andThen()`**<br>📌 *Hint:* `f1.andThen(f2)`                                    | 🔸 **Encadear duas `Function<String, String>` usando `andThen()`**<br>📌 *Dica:* `f1.andThen(f2)`                                     |
| 12 | 🔸 **Use a lambda inside a custom interface method**<br>📌 *Hint:* Interface funcional + implementação com lambda               | 🔸 **Usar uma lambda dentro de um método de interface personalizada**<br>📌 *Dica:* Interface funcional + implementação com lambda    |
| 13 | 🔸 **Create a list of lambdas and execute them in a loop**<br>📌 *Hint:* `List<Runnable>` ou `List<Supplier<?>>`                | 🔸 **Criar uma lista de lambdas e executá-las em um loop**<br>📌 *Dica:* `List<Runnable>` ou `List<Supplier<?>>`                      |
| 14 | 🔸 **Write a lambda to validate email format (regex)**<br>📌 *Hint:* `s -> s.matches(...)`                                      | 🔸 **Escrever uma lambda para validar formato de email (regex)**<br>📌 *Dica:* `s -> s.matches(...)`                                  |
| 15 | 🔸 **Use lambdas with Stream to combine operations**<br>📌 *Hint:* `.stream().filter(...).map(...)`                             | 🔸 **Usar lambdas com Stream para combinar operações**<br>📌 *Dica:* `.stream().filter(...).map(...)`                                 |

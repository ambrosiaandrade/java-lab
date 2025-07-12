# 🧪 Exercises

| ID  | Descrição (EN)                                                                                      | Descrição (PT-BR)                                                                                   |
| --- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| 1   | Create a `Function<String, Integer>` that returns the length of a string.                           | Crie uma `Function<String, Integer>` que retorne o comprimento da string.                           |
| 2   | Create a `Predicate<Integer>` that checks if a number is even.                                      | Crie um `Predicate<Integer>` que verifique se um número é par.                                      |
| 3   | Create a `Consumer<String>` that prints the string with `"Value: "` as a prefix.                    | Crie um `Consumer<String>` que imprima a string com prefixo `"Valor: "`.                            |
| 4   | Create a `Supplier<LocalDate>` that returns today’s date.                                           | Crie um `Supplier<LocalDate>` que retorne a data de hoje.                                           |
| 5   | Create a function that takes a list of `Integer` and uses `Stream` to return the average.           | Crie uma função que receba uma lista de `Integer` e use `Stream` para retornar a média.             |
| 6   | Create a function that filters a list of names with more than 4 letters.                            | Crie uma função que filtre uma lista de nomes com mais de 4 letras.                                 |
| 7   | Use `Optional` to encapsulate a string and print it in uppercase if present.                        | Use `Optional` para encapsular uma string e imprima em maiúsculas se estiver presente.              |
| 8   | Implement `Function<Integer, Function<Integer, Integer>>` to sum two numbers (currying).            | Implemente `Function<Integer, Function<Integer, Integer>>` para somar dois números (currying).      |
| 9   | Combine two `Function<Integer, Integer>` using `andThen` and `compose`.                             | Combine duas `Function<Integer, Integer>` usando `andThen` e `compose`.                             |
| 10  | Create a list of `String`, apply `map()` to transform to uppercase and print with `forEach`.        | Crie uma lista de `String`, aplique `map()` para transformar em maiúsculas e imprima com `forEach`. |
| 11  | Create a `UnaryOperator<String>` that removes spaces and converts the string to lowercase.          | Crie um `UnaryOperator<String>` que remova espaços e converta a string para minúsculas.             |
| 12  | Create a `BiFunction<String, Integer, String>` that repeats the string N times.                     | Crie uma `BiFunction<String, Integer, String>` que repita a string N vezes.                         |
| 13  | Use `Stream` with `filter` and `collect` to get numbers greater than 10 from a list.                | Use `Stream` com `filter` e `collect` para obter números maiores que 10 em uma lista.               |
| 14  | Use `reduce()` to concatenate a list of strings into a single string separated by hyphens.          | Use `reduce()` para concatenar uma lista de strings em uma única string separada por hífens.        |
| 15  | Create a `Map<String, Integer>` and use `forEach()` with lambda to print formatted key-value pairs. | Crie um `Map<String, Integer>` e use `forEach()` com lambda para imprimir chave e valor formatados. |

OBS: **Currying** é uma técnica de transformação de funções onde uma função que normalmente aceitaria múltiplos argumentos é transformada em uma sequência de funções, cada uma aceitando um único argumento.

# Programação Funcional em Java

![Java](https://img.shields.io/badge/Java-21-blue) ![Functional](https://img.shields.io/badge/Estilo-Funcional-green)

A Programação Funcional (PF) em Java ganhou força a partir do Java 8 com a introdução de **Lambda Expressions**, **interfaces funcionais**, **Stream API** e **Optional**. Ela permite escrever código mais **declarativo**, **modular** e **expressivo**, favorecendo a composição e a imutabilidade.

---

## 📚 Sumário

- [Conceitos Fundamentais](#conceitos-fundamentais)
- [Programação Funcional vs. Programação Orientada a Objetos (POO)](#programação-funcional-vs-programação-orientada-a-objetos-poo)
  - [Programação Orientada a Objetos (POO) 🧱](#programação-orientada-a-objetos-poo)
  - [Programação Funcional (PF) ⚛️](#programação-funcional-pf)
  - [Principais Diferenças e Características](#principais-diferenças-e-características)
  - [Quando Usar Cada um (e Juntos!)](#quando-usar-cada-um-e-juntos)
- [Interfaces Funcionais](#interfaces-funcionais)
- [Lambda Expressions](#lambda-expressions)
- [Optional](#optional)
- [Composição de Funções](#composição-de-funções)
- [Uso com Stream API](#uso-com-stream-api)
- [🤔 Não seria um exagero criar uma função que faz uma soma?](#não-seria-um-exagero-criar-uma-função-que-faz-uma-soma)
  - [Exemplo](#exemplo)
    - [Como Funciona a Redução (Passo a Passo)](#como-funciona-a-redução-passo-a-passo)
    - [O Valor Desse Exemplo ✨](#o-valor-desse-exemplo)
- [Exercícios Práticos](#exercícios-práticos)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Fundamentais

| Conceito                  | Descrição                                                |
| ------------------------- | -------------------------------------------------------- |
| **Imutabilidade**         | Dados não mudam após serem criados.                      |
| **Funções Puras**         | Mesmo input → mesmo output, sem efeitos colaterais.      |
| **First-class functions** | Funções podem ser passadas como parâmetros e retornadas. |
| **Composição**            | Combinar funções simples em operações maiores.           |
| **Lazy evaluation**       | Avaliação sob demanda (como em streams).                 |

De nada! Fico feliz em ajudar. É ótimo ver seu engajamento e dedicação aos estudos. 🚀

---

## Programação Funcional vs. Programação Orientada a Objetos (POO)

A Programação Funcional (PF) e a Programação Orientada a Objetos (POO) são dois **paradigmas de programação** distintos, cada um com sua própria filosofia sobre como estruturar e organizar o código para resolver problemas. Não são concorrentes diretos, mas sim abordagens complementares que podem (e frequentemente devem) ser usadas juntas no mesmo projeto.

### Programação Orientada a Objetos (POO) 🧱

A POO foca na **modelagem do mundo real** através de **objetos**. Um objeto é uma instância de uma **classe**, que combina **dados (atributos)** e **comportamentos (métodos)** relacionados.

- **Pilar Principal:** Objetos, classes, herança, polimorfismo, encapsulamento e abstração.
- **Estado Mutável:** Objetos em POO tipicamente têm **estado** que pode ser alterado ao longo do tempo. Por exemplo, um objeto `ContaBancaria` tem um saldo que muda após depósitos e saques.
- **Foco:** "Como as coisas _interagem_ e _mudam de estado_?"
- **Exemplos:** Java, C++, C#, Python (com aspectos de POO), Ruby.

### Programação Funcional (PF) ⚛️

A PF trata a computação como a **avaliação de funções matemáticas**, evitando estados mutáveis e dados que mudam. Ela foca no "o quê" (o resultado desejado) em vez do "como" (os passos exatos para alcançá-lo).

- **Pilar Principal:** **Funções puras**, **imutabilidade**, **efeitos colaterais mínimos ou ausentes**, e **funções de primeira classe** (funções podem ser passadas como argumentos, retornadas por outras funções, etc.).
- **Estado Imutável:** Dados são tratados como **imutáveis**. Em vez de modificar um objeto existente, uma função retorna um novo objeto com as modificações desejadas.
- **Foco:** "Como as coisas _se transformam_ de uma entrada para uma saída?"
- **Exemplos:** Haskell, Lisp, Scala (com aspectos de PF), JavaScript (com aspectos de PF), Java (a partir do Java 8 com Lambdas e Streams).

---

### Principais Diferenças e Características

| Característica         | Programação Orientada a Objetos (POO)                                          | Programação Funcional (PF)                                                      |
| :--------------------- | :----------------------------------------------------------------------------- | :------------------------------------------------------------------------------ |
| **Paradigma Central**  | Objetos e Classes                                                              | Funções e Imutabilidade                                                         |
| **Estado**             | **Mutável** (objetos podem mudar de estado)                                    | **Imutável** (funções evitam alterar dados existentes, criando novos)           |
| **Foco**               | Verbos (ações) e Nouns (dados); modelar entidades e suas interações.           | Funções como blocos de construção; transformar dados.                           |
| **Efeitos Colaterais** | Comum e geralmente aceitável (alterar o estado de objetos).                    | Evitados ou minimizados; funções puras não causam efeitos externos.             |
| **Concorrência**       | Mais desafiador devido ao estado compartilhado e mutável (requer locks, etc.). | Mais fácil de gerenciar, pois a imutabilidade reduz problemas de sincronização. |
| **Testabilidade**      | Pode ser mais complexo testar unidades que dependem de estado.                 | Funções puras são mais fáceis de testar (entradas fixas = saídas fixas).        |
| **Exemplo Java**       | Criar uma instância de `Carro`, chamar `carro.acelerar()`.                     | Usar `stream.map(s -> s.toUpperCase())` ou `list.filter(num -> num % 2 == 0)`.  |
| **Abstração**          | Encapsulamento de dados e métodos em objetos.                                  | Funções como cidadãos de primeira classe; abstração de comportamento.           |

---

### Quando Usar Cada um (e Juntos!)

- **POO:** Excelente para modelar sistemas complexos com muitas entidades e relacionamentos, onde o estado e o ciclo de vida dos objetos são importantes (por exemplo, sistemas de gestão, aplicações de negócios, interfaces gráficas).
- **PF:** Brilha em cenários de transformação de dados, processamento de coleções, lógica sem estado, algoritmos matemáticos e onde a concorrência é uma preocupação primordial (por exemplo, análise de dados, processamento de big data, pipelines de transformação de dados, APIs REST sem estado).

Em Java (a partir do Java 8), a POO e a PF não são mutuamente exclusivas. Você pode ter um código que usa classes e objetos (POO) e, dentro desses objetos, utiliza lambdas e Streams para processar dados de forma funcional. Essa combinação tira o melhor dos dois mundos: a **estrutura e modelagem da POO** com a **concisão, paralelismo e testabilidade da PF**.

---

## Interfaces Funcionais

| Interface             | Método Único          | Descrição                                |
| --------------------- | --------------------- | ---------------------------------------- |
| `Function<T,R>`       | `R apply(T t)`        | Transforma um valor em outro.            |
| `Predicate<T>`        | `boolean test(T t)`   | Avaliação booleana.                      |
| `Consumer<T>`         | `void accept(T t)`    | Executa ação com efeito colateral.       |
| `Supplier<T>`         | `T get()`             | Fornece valor sem entrada.               |
| `UnaryOperator<T>`    | `T apply(T t)`        | Como Function, entrada = saída.          |
| `BinaryOperator<T>`   | `T apply(T t1, T t2)` | Combina dois valores do mesmo tipo.      |
| `BiFunction<T, U, R>` | `R apply(T t, U u)`   | Aceita dois tipos e retorna um terceiro. |

---

## Lambda Expressions

```java
// Forma tradicional
Function<String, Integer> tamanho = new Function<>() {
    public Integer apply(String s) {
        return s.length();
    }
};

// Lambda
Function<String, Integer> tamanho2 = s -> s.length();
Predicate<Integer> par = x -> x % 2 == 0;
Consumer<String> print = System.out::println;
Supplier<Double> aleatorio = () -> Math.random();
UnaryOperator<String> caixaAlta = s -> s.toUpperCase();
BinaryOperator<Integer> soma = (num1, num2) -> num1 + num2;
BiFunction<T, U, R> combinar = (s, i) -> Double.parseDouble(s) + i; //  (entrada: String, Integer; saída: Double)
```

---

## Optional

Evita `NullPointerException` com estilo funcional:

```java
Optional<String> nome = Optional.of("João");

nome
  .map(String::toUpperCase)
  .filter(n -> n.startsWith("J"))
  .ifPresent(System.out::println);
```

---

## Composição de Funções

```java
Function<Integer, Integer> dobro = x -> x * 2;
Function<Integer, Integer> soma3 = x -> x + 3;

Function<Integer, Integer> composicao = dobro.compose(soma3); // (x + 3) * 2
Function<Integer, Integer> encadeamento = dobro.andThen(soma3); // (x * 2) + 3
```

---

## Uso com Stream API

```java
List<String> nomes = List.of("Ana", "Bruno", "Carlos");

nomes.stream()
  .filter(n -> n.length() > 4)
  .map(String::toUpperCase)
  .forEach(System.out::println);
```

---

## 🤔 Não seria um exagero criar uma função que faz uma soma?

Se é possível retornar diretamente a soma como `num1 + num2` porque implementar uma interface, como em `BinaryOperator<Integer> soma = (num1, num2) -> num1 + num2;` ?

A beleza das `interfaces funcionais` e das `lambdas` está em abstrair comportamentos para que possam ser passadas como argumentos, armazenadas em variáveis, retornados por outros métodos e reutilizadas de forma flexível, especialmente em operações com coleções (`Streams`).

Com isso:

- **Reusabilidade de Comportamento:** define uma vez e reutiliza em outros locais;
- **Abstração e Flexibilidade:** Em bibliotecas e frameworks (como a Streams API do Java), você não diz como somar, mas o que fazer (somar). A biblioteca então aplica essa "função de soma" onde for necessário.
- **Encadeamento (Pipelines):** Funções e operadores são blocos de construção que podem ser encadeados em pipelines de processamento de dados, como `stream.map(...).filter(...).reduce(...)`.

#### Exemplo

```java
List<Integer> transacoes = List.of(100, -20, 50, -30, 80);
int saldoInicial = 500;

BinaryOperator<Integer> somaBinaria = (num1, num2) -> num1 + num2;
int saldoFinal = transacoes.stream()
                           .reduce(saldoInicial, somaBinaria);
```

- **`BinaryOperator<Integer> somaBinaria = (num1, num2) -> num1 + num2;`**:
  - Aqui você define a **operação binária** que será aplicada. Ela pega dois `Integer`s e retorna um `Integer` (a soma deles).
  - Este `BinaryOperator` representa o **comportamento de soma**, que pode ser reutilizado em diversos contextos.
- **`int saldoFinal = transacoes.stream().reduce(saldoInicial, somaBinaria);`**:
  - **`transacoes.stream()`**: Cria um `Stream` a partir da sua lista de transações. Streams são o coração do processamento funcional de coleções em Java.
  - **`.reduce(saldoInicial, somaBinaria)`**: Esta é a operação de redução.
    - `saldoInicial`: É o **valor de identidade (ou valor inicial)**. É o ponto de partida para a redução e também o valor retornado se o stream estiver vazio.
    - `somaBinaria`: É o **acumulador**. Este `BinaryOperator` é aplicado repetidamente aos elementos do stream. O primeiro argumento (`num1`) recebe o resultado da operação anterior (ou `saldoInicial` na primeira vez), e o segundo argumento (`num2`) recebe o próximo elemento do stream.

##### Como Funciona a Redução (Passo a Passo)

Vamos simular como `reduce` opera com seus valores:

1.  **Início:** `acumulador` = `saldoInicial` (500)
2.  **Primeira Transação (100):** `somaBinaria.apply(500, 100)` $\rightarrow$ 600. `acumulador` agora é 600.
3.  **Segunda Transação (-20):** `somaBinaria.apply(600, -20)` $\rightarrow$ 580. `acumulador` agora é 580.
4.  **Terceira Transação (50):** `somaBinaria.apply(580, 50)` $\rightarrow$ 630. `acumulador` agora é 630.
5.  **Quarta Transação (-30):** `somaBinaria.apply(630, -30)` $\rightarrow$ 600. `acumulador` agora é 600.
6.  **Quinta Transação (80):** `somaBinaria.apply(600, 80)` $\rightarrow$ 680. `acumulador` agora é 680.
7.  **Fim do Stream:** O valor final da redução é 680.

A operação reduce pega um fluxo de elementos e os combina em um único resultado, usando uma função binária (como o BinaryOperator).

---

##### O Valor Desse Exemplo ✨

Este é um caso de uso **extremamente comum e poderoso** para `BinaryOperator` e `Stream.reduce()`. Em vez de escrever um loop `for` manual para somar, você:

1.  **Declara a Intenção:** Você expressa _o que_ quer fazer (`somaBinaria`), não _como_ (passos detalhados do loop).
2.  **Reutiliza Comportamento:** `somaBinaria` pode ser usada em qualquer outra operação de redução onde você precise somar `Integer`s.
3.  **Encadeamento Fluido:** Isso se encaixa perfeitamente em cadeias de operações de Stream (`.filter().map().reduce()`), tornando o código mais legível e mantenível.

---

## Exercícios Práticos

| Arquivo                                         | Descrição                               |
| ----------------------------------------------- | --------------------------------------- |
| [`LabFunctionalToDo`](./LabFunctionalToDo.java) | Classe com exercícios a implementar     |
| [`LabFunctionalDone`](./LabFunctionalDone.java) | Classe com soluções completas           |
| [`exercise.en-pt.md`](./exercise.en-pt.md)      | Lista de exercícios bilíngue (EN/PT-BR) |

---

## Recursos Adicionais

- [Documentação Oracle - Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Guia Baeldung - Functional Programming in Java](https://www.baeldung.com/java-functional-programming)
- [Optional no Java 8+](https://www.devmedia.com.br/java-8-optional-evitando-nullpointerexception/34487)
- [Blog da Oracle - Lambda e Streams](https://blogs.oracle.com/javamagazine/pt/uso-de-lambdas-e-streams-em-java)

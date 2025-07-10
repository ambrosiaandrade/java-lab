# 📚 Generics em Java

Generics, introduzidos no Java 5, são um recurso poderoso que permite escrever classes, interfaces e métodos que operam com objetos de **diferentes tipos**, mas com a garantia de **segurança de tipo em tempo de compilação**. Eles resolvem o problema de `ClassCastException` em tempo de execução, que era comum ao trabalhar com coleções e classes que manipulavam `Object`.

---

## 📚 Tabela de Conteúdos

- [Por Que Usar Generics?](#por-que-usar-generics)
- [Terminologia Básica de Generics](#terminologia-b%C3%A1sica-de-generics)
- [Usando Generics](#usando-generics)
  - [Classes Genéricas](#classes-gen%C3%A9ricas)
  - [Interfaces Genéricas](#interfaces-gen%C3%A9ricas)
  - [Métodos Genéricos](#m%C3%A9todos-gen%C3%A9ricos)
- [Parâmetros de Tipo Delimitados (Bounded Type Parameters)](#par%C3%A2metros-de-tipo-delimitados-bounded-type-parameters)
- [Wildcards (`?`)](#wildcards)
  - [PECS (Producer Extends, Consumer Super)](#pecs-producer-extends-consumer-super)
- [Apagamento de Tipo (Type Erasure)](#apagamento-de-tipo-type-erasure)
- [Exemplo Prático de Uso](#exemplo-pr%C3%A1tico-de-uso)
- [💡 Parâmetros de Tipo de Classe vs. Método](#-par%C3%A2metros-de-tipo-de-classe-vs-m%C3%A9todo)
  - [Parâmetro de Tipo de Classe](#par%C3%A2metro-de-tipo-de-classe)
  - [Parâmetro de Tipo de Método](#par%C3%A2metro-de-tipo-de-m%C3%A9todo)
  - [Exemplo Prático](#exemplo-pr%C3%A1tico)
- [Por que `extends` e Não `implements` em Parâmetros de Tipo?](#por-que-extends-e-n%C3%A3o-implements-em-par%C3%A2metros-de-tipo)
- [Resumo dos Wildcards e PECS](#resumo-dos-wildcards-e-pecs)
  - [`List<?>` (Unbounded Wildcard)](#list-unbounded-wildcard)
  - [`List<? extends T>` (Upper Bounded Wildcard)](#list-extends-t-upper-bounded-wildcard)
  - [`List<? super T>` (Lower Bounded Wildcard)](#list-super-t-lower-bounded-wildcard)
  - [Resumo do PECS](#resumo-do-pecs-1)

---

## Por Que Usar Generics?

Antes dos Generics, as coleções do Java (como `ArrayList` ou `HashMap`) operavam com o tipo `Object`. Isso trazia dois problemas principais:

1.  **Falta de Segurança de Tipo:** Você poderia adicionar qualquer tipo de objeto a uma coleção. Não havia garantia em tempo de compilação de que a coleção conteria apenas o tipo de objeto esperado.

    ```java
    List myList = new ArrayList(); // List de Objects
    myList.add("String 1");
    myList.add(123); // Ninguém impede você de adicionar um Integer
    String s = (String) myList.get(1); // Erro em tempo de execução: ClassCastException!
    ```

2.  **Necessidade de Casts Explícitos:** Era preciso fazer _casts_ explícitos ao recuperar objetos de uma coleção, o que tornava o código mais verboso e propenso a erros.

    ```java
    List myList = new ArrayList();
    myList.add("Hello");
    String s = (String) myList.get(0); // Cast necessário
    ```

**Generics resolvem isso ao permitir que você especifique o tipo dos objetos que uma classe ou método irá manipular, garantindo a segurança de tipo em tempo de compilação e eliminando a necessidade de casts explícitos.**

```java
List<String> myStringList = new ArrayList<>(); // Agora é uma lista DE Strings
myStringList.add("String 1");
// myStringList.add(123); // Erro em tempo de compilação! Não permite adicionar Integer
String s = myStringList.get(0); // Nenhum cast necessário!
```

---

## Terminologia Básica de Generics

- **Tipo Parametrizado:** Uma classe ou interface que é genérica. Ex: `List<String>`, `Map<Integer, String>`.
- **Parâmetro de Tipo:** O "placeholder" para um tipo, geralmente representado por uma única letra maiúscula (ex: `<E>`, `<T>`, `<K>`, `<V>`).
  - `E` - Element (usado extensivamente pela Java Collections Framework)
  - `K` - Key (chave)
  - `N` - Number (número)
  - `T` - Type (tipo)
  - `V` - Value (valor)
  - `S`, `U`, `V` - Segundo, terceiro e quarto tipos
- **Argumento de Tipo:** O tipo real que você fornece ao instanciar um tipo parametrizado. Ex: em `List<String>`, `String` é o argumento de tipo.
- **Diamond Operator (`<>`):** Introduzido no Java 7, permite que o compilador infira o argumento de tipo, tornando o código mais conciso.
  ```java
  List<String> myList = new ArrayList<>(); // Compilador infere <String>
  ```

---

## Usando Generics

Você pode usar Generics em:

### Classes Genéricas

Uma classe pode ser genérica ao declarar um ou mais parâmetros de tipo entre `< >` após o nome da classe.

```java
// Classe genérica 'Box' que pode conter qualquer tipo de objeto
public class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("Hello Generics!");
        System.out.println("Conteúdo da caixa de String: " + stringBox.getContent()); // Saída: Hello Generics!

        Box<Integer> integerBox = new Box<>(123);
        System.out.println("Conteúdo da caixa de Integer: " + integerBox.getContent()); // Saída: 123

        // Box<String> wrongBox = new Box<>(456); // Erro de compilação!
    }
}
```

### Interfaces Genéricas

Assim como classes, interfaces também podem ter parâmetros de tipo.

```java
// Interface genérica para um conversor
public interface Converter<S, T> {
    T convert(S source);
}

// Implementação de um conversor de String para Integer
public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.parseInt(source);
    }

    public static void main(String[] args) {
        Converter<String, Integer> converter = new StringToIntegerConverter();
        Integer number = converter.convert("100");
        System.out.println("String '100' convertida para Integer: " + number); // Saída: 100
    }
}
```

### Métodos Genéricos

Você pode declarar um método genérico independentemente de a classe em que ele está ser genérica ou não. O parâmetro de tipo é colocado antes do tipo de retorno do método.

```java
public class GenericMethods {

    // Método genérico para imprimir elementos de um array de qualquer tipo
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    // Método genérico para encontrar o maior de dois comparáveis
    public static <T extends Comparable<T>> T findMax(T x, T y) {
        if (x.compareTo(y) > 0) {
            return x;
        } else {
            return y;
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.print("Array de Inteiros: ");
        printArray(intArray); // Inferencia de tipo: <Integer>

        System.out.print("Array de Doubles: ");
        printArray(doubleArray); // Inferencia de tipo: <Double>

        System.out.print("Array de Caracteres: ");
        printArray(charArray); // Inferencia de tipo: <Character>

        System.out.println("Maior entre 5 e 10: " + findMax(5, 10)); // Inferencia de tipo: <Integer>
        System.out.println("Maior entre 'apple' e 'banana': " + findMax("apple", "banana")); // Inferencia de tipo: <String>
    }
}
```

---

## Parâmetros de Tipo Delimitados (Bounded Type Parameters)

Às vezes, você quer restringir os tipos que podem ser usados como parâmetros de tipo. Isso é feito usando `extends` para especificar uma classe superior ou uma interface que o tipo deve implementar.

- `<T extends Comparable<T>>`: `T` deve ser um tipo que implementa a interface `Comparable`. Isso permite chamar métodos de `Comparable` (como `compareTo()`) no tipo `T`.
- `<T extends Number>`: `T` deve ser uma subclasse de `Number` (Integer, Double, Float, etc.).

<!-- end list -->

```java
// Só aceita tipos que são subclasse de Number
public class NumericBox<T extends Number> {
    private T number;

    public NumericBox(T number) {
        this.number = number;
    }

    public double doubleValue() {
        return number.doubleValue(); // Podemos chamar métodos de Number
    }

    public static void main(String[] args) {
        NumericBox<Integer> intBox = new NumericBox<>(10);
        System.out.println("Valor Double do Integer: " + intBox.doubleValue());

        NumericBox<Double> doubleBox = new NumericBox<>(25.5);
        System.out.println("Valor Double do Double: " + doubleBox.doubleValue());

        // NumericBox<String> stringBox = new NumericBox<>("abc"); // Erro de compilação! String não é Number
    }
}
```

---

## Wildcards (`?`)

Wildcards são usados em argumentos de tipo (nas chamadas de método ou em declarações de variáveis) para expressar flexibilidade quanto aos tipos que podem ser usados.

- **`?` (Unbounded Wildcard):** Representa um tipo desconhecido. É equivalente a `<?>`.
  ```java
  public void printList(List<?> list) { // Aceita List<String>, List<Integer>, etc.
      for (Object o : list) {
          System.out.println(o);
      }
  }
  ```
- **`? extends T` (Upper Bounded Wildcard):** Representa um tipo desconhecido que é `T` ou uma **subclasse** de `T`. Usado quando você quer **ler** dados de uma estrutura genérica (princípio **PECS: Producer Extends**).
  ```java
  public void addNumbers(List<? extends Number> list) { // Aceita List<Integer>, List<Double>, etc.
      // list.add(new Integer(1)); // Erro de compilação! Não pode adicionar (a menos que seja null)
      Number num = list.get(0); // Pode ler como Number
  }
  ```
- **`? super T` (Lower Bounded Wildcard):** Representa um tipo desconhecido que é `T` ou uma **superclasse** de `T`. Usado quando você quer **escrever** dados em uma estrutura genérica (princípio **PECS: Consumer Super**).
  ```java
  public void addIntegers(List<? super Integer> list) { // Aceita List<Integer>, List<Number>, List<Object>
      list.add(10);    // Pode adicionar Integer
      list.add(20);    // Pode adicionar Integer
      // Number num = (Number) list.get(0); // Necessita cast e não é garantido que seja Number
      Object obj = list.get(0); // Pode ler como Object
  }
  ```

### PECS (Producer Extends, Consumer Super)

Uma regra mnemônica útil para wildcards:

- Se você vai **ler** (produzir) itens de uma coleção genérica, use `? extends T`.
- Se você vai **escrever** (consumir) itens para uma coleção genérica, use `? super T`.

---

## Apagamento de Tipo (Type Erasure)

Um conceito crucial em Generics é o **Type Erasure**. O Java implementa Generics usando apagamento de tipo, o que significa que as informações de tipo genérico (como `<T>` ou `<String>`) são **removidas em tempo de compilação**. O compilador usa essas informações para verificar a segurança de tipo, mas no _bytecode_ resultante, todos os tipos genéricos são substituídos por seus limites (ou `Object` se não houver limite).

- **Implicações:**
  - Você não pode usar informações de tipo genérico em tempo de execução (ex: `T.class`, `instanceof T`).
  - Não pode criar arrays de tipos genéricos diretamente (ex: `new T[10]`).
  - `List<String>` e `List<Integer>` são a mesma coisa em tempo de execução para a JVM (`List`).

Isso é importante para a **compatibilidade com versões anteriores** do Java, mas significa que os Generics são principalmente um recurso de **tempo de compilação**.

---

## Exemplo Prático de Uso

```java
import java.util.ArrayList;
import java.util.List;

public class GenericsExample {

    // Método genérico para copiar elementos de uma lista para outra
    // PECS: source é um Producer (extends), dest é um Consumer (super)
    public static <T> void copyList(List<? extends T> source, List<? super T> T> dest) {
        for (T element : source) {
            dest.add(element);
        }
    }

    public static void main(String[] args) {
        List<Integer> sourceInts = new ArrayList<>();
        sourceInts.add(1);
        sourceInts.add(2);

        List<Number> destNums = new ArrayList<>(); // Pode aceitar Integers
        copyList(sourceInts, destNums);
        System.out.println("Lista de Numbers após cópia: " + destNums); // Saída: [1, 2]

        List<Object> destObjects = new ArrayList<>(); // Pode aceitar Integers
        copyList(sourceInts, destObjects);
        System.out.println("Lista de Objects após cópia: " + destObjects); // Saída: [1, 2]

        List<Double> sourceDoubles = new ArrayList<>();
        sourceDoubles.add(3.14);

        // copyList(sourceDoubles, destInts); // Erro de compilação: Double não é subtipo de Integer para 'super'
        copyList(sourceDoubles, destNums); // Funciona: Double é subtipo de Number, Number é super de Double
        System.out.println("Lista de Numbers após adicionar Doubles: " + destNums); // Saída: [1, 2, 3.14]
    }
}
```

---

## 💡 Parâmetros de Tipo de Classe vs. Método

Ao trabalhar com Generics, é crucial entender a diferença entre um **parâmetro de tipo definido na classe** e um **parâmetro de tipo definido no método**.

### Parâmetro de Tipo de Classe

- **Onde é definido:** Após o nome da classe (ex: `MyClass<T>`).
- **Escopo:** O tipo `T` é válido por **toda a classe**. Todos os campos, construtores e métodos daquela classe podem usar e depender desse `T`.
- **Propósito:** Define o tipo dos dados que a **instância inteira da classe** irá manipular. Uma vez que você cria `MyClass<String>`, `T` _é_ `String` para aquela instância.

### Parâmetro de Tipo de Método

- **Onde é definido:** **Antes do tipo de retorno** do método (ex: `public <U> U myMethod(...)`).
- **Escopo:** O tipo `U` é válido **apenas dentro daquele método específico**. Ele é independente de qualquer parâmetro de tipo da classe.
- **Propósito:** Permite que o **método individual** seja genérico, operando com um tipo que pode ser diferente do tipo da classe, ou introduzindo um novo tipo genérico em uma classe não genérica.

#### Exemplo Prático:

```java
public class Box<T> { // T é o tipo da CLASSE
    private T item;

    public Box(T item) {
        this.item = item;
    }

    // Usa o T da CLASSE: Retorna o tipo de item que esta Box contém
    public T getItem() {
        return item;
    }

    // <U> introduz um NOVO tipo genérico U, válido apenas para este MÉTODO
    public <U> void printAndSet(U newValue) {
        System.out.println("New value type: " + newValue.getClass().getName());
        // item = (T) newValue; // Isso geraria um erro de compilação ou ClassCastException!
        // 'U' não é necessariamente compatível com 'T'.
    }
}
```

No exemplo acima, `Box<T>` define o tipo do `item` que a caixa armazena. Já o método `printAndSet<U>` é genérico por si só, permitindo que você passe _qualquer_ tipo `U` para ele, independentemente do `T` da `Box`.

Compreender essa distinção garante que você use os parâmetros de tipo no local certo, evitando erros de compilação e garantindo a segurança de tipo que Generics oferece.

---

## Por que `extends` e Não `implements` em Parâmetros de Tipo?

Essa é uma pergunta muito comum e importante\!

Em Java, na declaração de **parâmetros de tipo genéricos** (como `<T extends Comparable<T>>`), usamos a palavra-chave **`extends`** para ambos os casos:

1.  Quando queremos que o tipo `T` seja uma **subclasse** de uma determinada classe.
2.  Quando queremos que o tipo `T` **implemente** uma determinada interface.

**É uma peculiaridade da sintaxe dos Generics em Java.**

**Pense assim:**

- **`T extends MinhaClasseBase`**: Significa "T deve ser `MinhaClasseBase` ou uma de suas subclasses."
- **`T extends MinhaInterface`**: Significa "T deve implementar `MinhaInterface`."

Mesmo que `Comparable<T>` seja uma interface, a sintaxe para delimição de tipo em Generics usa `extends` para indicar que o tipo `T` deve "estender" (ou se conformar com) os comportamentos definidos por `Comparable<T>`.

Se você tentasse usar `implements` (`<T implements Comparable<T>>`), o compilador geraria um erro de sintaxe.

---

## Resumo dos Wildcards e PECS

Imagine que você tem uma **caixa (a `List`)** e quer que ela seja flexível o suficiente para trabalhar com diferentes tipos de "itens" de forma segura.

### `List<?>` (Unbounded Wildcard)

- **O que é:** Uma lista de tipo **desconhecido**.
- **Analogia:** Pense nisso como uma **caixa misteriosa**. Você sabe que tem itens lá dentro, mas não sabe o que são.
- **O que você pode fazer:**
  - **Ler:** Você pode pegar itens de dentro dela, mas eles sempre virão como `Object`, porque você não tem certeza do que está pegando.
  - **Não pode escrever (quase nunca):** Você não pode colocar nenhum item específico (fora `null`) nela, porque se a caixa for, por exemplo, de `String`s, você não pode colocar um `Integer` lá. Como você não sabe o tipo, o compilador te impede de colocar quase qualquer coisa para garantir a segurança.
- **Quando usar:** Quando você só precisa **ler** os elementos da lista, e o tipo específico não importa para a lógica (ex: imprimir todos os elementos).

### `List<? extends T>` (Upper Bounded Wildcard)

- **O que é:** Uma lista de `T` ou de **qualquer subtipo de `T`**.
- **Analogia:** Pense como uma **caixa que só aceita frutas (ou tipos mais específicos de frutas, como maçãs, bananas)**. A `T` aqui seria "Fruta". A caixa pode conter maçãs, bananas, ou qualquer coisa que _seja_ uma fruta.
- **O que você pode fazer:**
  - **Ler (Producer):** Você pode pegar itens dela e tratá-los como `T` (ou um tipo mais genérico que `T`). Se a caixa tem maçãs, você pode pegá-las e chamá-las de "fruta". **É um `Producer` porque você está `produzindo` (obtendo) valores dela.**
  - **Não pode escrever (Consumer - Restricted):** Você não pode colocar novos itens nela (exceto `null`). Se a caixa foi declarada como `List<? extends Maçã>`, ela pode estar recebendo uma lista de `MaçãFuji` ou `MaçãGala`. Se você tentar adicionar uma `MaçãVerde`, o compilador não tem como saber se `MaçãVerde` é compatível com o tipo exato da lista que foi passada (`MaçãFuji` ou `MaçãGala`). Por isso, a regra geral é: `extends` significa que você pode _ler_, mas não _escrever_.
- **Quando usar (PECS: Producer Extends):** Quando você precisa **ler** (produzir) itens de uma coleção, e esses itens devem ser de um tipo específico ou de um subtipo desse tipo. O "Extends" indica que o tipo da lista é um "produtor" de itens do tipo `T` ou seus subtipos.

### `List<? super T>` (Lower Bounded Wildcard)

- **O que é:** Uma lista de `T` ou de **qualquer supertipo de `T`**.
- **Analogia:** Pense como uma **caixa que pode receber maçãs (T), ou caixas mais genéricas que possam guardar maçãs, como uma caixa de frutas ou uma caixa de comestíveis**.
- **O que você pode fazer:**
  - **Escrever (Consumer):** Você pode colocar itens do tipo `T` (e seus subtipos) nela. Se a caixa é `List<? super Maçã>`, você pode colocar uma `Maçã` lá, ou uma `MaçãFuji`, porque você tem certeza que qualquer supertipo de `Maçã` conseguirá armazenar uma `Maçã` ou seus subtipos. **É um `Consumer` porque você está `consumindo` (adicionando) valores nela.**
  - **Não pode ler (Producer - Restricted):** Você pode pegar itens dela, mas eles virão como `Object`. Se a caixa é `List<? super Maçã>`, ela pode ser uma `List<Fruta>` ou `List<Object>`. Se você pegar um item, não pode ter certeza que é uma `Maçã` sem um cast, então o compilador te força a tratar como `Object`.
- **Quando usar (PECS: Consumer Super):** Quando você precisa **escrever** (consumir) itens em uma coleção, e esses itens são de um tipo específico ou um supertipo. O "Super" indica que a lista é um "consumidor" de itens do tipo `T` ou seus subtipos.

---

### Resumo do PECS

**PECS** é a sigla para:

- **P**roducer **E**xtends
- **C**onsumer **S**uper

É uma regra mnemônica para te ajudar a lembrar qual wildcard usar:

- Se a lista está **produzindo** valores para você (você está _lendo_ dela), use `? extends T`.
- Se a lista está **consumindo** valores que você está dando a ela (você está _escrevendo_ nela), use `? super T`.

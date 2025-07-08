| Versão     | Funcionalidades                                              |
| ---------- | ------------------------------------------------------------ |
| Java 8     | Lambdas, Streams, Optional, java.time, default methods       |
| Java 9-10  | Modules, inferência com var                                  |
| Java 11    | String, Files, HttpClient                                    |
| Java 14-17 | Records, Pattern Matching, Switch Expression                 |
| Java 21+   | Virtual Threads, SequencedCollection, Structured Concurrency |

## Pattern Matching em Java

O **Pattern Matching** é um recurso que facilita o trabalho com tipos e estruturas, permitindo que você teste se um objeto corresponde a um determinado padrão e, ao mesmo tempo, faça a extração de variáveis diretamente, sem a necessidade de cast explícito.

---

### Antes do Pattern Matching

Para verificar o tipo e usar o objeto, era comum fazer assim:

```java
if (obj instanceof String) {
    String s = (String) obj; // cast explícito
    System.out.println(s.toUpperCase());
}
```

Ou no `switch`, só dava para usar valores constantes.

---

### Com Pattern Matching (desde Java 16 e evoluindo)

Agora você pode fazer o teste e a extração numa só expressão:

```java
if (obj instanceof String s) {
    System.out.println(s.toUpperCase());
}
```

Aqui, o `instanceof` já declara e inicializa a variável `s` automaticamente se o teste passar.

---

### Pattern Matching no `switch` (Java 21)

Além do `if`, você pode usar o `switch` para fazer vários testes de tipos e extrair variáveis:

```java
switch (obj) {
    case String s -> System.out.println("String: " + s);
    case Integer i -> System.out.println("Integer: " + i);
    default -> System.out.println("Outro tipo");
}
```

Assim você trata vários tipos com clareza e sem casts.

---

### Guardas com `when`

É possível colocar uma condição extra para filtrar o padrão:

```java
if (obj instanceof Integer i && i > 10) {
    System.out.println("Inteiro maior que 10: " + i);
}
```

Ou no switch:

```java
switch (obj) {
    case Integer i when i > 10 -> System.out.println("Inteiro > 10: " + i);
    case Integer i -> System.out.println("Inteiro <= 10: " + i);
    default -> System.out.println("Outro tipo");
}
```

---

### Vantagens do Pattern Matching

- Menos código repetitivo com casts.
- Código mais seguro e claro.
- Mais fácil de manter e entender.
- Pode ser combinado com tipos selados para segurança extra.

---

## Sealed Classes em Java

As **Sealed Classes** (classes seladas) são um recurso introduzido no Java 17 que permite controlar quais outras classes ou interfaces podem estender ou implementar uma determinada classe ou interface.

---

### Por que usar Sealed Classes?

Elas servem para limitar a hierarquia de classes, garantindo que somente um conjunto restrito de subclasses possa existir. Isso traz:

- **Segurança**: evita que alguém crie subclasses não autorizadas.
- **Controle**: permite que o desenvolvedor defina exatamente quem pode estender a classe.
- **Verificação pelo compilador**: ajuda o compilador a garantir que todos os casos de uma hierarquia sejam tratados (por exemplo, em um switch).

---

### Como declarar uma Sealed Class?

Você usa a palavra-chave `sealed` junto com `permits` para indicar as classes que podem estender a classe selada.

```java
public sealed class Shape permits Circle, Rectangle, Square {
    // código da classe base
}
```

Aqui, só `Circle`, `Rectangle` e `Square` podem estender `Shape`.

---

### Como declarar as subclasses?

As subclasses precisam declarar o que fazem em relação à herança:

- `final`: não podem ser estendidas.
- `sealed`: podem ter suas próprias subclasses, mas também restritas.
- `non-sealed`: podem ser estendidas livremente (abertas).

Exemplo:

```java
public final class Circle extends Shape {
    // implementação
}

public sealed class Rectangle extends Shape permits FilledRectangle {
    // implementação
}

public non-sealed class Square extends Shape {
    // pode ser estendida livremente
}
```

---

### Exemplo completo

```java
public sealed interface Vehicle permits Car, Truck {}

public final class Car implements Vehicle {
    // ...
}

public non-sealed class Truck implements Vehicle {
    // pode ter subclasses abertas
}
```

---

### Benefícios

- **Modelagem explícita da hierarquia**: o design fica claro e seguro.
- **Permite uso avançado com pattern matching**: o compilador sabe todos os tipos possíveis e pode exigir tratamento completo.
- **Facilita manutenção e evolução do código.**

---

## Reflection em Java

**Reflection** é uma API poderosa que permite que o programa examine e modifique sua própria estrutura em tempo de execução — ou seja, você pode inspecionar classes, métodos, campos e construtores dinamicamente enquanto o programa está rodando.

---

### Para que serve Reflection?

- Instanciar objetos sem conhecer suas classes em tempo de compilação.
- Acessar e modificar atributos privados de objetos.
- Invocar métodos dinamicamente.
- Ferramentas de frameworks (ex: frameworks de injeção de dependência, ORM, bibliotecas de testes) usam Reflection para funcionar.
- Criar bibliotecas genéricas que manipulam objetos sem precisar de dependências diretas.

---

### Como usar Reflection?

#### Exemplo básico: obter a classe de um objeto

```java
String s = "Olá";
Class<?> clazz = s.getClass();
System.out.println(clazz.getName()); // java.lang.String
```

#### Criar instância dinamicamente

```java
Class<?> clazz = Class.forName("java.util.ArrayList");
List<String> list = (List<String>) clazz.getDeclaredConstructor().newInstance();
list.add("Item 1");
System.out.println(list);
```

#### Acessar método e invocar

```java
Method method = clazz.getMethod("size");
int size = (int) method.invoke(list);
System.out.println("Tamanho da lista: " + size);
```

### Acessar campo privado

```java
Field field = SomeClass.class.getDeclaredField("campoPrivado");
field.setAccessible(true);
Object value = field.get(objeto);
field.set(objeto, novoValor);
```

---

### Cuidados ao usar Reflection

- Pode quebrar encapsulamento, acessando membros privados.
- Performance é mais lenta do que chamadas normais.
- Pode causar erros difíceis de depurar (ex: `NoSuchMethodException`, `IllegalAccessException`).
- Uso excessivo pode tornar código menos legível.

---

### Quando usar Reflection?

- Frameworks e bibliotecas dinâmicas.
- Ferramentas de teste e depuração.
- Serialização/deserialização genérica.
- Quando é necessário um comportamento dinâmico baseado em tipos não conhecidos em tempo de compilação.

## `Supplier<T>`

Entender o **`Supplier`** em Java é mais simples do que parece\! Pense nele como uma **fábrica de um único produto** que você só ativa quando precisa.

Em termos técnicos, `Supplier<T>` é uma interface funcional que faz parte do pacote `java.util.function`. Ela possui apenas um método abstrato:

```java
T get();
```

Este método `get()` **não recebe nenhum argumento e retorna um valor do tipo `T`**.

---

### O que significa "fábrica de um único produto"?

Imagine que você tem uma receita para fazer um bolo, mas só vai fazer o bolo se realmente for necessário. O `Supplier` é como essa **receita**.

- **A "receita" (`Supplier`)**: Define como obter o bolo (o valor do tipo `T`).
- **O "bolo" (`T`)**: É o valor que será produzido quando você "executar a receita".

Você só executa a receita (chama o método `get()`) quando realmente precisa do bolo.

---

### Quando e por que usar um `Supplier`?

O `Supplier` é super útil em cenários onde você quer **adiar a criação ou o cálculo de um valor** até que ele seja realmente necessário. Isso é conhecido como **avaliação preguiçosa (lazy evaluation)**.

Vamos ver alguns exemplos práticos:

#### 1\. Valores Padrão em `Optional.orElseGet()`

Este é um dos usos mais comuns, e é onde o `Supplier` brilha em conjunto com `Optional`.

No seu exemplo anterior, você viu `orElseGet()`:

```java
public String getUserNameById(String id) {
    String userName = findUserInDatabase(id);
    Optional<String> optionalUserName = Optional.ofNullable(userName);

    // Usando orElseGet com um Supplier (lambda expression)
    return optionalUserName.orElseGet(() -> "Usuário desconhecido");
}
```

Aqui, `() -> "Usuário desconhecido"` é um `Supplier<String>`.

- Se `optionalUserName` **contiver** um valor (o usuário for encontrado), o `Supplier` **nunca será invocado**, e a String "Usuário desconhecido" **nunca será criada**. Isso economiza recursos.
- Se `optionalUserName` **estiver vazio** (o usuário não for encontrado), aí sim o `Supplier` é invocado, e ele **fornece** a String "Usuário desconhecido".

Compare isso com `orElse("Usuário desconhecido")`:

```java
// Usando orElse - a String "Usuário desconhecido" é criada SEMPRE, mesmo que não seja usada
return optionalUserName.orElse("Usuário desconhecido");
```

Com `orElse()`, o valor padrão (`"Usuário desconhecido"`) é criado **sempre**, independentemente de o `Optional` ter um valor ou não. Para Strings simples, isso não é um problema, mas imagine se o valor padrão fosse o resultado de uma operação cara (como uma consulta a um banco de dados ou um cálculo complexo). O `orElseGet()` evita essa criação desnecessária, usando o `Supplier`.

#### 2\. Log condicional ou operações caras

Imagine que você só quer gerar uma mensagem de log muito complexa se o nível de log estiver ativado:

```java
import java.util.function.Supplier;

public class LoggerExample {

    public void logIfDebugEnabled(boolean debugEnabled) {
        if (debugEnabled) {
            // Se o debug estiver ativado, só então a mensagem complexa é gerada
            System.out.println(getComplexLogMessage());
        } else {
            // A mensagem complexa nunca é gerada se o debug estiver desativado
            System.out.println("Debug não ativado. Nenhuma mensagem complexa gerada.");
        }
    }

    // Método que simula uma operação cara para gerar a mensagem
    private String getComplexLogMessage() {
        System.out.println("Gerando mensagem de log complexa...");
        // Simula um cálculo ou busca de dados demorada
        try {
            Thread.sleep(1000); // Demora 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Mensagem de log detalhada: Dados carregados com sucesso em " + System.currentTimeMillis();
    }

    public static void main(String[] args) {
        LoggerExample logger = new LoggerExample();

        System.out.println("--- Cenário 1: Debug DESATIVADO ---");
        logger.logIfDebugEnabled(false); // Mensagem complexa NÃO é gerada

        System.out.println("\n--- Cenário 2: Debug ATIVADO ---");
        logger.logIfDebugEnabled(true);  // Mensagem complexa É gerada
    }
}
```

Se você passasse o resultado de `getComplexLogMessage()` diretamente para o método de log, ele seria executado **sempre**. Com um `Supplier`, você passaria a **lógica** de como obter a mensagem, e essa lógica só seria executada se a condição `debugEnabled` fosse verdadeira.

---

### Em resumo:

- **O que é?** Uma interface funcional com o método `get()` que retorna um valor do tipo `T`.
- **O que faz?** Fornece um valor (cria um objeto, executa um cálculo, etc.).
- **Quando usar?** Quando você quer **adiar a criação ou o cálculo de um valor** até o momento em que ele é realmente necessário (avaliação preguiçosa).

Pense no `Supplier` como uma "caixa" que contém a instrução de como obter um valor, mas que só entrega esse valor quando você a "abre" (chamando `get()`). Isso otimiza seu código, evitando que operações desnecessariamente custosas sejam executadas.

## `Consumer<T>`

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerStreamExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Usando forEach com um Consumer para imprimir cada nome
        names.stream().forEach(name -> System.out.println("Nome: " + name));

        // Outro Consumer para adicionar um prefixo e imprimir
        Consumer<String> printWithPrefix = name -> System.out.println("Olá, " + name + "!");
        names.stream().forEach(printWithPrefix);

        // Você pode encadear Consumers (não é tão comum, mas é possível)
        Consumer<String> logAndPrint = name -> {
            System.out.println("Processando: " + name);
            System.out.println("Exibindo: " + name.toUpperCase());
        };
        names.stream().forEach(logAndPrint);
    }
}
```

**Em resumo:**

- **O que é?** Uma interface funcional com o método void `accept(T t)` que recebe um valor do tipo T e não retorna nada.

- **O que faz?** Realiza uma ação ou efeito colateral com o valor fornecido.

- **Quando usar?** Quando você quer executar uma operação em um valor (ou em cada valor de uma coleção/Stream) sem esperar um retorno, especialmente em contextos onde o valor pode estar ausente (`Optional.ifPresent()`) ou para processamento em pipelines de Stream.

O `Consumer` é o oposto do `Supplier` nesse sentido: um fornece, o outro consome/processa. Ambos são pilares da programação funcional em Java, permitindo que você passe comportamentos como argumentos de forma concisa e eficiente.

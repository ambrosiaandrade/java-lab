Versão	Funcionalidades
Java 8	Lambdas, Streams, Optional, java.time, default methods
Java 9-10	Modules, inferência com var
Java 11	String, Files, HttpClient
Java 14-17	Records, Pattern Matching, Switch Expression
Java 21+	Virtual Threads, SequencedCollection, Structured Concurrency


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

* Instanciar objetos sem conhecer suas classes em tempo de compilação.
* Acessar e modificar atributos privados de objetos.
* Invocar métodos dinamicamente.
* Ferramentas de frameworks (ex: frameworks de injeção de dependência, ORM, bibliotecas de testes) usam Reflection para funcionar.
* Criar bibliotecas genéricas que manipulam objetos sem precisar de dependências diretas.

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

* Pode quebrar encapsulamento, acessando membros privados.
* Performance é mais lenta do que chamadas normais.
* Pode causar erros difíceis de depurar (ex: `NoSuchMethodException`, `IllegalAccessException`).
* Uso excessivo pode tornar código menos legível.

---

### Quando usar Reflection?

* Frameworks e bibliotecas dinâmicas.
* Ferramentas de teste e depuração.
* Serialização/deserialização genérica.
* Quando é necessário um comportamento dinâmico baseado em tipos não conhecidos em tempo de compilação.


# Exemplos Práticos de Records em Java

Desde o Java 14 (preview) e oficialmente no Java 16, `record` é uma forma concisa e imutável de declarar classes que existem apenas para armazenar dados.

---

## 🔸 Declaração Básica

```java
public record Person(String name, int age) {}
```

Equivalente a:

```java
public final class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() { return name; }
    public int age() { return age; }

    // equals(), hashCode() e toString() são gerados automaticamente
}
```

---

## 🔸 Uso Básico

```java
Person p = new Person("Ana", 30);
System.out.println(p.name()); // Ana
System.out.println(p);        // Person[name=Ana, age=30]
```

---

## 🔸 Records são imutáveis

Os campos não podem ser modificados após a construção:

```java
p.name = "Carlos"; // Erro de compilação
```

---

## 🔸 Validando no Construtor Compacto

```java
public record Product(String name, double price) {
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }
    }
}
```

---

## 🔸 Métodos podem ser adicionados

```java
public record Rectangle(double width, double height) {
    public double area() {
        return width * height;
    }
}
```

---

## 🔸 Implementando interfaces

```java
public record Point(int x, int y) implements Comparable<Point> {
    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.x, other.x);
    }
}
```

---

## 🔸 Com anotações

```java
public record User(@NotNull String username, int level) {}
```

---

## Observações

* Um record **não pode herdar** de outra classe (sempre herda de `java.lang.Record`)
* Pode implementar interfaces
* Ideal para representar **DTOs**, **valores imutáveis**, **respostas de APIs** e **chaves de mapas**

```

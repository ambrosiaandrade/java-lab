# Record no Java

![Java](https://img.shields.io/badge/Java-16%2B-blue) ![Recurso](https://img.shields.io/badge/Recurso-record-lightgrey)

O `record` é uma estrutura especial introduzida no Java 14 (preview) e oficialmente no Java 16, criada para facilitar a definição de classes que apenas armazenam dados, de forma imutável e concisa.

---

## 📚 Sumário

- [Conceitos Principais](#conceitos-principais)
- [Sintaxe Básica](#sintaxe-básica)
- [Imutabilidade](#imutabilidade)
- [Construtor Compacto e Validação](#construtor-compacto-e-validação)
- [Adicionando Métodos](#adicionando-métodos)
- [Interfaces e Anotações](#interfaces-e-anotações)
- [Exercícios Práticos](#exercícios-práticos)
- [Quando Usar Records?](#quando-usar-records)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Principais

- `record` fornece uma **sintaxe enxuta** para classes de dados imutáveis
- Geração automática de:
  - Construtor
  - Métodos de acesso (sem prefixo `get`)
  - `equals()`, `hashCode()` e `toString()`
- Sempre `final` (não pode ser estendida)
- Pode implementar interfaces, mas não pode herdar de classes

---

## Sintaxe Básica

```java
public record Pessoa(String nome, int idade) {}
```

---

## Imutabilidade

Uma vez criado, o objeto `record` não pode ser alterado. Todos os campos são `private final` por padrão.

---

## Construtor Compacto e Validação

É possível validar os dados de entrada:

```java
public record Produto(String nome, double preco) {
    public Produto {
        if (preco < 0)
            throw new IllegalArgumentException("Preço inválido");
    }
}
```

---

## Adicionando Métodos

Você pode adicionar métodos como em uma classe comum:

```java
public record Retangulo(double largura, double altura) {
    public double area() {
        return largura * altura;
    }
}
```

---

## Interfaces e Anotações

É possível implementar interfaces e adicionar anotações:

```java
public record Usuario(@NotNull String login, int nivel) implements Serializable {}
```

---

## Exercícios Práticos

| Classe Java                               | Descrição (PT-BR)                               |
| ----------------------------------------- | ----------------------------------------------- |
| [`LabRecordsToDo`](./LabRecordsToDo.java) | Classe Java para você implementar os exercícios |
| [`LabRecordsDone`](./LabRecordsDone.java) | Classe Java com todos os exercícios resolvidos  |

Para ver a lista completa de exercícios, acesse [`exercise.en-pt.md`](./exercise.en-pt.md)

---

## Quando Usar Records?

* Para DTOs (Data Transfer Objects)
* Modelos de resposta de APIs imutáveis
* Como chave de `Map`
* Para representar dados simples com pouco comportamento

---

## Recursos Adicionais

* [Documentação Oficial da Oracle: Records](https://docs.oracle.com/en/java/javase/21/language/records.html)
* [Baeldung: Guia sobre Java Records](https://www.baeldung.com/java-record-keyword)
* [Java Records na Prática (DevMedia)](https://www.devmedia.com.br/records-no-java/42698)

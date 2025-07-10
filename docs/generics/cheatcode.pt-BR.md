## 🚀 Cheat Sheet: Java Generics (Direto ao Ponto)

Generics é um recurso do Java 5+ para escrever código mais seguro em relação a tipos, reutilizável e legível, eliminando `ClassCastException` em tempo de execução e _casts_ explícitos.

---

- [Por que Usar?](#por-que-usar)
- [Terminologia Essencial](#terminologia-essencial)
- [Como Usar Generics](#como-usar-generics)
- [Restrições de Tipos (Bounds)](#restrições-de-tipos-bounds)
- [Wildcards (`?`) - Flexibilidade em Parâmetros](#wildcards---flexibilidade-em-parâmetros)
- [PECS: **P**roducer **E**xtends, **C**onsumer **S**uper](#pecs-producer-extends-consumer-super)
- [Type Erasure (Apagamento de Tipo)](#type-erasure-apagamento-de-tipo)

---

### Por que Usar?

- **Segurança de Tipo em Compilação:** Pega erros de tipo antes do código rodar.
- **Reusabilidade:** Escreva uma vez, use com vários tipos.
- **Clareza:** Reduz _casts_ e torna o código mais limpo.

---

### Terminologia Essencial

- **`List<String>`**: `List` é o tipo genérico, `String` é o argumento de tipo.
- **`<T>` (Type Parameter)**: Placeholder para um tipo (`E`, `K`, `V` são comuns).
- **`<>` (Diamond Operator)**: (Java 7+) Inferência de tipo ao instanciar: `new ArrayList<>()`.

---

### Como Usar Generics

1.  **Classes Genéricas:**

    ```java
    public class Box<T> { // T é o tipo que a Box guarda
        private T content;
        public Box(T c) { this.content = c; }
        public T getContent() { return content; }
    }
    // Uso: Box<String> box = new Box<>("Olá");
    ```

2.  **Interfaces Genéricas:**

    ```java
    public interface Converter<S, T> { // S: Source, T: Target
        T convert(S source);
    }
    // Uso: public class StrToInt implements Converter<String, Integer> {...}
    ```

3.  **Métodos Genéricos:**

    ```java
    public class Util {
        // <E> antes do tipo de retorno, E é local ao método
        public static <E> void printArray(E[] array) { /* ... */ }
    }
    // Uso: Util.printArray(new Integer[]{1,2,3});
    ```

---

### Restrições de Tipos (Bounds)

Use `extends` para limitar os tipos que `T` pode ser:

- **`<T extends ClasseBase>`**: `T` deve ser `ClasseBase` ou uma subclasse.
- **`<T extends Interface>`**: `T` deve implementar `Interface`.
  - **Lembre-se:** Sempre `extends`, mesmo para interfaces\!

<!-- end list -->

```java
// T deve ser um Number (Integer, Double, etc.) ou subclasse
public class NumericBox<T extends Number> { /* ... */ }
```

---

### Wildcards (`?`) - Flexibilidade em Parâmetros

Usados em **argumentos de tipo** (variáveis, parâmetros de método) para maior flexibilidade:

1.  **`List<?>` (Unbounded Wildcard):** Lista de tipo **desconhecido**.

    - **Pode ler:** Itens vêm como `Object`.
    - **Não pode escrever:** Exceto `null`.
    - **Uso:** Quando o tipo específico não importa para leitura genérica.

2.  **`List<? extends T>` (Upper Bounded Wildcard):** Lista de `T` ou seus **subtipos**.

    - **Pode ler (Producer):** Itens podem ser lidos como `T` (ou mais genérico).
    - **Não pode escrever:** Exceto `null`.
    - **Uso:** Quando a lista é uma **fonte** de dados (você só vai pegar itens dela).

3.  **`List<? super T>` (Lower Bounded Wildcard):** Lista de `T` ou seus **supertipos**.

    - **Não pode ler:** Itens vêm como `Object`.
    - **Pode escrever (Consumer):** Pode adicionar `T` e seus subtipos.
    - **Uso:** Quando a lista é um **destino** para dados (você vai adicionar itens nela).

---

### PECS: **P**roducer **E**xtends, **C**onsumer **S**uper

- Se a coleção é uma **PRODUTORA** de dados (você **lê** dela), use `? **EXTENDS** T`.
- Se a coleção é uma **CONSUMIDORA** de dados (você **escreve** nela), use `? **SUPER** T`.

---

### Type Erasure (Apagamento de Tipo)

- **O que é:** As informações de tipo genérico (`<String>`, `<T>`) são **removidas** pelo compilador no _bytecode_.
- **Impacto:** Generics são principalmente um recurso de **tempo de compilação** para segurança.
- **Consequências:**
  - Não pode usar `instanceof T` ou `new T()`.
  - `List<String>` e `List<Integer>` são apenas `List` em tempo de execução.

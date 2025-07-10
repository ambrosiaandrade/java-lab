## Classificação

- Os **padrões criacionais** fornecem mecanismos de criação de objetos que aumentam a flexibilidade e a reutilização de código.

- Os **padrões estruturais** explicam como montar objetos e classes em estruturas maiores, enquanto ainda mantém as estruturas flexíveis e eficientes.

- Os **padrões comportamentais** cuidam da comunicação eficiente e da assinalação de responsabilidades entre objetos.

## Design Patterns Mais Usados no Mercado Java

Esses padrões cobrem uma boa parte dos problemas comuns de design que você enfrentará e são amplamente reconhecidos e aplicados na indústria. Dominá-los lhe dará uma base muito sólida para entender o código existente e para projetar suas próprias soluções de forma mais robusta e manutenível.

### 1. **Singleton (Criacional)**

- **Por que é popular:** Extremamente simples de entender e implementar. É usado para garantir que uma classe tenha apenas uma instância global, o que é útil para gerenciadores de configuração, loggers, pools de conexão (embora o Spring geralmente gerencie isso) ou objetos de serviço que não precisam de múltiplas instâncias.
- **Onde você o vê:** Em quase todo lugar, embora o Spring (via beans com escopo Singleton) gerencie isso para você na maioria das vezes, é crucial entender o padrão puro.

### 2. **Factory Method (Criacional)**

- **Por que é popular:** Essencial para desacoplar a criação de objetos da lógica de negócio. Quando você precisa criar diferentes tipos de objetos baseados em alguma condição, o Factory Method encapsula essa lógica, tornando seu código mais flexível e fácil de estender.
- **Onde você o vê:** Bancos de dados (fábricas de conexões), parsers, leitores de arquivos, e em qualquer lugar onde a decisão de qual objeto instanciar é dinâmica.

### 3. **Strategy (Comportamental)**

- **Por que é popular:** Permite que você defina uma família de algoritmos, coloque cada um em uma classe separada e os torne intercambiáveis. Isso é perfeito para lógica de negócio que muda ou que possui múltiplas variações. Você pode mudar o "comportamento" de um objeto em tempo de execução.
- **Onde você o vê:** Processadores de pagamento, validadores de entrada, algoritmos de ordenação, diferentes estratégias de cálculo de frete, regras de negócio. Com o Spring, isso é frequentemente implementado usando injeção de dependência para diferentes beans de estratégia.

### 4. **Observer (Comportamental)**

- **Por que é popular:** Fundamental para sistemas orientados a eventos. Permite que um objeto (sujeito) notifique múltiplos objetos (observadores) automaticamente quando seu estado muda, sem que o sujeito precise saber detalhes dos observadores.
- **Onde você o vê:** Interfaces gráficas (eventos de clique de botão), sistemas de mensagens, sistemas de log, e qualquer sistema onde a mudança de estado de um componente afeta outros.

### 5. **Decorator (Estrutural)**

- **Por que é popular:** Oferece uma maneira flexível de estender a funcionalidade de um objeto dinamicamente, sem modificar sua classe original e sem criar muitas subclasses. Você "envolve" o objeto original com novos comportamentos.
- **Onde você o vê:** Streams de I/O em Java (BufferdInputStream, GzipInputStream), componentes de UI, ou para adicionar logging/cache/segurança a métodos de serviço sem alterá-los diretamente.

### 6. **Facade (Estrutural)**

- **Por que é popular:** Simplifica o acesso a um subsistema complexo. Ele fornece uma interface única e simplificada para um conjunto de interfaces em um subsistema, facilitando o uso e reduzindo o acoplamento.
- **Onde você o vê:** APIs que expõem funcionalidades complexas de backend, módulos de serviço em aplicações maiores que orquestram várias outras classes.

---

## Implementação

### Singleton

- 1\. Adicione um campo privado e estático para armazenar a instância singleton.
- 2\. O construtor da classe é privado para impedir que a classe seja instanciada diretamente de fora usando `new`.
- 3\. Crie um método estático e público, por exemplo `getInstance()`, que irá retornar a mesma instância. Na primeira chamada desse método, ele usará o construtor privado para criar a instância; nas chamadas subsequentes, ele simplesmente retornará a instância já existente.

> OBS: Esse padrão requer um tratamento especial em um ambiente multithreaded para que múltiplas threads não criem vários singleton.


```java
public class Singleton {

    // 1. Campo privado e estático para armazenar a instância única
    private static Singleton instance;

    // 2. Construtor privado para impedir instanciação externa
    private Singleton() {
        // Opcional: inicializações complexas ou caras podem ir aqui
        System.out.println("Singleton: Instância criada.");
    }

    // 3. Método estático e público para obter a instância
    public static Singleton getInstance() {
        if (instance == null) {
                instance = new Singleton();
            }
        return instance;
        }
    }

    // Método de exemplo para demonstrar o uso
    public void showMessage() {
        System.out.println("Olá do Singleton!");
    }
}
```

**Uma implementação mais avançada com tratamento de muitas ``threads`` com  double check lock**

```java
public class Singleton {

    // O campo deve ser declarado como `volatile` para que a dupla verificação funcione corretamente
    private static volatile Singleton instance;

    private Singleton() {
        System.out.println("Singleton: Instância criada.");
    }

    public static Singleton getInstance() {
        // OBS: Tratamento para ambiente multithreaded (abordagem "Lazy Initialization" com "Double-Checked Locking")
        if (instance == null) { // Primeira verificação: evita sincronização desnecessária após a primeira criação
            synchronized (Singleton.class) { // Bloco sincronizado para garantir atomicidade na criação
                if (instance == null) { // Segunda verificação: garante que apenas uma thread crie a instância
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Olá do Singleton!");
    }
}
```

#### Entendendo a Palavra-Chave ``synchronized``

A palavra-chave synchronized em Java é uma ferramenta fundamental para controlar o acesso de múltiplas **threads** a um recurso compartilhado, prevenindo **condições de corrida** e garantindo a **integridade dos dados**.

Quando você marca um método ou um bloco de código como ``synchronized``:

1. **Apenas uma Thread por Vez:** No máximo uma thread pode executar aquele pedaço de código sincronizado por vez. Se uma thread está executando o código sincronizado, qualquer outra thread que tentar entrar nesse mesmo código terá que esperar (ficar bloqueada) até que a primeira thread saia.

2. **Garante Visibilidade:** Além de controlar o acesso, synchronized também garante que as mudanças feitas por uma thread dentro do bloco sincronizado sejam visíveis para outras threads quando elas entrarem no mesmo bloco. Isso resolve o problema de uma thread não conseguir ver as atualizações mais recentes feitas por outra thread.

---

#### Entendendo a Palavra-Chave `volatile`

Em Java, a palavra-chave `volatile` é usada para garantir a **visibilidade** e impedir a **reordenação de instruções** por parte do compilador e da CPU. Ela atua em duas frentes principais:

##### 1. Visibilidade

Quando uma variável é declarada como `volatile`, as mudanças feitas por uma thread nessa variável se tornam **imediatamente visíveis** para todas as outras threads. Sem `volatile`, uma thread pode estar trabalhando com uma cópia em cache (na memória local da CPU) da variável, e as mudanças feitas por outra thread na memória principal podem não ser propagadas para essa cópia em cache em tempo hábil.

* **Problema Sem `volatile`:** Uma thread A escreve um novo valor para `instance`. Uma thread B tenta ler `instance` e, em vez de ver o valor atualizado (que já aponta para a nova instância do Singleton), ela pode ler um valor antigo em seu cache local (ainda `null`).

##### 2. Prevenção de Reordenação de Instruções

Compiladores e CPUs podem reordenar instruções para otimizar o desempenho do código, desde que essa reordenação não altere o resultado lógico para uma **única thread**. No entanto, em um ambiente multithreaded, essa reordenação pode causar problemas sutis.

A criação de um objeto em Java, como `new Singleton()`, não é uma operação atômica única. Ela geralmente envolve três passos, que podem ser reordenados:

1.  **Alocar memória** para o novo objeto `Singleton`.
2.  **Inicializar o construtor** do objeto `Singleton` (preenchendo os campos).
3.  **Atribuir a referência** do objeto alocado/inicializado à variável `instance`.

* **Problema Sem `volatile`:** Imagine que a ordem seja reordenada para (1) -> (3) -> (2). Ou seja, a memória é alocada e a referência é atribuída a `instance` (tornando `instance` **não-nulo**), mas o **construtor ainda não foi totalmente executado** (o objeto não está completamente inicializado).
    * Se, nesse ponto, uma segunda thread chamar `getInstance()`, ela pode ver `instance` **não-nulo** (passando pela primeira verificação `if (instance == null)`) e tentar usar um objeto `Singleton` que ainda não foi totalmente inicializado. Isso levaria a um erro ou comportamento imprevisível.

* **Com `volatile`:** Declarar `instance` como `volatile` cria uma "barreira de memória" (memory barrier). Essa barreira garante que:
    1.  Todas as **escritas** para `instance` aconteçam antes de qualquer **leitura** de `instance` por outras threads.
    2.  Nenhuma instrução que venha depois da escrita de `instance = new Singleton();` pode ser movida para antes dessa escrita.
    3.  Nenhuma instrução que venha antes da leitura de `instance` pode ser movida para depois dessa leitura.

    Isso efetivamente **impede a reordenação** da etapa de atribuição (`3`) antes da inicialização completa (`2`), garantindo que quando uma thread vê `instance` como não-nulo, ela tem certeza de que o objeto já está **completamente construído e inicializado**.

---

##### Por que `volatile` é necessário no Double-Checked Locking?

Para que o Double-Checked Locking funcione corretamente, garantindo tanto a unicidade quanto a segurança em um ambiente multithreaded, o campo `instance` **deve ser `volatile`**.

* O `synchronized` protege apenas o bloco de criação, evitando que múltiplas threads entrem nele ao mesmo tempo.
* O `volatile` protege contra os problemas de **visibilidade de cache** e **reordenação de instruções**, que podem fazer com que uma thread veja uma referência não-nula para um objeto parcialmente construído.

**Em resumo:**

* **`synchronized`**: Garante que **apenas uma thread por vez** execute um bloco de código crítico (atomicidade e exclusão mútua).
* **`volatile`**: Garante que **todas as threads vejam as mudanças mais recentes** em uma variável e que as **operações de escrita/leitura não sejam reordenadas** de forma a comprometer a integridade (visibilidade e ordenação).

Embora o `volatile` resolva os problemas do Double-Checked Locking, a abordagem da **Inner Static Helper Class** ainda é frequentemente preferida por ser mais simples, mais idiomática em Java e sem a necessidade de gerenciar explicitamente `volatile` ou `synchronized` no método `getInstance()`, pois a JVM garante a segurança de thread na inicialização de classes internas estáticas.

* **synchronized (Singleton.class):** O ``Singleton.class`` é um objeto que representa a própria classe ``Singleton``. Quando você usa ``synchronized`` em um bloco, precisa de um objeto para atuar como o "cadeado". Usar ``Singleton.class`` significa que o cadeado é da classe em si.

* **Cadeado da Classe:** Isso garante que apenas uma thread por vez possa executar o código dentro do bloco ``{ ... }`` onde ``instance = new Singleton();`` acontece. Sem esse cadeado, duas threads poderiam, por exemplo, passar pelo primeiro ``if (instance == null)`` ao mesmo tempo e ambas tentarem criar uma nova instância, violando o princípio do Singleton.

**Por que o Double-Check?**

* A **primeira verificação** ``if (instance == null)`` (fora do ``synchronized``) é para otimização. Depois que a instância é criada, a maioria das chamadas ao ``getInstance()`` não precisará entrar no bloco sincronizado (que é mais lento), pois ``instance`` já não será ``null``.

* A **segunda verificação** ``if (instance == null)`` (dentro do ``synchronized``) é crucial. Imagine que duas threads A e B chegam ao primeiro ``if`` e ambas veem instance como ``null``. A thread A entra no bloco ``synchronized`` primeiro. Ela cria a instância. Quando a thread B finalmente entra no bloco ``synchronized`` (depois que a A saiu), ela precisa verificar novamente se ``instance`` ainda é ``null``. Se não verificasse, criaria uma segunda instância desnecessariamente.

### Outras abordagens para garantir thread-safety:

* **Inicialização Eager (Eager Initialization):** Cria a instância na declaração do campo estático. É simples e thread-safe por natureza, mas a instância é criada assim que a classe é carregada, mesmo que nunca seja usada.

```java
// private static Singleton instance = new Singleton();
// public static Singleton getInstance() { return instance; }
```

* **Sincronização no Método getInstance():** Sincroniza o método getInstance() inteiro. Garante thread-safety, mas pode ter um impacto no desempenho, pois cada chamada ao método requer a aquisição do lock, mesmo após a instância já ter sido criada.

```java
// public static synchronized Singleton getInstance() { ... }
```

* **Inner Static Helper Class (Initialization-on-demand holder idiom):** Considerado por muitos a melhor abordagem para lazy initialization e thread-safety em Java, pois não requer ``synchronized`` ou ``volatile`` explícito e é mais simples que o Double-Checked Locking. A instância é criada apenas quando o método getInstance() é chamado pela primeira vez, e a JVM garante a thread-safety.

```java
public class Singleton {
    // Construtor privado, como sempre
    private Singleton() {
        System.out.println("Singleton: Instância criada (Inner Static Helper Class).");
    }

    // Classe auxiliar estática interna
    private static class SingletonHolder {
        // A instância Singleton é criada aqui, na inicialização da SingletonHolder
        public static final Singleton INSTANCE = new Singleton();
    }

    // Método público para obter a instância
    public static Singleton getInstance() {
        // Retorna a instância da classe auxiliar
        return SingletonHolder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Olá do Singleton da Inner Static Helper Class!");
    }
}
```

#### O Segredo da Abordagem: Carregamento de Classe na JVM

A magia aqui reside na forma como a Java Virtual Machine (JVM) carrega classes e inicializa campos estáticos:

1.  **Inicialização Preguiçosa (Lazy Loading):**

      * A classe `SingletonHolder` (a classe auxiliar estática interna) **não é carregada nem inicializada quando a classe `Singleton` é carregada**.
      * Ela só é carregada e inicializada quando **`SingletonHolder.INSTANCE` é referenciado pela primeira vez**, o que acontece quando `Singleton.getInstance()` é chamado pela primeira vez.
      * Isso significa que a instância do `Singleton` só é criada quando realmente solicitada, tornando-a "lazy".

2.  **Segurança de Thread (Thread-Safety) por Garantia da JVM:**

      * A especificação da linguagem Java garante que a **inicialização de uma classe estática é intrinsecamente thread-safe**.
      * Ou seja, a JVM garante que a classe `SingletonHolder` será inicializada apenas uma vez por um único thread, mesmo que várias threads tentem acessar `SingletonHolder.INSTANCE` ao mesmo tempo. As outras threads esperarão pacificamente até que a inicialização esteja completa.
      * Uma vez que `SingletonHolder.INSTANCE` é inicializado (o que envolve a chamada ao construtor privado de `Singleton`), ele é um campo `final`, garantindo que não pode ser alterado e é visível para todas as threads.

#### Vantagens Desta Abordagem:

  * **Thread-Safe:** Garantida pela JVM sem precisar de `synchronized` explícito no `getInstance()`.
  * **Lazy Initialization:** A instância só é criada no primeiro acesso, economizando recursos se o Singleton não for usado.
  * **Performance:** Não há sobrecarga de sincronização (lock) em cada chamada ao `getInstance()` após a primeira, tornando-o mais performático que a sincronização de método e mais simples que o Double-Checked Locking.
  * **Simplicidade:** O código é mais limpo e fácil de entender do que o Double-Checked Locking, pois remove a complexidade do `volatile` e dos múltiplos `if`s.

Por essas razões, a "Inner Static Helper Class" é frequentemente considerada a abordagem mais elegante e robusta para implementar o padrão Singleton em Java, combinando lazy initialization com thread-safety de forma eficiente e segura.

## Fontes:

- [Refactoring](https://refactoring.guru/pt-br)
- [Java Singleton Design Pattern Best Practices with Examples](https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples)

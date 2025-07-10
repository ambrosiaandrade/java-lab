# 📚 Design Patterns em Java

Este documento explora os Design Patterns mais relevantes para o desenvolvimento Java, categorizando-os e detalhando suas aplicações práticas no mercado. O foco principal é no padrão **Singleton**, com suas diversas implementações e considerações sobre concorrência.

-----

## Classificação dos Design Patterns

Design Patterns são soluções comprovadas para problemas comuns de design de software. Eles são divididos em três categorias principais, baseadas no problema que resolvem:

  * **Padrões Criacionais:** Oferecem mecanismos flexíveis e reutilizáveis para a **criação de objetos**, aumentando a adaptabilidade do código.
  * **Padrões Estruturais:** Explicam como **montar objetos e classes em estruturas maiores**, mantendo-as flexíveis e eficientes.
  * **Padrões Comportamentais:** Gerenciam a **comunicação eficiente e a atribuição de responsabilidades** entre objetos.

-----

## Design Patterns Mais Usados no Mercado Java

Dominar os seguintes padrões é crucial para entender código existente e projetar soluções robustas e manuteníveis. Eles são amplamente reconhecidos e aplicados na indústria:

### 1\. **Singleton (Criacional)**

  * **Popularidade:** Simples, garante uma única instância global de uma classe. Útil para gerenciadores de configuração, loggers ou pools de conexão (embora frameworks como Spring frequentemente gerenciem isso via Injeção de Dependência).
  * **Onde é visto:** Presente em diversas aplicações, mas é vital compreender o padrão puro mesmo quando frameworks o abstraem.

### 2\. **Factory Method (Criacional)**

  * **Popularidade:** Desacopla a lógica de criação de objetos da lógica de negócio. Essencial ao criar diferentes tipos de objetos baseados em condições dinâmicas, tornando o código mais flexível e extensível.
  * **Onde é visto:** Fábricas de conexões de banco de dados, parsers, leitores de arquivos e em qualquer contexto onde a instância a ser criada varia dinamicamente.

### 3\. **Strategy (Comportamental)**

  * **Popularidade:** Permite definir uma família de algoritmos, encapsulá-los em classes separadas e torná-los intercambiáveis em tempo de execução. Ideal para lógicas de negócio com múltiplas variações.
  * **Onde é visto:** Processadores de pagamento, validadores de entrada, algoritmos de ordenação, diferentes estratégias de cálculo de frete ou regras de negócio. Frequentemente implementado no Spring usando injeção de dependência.

### 4\. **Observer (Comportamental)**

  * **Popularidade:** Fundamental em sistemas orientados a eventos. Permite que um objeto (sujeito) notifique múltiplos objetos (observadores) automaticamente sobre mudanças de estado, sem conhecimento direto dos observadores.
  * **Onde é visto:** Interfaces gráficas (eventos de clique), sistemas de mensagens, sistemas de log e qualquer comunicação baseada em eventos.

### 5\. **Decorator (Estrutural)**

  * **Popularidade:** Oferece uma forma flexível de estender a funcionalidade de um objeto dinamicamente, sem modificar sua classe original ou criar muitas subclasses. Adiciona novos comportamentos "envolvendo" o objeto existente.
  * **Onde é visto:** Streams de I/O em Java (`BufferedInputStream`, `GzipInputStream`), componentes de UI, ou para adicionar funcionalidades como logging, cache ou segurança a métodos de serviço.

### 6\. **Facade (Estrutural)**

  * **Popularidade:** Simplifica o acesso a um subsistema complexo, fornecendo uma interface unificada e de alto nível. Reduz o acoplamento e facilita o uso de funcionalidades intrincadas.
  * **Onde é visto:** APIs que expõem funcionalidades complexas de backend, ou módulos de serviço em grandes aplicações que orquestram várias outras classes.

-----

## Implementação Detalhada: Singleton

O padrão **Singleton** garante que uma classe tenha apenas uma única instância e fornece um ponto de acesso global a ela. Sua implementação básica requer:

1.  Um **campo estático privado** para armazenar a instância única.
2.  Um **construtor privado** para impedir a criação de novas instâncias externas.
3.  Um **método estático público (`getInstance()`)** que retorna a instância única, criando-a apenas na primeira chamada.

### Implementação Básica (Não Thread-Safe)

Este é o conceito mais simples, mas **não é seguro em ambientes com múltiplas threads**, pois pode criar mais de uma instância.

```java
public class Singleton {

    // 1. Campo privado e estático para armazenar a instância única
    private static Singleton instance;

    // 2. Construtor privado para impedir instanciação externa
    private Singleton() {
        System.out.println("Singleton: Instância criada.");
    }

    // 3. Método estático e público para obter a instância (NÃO THREAD-SAFE)
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Olá do Singleton!");
    }
}
```

### Implementação Thread-Safe: Double-Checked Locking

Para garantir que o Singleton funcione corretamente em ambientes multithreaded (onde várias threads podem tentar criar a instância simultaneamente), o **Double-Checked Locking** é uma abordagem comum que combina **sincronização** e a palavra-chave **`volatile`**.

```java
public class Singleton {

    // O campo 'instance' DEVE ser declarado como 'volatile' para garantir
    // visibilidade e evitar reordenação de instruções.
    private static volatile Singleton instance;

    private Singleton() {
        System.out.println("Singleton: Instância criada.");
    }

    public static Singleton getInstance() {
        // Primeira verificação: evita sincronização desnecessária após a primeira criação
        if (instance == null) {
            // Bloco sincronizado: garante atomicidade e exclusão mútua na criação
            synchronized (Singleton.class) { // Usa o objeto Class como cadeado
                // Segunda verificação: garante que apenas uma thread crie a instância
                if (instance == null) {
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

-----

#### Entendendo a Palavra-Chave `synchronized`

`synchronized` é uma ferramenta fundamental em Java para controlar o acesso de **múltiplas threads** a um recurso compartilhado, prevenindo **condições de corrida** e garantindo a **integridade dos dados**.

Quando você marca um método ou um bloco de código como `synchronized`:

1.  **Apenas uma Thread por Vez:** Garante que, no máximo, uma thread possa executar o código sincronizado por vez. Outras threads que tentarem entrar no mesmo código esperarão (ficarão bloqueadas) até que a primeira thread saia.
2.  **Garante Visibilidade:** Assegura que todas as mudanças feitas por uma thread dentro do bloco sincronizado sejam imediatamente **visíveis** para outras threads quando elas entrarem no mesmo bloco, resolvendo problemas de cache de memória.

No `Double-Checked Locking`, `synchronized (Singleton.class)` usa o objeto `Class` da classe `Singleton` como cadeado. Como há apenas um objeto `Class` por classe na JVM, isso garante que a criação da instância (`instance = new Singleton();`) ocorra de forma atômica e exclusiva, por apenas uma thread em todo o sistema.

-----

#### Entendendo a Palavra-Chave `volatile`

`volatile` é usada para garantir a **visibilidade** e impedir a **reordenação de instruções** por parte do compilador e da CPU em ambientes multithreaded.

1.  **Visibilidade:** Quando uma variável é `volatile`, as mudanças feitas por uma thread nela se tornam **imediatamente visíveis** para todas as outras threads. Sem `volatile`, uma thread pode operar com uma cópia desatualizada da variável em seu cache local.

2.  **Prevenção de Reordenação de Instruções:** A criação de um objeto (`new Singleton()`) em Java envolve múltiplas etapas (alocação de memória, inicialização do construtor, atribuição da referência). Compiladores e CPUs podem reordenar essas etapas para otimização.

      * **Problema sem `volatile`:** Uma reordenação (ex: alocação -\> atribuição da referência -\> inicialização do construtor) pode fazer com que uma thread veja a variável `instance` como **não-nula** (porque a referência foi atribuída), mas o objeto `Singleton` ainda não estar **completamente inicializado**. Usar esse objeto parcialmente inicializado causaria erros.
      * **Com `volatile`:** `volatile` cria uma "barreira de memória" que impede que a atribuição da referência seja reordenada antes da inicialização completa do objeto. Isso garante que, se uma thread vê `instance` como não-nulo, o objeto já está **completamente construído e seguro para uso**.

Portanto, em `Double-Checked Locking`, `synchronized` garante que apenas uma thread crie a instância, e `volatile` garante que essa instância seja vista corretamente e completamente inicializada por todas as threads.

-----

### Outras Abordagens Thread-Safe para Singleton

Embora o `Double-Checked Locking` seja eficaz, outras abordagens podem ser mais simples ou performáticas, dependendo do cenário:

1.  **Inicialização Eager (Eager Initialization):**

      * A instância é criada na declaração do campo estático. Simples e intrinsecamente thread-safe.
      * **Desvantagem:** A instância é criada assim que a classe é carregada pela JVM, mesmo que nunca seja usada (não é *lazy*).

    <!-- end list -->

    ```java
    public class SingletonEager {
        private static final SingletonEager instance = new SingletonEager(); // Instância criada no carregamento da classe

        private SingletonEager() {
            System.out.println("Singleton: Instância Eager criada.");
        }

        public static SingletonEager getInstance() {
            return instance;
        }

        // Métodos de exemplo (getInfo, setInfo)
        private String info = "Info Eager";
        public String getInfo() { return info; }
        public void setInfo(String info) { this.info = info; }
    }
    ```

2.  **Sincronização no Método `getInstance()`:**

      * Sincroniza o método `getInstance()` inteiro. Simples e thread-safe.
      * **Desvantagem:** Impacto no desempenho, pois cada chamada ao método exige a aquisição de um *lock*, mesmo após a instância já ter sido criada.

    <!-- end list -->

    ```java
    public class SingletonLazySyncMethod {
        private static SingletonLazySyncMethod instance;

        private SingletonLazySyncMethod() {
            System.out.println("Singleton: Instância Lazy (método sincronizado) criada.");
        }

        public static synchronized SingletonLazySyncMethod getInstance() { // Método inteiro sincronizado
            if (instance == null) {
                instance = new SingletonLazySyncMethod();
            }
            return instance;
        }

        // Métodos de exemplo (getInfo, setInfo)
        private String info = "Info Lazy Sync Method";
        public String getInfo() { return info; }
        public void setInfo(String info) { this.info = info; }
    }
    ```

### Implementação recomendada (Thread-Safe)

3.  **Inner Static Helper Class (Initialization-on-demand holder idiom):**

      * **Considerada por muitos a melhor abordagem** para inicialização preguiçosa (lazy initialization) e thread-safety em Java.
      * Não requer `synchronized` ou `volatile` explícito no `getInstance()` e é mais simples que o `Double-Checked Locking`.
      * A instância é criada apenas quando o método `getInstance()` é chamado pela primeira vez, e a JVM garante a thread-safety da inicialização da classe interna estática.

    <!-- end list -->

    ```java
    public class SingletonInnerClass {
        private SingletonInnerClass() {
            System.out.println("Singleton: Instância Inner Static Helper Class criada.");
        }

        // Classe auxiliar estática interna: só é carregada na primeira chamada a getInstance()
        private static class SingletonHolder {
            public static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
        }

        public static SingletonInnerClass getInstance() {
            return SingletonHolder.INSTANCE; // Acessa a instância através da classe auxiliar
        }

        // Métodos de exemplo (getInfo, setInfo)
        private String info = "Info Inner Class";
        public String getInfo() { return info; }
        public void setInfo(String info) { this.info = info; }
    }
    ```

    #### O Segredo da Abordagem: Carregamento de Classe na JVM

    A eficácia da abordagem `Inner Static Helper Class` reside na forma como a Java Virtual Machine (JVM) carrega classes e inicializa campos estáticos:

      * **Inicialização Preguiçosa (`Lazy Loading`):** A classe `SingletonHolder` (a classe auxiliar estática interna) não é carregada nem inicializada quando a classe `Singleton` é carregada. Ela só é carregada e inicializada quando `SingletonHolder.INSTANCE` é referenciado pela primeira vez (o que ocorre na primeira chamada a `Singleton.getInstance()`). Isso garante que a instância do `Singleton` só seja criada quando realmente necessária.
      * **Segurança de Thread por Garantia da JVM:** A especificação da linguagem Java garante que a **inicialização de uma classe estática é intrinsecamente thread-safe**. A JVM assegura que a classe `SingletonHolder` será inicializada apenas uma vez, por um único thread, mesmo que várias threads tentem acessá-la simultaneamente.

    **Vantagens Desta Abordagem:**

      * **Thread-Safe:** Garante segurança sem `synchronized` explícito no `getInstance()`.
      * **Lazy Initialization:** Instância criada sob demanda.
      * **Performance:** Não há sobrecarga de sincronização em chamadas subsequentes a `getInstance()`.
      * **Simplicidade:** Código mais limpo e fácil de entender comparado ao `Double-Checked Locking`.

-----

## Fontes:

  * [Refactoring Guru](https://refactoring.guru/pt-br)
  * [Java Singleton Design Pattern Best Practices with Examples (DigitalOcean)](https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples)


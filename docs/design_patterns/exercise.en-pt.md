# 🧪 Exercises of Design Pattern

## Singleton

<details>
  <summary>Português</summary>

### Exercício 1: Singleton Básico (Não Thread-Safe)

**Cenário:** Você precisa de uma classe que gerencie as configurações globais de uma aplicação. Por simplicidade, inicialmente não se preocupe com ambientes multi-thread.

**Objetivo:** Implementar a versão mais simples do padrão Singleton em Java.

#### Tarefas:

1.  **Crie a classe `ConfiguracoesApp`:**

    - Declare um **campo estático privado** do tipo `ConfiguracoesApp` para armazenar a única instância.
    - Defina um **construtor privado** para impedir que a classe seja instanciada diretamente de fora.
    - Crie um **método estático público** chamado `getInstance()` que:
      - Verifica se a instância já existe.
      - Se não existir, cria a instância.
      - Retorna a instância existente ou recém-criada.
    - Adicione um método público simples, como `obterVersao()`, que retorna uma `String` (ex: "Versão 1.0").

2.  **No `main` (código cliente):**
    - Obtenha duas referências à instância do `ConfiguracoesApp` usando `getInstance()`.
    - Imprima a versão usando ambas as referências.
    - Verifique se as duas referências apontam para o mesmo objeto (usando `==`).

---

### Exercício 2: Singleton Thread-Safe com Double-Checked Locking

**Cenário:** Seu aplicativo de configurações globais agora será executado em um ambiente multi-thread, e você precisa garantir que apenas uma instância seja criada, mesmo sob alta concorrência, e que a inicialização seja "preguiçosa" (lazy).

**Objetivo:** Implementar o padrão Singleton usando a técnica de Double-Checked Locking para garantir segurança de threads e inicialização preguiçosa.

#### Tarefas:

1.  **Modifique a classe `ConfiguracoesApp` (ou crie uma nova `ConfiguracoesAppThreadSafe`):**

    - Mantenha o **construtor privado**.
    - Declare o campo da instância como `private static volatile ConfiguracoesAppThreadSafe instance;`.
    - Implemente o método `getInstance()` utilizando o padrão **Double-Checked Locking**:
      - Primeira verificação `if (instance == null)`.
      - Bloco `synchronized` no objeto `ConfiguracoesAppThreadSafe.class`.
      - Segunda verificação `if (instance == null)` dentro do bloco sincronizado.
      - Crie a instância dentro da segunda verificação.
    - Adicione um método público, como `carregarPropriedade(String chave)`, que imprime uma mensagem (ex: "Carregando propriedade [chave]").

2.  **No `main` (código cliente):**
    - Crie um conjunto de threads (ex: 5 a 10 threads).
    - Em cada thread, chame `ConfiguracoesAppThreadSafe.getInstance()` e então chame `carregarPropriedade("tema")`.
    - Verifique se a mensagem de "instância criada" aparece apenas uma vez no console, confirmando a criação única.

---

### Exercício 3: Singleton com Inner Static Helper Class

**Cenário:** Você busca a forma mais idiomática e robusta de implementar um Singleton thread-safe e preguiçoso em Java, evitando as complexidades do `volatile` e `synchronized` explícitos no método `getInstance()`.

**Objetivo:** Implementar o padrão Singleton utilizando a abordagem da Inner Static Helper Class (Initialization-on-demand holder idiom).

#### Tarefas:

1.  **Crie a classe `GerenciadorDeRecursos`:**

    - Mantenha o **construtor privado**.
    - Crie uma **classe estática interna privada** (ex: `RecursoHolder`).
    - Dentro de `RecursoHolder`, declare e inicialize o campo `public static final GerenciadorDeRecursos INSTANCE = new GerenciadorDeRecursos();`.
    - Implemente o método `public static GerenciadorDeRecursos getInstance()` que simplesmente retorna `RecursoHolder.INSTANCE`.
    - Adicione um método público, como `alocarRecurso(String nomeRecurso)`, que imprime uma mensagem (ex: "Alocando recurso: [nomeRecurso]").

2.  **No `main` (código cliente):**
    - Simule o acesso concorrente ao `GerenciadorDeRecursos` de várias threads, assim como no Exercício 2.
    - Em cada thread, chame `GerenciadorDeRecursos.getInstance()` e então chame `alocarRecurso("conexao_db")`.
    - Observe o console para confirmar que a instância é criada apenas uma vez e que a inicialização ocorre de forma preguiçosa e thread-safe.

</details>

</br>

<details>
    <summary>English</summary>

### Exercise 1: Basic Singleton (Non-Thread-Safe)

**Scenario:** You need a class to manage global application settings. For simplicity, initially don't worry about multi-threaded environments.

**Objective:** Implement the simplest version of the Singleton pattern in Java.

#### Tasks:

1.  **Create the `AppConfig` class:**

    - Declare a **private static field** of type `AppConfig` to store the single instance.
    - Define a **private constructor** to prevent external direct instantiation of the class.
    - Create a **public static method** named `getInstance()` that:
      - Checks if the instance already exists.
      - If it doesn't exist, creates the instance.
      - Returns the existing or newly created instance.
    - Add a simple public method, like `getVersion()`, that returns a `String` (e.g., "Version 1.0").

2.  **In `main` (client code):**
    - Get two references to the `AppConfig` instance using `getInstance()`.
    - Print the version using both references.
    - Verify that both references point to the same object (using `==`).

---

### Exercise 2: Thread-Safe Singleton with Double-Checked Locking

**Scenario:** Your global settings application will now run in a multi-threaded environment, and you need to ensure that only one instance is created, even under high concurrency, and that initialization is lazy.

**Objective:** Implement the Singleton pattern using the Double-Checked Locking technique to ensure thread safety and lazy initialization.

#### Tasks:

1.  **Modify the `AppConfig` class (or create a new `AppConfigThreadSafe`):**

    - Keep the **private constructor**.
    - Declare the instance field as `private static volatile AppConfigThreadSafe instance;`.
    - Implement the `getInstance()` method using the **Double-Checked Locking** pattern:
      - First `if (instance == null)` check.
      - `synchronized` block on the `AppConfigThreadSafe.class` object.
      - Second `if (instance == null)` check inside the synchronized block.
      - Create the instance within the second check.
    - Add a public method, like `loadProperty(String key)`, that prints a message (e.g., "Loading property [key]").

2.  **In `main` (client code):**
    - Create a set of threads (e.g., 5 to 10 threads).
    - In each thread, call `AppConfigThreadSafe.getInstance()` and then call `loadProperty("theme")`.
    - Verify that the "instance created" message appears only once in the console, confirming unique creation.

---

### Exercise 3: Singleton with Inner Static Helper Class

**Scenario:** You're looking for the most idiomatic and robust way to implement a thread-safe and lazy Singleton in Java, avoiding the complexities of explicit `volatile` and `synchronized` in the `getInstance()` method.

**Objective:** Implement the Singleton pattern using the Inner Static Helper Class approach (Initialization-on-demand holder idiom).

#### Tasks:

1.  **Create the `ResourceManager` class:**

    - Keep the **private constructor**.
    - Create a **private static inner class** (e.g., `ResourceHolder`).
    - Inside `ResourceHolder`, declare and initialize the field `public static final ResourceManager INSTANCE = new ResourceManager();`.
    - Implement the method `public static ResourceManager getInstance()` that simply returns `ResourceHolder.INSTANCE`.
    - Add a public method, like `allocateResource(String resourceName)`, that prints a message (e.g., "Allocating resource: [resourceName]").

2.  **In `main` (client code):**
    - Simulate concurrent access to the `ResourceManager` from multiple threads, similar to Exercise 2.
    - In each thread, call `ResourceManager.getInstance()` and then call `allocateResource("db_connection")`.
    - Observe the console to confirm that the instance is created only once and that initialization occurs lazily and in a thread-safe manner.

</details>

## Factory method

  <details>
    <summary>Português</summary>

### Exercício 1: Construtor de Mensagens de Notificação

**Cenário:** Você está desenvolvendo um sistema de notificação que precisa enviar mensagens através de diferentes canais, como SMS, E-mail e Push Notification. O tipo de canal pode ser escolhido em tempo de execução.

**Objetivo:** Implementar o Factory Method para criar e enviar mensagens através de diferentes tipos de notificadores.

#### Tarefas:

1.  **Defina a interface `Notificador` (Product):**

    - Deve ter um método `enviar(String mensagem)`.

2.  **Crie as classes `SMSNotificador`, `EmailNotificador` e `PushNotificador` (ConcreteProduct):**

    - Cada classe deve implementar a interface `Notificador`.
    - A implementação do método `enviar()` deve apenas imprimir uma mensagem indicando o tipo de notificador e a mensagem enviada (ex: "Enviando SMS: [mensagem]").

3.  **Defina a classe abstrata `NotificadorFactory` (Creator):**

    - Deve ter um método abstrato `criarNotificador()` que retorna um `Notificador`.
    - Pode ter um método `notificarUsuario(String mensagem)` que utiliza `criarNotificador()` e então chama `enviar(mensagem)` no notificador criado.

4.  **Crie as classes `SMSFactory`, `EmailFactory` e `PushFactory` (ConcreteCreator):**

    - Cada classe deve estender `NotificadorFactory`.
    - Cada uma deve sobrescrever o método `criarNotificador()` para retornar a implementação correta de `Notificador`.

5.  **No `main` (código cliente):**
    - Crie instâncias de `SMSFactory`, `EmailFactory` e `PushFactory`.
    - Chame o método `notificarUsuario()` em cada fábrica com uma mensagem de exemplo, demonstrando a criação e o envio de mensagens de forma polimórfica.

---

### Exercício 2: Criador de Personagens para Jogo

**Cenário:** Em um jogo, você precisa criar diferentes tipos de personagens (Guerreiro, Mago, Arqueiro) que compartilham comportamentos básicos (atacar, defender), mas possuem implementações distintas para suas habilidades. O jogo precisa criar esses personagens sem ter que saber os detalhes de sua construção.

**Objetivo:** Utilizar o Factory Method para abstrair a criação de diferentes tipos de personagens.

#### Tarefas:

1.  **Defina a interface `Personagem` (Product):**

    - Deve ter métodos como `atacar()`, `defender()`, e `exibirHabilidades()`.

2.  **Crie as classes `Guerreiro`, `Mago` e `Arqueiro` (ConcreteProduct):**

    - Implemente a interface `Personagem` para cada tipo de personagem.
    - Os métodos devem imprimir mensagens descrevendo a ação ou as habilidades específicas do personagem (ex: "Guerreiro ataca com espada!", "Mago conjura feitiço de proteção!").

3.  **Defina a classe abstrata `FabricaDePersonagens` (Creator):**

    - Deve ter um método abstrato `criarPersonagem()` que retorna um `Personagem`.
    - Pode ter um método `iniciarAventura()` que usa `criarPersonagem()` e então chama `atacar()`, `defender()` e `exibirHabilidades()` no personagem criado.

4.  **Crie as classes `FabricaDeGuerreiros`, `FabricaDeMagos` e `FabricaDeArqueiros` (ConcreteCreator):**

    - Cada uma deve estender `FabricaDePersonagens`.
    - Sobrescreva `criarPersonagem()` para retornar a instância apropriada (`Guerreiro`, `Mago`, `Arqueiro`).

5.  **No `main` (código cliente):**
    - Crie diferentes fábricas de personagens.
    - Use cada fábrica para "iniciar uma aventura" ou criar um personagem diretamente, mostrando como o cliente interage com os personagens de forma genérica, sem conhecer suas classes concretas.

---

### Exercício 3 (Desafio): Leitor de Arquivos Configurable

**Cenário:** Seu aplicativo precisa ler dados de diferentes tipos de arquivos (CSV, JSON, XML). A estrutura de leitura pode variar, mas o processo de "processar" os dados é comum. Você quer adicionar novos formatos de arquivo no futuro sem alterar o código principal.

**Objetivo:** Aplicar o Factory Method para criar leitores de arquivos específicos com base no tipo de arquivo.

#### Tarefas:

1.  **Defina a interface `FileReader` (Product):**

    - Deve ter um método `read()` que retorna uma `String` (simulando o conteúdo lido).
    - Deve ter um método `processData(String data)` (simulando o processamento).

2.  **Crie `CsvFileReader`, `JsonFileReader`, `XmlFileReader` (ConcreteProduct):**

    - Implemente a interface `FileReader`.
    - Para `read()`, retorne uma string simples (ex: "Dados CSV simulados").
    - Para `processData()`, imprima uma mensagem indicando qual leitor está processando os dados e os dados recebidos.

3.  **Defina a classe abstrata `FileReaderFactory` (Creator):**

    - Deve ter um método abstrato `createReader()` que retorna um `FileReader`.
    - Deve ter um método `handleFile()` que usa `createReader()`, chama `read()` e `processData()` com o resultado da leitura.

4.  **Crie `CsvFileReaderFactory`, `JsonFileReaderFactory`, `XmlFileReaderFactory` (ConcreteCreator):**

    - Sobrescreva `createReader()` para retornar o leitor de arquivo específico.

5.  **No `main` (código cliente):**
    - Demonstre como usar as diferentes fábricas para lidar com diferentes tipos de arquivos, mantendo a lógica de manuseio de arquivos separada da lógica de criação.
    - **Bônus:** Adicione um método estático na classe `FileReaderFactory` que, dado um tipo de arquivo (ex: "CSV", "JSON"), retorne a fábrica apropriada. Isso pode simular uma lógica de decisão mais complexa para selecionar a fábrica.

</details>

</br>

<details>
    <summary>English</summary>

### Exercise 1: Notification Message Builder

**Scenario:** You're developing a notification system that needs to send messages through different channels, such as SMS, Email, and Push Notification. The specific channel type can be chosen at runtime.

**Objective:** Implement the Factory Method to create and send messages through different types of notifiers.

#### Tasks:

1.  **Define the `Notifier` interface (Product):**

    - It should have a method `send(String message)`.

2.  **Create the `SMSNotifier`, `EmailNotifier`, and `PushNotifier` classes (ConcreteProduct):**

    - Each class must implement the `Notifier` interface.
    - The `send()` method implementation should simply print a message indicating the notifier type and the message sent (e.g., "Sending SMS: [message]").

3.  **Define the abstract `NotifierFactory` class (Creator):**

    - It must have an abstract method `createNotifier()` that returns a `Notifier`.
    - It can have a method `notifyUser(String message)` that uses `createNotifier()` and then calls `send(message)` on the created notifier.

4.  **Create the `SMSFactory`, `EmailFactory`, and `PushFactory` classes (ConcreteCreator):**

    - Each class must extend `NotifierFactory`.
    - Each one must override the `createNotifier()` method to return the correct `Notifier` implementation.

5.  **In `main` (client code):**
    - Create instances of `SMSFactory`, `EmailFactory`, and `PushFactory`.
    - Call the `notifyUser()` method on each factory with an example message, demonstrating the polymorphic creation and sending of messages.

---

### Exercise 2: Game Character Creator

**Scenario:** In a game, you need to create different types of characters (Warrior, Mage, Archer) that share basic behaviors (attack, defend) but have distinct implementations for their abilities. The game needs to create these characters without knowing the details of their construction.

**Objective:** Use the Factory Method to abstract the creation of different types of characters.

#### Tasks:

1.  **Define the `Character` interface (Product):**

    - It should have methods like `attack()`, `defend()`, and `displayAbilities()`.

2.  **Create the `Warrior`, `Mage`, and `Archer` classes (ConcreteProduct):**

    - Implement the `Character` interface for each character type.
    - The methods should print messages describing the specific action or abilities of the character (e.g., "Warrior attacks with sword!", "Mage casts protection spell!").

3.  **Define the abstract `CharacterFactory` class (Creator):**

    - It must have an abstract method `createCharacter()` that returns a `Character`.
    - It can have a method `startAdventure()` that uses `createCharacter()` and then calls `attack()`, `defend()`, and `displayAbilities()` on the created character.

4.  **Create the `WarriorFactory`, `MageFactory`, and `ArcherFactory` classes (ConcreteCreator):**

    - Each one must extend `CharacterFactory`.
    - Override `createCharacter()` to return the appropriate instance (`Warrior`, `Mage`, `Archer`).

5.  **In `main` (client code):**
    - Create different character factories.
    - Use each factory to "start an adventure" or directly create a character, showing how the client interacts with characters generically, without knowing their concrete classes.

---

### Exercise 3 (Challenge): Configurable File Reader

**Scenario:** Your application needs to read data from different file types (CSV, JSON, XML). The reading structure may vary, but the process of "processing" the data is common. You want to add new file formats in the future without changing the main code.

**Objective:** Apply the Factory Method to create specific file readers based on the file type.

#### Tasks:

1.  **Define the `FileReader` interface (Product):**

    - It should have a `read()` method that returns a `String` (simulating the read content).
    - It should have a `processData(String data)` method (simulating the processing).

2.  **Create `CsvFileReader`, `JsonFileReader`, `XmlFileReader` (ConcreteProduct):**

    - Implement the `FileReader` interface.
    - For `read()`, return a simple string (e.g., "Simulated CSV data").
    - For `processData()`, print a message indicating which reader is processing the data and the received data.

3.  **Define the abstract `FileReaderFactory` class (Creator):**

    - It must have an abstract method `createReader()` that returns a `FileReader`.
    - It must have a `handleFile()` method that uses `createReader()`, calls `read()`, and then `processData()` with the result of the read operation.

4.  **Create `CsvFileReaderFactory`, `JsonFileReaderFactory`, `XmlFileReaderFactory` (ConcreteCreator):**

    - Override `createReader()` to return the specific file reader.

5.  **In `main` (client code):**
    - Demonstrate how to use the different factories to handle various file types, keeping the file handling logic separate from the creation logic.
    - **Bonus:** Add a static method to the `FileReaderFactory` class that, given a file type (e.g., "CSV", "JSON"), returns the appropriate factory. This can simulate more complex decision logic for selecting the factory.

</details>

<!-- details style -->

<style>
details {
  border: 1px solid none;
  border-radius: 4px;
  padding: 0.5em 0.5em 0;
}

summary {
  font-weight: bold;
  margin: -0.5em -0.5em 0;
  padding: 0.5em;
  color:rgb(36, 52, 52);
  background-color: #97EFE9;
}

details[open] {
  padding: 0.5em;
}

details[open] summary {
  border-bottom: 1px solid #aaa;
  margin-bottom: 0.5em;
  background-color: black;
  color: white;
}
</style>

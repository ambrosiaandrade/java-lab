# Java Collections Framework

![Java](https://img.shields.io/badge/Java-21-blue) ![Collections API](https://img.shields.io/badge/Collections--API-Java%20SE-yellow)

O **Java Collections Framework (JCF)** é um conjunto unificado de interfaces, implementações e algoritmos para representar e manipular coleções de dados. Ele padroniza a forma como grupos de objetos são gerenciados, oferecendo estruturas de dados robustas e eficientes para diversas necessidades de armazenamento, acesso e manipulação. Ao usar o JCF, você se beneficia de um código mais limpo, reutilizável e com performance otimizada, evitando a necessidade de implementar estruturas complexas do zero.

---

## 📚 Sumário

- [Conceitos Chave](#conceitos-chave)
- [Principais Interfaces](#principais-interfaces)
- [Implementações Comuns](#implementações-comuns)
- [Generics e Tipagem Segura](#generics-e-tipagem-segura)
- [Integração com Stream API](#integracao-com-stream-api)
- [Considerações de Performance](#consideracoes-de-performance)
- [Thread Safety](#thread-safety)
- [Operações Básicas](#operações-básicas)
- [Coleções Imutáveis](#coleções-imutáveis)
- [Exemplos de Uso](#exemplos-de-uso)
- [Exercícios Práticos](#exercícios-práticos)
- [Quando Usar Collections?](#quando-usar-collections)
- [Testes](#testes)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Chave

- **Collection:** Interface raiz do framework, representando um grupo de objetos. Serve como base para interfaces como `List`, `Set` e `Queue`.
- **Map:** Uma estrutura de dados que associa **chaves únicas a valores**. É importante notar que `Map` **não estende** a interface `Collection`, mas é parte integral do Framework Collections.
- **List:** Uma coleção **ordenada** (a ordem de inserção é mantida) que **permite elementos duplicados**. Permite acesso a elementos por índice.
- **Set:** Uma coleção que **não permite elementos duplicados**. Sua principal função é garantir a unicidade dos elementos. A ordem dos elementos geralmente não é garantida.
- **Queue:** Uma coleção projetada para manter elementos antes do processamento. Geralmente segue o princípio **FIFO** (First-In, First-Out - Primeiro a Entrar, Primeiro a Sair).
- **Iterator:** Objeto que permite percorrer uma coleção e remover elementos com segurança durante a iteração.

---

## Principais Interfaces

| Interface        | Descrição                                                                                                                                              |
| :--------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------- |
| `Collection<E>`  | Interface base para todas as coleções do framework (List, Set e Queue).                                                                                |
| `List<E>`        | Lista ordenada que mantém a ordem de inserção e permite duplicatas.                                                                                    |
| `Set<E>`         | Conjunto de elementos únicos, sem ordem garantida (exceto `LinkedHashSet` e `TreeSet`).                                                                |
| `Queue<E>`       | Fila de elementos, geralmente seguindo a ordem FIFO.                                                                                                   |
| `Deque<E>`       | Fila de duas pontas (Double-ended Queue), permitindo inserção e remoção em ambas as extremidades (útil para pilhas e filas).                           |
| `Map<K,V>`       | Mapeamento de chaves para valores, onde cada chave é única. Não herda de `Collection`.                                                                 |
| `Map.Entry<K,V>` | Interface aninhada em `Map` que representa um par chave-valor individual.                                                                              |
| `Comparable<T>`  | Interface para definir a **ordem natural** de objetos (ex: ordem alfabética para Strings).                                                             |
| `Comparator<T>`  | Interface para definir **ordens personalizadas** de objetos, geralmente passada a construtores de coleções ordenadas (ex: `TreeSet`, `PriorityQueue`). |

---

## Implementações Comuns

| Implementação        | Descrição                                                                                                                                                                                          |
| :------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `ArrayList<E>`       | Implementação de `List` baseada em um **array dinâmico**. Rápida para acesso por índice e iteração. Eficiente para adições no final.                                                               |
| `LinkedList<E>`      | Implementação de `List` e `Deque` baseada em uma **lista duplamente ligada**. Eficiente para inserção e remoção no início/fim e no meio.                                                           |
| `Vector<E>`          | Similar ao `ArrayList`, mas **sincronizado** (thread-safe) por padrão, o que o torna mais lento em ambientes de thread única. Legado.                                                              |
| `Stack<E>`           | Uma subclasse de `Vector` que implementa uma estrutura de dados **LIFO** (Last-In, First-Out - Pilha). Legado.                                                                                     |
| `HashSet<E>`         | Implementação de `Set` baseada em uma **tabela hash**. Oferece operações de adição, remoção e busca com performance média constante (O(1)). Não garante ordem.                                     |
| `LinkedHashSet<E>`   | Implementação de `Set` baseada em hash, mas que **mantém a ordem de inserção** dos elementos.                                                                                                      |
| `TreeSet<E>`         | Implementação de `Set` baseada em uma **árvore binária de busca balanceada**. Armazena elementos em ordem **natural** ou definida por um `Comparator`.                                             |
| `HashMap<K,V>`       | Implementação de `Map` baseada em uma **tabela hash**. Oferece operações de adição, remoção e busca com performance média constante (O(1)). Não garante ordem.                                     |
| `LinkedHashMap<K,V>` | Implementação de `Map` baseada em hash, mas que **mantém a ordem de inserção** das chaves (ou ordem de acesso).                                                                                    |
| `TreeMap<K,V>`       | Implementação de `Map` baseada em uma **árvore binária de busca balanceada**. Armazena chaves em ordem **natural** ou definida por um `Comparator`.                                                |
| `PriorityQueue<E>`   | Implementação de `Queue` que organiza os elementos com base em sua **prioridade** (definida por ordem natural ou `Comparator`). O elemento de maior prioridade é sempre o primeiro a ser removido. |
| `ArrayDeque<E>`      | Implementação de `Deque` e `Queue` baseada em array redimensionável. Mais eficiente que `LinkedList` para a maioria das operações de `Deque` quando não há remoções intermediárias frequentes.     |

---

## O que é uma Tabela Hash?

Imagine uma **tabela de duas colunas**: em uma coluna você tem as **chaves** e na outra, os **valores** associados a essas chaves. O grande truque de uma tabela hash é que ela usa uma função especial, chamada **função de hash**, para descobrir rapidamente onde um valor deve ser armazenado ou onde ele pode ser encontrado.

1.  **Função de Hash:** Quando você adiciona um item (uma chave e seu valor), a função de hash pega a **chave** e calcula um **número** (o "código hash" ou "índice"). Esse número indica a posição (ou "bucket") na tabela onde o valor será guardado.
2.  **Armazenamento e Recuperação Rápida:** Como a função de hash te dá um "endereço" quase instantâneo, a tabela consegue ir diretamente para aquela posição para guardar ou buscar o item. É como ter um índice super eficiente para cada entrada.

### Analogia:

Pense em um **arquivo de fichas gigante** com milhares de gavetas.

- Uma estrutura de dados normal (como uma lista ligada) seria como folhear todas as fichas uma por uma até achar a que você quer.
- Uma estrutura **baseada em tabela hash** seria como ter um pequeno dispositivo (a função de hash) que, ao digitar o nome na ficha (a chave), te diz exatamente o número da gaveta onde ela está. Você vai direto na gaveta, pega a ficha, e pronto!

### Quando usar?

Coleções como `HashSet`, `HashMap` e `LinkedHashSet`/`LinkedHashMap` são baseadas em tabelas hash. Elas são ideais quando a velocidade de **adição**, **remoção** e **verificação de existência** de elementos (usando a chave) é crucial, com uma performance média de tempo constante (O(1)).

A "mágica" acontece porque, na maioria das vezes, a função de hash leva você diretamente ao local certo, sem precisar "percorrer" a estrutura inteira.

---

## Generics e Tipagem Segura

O Java Collections Framework faz uso extensivo de **Generics** (`<E>`, `<K,V>`) para fornecer **tipagem segura** em tempo de compilação. Isso significa que você especifica o tipo de objetos que uma coleção pode conter, prevenindo `ClassCastException`s em tempo de execução e tornando seu código mais robusto e legível.

```java
// Exemplo: List<String> garante que a lista conterá apenas Strings
List<String> nomes = new ArrayList<>();
nomes.add("Alice"); // OK
// nomes.add(123); // Erro de compilação: int não é String
```

---

## Integração com Stream API

A partir do Java 8, a **Stream API** oferece uma poderosa maneira funcional de processar coleções. Você pode converter qualquer `Collection` em um `Stream` para realizar operações como filtragem, mapeamento, redução e ordenação de forma declarativa e concisa. Streams são **lazily evaluated** (processam sob demanda) e **não modificam a coleção original**.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

List<String> frutas = Arrays.asList("Maçã", "Banana", "Morango", "Abacaxi", "Manga");

// Filtrar frutas que começam com 'M' e coletar em uma nova lista
List<String> frutasComM = frutas.stream()
                               .filter(s -> s.startsWith("M"))
                               .collect(Collectors.toList());

System.out.println(frutasComM); // Saída: [Maçã, Morango, Manga]

// Contar elementos únicos e ordenados
long countUnique = frutas.stream()
                         .distinct() // Remove duplicatas (se houver)
                         .sorted()   // Ordena os elementos
                         .count();
System.out.println("Total de frutas únicas e ordenadas: " + countUnique);
```

---

## Considerações de Performance

A escolha da implementação correta é crucial para a performance da sua aplicação.

- **`ArrayList` vs. `LinkedList`:**
  - `ArrayList` é ideal para **acesso frequente por índice** (`get(index)`) e **adição/remoção no final**. Inserções/remoções no meio são caras (O(n)).
  - `LinkedList` é ideal para **inserções e remoções frequentes no início ou no meio** (O(1) após encontrar o nó, mas encontrar o nó pode ser O(n)). Acesso por índice é lento (O(n)).
- **`HashSet` vs. `TreeSet`:**
  - `HashSet` oferece performance média **O(1)** para `add`, `remove` e `contains` (assumindo uma boa função hash), mas não garante ordem.
  - `TreeSet` oferece performance **O(log n)** para as mesmas operações, mas mantém os elementos em ordem classificada.
- **`HashMap` vs. `TreeMap`:**
  - `HashMap` oferece performance média **O(1)** para `put`, `get` e `remove`, mas não garante ordem das chaves.
  - `TreeMap` oferece performance **O(log n)** para as mesmas operações e mantém as chaves em ordem classificada.

---

## Thread Safety

Por padrão, a maioria das implementações do Java Collections Framework (`ArrayList`, `HashMap`, `HashSet`, etc.) **NÃO são thread-safe**. Isso significa que se múltiplas threads acessarem e modificarem a mesma coleção simultaneamente, pode ocorrer inconsistência de dados ou comportamentos imprevisíveis.

Para cenários multi-thread, você tem algumas opções:

- **Wrappers Sincronizados:** Usar métodos como `Collections.synchronizedList()`, `Collections.synchronizedSet()`, `Collections.synchronizedMap()` para obter versões sincronizadas das coleções existentes.
- **Concurrencia Collections:** Usar classes do pacote `java.util.concurrent` (ex: `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`) que são projetadas para ambientes multi-threaded e geralmente oferecem melhor performance e escalabilidade do que os wrappers sincronizados.

---

## Operações Básicas

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class BasicOperations {
    public static void main(String[] args) {
        // Exemplo de List (ArrayList)
        System.out.println("--- List (ArrayList) ---");
        List<String> nomes = new ArrayList<>();
        nomes.add("Java");        // Adiciona elemento
        nomes.add("Collections");
        nomes.add("Java");        // Permite duplicatas
        System.out.println("Lista: " + nomes);
        System.out.println("Contém 'Java'? " + nomes.contains("Java")); // Verifica existência
        nomes.remove("Collections"); // Remove a primeira ocorrência
        System.out.println("Lista após remover 'Collections': " + nomes);
        System.out.println("Elemento no índice 0: " + nomes.get(0)); // Acesso por índice

        // Iteração com Enhanced For Loop
        System.out.println("\nIterando sobre a lista:");
        for(String s : nomes) {
            System.out.println("- " + s);
        }

        // Exemplo de Set (HashSet)
        System.out.println("\n--- Set (HashSet) ---");
        Set<Integer> numeros = new HashSet<>();
        numeros.add(1);
        numeros.add(2);
        numeros.add(1); // Não adiciona duplicata
        System.out.println("Conjunto: " + numeros); // Ordem não garantida
        System.out.println("Contém '2'? " + numeros.contains(2));
        numeros.remove(1);
        System.out.println("Conjunto após remover '1': " + numeros);

        // Exemplo de Map (HashMap)
        System.out.println("\n--- Map (HashMap) ---");
        Map<String, Integer> idades = new HashMap<>();
        idades.put("Alice", 30);  // Adiciona par chave-valor
        idades.put("Bob", 25);
        idades.put("Alice", 31);  // Atualiza o valor para a chave 'Alice'
        System.out.println("Mapa: " + idades);
        System.out.println("Idade de Bob: " + idades.get("Bob")); // Acessa valor pela chave
        idades.remove("Bob");     // Remove par chave-valor
        System.out.println("Mapa após remover Bob: " + idades);

        // Iteração sobre Map com forEach (Java 8+)
        System.out.println("\nIterando sobre o mapa:");
        idades.forEach((nome, idade) -> System.out.println(nome + " tem " + idade + " anos."));
    }
}
```

---

## Coleções Imutáveis

A partir do Java 9, é possível criar coleções imutáveis de forma concisa e eficiente usando os métodos estáticos `of()` das interfaces. Isso é útil para garantir que uma coleção não será modificada após sua criação, o que melhora a segurança e a previsibilidade do código.

```java
import java.util.List;
import java.util.Set;
import java.util.Map;

public class ImmutableCollections {
    public static void main(String[] args) {
        List<String> listaImutavel = List.of("A", "B", "C");
        System.out.println("Lista Imutável: " + listaImutavel);
        // listaImutavel.add("D"); // Isso lançaria UnsupportedOperationException

        Set<Integer> conjuntoImutavel = Set.of(1, 2, 3);
        System.out.println("Conjunto Imutável: " + conjuntoImutavel);

        Map<String, String> mapaImutavel = Map.of("chave1", "valor1", "chave2", "valor2");
        System.out.println("Mapa Imutável: " + mapaImutavel);
    }
}
```

---

## Exemplos de Uso

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UsageExamples {
    public static void main(String[] args) {
        // Exemplo 1: Ordenar uma lista de strings (ordem natural)
        System.out.println("--- Ordenando uma lista ---");
        List<String> nomes = new ArrayList<>(Arrays.asList("Maria", "João", "Ana", "Carlos"));
        Collections.sort(nomes); // Usa a ordem natural (alfabética para String)
        System.out.println("Nomes ordenados: " + nomes); // Saída: [Ana, Carlos, João, Maria]

        // Exemplo 2: Usando PriorityQueue com Comparator personalizado
        System.out.println("\n--- PriorityQueue com Comparator ---");
        // Prioriza strings pelo menor comprimento
        PriorityQueue<String> filaPorComprimento = new PriorityQueue<>(
            Comparator.comparingInt(String::length)
        );
        filaPorComprimento.add("Maçã");     // 4
        filaPorComprimento.add("Banana");   // 6
        filaPorComprimento.add("Uva");      // 3
        filaPorComprimento.add("Pêssego");  // 7

        System.out.println("Elementos da PriorityQueue (removendo um a um por prioridade):");
        while (!filaPorComprimento.isEmpty()) {
            System.out.println(filaPorComprimento.poll());
        }
        // Saída esperada: Uva, Maçã, Banana, Pêssego
    }
}
```

---

## Exercícios Práticos

| Arquivo                                                                                | Descrição                                                                                             |
| :------------------------------------------------------------------------------------- | :---------------------------------------------------------------------------------------------------- |
| [`LabCollectionsToDo.java`](https://www.google.com/search?q=./LabCollectionsToDo.java) | Classe para implementar os exercícios propostos sobre o uso e manipulação das Collections.            |
| [`LabCollectionsDone.java`](https://www.google.com/search?q=./LabCollectionsDone.java) | Classe contendo as soluções completas e comentadas para os exercícios, servindo como referência.      |
| [`exercise.en-pt.md`](https://www.google.com/search?q=./exercise.en-pt.md)             | Lista bilíngue (inglês-português) dos exercícios, com instruções e objetivos claros para cada tarefa. |

---

## Quando Usar Collections?

- Para **armazenar e gerenciar grupos de objetos** de forma organizada e eficiente.
- Quando a necessidade de **estrutura de dados** (linear, única, chave-valor, prioridade) for um fator importante.
- Para realizar **operações comuns** como adição, remoção, busca, ordenação, filtragem e transformação de dados.
- Para **simplificar o desenvolvimento** e aumentar a legibilidade do código, utilizando implementações testadas e otimizadas.
- Ao lidar com **dados dinâmicos** cujo tamanho pode variar durante a execução do programa.

---

## Extra

É mais comum usar mais **`ArrayList`** e **`HashMap`** no dia a dia do desenvolvimento. Isso acontece porque eles cobrem uma vasta gama de cenários, oferecendo um bom equilíbrio entre performance e simplicidade para acesso rápido por índice ou por chave, respectivamente.

No entanto, as outras implementações do Collections Framework não estão lá por acaso! Elas brilham em situações específicas onde suas características únicas, como **garantia de ordem**, **prioridade** ou **performance otimizada para certas operações**, se tornam cruciais.

Vamos explorar exemplos e cenários para cada uma delas, mostrando quando elas são a escolha ideal.

---

### Quando a Ordem Importa (ou é Especial)

#### 1. `LinkedList` (quando a ordem importa e você altera muito o começo/meio)

Você já viu que `LinkedList` é eficiente para inserções e remoções no início ou no meio. Pense em sistemas onde elementos são constantemente adicionados e removidos das pontas:

- **Fila de espera de um sistema:** Imagine um sistema de pedidos online. Novos pedidos chegam no final da fila (`addLast`), e os pedidos mais antigos são processados e removidos do início (`removeFirst`). Uma `LinkedList` (usada como `Queue` ou `Deque`) é perfeita para isso, pois essas operações são muito eficientes.
- **Histórico de navegação/ações desfazíveis (Undo/Redo):** Se você tem uma lista de ações em um editor de texto, e as ações mais recentes são adicionadas e as mais antigas são removidas para limitar o histórico, uma `LinkedList` pode ser mais performática que um `ArrayList` para gerenciar essas adições e remoções no "topo" ou "fundo" do histórico.

---

#### 2. `LinkedHashSet` (elementos únicos com ordem de inserção)

Normalmente, um `HashSet` não se importa com a ordem. Mas e se você precisa de elementos **únicos** e também quer que eles sejam recuperados na **ordem em que foram adicionados**?

- **Cache de itens recentemente vistos:** Em um e-commerce, você pode querer mostrar os "últimos produtos que você viu", mas sem duplicar produtos caso o usuário os veja várias vezes. Um `LinkedHashSet` seria ideal: ele garante que cada produto apareça apenas uma vez e na ordem em que foi visualizado pela primeira vez.
- **Manter a ordem de opções selecionadas:** Se um usuário seleciona várias opções de uma lista, e você precisa processá-las em uma ordem específica (a ordem de seleção), mas sem que ele possa selecionar a mesma opção duas vezes.

---

#### 3. `TreeSet` (elementos únicos e sempre ordenados)

O `TreeSet` garante que os elementos estejam **sempre em ordem** (alfabética, numérica ou personalizada), além de serem únicos.

- **Lista de contatos alfabética:** Se você precisa de uma lista de nomes única, mas que seja sempre apresentada em ordem alfabética. Adicionar ou remover um nome automaticamente o insere no lugar certo.
- **Ranking de scores de jogos:** Manter um ranking dos maiores scores, onde cada score (se for um objeto que implementa `Comparable` ou usa um `Comparator`) é único e o conjunto precisa estar sempre ordenado.
- **Remover duplicatas e ordenar ao mesmo tempo:** Se você tem uma `List` com muitos itens desordenados e duplicados e precisa de uma lista final com itens únicos e ordenados, converter para um `TreeSet` e depois para uma `List` é uma forma eficiente de fazer isso em uma única operação.

---

#### 4. `TreeMap` (Mapas onde as chaves precisam estar ordenadas)

Assim como o `TreeSet`, o `TreeMap` mantém as **chaves em ordem**. Isso é crucial quando você precisa iterar sobre o mapa em uma sequência previsível de chaves ou encontrar elementos dentro de um **intervalo de chaves**.

- **Dicionário ou índice remissivo:** Um dicionário (chave=palavra, valor=definição) pode ser armazenado em um `TreeMap` para que as palavras estejam sempre em ordem alfabética, facilitando a navegação ou a busca por ranges de palavras (ex: "todas as palavras entre 'casa' e 'carro'").
- **Dados temporais:** Se as chaves são datas/horas e você precisa acessar ou iterar os dados cronologicamente. Por exemplo, registros de transações bancárias por data, onde você quer ver todas as transações de um determinado mês.
- **Ranges de valores:** Se você precisa encontrar a primeira chave maior que X, ou a última chave menor que Y. O `TreeMap` oferece métodos específicos para isso (`lowerKey()`, `higherKey()`, `subMap()`), que não estão disponíveis em `HashMap`.

---

#### 5. `PriorityQueue` (Ordem de processamento é por prioridade, não por inserção)

Esta é a "fila de emergência" que discutimos. Os elementos saem na ordem da **maior prioridade**, não na ordem em que foram adicionados.

- **Agendador de tarefas (Task Scheduler):** Um sistema operacional pode usar uma `PriorityQueue` para agendar processos. Tarefas com prioridade "alta" são executadas antes de tarefas com prioridade "baixa", independentemente de quando foram enviadas.
- **Simulações de eventos discretos:** Em simulações (por exemplo, de uma fila de banco ou de eventos em uma rede), os eventos são processados não na ordem que foram criados, mas na ordem do tempo em que devem ocorrer. Uma `PriorityQueue` armazena os eventos e sempre retorna o próximo evento a ser processado (o de menor tempo).
- **Algoritmos de caminho mais curto (Dijkstra):** Algoritmos de grafos que precisam sempre visitar o nó mais "promissor" (o com menor custo acumulado até o momento) usam uma fila de prioridade para armazenar os nós a serem explorados.

---

## Testes

Os testes para este laboratório estão localizados em:
`../../tests/CollectionsTest.java`
Eles são essenciais para verificar a correção das implementações e garantir o entendimento dos conceitos.

---

## Recursos Adicionais

- [Documentação Oficial Oracle - Collections Framework](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Collection.html) - A fonte definitiva para informações detalhadas.
- [Guia Baeldung: Java Collections Framework](https://www.baeldung.com/java-collections) - Um guia abrangente com muitos exemplos práticos.
- [Tutorial Oracle: Collections](https://docs.oracle.com/javase/tutorial/collections/overview/index.html) - Um bom ponto de partida para iniciantes.
- [Vídeo: Java Collections Framework Explained](https://www.google.com/search?q=https://www.youtube.com/watch%3Fv%3DJjV2Q6moYhM) (Exemplo de recurso externo, adapte se tiver um específico)

<!-- end list -->

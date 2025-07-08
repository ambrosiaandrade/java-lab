# Java Collections Framework

![Java](https://img.shields.io/badge/Java-21-blue) ![Collections API](https://img.shields.io/badge/Collections--API-Java%20SE-yellow)

O Framework Collections do Java fornece estruturas de dados e algoritmos para armazenar, acessar e manipular grupos de objetos de forma eficiente e flexível. Ele inclui interfaces, implementações e utilitários para trabalhar com listas, conjuntos, filas, mapas, entre outros.

---

## 📚 Sumário

* [Conceitos Chave](#conceitos-chave)
* [Principais Interfaces](#principais-interfaces)
* [Implementações Comuns](#implementações-comuns)
* [Operações Básicas](#operações-básicas)
* [Coleções Imutáveis](#coleções-imutáveis)
* [Exemplos de Uso](#exemplos-de-uso)
* [Exercícios Práticos](#exercícios-práticos)
* [Quando Usar Collections?](#quando-usar-collections)
* [Testes](#testes)
* [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Chave

* **Collection:** Interface raiz para grupos de objetos, inclui listas, conjuntos e filas.
* **Map:** Estrutura que mapeia chaves para valores, não estende Collection.
* **List:** Coleção ordenada que permite elementos duplicados.
* **Set:** Coleção que não permite elementos duplicados.
* **Queue:** Coleção usada para manter elementos para processamento em ordem.

---

## Principais Interfaces

| Interface       | Descrição                                                    |
| --------------- | ------------------------------------------------------------ |
| `Collection<E>` | Interface base para List, Set e Queue                        |
| `List<E>`       | Lista ordenada, permite duplicatas                           |
| `Set<E>`        | Conjunto sem duplicatas                                      |
| `Queue<E>`      | Fila, geralmente FIFO                                        |
| `Deque<E>`      | Fila dupla, permite inserção e remoção em ambas extremidades |
| `Map<K,V>`      | Mapeamento chave-valor, não é Collection                     |

---

## Implementações Comuns

| Implementação      | Descrição                                  |
| ------------------ | ------------------------------------------ |
| `ArrayList<E>`     | Lista baseada em array, rápida leitura     |
| `LinkedList<E>`    | Lista ligada, eficiente inserção/removação |
| `HashSet<E>`       | Conjunto baseado em hash, rápida busca     |
| `TreeSet<E>`       | Conjunto ordenado, baseado em árvore       |
| `HashMap<K,V>`     | Mapa baseado em hash, rápida busca         |
| `TreeMap<K,V>`     | Mapa ordenado por chave                    |
| `PriorityQueue<E>` | Fila de prioridade                         |

---

## Operações Básicas

```java
List<String> lista = new ArrayList<>();
lista.add("Java");
lista.add("Collections");

Set<Integer> conjunto = new HashSet<>();
conjunto.add(1);
conjunto.add(2);

Map<String, Integer> mapa = new HashMap<>();
mapa.put("um", 1);
mapa.put("dois", 2);

for(String s : lista) {
    System.out.println(s);
}
```

---

## Coleções Imutáveis

A partir do Java 9, é possível criar coleções imutáveis facilmente:

```java
List<String> listaImutavel = List.of("A", "B", "C");
Set<Integer> conjuntoImutavel = Set.of(1, 2, 3);
Map<String, String> mapaImutavel = Map.of("chave1", "valor1", "chave2", "valor2");
```

---

## Exemplos de Uso

```java
// Ordenar uma lista de strings
List<String> nomes = new ArrayList<>(Arrays.asList("Maria", "João", "Ana"));
Collections.sort(nomes);
System.out.println(nomes);
```

---

## Exercícios Práticos

| Arquivo                                                | Descrição                                               |
| ------------------------------------------------------ | ------------------------------------------------------- |
| [`LabCollectionsToDo.java`](./LabCollectionsToDo.java) | Classe para implementar os exercícios sobre Collections |
| [`LabCollectionsDone.java`](./LabCollectionsDone.java) | Classe com soluções completas                           |
| [`exercise.en-pt.md`](./exercise.en-pt.md)             | Lista bilíngue dos exercícios                           |

---

## Quando Usar Collections?

* Para armazenar e manipular grupos de objetos de forma eficiente.
* Quando precisar de diferentes estruturas de dados (lista, conjunto, mapa).
* Para operações complexas de busca, ordenação e filtragem.

---

## Testes

Os testes para este laboratório estão localizados em:
`../../tests/CollectionsTest.java`

---

## Recursos Adicionais

* [Documentação Oficial Oracle - Collections Framework](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Collection.html)
* [Guia Baeldung: Java Collections Framework](https://www.baeldung.com/java-collections)
* [Tutorial Oracle: Collections](https://docs.oracle.com/javase/tutorial/collections/overview/index.html)

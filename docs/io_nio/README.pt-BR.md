# Java IO e NIO

![Java](https://img.shields.io/badge/Java-21-blue) ![IO-NIO](https://img.shields.io/badge/IO--NIO-Java%208%2B-yellowgreen)

Java oferece duas principais APIs para manipulação de arquivos e entrada/saída: a tradicional `java.io` e a moderna `java.nio`. Enquanto `java.io` trabalha com fluxos de dados de forma simples, `java.nio` introduz buffers, canais, e operações mais eficientes e flexíveis.

---

## 📚 Sumário

- [Diferenças entre IO e NIO](#diferenças-entre-io-e-nio)
- [Manipulação de Arquivos e Diretórios](#manipulação-de-arquivos-e-diretórios)
- [Canais e Buffers](#canais-e-buffers)
- [Percorrendo Diretórios](#percorrendo-diretórios)
- [Exemplos de Uso](#exemplos-de-uso)
- [Exercícios Práticos](#exercícios-práticos)
- [Recursos Adicionais](#recursos-adicionais)

---

## Diferenças entre IO e NIO

| Característica            | `java.io`                      | `java.nio`                           |
| ------------------------- | ------------------------------ | ------------------------------------ |
| Estilo                    | Fluxo de dados (stream-based)  | Buffer e canal (buffer-based)        |
| Performance               | Bloqueante                     | Possui opções não-bloqueantes        |
| API                       | Tradicional                    | Moderna, baseada em `Path`, `Files`  |
| Leitura de Arquivos       | `FileReader`, `BufferedReader` | `Files`, `FileChannel`, `ByteBuffer` |
| Manipulação de Diretórios | `File`                         | `Files`, `Path`, `FileVisitor`       |

---

## Manipulação de Arquivos e Diretórios

- Criar, ler, escrever e copiar arquivos com `Files` ou `FileWriter`/`BufferedReader`.
- Criar diretórios com `Files.createDirectory()` ou `mkdir()`.
- Listar arquivos usando `File.listFiles()` ou `Files.walk()`.
- Verificar existência, permissões e tamanho com `Files.exists()`, `Files.isReadable()`, etc.

---

## Canais e Buffers

- `FileChannel` permite ler e escrever arquivos com buffers.
- `ByteBuffer` armazena dados em memória para operações eficientes.
- Uso típico envolve `buffer.clear()`, `buffer.flip()` e operações de leitura/escrita.

---

## Percorrendo Diretórios

- `Files.walkFileTree()` permite visitar arquivos e pastas recursivamente.
- `SimpleFileVisitor` pode ser extendido para definir ações customizadas.

---

## Exemplos de Uso

```java
// Ler linhas com Files.lines()
try (Stream<String> lines = Files.lines(Paths.get("arquivo.txt"))) {
    lines.filter(line -> line.contains("Java"))
         .forEach(System.out::println);
}

// Copiar arquivo com FileChannel e ByteBuffer
try (FileChannel source = FileChannel.open(Paths.get("origem.txt"), StandardOpenOption.READ);
     FileChannel dest = FileChannel.open(Paths.get("destino.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    while (source.read(buffer) > 0) {
        buffer.flip();
        dest.write(buffer);
        buffer.clear();
    }
}
```

---

## Recursos Adicionais

- [Documentação Oficial Java IO](https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html)
- [Documentação Oficial Java NIO](https://docs.oracle.com/javase/8/docs/api/java/nio/file/package-summary.html)
- [Tutorial Oracle sobre NIO](https://docs.oracle.com/javase/tutorial/essential/io/file.html)
- [Guia Baeldung: Java NIO Basics](https://www.baeldung.com/java-nio)

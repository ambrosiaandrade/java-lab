# 📄 Cheatsheet — Java IO & NIO

---

## 📁 `java.io` vs `java.nio`

| Característica   | `java.io`                      | `java.nio`                              |
| ---------------- | ------------------------------ | --------------------------------------- |
| Estilo           | Orientado a fluxo              | Orientado a buffer                      |
| Bloqueante       | Sim                            | Não-bloqueante (em `java.nio.channels`) |
| Suporte a canais | ❌                              | ✅                                       |
| Performance      | Menor em operações intensivas  | Maior controle e melhor performance     |
| Leitura em bloco | Linha por linha ou byte a byte | Buffer de leitura/gravação em blocos    |

---

## 🔹 Leitura e Escrita com `java.io`

### 📝 Ler arquivo linha a linha

```java
try (BufferedReader reader = new BufferedReader(new FileReader("arquivo.txt"))) {
    String linha;
    while ((linha = reader.readLine()) != null) {
        System.out.println(linha);
    }
}
```

### 📝 Escrever arquivo

```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter("saida.txt"))) {
    writer.write("Olá, mundo!");
}
```

---

## 🔸 Leitura e Escrita com `java.nio.file`

```java
Path path = Paths.get("dados.txt");
```

### 📖 Ler todas as linhas

```java
List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
```

### 🖊️ Escrever em arquivo

```java
Files.write(path, List.of("Linha 1", "Linha 2"), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
```

### 📦 Copiar, mover e deletar

```java
Files.copy(Paths.get("origem.txt"), Paths.get("copia.txt"));
Files.move(Paths.get("arquivo.txt"), Paths.get("novo.txt"));
Files.deleteIfExists(Paths.get("arquivoAntigo.txt"));
```

---

## 🔸 Leitura com `Files.lines()` (Stream)

```java
try (Stream<String> stream = Files.lines(path)) {
    stream.filter(l -> l.contains("Java"))
          .map(String::toUpperCase)
          .forEach(System.out::println);
}
```

---

## 📂 Criar diretórios

```java
Files.createDirectory(Paths.get("novaPasta"));
Files.createDirectories(Paths.get("a/b/c/d")); // cria múltiplos
```

---

## 🔸 Caminhos com `Path` e `Paths`

```java
Path caminho = Paths.get("docs/arquivo.txt");

System.out.println(caminho.getFileName());   // arquivo.txt
System.out.println(caminho.getParent());     // docs
System.out.println(caminho.toAbsolutePath()); // caminho completo
System.out.println(Files.exists(caminho));   // true / false
```

---

## 📦 `java.nio.channels` + `ByteBuffer`

### 🔄 Copiar arquivo com canais

```java
try (FileChannel in = FileChannel.open(Paths.get("entrada.txt"), StandardOpenOption.READ);
     FileChannel out = FileChannel.open(Paths.get("saida.txt"),
       StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

    ByteBuffer buffer = ByteBuffer.allocate(1024);

    while (in.read(buffer) > 0) {
        buffer.flip();        // preparar para leitura
        out.write(buffer);    // gravar
        buffer.clear();       // preparar para próxima leitura
    }
}
```

---

## 📦 FileVisitor para percorrer diretórios

```java
Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<>() {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        System.out.println("Arquivo: " + file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        System.out.println("Diretório: " + dir);
        return FileVisitResult.CONTINUE;
    }
});
```

---

## 🔍 Buscar arquivos com `Files.walk()`

```java
try (Stream<Path> paths = Files.walk(Paths.get("src"))) {
    paths.filter(Files::isRegularFile)
         .filter(p -> p.toString().endsWith(".java"))
         .forEach(System.out::println);
}
```

---

## 📘 Extras

* `Files.isDirectory(path)`
* `Files.size(path)`
* `Files.isReadable(path)`
* `Files.newBufferedReader(path)`
* `Files.newBufferedWriter(path)`

---

## ✅ Dicas de Uso

| Quando usar                   | Use...                                          |
| ----------------------------- | ----------------------------------------------- |
| Manipular arquivos simples    | `Files.readAllLines`, `BufferedReader`          |
| Leitura rápida em blocos      | `FileChannel` + `ByteBuffer`                    |
| Múltiplos arquivos/diretórios | `Files.walkFileTree` / `Files.walk()`           |
| Escrita performática          | `BufferedWriter` ou `Files.newBufferedWriter()` |
| Operações de Stream           | `Files.lines()`                                 |
-----

**o `java.nio` (New IO)** é **mais recomendado** que o `java.io`, principalmente por ser **mais moderno, performático e flexível**.

### ✅ **Recomenda-se usar `java.nio` quando:**

| Situação                                            | Por quê?                                                                                |
| --------------------------------------------------- | --------------------------------------------------------------------------------------- |
| **Leitura/escrita de arquivos grandes**             | `NIO` usa buffers e canais, que permitem operações mais rápidas e eficientes.           |
| **Operações em lote de arquivos ou diretórios**     | Métodos como `Files.walk()` e `Files.walkFileTree()` facilitam a recursão e filtragem.  |
| **Necessidade de trabalhar com `Stream`**           | `Files.lines()` permite processar linhas como stream, ideal para programação funcional. |
| **Deseja mais controle sobre o caminho do arquivo** | `Path`, `Files` e `StandardOpenOption` fornecem uma API clara e robusta.                |
| **Aplicações modernas (Java 8+)**                   | Integra bem com `Streams`, `Lambda`, `try-with-resources`, etc.                         |
| **Precisa evitar bloqueio de I/O**                  | `java.nio.channels` permite I/O não bloqueante, útil em servidores, sockets etc.        |

---

### ⚠️ **Quando ainda pode ser aceitável usar `java.io`:**

| Situação                                     | Por quê?                                                                                |
| -------------------------------------------- | --------------------------------------------------------------------------------------- |
| **Tarefas simples e pequenas**               | `BufferedReader`, `FileWriter`, etc., são simples de entender e rápidos de implementar. |
| **Projetos legados**                         | Muitos projetos antigos usam `java.io` — manter a consistência pode evitar conflitos.   |
| **IO tradicional por linha (stdin, stdout)** | Exemplo: leitura do console, saída formatada.                                           |

---

## 🚀 Comparativo direto

| Operação             | `java.io`                   | `java.nio.file` (`Files`)         |
| -------------------- | --------------------------- | --------------------------------- |
| Ler linha a linha    | `BufferedReader.readLine()` | `Files.lines(Path)`               |
| Escrever em arquivo  | `BufferedWriter.write()`    | `Files.write(Path, List<String>)` |
| Criar arquivo        | `new File(...)`             | `Files.createFile(Path)`          |
| Copiar arquivo       | Manual com streams          | `Files.copy(src, dest)`           |
| Listar diretórios    | `File.listFiles()`          | `Files.walk()` / `Files.list()`   |
| Verificar existência | `file.exists()`             | `Files.exists(Path)`              |

---

## ✅ Resumo prático

| Critério                  | Recomendado usar... |
| ------------------------- | ------------------- |
| Aplicações modernas       | `java.nio.file`     |
| Melhor performance        | `java.nio.channels` |
| Código funcional / stream | `java.nio.file`     |
| Código legado / simples   | `java.io`           |

---

### 💡 Recomendação geral:

> **Prefira `java.nio.file` e `Path` para novos projetos.**
> `java.io` ainda funciona bem para tarefas pequenas, mas `NIO` é a escolha moderna, extensível e mais poderosa.

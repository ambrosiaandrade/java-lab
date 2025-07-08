# 🧩 Documentação do Pacote `config`

Este pacote contém classes utilitárias que fornecem suporte à **internacionalização (i18n)** e à **formatação estilizada de mensagens no console**. Ele é especialmente útil em aplicações Java com múltiplos módulos e suporte a diferentes idiomas.

---

## 📘 Classe: `ConfigLanguage.java`

### 📌 Função

Carrega o idioma da aplicação com base no arquivo `.env` e define a `Locale` que será utilizada na leitura de mensagens internacionalizadas.

### ⚙️ Comportamento

- Procura o arquivo `.env` na raiz do projeto.
- Lê a propriedade `app.profile`:

  - `"pt"` → define `pt-BR` (padrão).
  - `"en"` → define `en-US`.

- Em caso de erro, utiliza `pt-BR` e exibe um aviso no console.

### 🧪 Exemplo de `.env`:

```
app.profile=en
```

---

## 📘 Classe: `MessageProvider.java`

### 📌 Função

Responsável por buscar mensagens traduzidas, com base na `Locale` atual e no nome do **módulo ativo**.

### 🧠 Lógica

- Define o nome do módulo com `setModuleName("nome")`.
- Constrói o caminho base:

  ```
  <modulo>.i18n.messages
  ```

- Usa `ResourceBundle` para carregar o arquivo `.properties` correspondente à `Locale` atual:

  - Exemplo: `streams.i18n.messages_pt_BR.properties`

> ✅ A pasta `resources` deve estar configurada corretamente como **Resource Root** no projeto.

### 🧪 Exemplo de uso:

```java
MessageProvider.setModuleName("streams");
String texto = MessageProvider.get("exercise1");
```

---

## 📘 Classe: `CustomPrint.java`

### 📌 Função

Imprime mensagens no console com **formatação estilizada e colorida**, utilizando as traduções fornecidas por `MessageProvider`.

### 🖨️ Métodos disponíveis

#### `greeting()`

Imprime uma linha centralizada com o nome do módulo, estilizada com cores ANSI.

#### `of(String message)`

Exibe a mensagem no formato:

```
[MODULO] chave: valor
```

#### `colored(String message)`

Exibe a mensagem com cores, no formato:

```
MODULO [chave]: valor
```

### 🎨 Exemplo de saída no console:

```
================== [STREAMS] ==================
[STREAMS] exercise1: Descrição do exercício
STREAMS [exercise2]: Segunda descrição formatada
```

---

## 📂 Estrutura Esperada do Projeto

```
java-lab/
├── .env
├── build.gradle
├── src/
│   └── main/
│       └── java/
│           └── config/
│               ├── ConfigLanguage.java
│               ├── CustomPrint.java
│               └── MessageProvider.java
└── src/
    └── main/
        └── resources/
            └── streams/
                └── i18n/
                    ├── messages_pt_BR.properties
                    └── messages_en_US.properties
```

---

## ✅ Requisitos

- O arquivo `.env` deve estar presente na raiz do projeto.
- Os arquivos `.properties` devem estar em `src/main/resources/`.
- O projeto deve reconhecer corretamente o `resources/` como **source folder** para recursos.

# Java Date and Time API (`java.time`)

![Java](https://img.shields.io/badge/Java-21-blue) ![java.time](https://img.shields.io/badge/java.time-Java%208%2B-yellow)

A API `java.time`, introduzida no Java 8, fornece uma abordagem moderna, imutável e clara para lidar com datas, horas, fusos horários, períodos e durações. Ela substitui as classes antigas (`Date`, `Calendar`, etc.) com uma estrutura robusta e segura para manipulação temporal.

---

## 📚 Sumário

- [Conceitos Chave](#conceitos-chave)
- [Criação de Objetos](#criação-de-objetos)
- [Manipulação de Datas e Horas](#manipulação-de-datas-e-horas)
- [Fusos Horários](#fusos-horários)
- [Formatar e Analisar Datas](#formatar-e-analisar-datas)
- [Duração e Período](#duração-e-período)
- [Trabalhando com Instant](#trabalhando-com-instant)
- [Exercícios Práticos](#exercícios-práticos)
- [Recursos Adicionais](#recursos-adicionais)

---

## Conceitos Chave

| Classe              | Descrição                                             |
| ------------------- | ----------------------------------------------------- |
| `LocalDate`         | Representa uma data (ano, mês, dia), sem hora         |
| `LocalTime`         | Representa uma hora (hora, minuto, segundo), sem data |
| `LocalDateTime`     | Combina data e hora, sem fuso horário                 |
| `ZonedDateTime`     | Data e hora com fuso horário                          |
| `Instant`           | Momento exato no tempo em UTC                         |
| `Period`            | Intervalo entre duas datas                            |
| `Duration`          | Intervalo entre dois instantes (com tempo)            |
| `DateTimeFormatter` | Formatação e análise de datas e horas                 |

---

## Criação de Objetos

```java
LocalDate data = LocalDate.of(2025, 7, 8);
LocalTime hora = LocalTime.of(14, 30);
LocalDateTime dataHora = LocalDateTime.of(data, hora);

ZonedDateTime zoned = ZonedDateTime.of(dataHora, ZoneId.of("America/Sao_Paulo"));
Instant instante = Instant.now();
```

---

## Manipulação de Datas e Horas

```java
// Adicionar/Subtrair
LocalDate amanha = data.plusDays(1);
LocalTime depois = hora.plusHours(2);
LocalDateTime futuro = dataHora.plusMonths(3).minusDays(5);

// Comparar
boolean passado = data.isBefore(LocalDate.now());
```

---

## Fusos Horários

```java
ZonedDateTime sp = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
ZonedDateTime tokyo = sp.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
```

---

## Formatar e Analisar Datas

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
String formatado = dataHora.format(formatter);

LocalDateTime analisado = LocalDateTime.parse("25/12/2025 23:00", formatter);
```

---

## Duração e Período

```java
// Duration (tempo)
Duration dur = Duration.between(LocalTime.now(), LocalTime.now().plusMinutes(90));
long minutos = dur.toMinutes();

// Period (datas)
Period periodo = Period.between(LocalDate.of(2023, 1, 1), LocalDate.of(2025, 7, 8));
int anos = periodo.getYears();
```

---

## Trabalhando com Instant

```java
Instant agora = Instant.now();
long millis = agora.toEpochMilli();

Instant aPartirDoMillis = Instant.ofEpochMilli(millis);
```

---

## Exercícios Práticos

| Arquivo                                     | Descrição                                 |
| ------------------------------------------- | ----------------------------------------- |
| [`LabDateTimeToDo`](./LabDateTimeToDo.java) | Classe com os exercícios para implementar |
| [`LabDateTimeDone`](./LabDateTimeDone.java) | Classe com soluções completas             |
| [`exercise.en-pt.md`](./exercise.en-pt.md)  | Lista de exercícios bilíngue (EN/PT-BR)   |

---

## Recursos Adicionais

- [Documentação Oficial Oracle - java.time](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/package-summary.html)
- [Baeldung - Guia Completo sobre java.time](https://www.baeldung.com/java-8-date-time-intro)
- [Blog DevMedia: Trabalhando com Data e Hora no Java 8+](https://www.devmedia.com.br/trabalhando-com-data-e-hora-no-java-8/31802)
- [Cheatsheet java.time em PDF (Baeldung)](https://www.baeldung.com/java-8-date-time-intro#cheat-sheet)

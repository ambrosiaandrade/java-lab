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
- [O que é `ChronoUnit`?](#o-que-e-chronounit)
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

## O que é `ChronoUnit`?

`ChronoUnit` é uma enumeração poderosa da API `java.time` (introduzida no Java 8) que representa **unidades de tempo padrão**, como dias, meses, anos, horas, minutos, segundos, etc. Ela implementa a interface `TemporalUnit`, que define como uma unidade de tempo pode ser usada para medir ou manipular objetos temporais.

Em termos mais simples, o `ChronoUnit` é o seu canivete suíço para medir diferenças entre datas e horas ou para adicionar/subtrair valores de unidades específicas de um ponto no tempo.

---

### Por que usar `ChronoUnit`?

1.  **Precisão e Clareza**: Oferece uma maneira explícita e clara de definir a unidade de tempo com a qual você está trabalhando (por exemplo, `ChronoUnit.DAYS` é inequivocamente sobre dias).
2.  **Cálculo de Diferenças**: É a forma recomendada de calcular a "distância" entre dois pontos no tempo em uma unidade específica (como no seu exemplo de calcular a idade em anos ou dias passados).
3.  **Manipulação de Objetos Temporais**: Permite adicionar ou subtrair quantidades específicas de uma unidade a um `LocalDate`, `LocalTime`, `LocalDateTime`, `Instant`, etc.
4.  **Segurança de Tipo**: Sendo uma enumeração, ajuda a evitar erros de digitação e garante que você esteja usando unidades de tempo válidas.

---

### Principais Usos e Exemplos

Vamos ver como o `ChronoUnit` é comumente utilizado:

#### 1\. Calculando a Diferença entre Duas Datas/Horas

Este é um dos usos mais frequentes. O método estático `between()` é ideal para isso.

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitExamples {

    public static void main(String[] args) {
        // Diferença em dias
        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 1, 31);
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        System.out.println("Dias entre " + date1 + " e " + date2 + ": " + daysBetween); // Saída: 30

        LocalDate date3 = LocalDate.of(2025, 7, 1); // Exemplo com a data atual (considerando 09/07/2025)
        LocalDate date4 = LocalDate.of(2025, 7, 9); // Sua data de hoje
        long daysPassed = ChronoUnit.DAYS.between(date3, date4);
        System.out.println("Dias entre " + date3 + " e " + date4 + ": " + daysPassed); // Saída: 8

        // Diferença em meses
        LocalDate startMonth = LocalDate.of(2024, 1, 15);
        LocalDate endMonth = LocalDate.of(2025, 6, 10);
        long monthsBetween = ChronoUnit.MONTHS.between(startMonth, endMonth);
        System.out.println("Meses entre " + startMonth + " e " + endMonth + ": " + monthsBetween); // Saída: 17

        // Diferença em horas (com LocalDateTime)
        LocalDateTime startDateTime = LocalDateTime.of(2025, 7, 9, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 7, 9, 14, 30);
        long hoursBetween = ChronoUnit.HOURS.between(startDateTime, endDateTime);
        System.out.println("Horas entre " + startDateTime + " e " + endDateTime + ": " + hoursBetween); // Saída: 4 (apenas horas inteiras)

        long minutesBetween = ChronoUnit.MINUTES.between(startDateTime, endDateTime);
        System.out.println("Minutos entre " + startDateTime + " e " + endDateTime + ": " + minutesBetween); // Saída: 270 (4*60 + 30)
    }
}
```

**Observação:** O método `between()` retorna a diferença na unidade especificada. Se a segunda data/hora for anterior à primeira, o resultado será negativo. Se você sempre precisar do valor absoluto, use `Math.abs()`, como vimos no exercício anterior.

#### 2\. Adicionando ou Subtraindo Unidades de Tempo

Você pode usar `ChronoUnit` com os métodos `plus()` e `minus()` de objetos temporais.

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitManipulation {

    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2025, 7, 9);

        // Adicionar 5 dias
        LocalDate fiveDaysLater = today.plus(5, ChronoUnit.DAYS);
        System.out.println("5 dias depois de " + today + ": " + fiveDaysLater); // Saída: 2025-07-14

        // Subtrair 2 meses
        LocalDate twoMonthsAgo = today.minus(2, ChronoUnit.MONTHS);
        System.out.println("2 meses antes de " + today + ": " + twoMonthsAgo); // Saída: 2025-05-09

        // Adicionar 30 minutos a um LocalDateTime
        LocalDateTime now = LocalDateTime.of(2025, 7, 9, 13, 30);
        LocalDateTime thirtyMinutesLater = now.plus(30, ChronoUnit.MINUTES);
        System.out.println("30 minutos depois de " + now + ": " + thirtyMinutesLater); // Saída: 2025-07-09T14:00
    }
}
```

---

## Diferença entre `Period`, `Duration` e `ChronoUnit`

É comum confundir esses três, mas eles têm propósitos distintos:

- **`Period`**: Mede uma quantidade de tempo em **unidades baseadas em datas** (anos, meses, dias). É ideal para coisas como calcular a idade de uma pessoa ou a duração de um contrato.
- **`Duration`**: Mede uma quantidade de tempo em **unidades baseadas em tempo** (horas, minutos, segundos, nanossegundos). É ideal para medir a duração de eventos, como quanto tempo um vídeo tem ou a diferença entre dois `Instant`s.
- **`ChronoUnit`**: É a **enumeração das unidades individuais** (DIAS, MESES, HORAS, etc.) que `Period` e `Duration` usam internamente, e que você usa diretamente com o método `between()` para obter a diferença total em uma única unidade, ou com `plus()`/`minus()` para manipular objetos temporais.

Pense assim: `Period` e `Duration` são "quantidades de tempo" (um ano e um mês, ou 5 horas e 30 minutos). `ChronoUnit` é a "régua" que você usa para medir ou aplicar essas quantidades em diversas unidades.

`ChronoUnit` é uma ferramenta fundamental na API `java.time` para qualquer tipo de manipulação ou cálculo temporal. Entender como e quando usá-lo corretamente o tornará muito mais eficiente ao trabalhar com datas e horas em Java.

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

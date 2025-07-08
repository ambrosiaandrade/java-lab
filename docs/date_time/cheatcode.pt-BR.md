# Cheatsheet Java Date and Time (`java.time`)

---

## Principais Classes

| Classe              | Uso Principal                                     |
| ------------------- | ------------------------------------------------- |
| `LocalDate`         | Data (ano, mês, dia) sem tempo                    |
| `LocalTime`         | Hora (hora, minuto, segundo, nanos) sem data      |
| `LocalDateTime`     | Data + hora sem fuso horário                      |
| `ZonedDateTime`     | Data + hora com fuso horário                      |
| `Instant`           | Timestamp UTC (momento na linha do tempo)         |
| `Duration`          | Intervalo entre dois tempos (tempo, nanosegundos) |
| `Period`            | Intervalo entre duas datas (anos, meses, dias)    |
| `DateTimeFormatter` | Formatação e análise de datas/horas               |
| `ZoneId`            | Identificador de fuso horário                     |
| `ZoneOffset`        | Deslocamento fixo do UTC (ex: +02:00)             |

---

## Criar Data/Hora

```java
LocalDate date = LocalDate.of(2025, 7, 7);
LocalTime time = LocalTime.of(19, 30, 0);
LocalDateTime dateTime = LocalDateTime.of(date, time);

ZonedDateTime zoned = ZonedDateTime.of(dateTime, ZoneId.of("America/Sao_Paulo"));
Instant now = Instant.now();
```

---

## Obter valores

```java
int year = date.getYear();
int month = date.getMonthValue();  // 1-12
int day = date.getDayOfMonth();

int hour = time.getHour();
int minute = time.getMinute();
int second = time.getSecond();
```

---

## Adicionar e subtrair

```java
LocalDate tomorrow = date.plusDays(1);
LocalDate lastWeek = date.minusWeeks(1);

LocalTime nextHour = time.plusHours(1);
LocalDateTime nextMonth = dateTime.plusMonths(1);
```

---

## Comparar datas

```java
boolean before = date.isBefore(LocalDate.now());
boolean after = date.isAfter(LocalDate.now());
boolean equals = date.equals(LocalDate.now());
```

---

## Formatação e parsing

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

String formatted = dateTime.format(formatter);        // formata para string
LocalDateTime parsed = LocalDateTime.parse("07/07/2025 19:30", formatter);
```

---

## Durations e Periods

```java
Duration duration = Duration.between(LocalTime.now(), LocalTime.now().plusHours(3));
long minutes = duration.toMinutes();

Period period = Period.between(LocalDate.of(2023,1,1), LocalDate.of(2025,7,7));
int years = period.getYears();
int months = period.getMonths();
int days = period.getDays();
```

---

## Fuso Horário

```java
ZoneId zone = ZoneId.of("Europe/London");
ZonedDateTime londonTime = ZonedDateTime.now(zone);

ZoneOffset offset = ZoneOffset.of("-03:00");
OffsetDateTime offsetDT = OffsetDateTime.now(offset);
```

---

## Timestamp (Epoch)

```java
Instant instant = Instant.now();
long epochMillis = instant.toEpochMilli();

Instant fromEpoch = Instant.ofEpochMilli(epochMillis);
```

---

## Exemplo rápido: mostrar data/hora atual em vários fusos

```java
ZonedDateTime saoPaulo = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));
ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

System.out.println("São Paulo: " + saoPaulo);
System.out.println("London: " + london);
System.out.println("Tokyo: " + tokyo);
```

Here's the English translation of your content, incorporating the `ChronoUnit` explanation:

---

# Java Date and Time API (`java.time`)

The `java.time` API, introduced in Java 8, provides a modern, immutable, and clear approach to handling dates, times, time zones, periods, and durations. It replaces the older classes (`Date`, `Calendar`, etc.) with a robust and safe structure for temporal manipulation.

---

## 📚 Summary

- [Key Concepts](#key-concepts)
- [Object Creation](#object-creation)
- [Date and Time Manipulation](#date-and-time-manipulation)
- [Time Zones](#time-zones)
- [Formatting and Parsing Dates](#formatting-and-parsing-dates)
- [Duration and Period](#duration-and-period)
- [Working with Instant](#working-with-instant)
- [What is `ChronoUnit`?](#what-is-chronounit)
- [Practical Exercises](#practical-exercises)
- [Additional Resources](#additional-resources)

---

## Key Concepts

| Class                | Description                                               |
| :------------------- | :-------------------------------------------------------- |
| `LocalDate`          | Represents a date (year, month, day), without time        |
| `LocalTime`          | Represents a time (hour, minute, second), without date    |
| `LocalDateTime`      | Combines date and time, without a time zone               |
| `ZonedDateTime`      | Date and time with a time zone                            |
| `Instant`            | An exact moment in time in UTC                            |
| `Period`             | Interval between two dates                                |
| `Duration`           | Interval between two instants (with time)                 |
| `DateTimeFormatter`  | Formatting and parsing dates and times                    |

---

## Object Creation

```java
LocalDate date = LocalDate.of(2025, 7, 8);
LocalTime time = LocalTime.of(14, 30);
LocalDateTime dateTime = LocalDateTime.of(date, time);

ZonedDateTime zoned = ZonedDateTime.of(dateTime, ZoneId.of("America/Sao_Paulo"));
Instant instant = Instant.now();
```

---

## Date and Time Manipulation

```java
// Add/Subtract
LocalDate tomorrow = date.plusDays(1);
LocalTime later = time.plusHours(2);
LocalDateTime future = dateTime.plusMonths(3).minusDays(5);

// Compare
boolean isPast = date.isBefore(LocalDate.now());
```

---

## Time Zones

```java
ZonedDateTime sp = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
ZonedDateTime tokyo = sp.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
```

---

## Formatting and Parsing Dates

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
String formatted = dateTime.format(formatter);

LocalDateTime parsed = LocalDateTime.parse("25/12/2025 23:00", formatter);
```

---

## Duration and Period

```java
// Duration (time)
Duration dur = Duration.between(LocalTime.now(), LocalTime.now().plusMinutes(90));
long minutes = dur.toMinutes();

// Period (dates)
Period period = Period.between(LocalDate.of(2023, 1, 1), LocalDate.of(2025, 7, 8));
int years = period.getYears();
```

---

## Working with Instant

```java
Instant now = Instant.now();
long millis = now.toEpochMilli();

Instant fromMillis = Instant.ofEpochMilli(millis);
```

---

## What is `ChronoUnit`?

`ChronoUnit` is a powerful enumeration from the `java.time` API (introduced in Java 8) that represents **standard time units**, such as days, months, years, hours, minutes, seconds, etc. It implements the `TemporalUnit` interface, which defines how a time unit can be used to measure or manipulate temporal objects.

In simpler terms, `ChronoUnit` is your Swiss Army knife for measuring differences between dates and times or for adding/subtracting specific unit values from a point in time.

---

### Why use `ChronoUnit`?

1.  **Precision and Clarity**: It offers an explicit and clear way to define the time unit you are working with (e.g., `ChronoUnit.DAYS` is unequivocally about days).
2.  **Difference Calculation**: It's the recommended way to calculate the "distance" between two points in time in a specific unit (like in your example of calculating age in years or days passed).
3.  **Temporal Object Manipulation**: It allows you to add or subtract specific quantities of a unit to a `LocalDate`, `LocalTime`, `LocalDateTime`, `Instant`, etc.
4.  **Type Safety**: As an enumeration, it helps prevent typos and ensures you are using valid time units.

---

### Key Uses and Examples

Let's see how `ChronoUnit` is commonly used:

#### 1\. Calculating the Difference between Two Dates/Times

This is one of the most frequent uses. The static `between()` method is ideal for this.

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitExamples {

    public static void main(String[] args) {
        // Difference in days
        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 1, 31);
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        System.out.println("Days between " + date1 + " and " + date2 + ": " + daysBetween); // Output: 30

        LocalDate date3 = LocalDate.of(2025, 7, 1); // Example with current date (considering 09/07/2025)
        LocalDate date4 = LocalDate.of(2025, 7, 9); // Your "today"
        long daysPassed = ChronoUnit.DAYS.between(date3, date4);
        System.out.println("Days between " + date3 + " and " + date4 + ": " + daysPassed); // Output: 8

        // Difference in months
        LocalDate startMonth = LocalDate.of(2024, 1, 15);
        LocalDate endMonth = LocalDate.of(2025, 6, 10);
        long monthsBetween = ChronoUnit.MONTHS.between(startMonth, endMonth);
        System.out.println("Months between " + startMonth + " and " + endMonth + ": " + monthsBetween); // Output: 17

        // Difference in hours (with LocalDateTime)
        LocalDateTime startDateTime = LocalDateTime.of(2025, 7, 9, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 7, 9, 14, 30);
        long hoursBetween = ChronoUnit.HOURS.between(startDateTime, endDateTime);
        System.out.println("Hours between " + startDateTime + " and " + endDateTime + ": " + hoursBetween); // Output: 4 (only whole hours)

        long minutesBetween = ChronoUnit.MINUTES.between(startDateTime, endDateTime);
        System.out.println("Minutes between " + startDateTime + " and " + endDateTime + ": " + minutesBetween); // Output: 270 (4*60 + 30)
    }
}
```

**Note:** The `between()` method returns the difference in the specified unit. If the second date/time is earlier than the first, the result will be negative. If you always need the absolute value, use `Math.abs()`, as seen in the previous exercise.

#### 2\. Adding or Subtracting Time Units

You can use `ChronoUnit` with the `plus()` and `minus()` methods of temporal objects.

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitManipulation {

    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2025, 7, 9);

        // Add 5 days
        LocalDate fiveDaysLater = today.plus(5, ChronoUnit.DAYS);
        System.out.println("5 days after " + today + ": " + fiveDaysLater); // Output: 2025-07-14

        // Subtract 2 months
        LocalDate twoMonthsAgo = today.minus(2, ChronoUnit.MONTHS);
        System.out.println("2 months before " + today + ": " + twoMonthsAgo); // Output: 2025-05-09

        // Add 30 minutes to a LocalDateTime
        LocalDateTime now = LocalDateTime.of(2025, 7, 9, 13, 30);
        LocalDateTime thirtyMinutesLater = now.plus(30, ChronoUnit.MINUTES);
        System.out.println("30 minutes after " + now + ": " + thirtyMinutesLater); // Output: 2025-07-09T14:00
    }
}
```

---

## Difference between `Period`, `Duration`, and `ChronoUnit`

It's common to confuse these three, but they have distinct purposes:

- **`Period`**: Measures a quantity of time in **date-based units** (years, months, days). It's ideal for things like calculating a person's age or the length of a contract.
- **`Duration`**: Measures a quantity of time in **time-based units** (hours, minutes, seconds, nanoseconds). It's ideal for measuring the length of events, like how long a video is or the difference between two `Instant`s.
- **`ChronoUnit`**: This is the **enumeration of individual units** (DAYS, MONTHS, HOURS, etc.) that `Period` and `Duration` use internally, and that you use directly with the `between()` method to get the total difference in a single unit, or with `plus()`/`minus()` to manipulate temporal objects.

Think of it this way: `Period` and `Duration` are "amounts of time" (one year and one month, or 5 hours and 30 minutes). `ChronoUnit` is the "ruler" you use to measure or apply these amounts in various units.

`ChronoUnit` is a fundamental tool in the `java.time` API for any type of temporal manipulation or calculation. Understanding how and when to use it correctly will make you much more efficient when working with dates and times in Java.

---

## Practical Exercises

| File                                                                         | Description                                   |
| :--------------------------------------------------------------------------- | :-------------------------------------------- |
| [`LabDateTimeToDo`](https://www.google.com/search?q=./LabDateTimeToDo.java)  | Class with exercises to implement             |
| [`LabDateTimeDone`](https://www.google.com/search?q=./LabDateTimeDone.java)  | Class with complete solutions                 |
| [`exercise.en-pt.md`](https://www.google.com/search?q=./exercise.en-pt.md)   | Bilingual exercise list (EN/PT-BR)            |

---

## Additional Resources

- [Official Oracle Documentation - java.time](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/package-summary.html)
- [Baeldung - A Guide to java.time](https://www.baeldung.com/java-8-date-time-intro)
- [DevMedia Blog: Working with Date and Time in Java 8+](https://www.devmedia.com.br/trabalhando-com-data-e-hora-no-java-8/31802)
- [java.time Cheatsheet in PDF (Baeldung)](https://www.baeldung.com/java-8-date-time-intro#cheat-sheet)

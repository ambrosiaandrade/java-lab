package date_time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import config.CustomPrint;
import config.MessageProvider;

public class LabDateTimeDone {

    static {
        MessageProvider.setModuleName("date_time");
        CustomPrint.greeting();
    }

    public static void main(String[] args) {

        CustomPrint.colored("exercise1");
        exercise1();
        CustomPrint.colored("exercise2");
        exercise2();
        CustomPrint.colored("exercise3");
        exercise3();
        CustomPrint.colored("exercise4");
        exercise4();
        CustomPrint.colored("exercise5");
        exercise5();
        CustomPrint.colored("exercise6");
        exercise6();
        CustomPrint.colored("exercise7");
        exercise7();
        CustomPrint.colored("exercise8");
        exercise8();
        CustomPrint.colored("exercise9");
        exercise9();
        CustomPrint.colored("exercise10");
        exercise10();
        CustomPrint.colored("exercise11");
        exercise11();
        CustomPrint.colored("exercise12");
        exercise12();
        CustomPrint.colored("exercise13");
        exercise13();
        CustomPrint.colored("exercise14");
        exercise14();
        CustomPrint.colored("exercise15");
        exercise15();

    }

    public static void exercise1() {
        var today = LocalDate.now();
        System.out.println("Year: " + today.getYear());
        System.out.println("Month: " + today.getMonth());
        System.out.println("Day: " + today.getDayOfMonth());
    }

    public static void exercise2() {
        var now = LocalTime.now();
        var nowPlus90Minutes = now.plusMinutes(90);
        System.out.println("Current time: " + now);
        System.out.println("Time after 90 minutes: " + nowPlus90Minutes);
    }

    public static void exercise3() {
        var date = LocalDateTime.now();
        var newDate = date.plusMonths(1).plusDays(10);
        System.out.println("Current date and time: " + date);
        System.out.println("Date and time after 1 month and 10 days: " + newDate);
    }

    public static void exercise4() {
        var date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        var dateFormatted = date.format(formatter);
        System.out.println("Current date and time formatted: " + dateFormatted);
    }

    public static void exercise5() {
        var dateString = "25/12/2025 23:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        System.out.println("Parsed date and time: " + dateTime);
    }

    public static void exercise6() {
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        System.out.println("Current date and time in Tokyo: " + zonedDateTime);
    }

    public static void exercise7() {
        Period period = Period.between(LocalDate.now(), LocalDate.of(2030, 1, 1));
        String result = "Years=" + period.getYears() + ", months=" + period.getMonths() + ", days=" + period.getDays();
        System.out.println(result);
    }

    public static void exercise8() {
        Duration duration = Duration.between(LocalTime.now(), LocalTime.now().plusHours(2));
        System.out.println("Duration in minutes: " + duration.toMinutes());
    }

    public static void exercise9() {
        Instant instant = Instant.now();
        long epochMilli = instant.toEpochMilli();
        System.out.println("Current instant in milliseconds since epoch: " + epochMilli);
    }

    public static void exercise10() {
        LocalDate date1 = LocalDate.of(2023, 10, 1);
        LocalDate date2 = LocalDate.of(2023, 10, 15);
        long days = daysPassed(date1, date2);
        System.out.println("Days passed between " + date1 + " and " + date2 + ": " + days);

        LocalDate date3 = LocalDate.of(2023, 10, 20);
        LocalDate date4 = LocalDate.of(2023, 11, 5); // Example spanning months
        long days2 = daysPassed(date3, date4);
        System.out.println("Days passed between " + date3 + " and " + date4 + ": " + days2);

        LocalDate date5 = LocalDate.of(2024, 1, 1);
        LocalDate date6 = LocalDate.of(2023, 12, 25); // Example with dates in reverse order
        long days3 = daysPassed(date5, date6);
        System.out.println("Days passed between " + date5 + " and " + date6 + ": " + days3);
    }

    private static long daysPassed(LocalDate d1, LocalDate d2) {
        // ChronoUnit.DAYS.between automatically handles the order of dates
        // and returns the total number of days.
        return Math.abs(ChronoUnit.DAYS.between(d1, d2));
    }

    public static void exercise11() {
        ZonedDateTime sp = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime ny = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));
        System.out.println("Current date and time in São Paulo: " + sp);
        System.out.println("Current date and time in New York: " + ny);
        System.out.println("Current date and time in London: " + london);
    }

    public static void exercise12() {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2021,8,16), LocalTime.of(10, 30));
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("America/Mexico_City"));
        System.out.println("LocalDateTime: " + dateTime);
        System.out.println("ZonedDateTime in Mexico City: " + zonedDateTime);
    }

    public static void exercise13() {
        LocalDate date = LocalDate.of(2000, 1, 1);
        Predicate<LocalDate> isOver18 = d -> {
            long age = ChronoUnit.YEARS.between(d, LocalDate.now());
            return age >= 18;
        };
        System.out.println("Is the person born on " + date + " over 18? " + isOver18.test(date));
        date = LocalDate.of(2010, 1, 1);
        System.out.println("Is the person born on " + date + " over 18? " + isOver18.test(date));
    }

    public static void exercise14() {
        LocalDate christmasDate = LocalDate.of(LocalDate.now().getYear(), 12, 25);
        LocalDate today = LocalDate.now();
        long daysUntilChristmas = ChronoUnit.DAYS.between(today, christmasDate);
        System.out.println("Days until Christmas: " + daysUntilChristmas);
    }

    public static void exercise15() {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        Instant instant = now.toInstant();
        System.out.println("Current date and time in São Paulo: " + now);
        System.out.println("Instant representation: " + instant);
        ZonedDateTime convertedBack = ZonedDateTime.ofInstant(instant, zoneId);
        System.out.println("Converted back to ZonedDateTime: " + convertedBack);
    }

}

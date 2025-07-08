package streams;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import config.CustomPrint;
import config.MessageProvider;

public class LabStreamDone {

    private static List<Person> persons = PersonFactory.generatePersons(10);

    static {
        MessageProvider.setModuleName("streams");
        CustomPrint.greeting();
    }

    public static void main(String[] args) {

        System.out.println(":: List of people ::");
        persons.forEach(System.out::println);

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
        List<Person> result = persons.stream()
                .filter(person -> person.getAge() > 30)
                .toList();

        System.out.println(":: " + result.size() + " in total ::");
        result.forEach(person -> System.out.println(" - " + person.getName() + " (" + person.getAge() + ")"));
    }

    public static void exercise2() {
        List<String> result = persons.stream()
                .map(Person::getName)
                .toList();

        System.out.println(result);
    }

    public static void exercise3() {
        Optional<Person> result = persons.stream()
                .max(Comparator.comparingInt(Person::getAge));

        result.ifPresentOrElse(
                person -> System.out.println(" - " + person.getName() + " (" + person.getAge() + ")"),
                () -> System.out.println(" - No persons found"));
    }

    public static void exercise4() {
        Map<String, List<Person>> result = persons.stream()
                .collect(Collectors.groupingBy(person -> person.getFavoriteColor()));

        result.forEach((color, people) -> {
            System.out.println(" - " + color + ": " + people.size() + " persons");
            people.forEach(person -> System.out.println("   - " + person.getName() + " (" + person.getAge() + ")"));
        });
    }

    public static void exercise5() {
        Map<String, Long> result = persons.stream()
                .collect(Collectors.groupingBy(Person::getFavoriteColor, Collectors.counting()));

        result.forEach((color, count) -> System.out.println(" - " + color + ": " + count + " persons"));
    }

    public static void exercise6() {
        boolean result = persons.stream()
                .anyMatch(person -> person.getAge() < 18);
        System.out.println(":: Is there anyone under 18? :: " + (result ? "Yes" : "No"));
    }

    public static void exercise7() {
        List<String> result = persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .map(Person::getName)
                .toList();

        System.out.println(result);
    }

    public static void exercise8() {
        OptionalDouble result = persons.stream()
                .mapToInt(Person::getAge).average();

        result.ifPresentOrElse(
                average -> System.out.printf(":: Average age of persons: %.2f ::%n", average),
                () -> System.out.println(":: No persons found to calculate average age ::"));
    }

    public static void exercise9() {
        List<String> result = persons.stream()
                .filter(person -> person.getName().length() > 5)
                .map(person -> person.getName().toUpperCase())
                .toList();
        System.out.println(":: " + result.size());
        System.out.println(result);
    }

    public static void exercise10() {
        StringBuilder sb = new StringBuilder();
        persons.stream()
                .forEach(person -> {
                    if (sb.length() > 0)
                        sb.append(", ");
                    sb.append(person.getName());
                });
        System.out.println(":: StringBuilder :: \n" + sb.toString());

        String result = persons.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println(":: Collectors.joining() :: \n" + result);

        result = persons.stream()
                .map(Person::getName)
                .reduce((name1, name2) -> name1 + ", " + name2)
                .orElse("");
        System.out.println(":: .reduce() :: \n" + result);
    }

    public static void exercise11() {
        IntSummaryStatistics restult = persons.stream()
                .collect(Collectors.summarizingInt(Person::getAge));

        System.out.println(" - Count: " + restult.getCount());
        System.out.println(" - Min: " + restult.getMin());
        System.out.println(" - Max: " + restult.getMax());
        System.out.printf(" - Average: %.2f%n", restult.getAverage());
        System.out.println(" - Sum: " + restult.getSum());
        System.out.println(" - Range: " + (restult.getMax() - restult.getMin()));
    }

    public static void exercise12() {
        int result = persons.stream()
                .mapToInt(Person::getAge)
                .sum();
        System.out.println(":: Total sum of ages: " + result + " ::");
    }

    public static void exercise13() {
        Map<Character, List<Person>> result = persons.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        result.forEach((gender, person) -> {
            String genderText = gender.equals('M') ? "Male" : "Female";
            System.out.println(" - " + genderText);
            person.forEach(p -> System.out.println("   - " + p.getName() + " (" + p.getAge() + ")"));
        });
    }

    public static void exercise14() {
        Set<String> result = persons.stream()
                .map(Person::getFavoriteColor)
                .collect(Collectors.toSet());

        System.out.println(result.size() + " unique colors found:");
        result.forEach(color -> System.out.println(" - " + color));
    }

    public static void exercise15() {
        Map<Boolean, List<Person>> result = persons.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));

        result.forEach((isAdult, people) -> {
            String ageGroup = isAdult ? "Adults" : "Minors";
            System.out.println(" - " + ageGroup + ": " + people.size() + " persons");
            people.forEach(person -> System.out.println("   - " + person.getName() + " (" + person.getAge() + ")"));
        });
    }

}

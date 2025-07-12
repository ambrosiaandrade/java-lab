package func_prog;

import config.CustomPrint;
import config.MessageProvider;

import java.util.function.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.*;

public class LabFuncProgDone {

    static {
        MessageProvider.setModuleName("func_prog");
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
        // Before java 8
        Function<String, Integer> lengthOfStringOldWay = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            };
        };

        // After java 8 with lambda expression
        Function<String, Integer> lengthOfStringNewWay = s -> s.length();

        Integer result1 = lengthOfStringOldWay.apply("Old way");
        Integer result2 = lengthOfStringNewWay.apply("New way");

        System.out.println(":: Function<String, Integer>\nOld way: " + result1 + "\nNew way: " + result2);
    }

    public static void exercise2() {
        System.out.println(":: Predicate<Integer>");
        Predicate<Integer> isEven = number -> number % 2 == 0;
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        numbers.forEach(n -> {
            String r = String.format("Is %s even? %s", n, isEven.test(n));
            System.out.println(r);
        });
    }

    public static void exercise3() {
        System.out.println(":: Consumer<String>");
        Consumer<String> withPrefix = s -> System.out.println("Value: " + s);
        withPrefix.accept("Saturday");
        withPrefix.accept("Sunday");
    }

    public static void exercise4() {
        System.out.println(":: Supplier<LocalDate>");
        Supplier<LocalDate> today = () -> LocalDate.now();
        System.out.println(today.get());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Supplier<String> todayFormatted = () -> LocalDate.now().format(df);
        System.out.println(todayFormatted.get());
    }

    public static void exercise5() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        var result = getAverage(list);
        System.out.println(result);
    }

    private static double getAverage(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public static void exercise6() {
        var names = List.of("Ana", "Beatriz", "Márcia", "João", "Paulo");
        System.out.println(moreWith4(names));
    }

    private static List<String> moreWith4(List<String> list) {
        return list.stream().filter(s -> s.length() > 4).collect(Collectors.toList());
    }

    public static void exercise7() {
        Optional<String> optString1 = Optional.of("Hello");
        Optional<String> optString2 = Optional.empty();

        optString1.ifPresent(System.out::println);
        optString2.ifPresent(System.out::println);
    }

    public static void exercise8() {
        // Old way
        Function<Integer, Function<Integer, Integer>> func1 = new Function<Integer, Function<Integer, Integer>>() {
            @Override
            public Function<Integer, Integer> apply(Integer i) {
                return new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer y) {
                        return i + y;
                    }
                };
            };
        };

        // Chamando em cadeia
        int i = 10, y = 20;
        Integer result1 = func1.apply(i).apply(y);
        System.out.println(result1);

        // Chamando separado
        Function<Integer, Integer> addY = func1.apply(y);
        Integer result2 = addY.apply(i);
        System.out.println(result2);

        // New way
        Function<Integer, Function<Integer, Integer>> func2 = z -> w -> z + w;
        Integer result3 = func2.apply(i).apply(y);
        System.out.println(result3);

    }

    public static void exercise9() {
        Function<Integer, Integer> f1 = i -> i + 1;
        Function<Integer, Integer> f2 = y -> y * 2;

        System.out.println("Original functions:");
        System.out.println("f1(5) = " + f1.apply(5)); // Expected: 6
        System.out.println("f2(5) = " + f2.apply(5)); // Expected: 10
        System.out.println("---");

        // --- Using andThen ---
        // f1.andThen(f2) means: first apply f1, then apply f2 to f1's result
        // So, for input 'x': (x + 1) * 2
        Function<Integer, Integer> f1AndThenF2 = f1.andThen(f2);
        System.out.println("Using andThen (f1 then f2):");
        System.out.println("f1AndThenF2(5) = " + f1AndThenF2.apply(5));
        // Calculation: f1(5) = 5 + 1 = 6; then f2(6) = 6 * 2 = 12
        System.out.println("Expected: 12");
        System.out.println("---");

        // --- Using compose ---
        // f1.compose(f2) means: first apply f2, then apply f1 to f2's result
        // So, for input 'x': (x * 2) + 1
        Function<Integer, Integer> f1ComposeF2 = f1.compose(f2);
        System.out.println("Using compose (f2 then f1):");
        System.out.println("f1ComposeF2(5) = " + f1ComposeF2.apply(5));
        // Calculation: f2(5) = 5 * 2 = 10; then f1(10) = 10 + 1 = 11
        System.out.println("Expected: 11");
        System.out.println("---");

        // Let's try another combination for clarity
        // f2.andThen(f1) means: first apply f2, then apply f1 to f2's result
        // So, for input 'x': (x * 2) + 1
        Function<Integer, Integer> f2AndThenF1 = f2.andThen(f1);
        System.out.println("Using andThen (f2 then f1):");
        System.out.println("f2AndThenF1(5) = " + f2AndThenF1.apply(5));
        // Calculation: f2(5) = 5 * 2 = 10; then f1(10) = 10 + 1 = 11
        System.out.println("Expected: 11");
        System.out.println("---");

        // f2.compose(f1) means: first apply f1, then apply f2 to f1's result
        // So, for input 'x': (x + 1) * 2
        Function<Integer, Integer> f2ComposeF1 = f2.compose(f1);
        System.out.println("Using compose (f1 then f2):");
        System.out.println("f2ComposeF1(5) = " + f2ComposeF1.apply(5));
        // Calculation: f1(5) = 5 + 1 = 6; then f2(6) = 6 * 2 = 12
        System.out.println("Expected: 12");
        System.out.println("---");
    }

    public static void exercise10() {
        List<String> list = List.of("La", "casa", "del", "papel");
        List<String> list2 = list.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        ;
        list2.forEach(System.out::println);
    }

    public static void exercise11() {
        UnaryOperator<String> removeSpaceAndLowerIt = s -> s.replaceAll(" ", "").toLowerCase();
        System.out.println(removeSpaceAndLowerIt.apply(" LISBOA W   as there"));
    }

    public static void exercise12() {
        BiFunction<String, Integer, String> repeats = (var0, var1) -> var0.repeat(var1);
        System.out.println(repeats.apply("Word", 3));
    }

    public static void exercise13() {
        List<Number> numbers = List.of(10, 10.4, 6, 11L, 50, 3, 2);
        List<Number> greaterThan10 = numbers.stream().filter(n -> n.doubleValue() > 10).collect(Collectors.toList());
        System.out.println(numbers);
        System.out.println("to");
        System.out.println(greaterThan10);
    }

    public static void exercise14() {
        List<String> list = List.of("La", "casa", "del", "papel");
        Optional<String> result = list.stream().reduce((str0, str1) -> str0 + "-" + str1);
        result.ifPresent(System.out::println);
    }

    public static void exercise15() {
        Map<String, Integer> map = new HashMap<>();
        map.put("TV Show", 1);
        map.put("Movies", 2);
        map.put("Theater", 3);
        map.forEach((key, value) -> System.out.println(key + ": " + value));
    }

}

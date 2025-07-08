package optionals;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import config.CustomPrint;
import config.MessageProvider;

public class LabOptionalsDone {

    static {
        MessageProvider.setModuleName("optionals");
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
    }

    public static void exercise1() {
        Optional<String> optionalEmpty = Optional.empty();
        try {
            System.out.println("Optional.empty();\nValue: " + optionalEmpty.get());
        } catch (NoSuchElementException e) {
            System.err.println("NoSuchElementException caught: " + e.getMessage());
        } finally {
            System.out.println(
                    "::The value of the optional is not present, so an exception is thrown when trying to access it.");
        }
    }

    public static void exercise2() {
        Optional<String> optionalWithValue = Optional.of("Hello world");
        System.out.println("Optional.of();\nValue: " + optionalWithValue.get());
    }

    public static void exercise3() {
        Optional<String> optional = Optional.empty();
        String value = optional.orElse("Default value");
        System.out.println("Optional.empty() then orElse():\nValue: " + value);

        optional = Optional.of("Hello world");
        value = optional.orElse("Default value");
        System.out.println("Optional.of() then orElse():\nValue: " + value);

        System.out.println(
                "::The value of the optional is return when it has a value, otherwise the default value is returned.");
    }

    public static void exercise4() {
        Optional<String> optional = Optional.of("Exercising optionals");
        Optional<String> optionalMapped = optional.map(value -> value.toUpperCase());
        System.out.println("Optional.of() then map():\nValue: " + optionalMapped.get());
    }

    public static void exercise5() {
        List<Double> numbers = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        String result = numbers.stream()
                .filter(number -> number > 3.0)
                .map(number -> "Number: " + number)
                .collect(Collectors.joining(", "));
        System.out.println("Optional with stream operations:\nValue: " + result);
    }

    public static void exercise6() {
        OptionalInt optionalInt = OptionalInt.of(55);
        System.out.println("OptionalInt.of(55);\nValue: " + optionalInt.getAsInt());
    }

    public static void exercise7() {
        OptionalLong optionalLong = OptionalLong.of(44);
        System.out.println("OptionalLong.of(44);\nValue: " + optionalLong.getAsLong());
    }

    public static void exercise8() {
        OptionalDouble optionalDouble = OptionalDouble.of(33.00);
        System.out.println("OptionalDouble.of(33.00);\nValue: " + optionalDouble.getAsDouble());
    }

    public static void exercise9() {
        try {
            Optional<String> optional = Optional.empty();
            String value = optional.orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException caught: " + e.getMessage());
        }
    }

    public static void exercise10() {
        String name = getNameById(1);
        Optional<String> optional = Optional.ofNullable(name);
        if (optional.isPresent())
            System.out.println("Optional.ofNullable();\nValue: " + optional.get());
        else
            System.out.println("Value is not present.");

        name = getNameById(2);
        optional = Optional.ofNullable(name);
        if (optional.isPresent())
            System.out.println("Optional.ofNullable();\nValue: " + optional.get());
        else
            System.out.println("Value is not present.");
    }

    private static String getNameById(int id) {
        if (id == 1)
            return "Eve Macarro";
        return null;
    }

    public static void exercise11() {
        String name = getNameById(1);
        Optional<String> optional = Optional.ofNullable(name);
        optional.ifPresent(value -> System.out.println("Optional.ifPresent();\nValue: " + value));
    }

}

package lambdas;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import config.CustomPrint;
import config.MessageProvider;

public class LabLambdaDone {

    static {
        MessageProvider.setModuleName("lambdas");
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
        Runnable runnable = () -> System.out.println("Hello runnable!\nrunnable.run() called");
        runnable.run();
    }

    private record Person(String name, int age) {
    }

    public static void exercise2() {
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Bob", 25);

        Comparator<Person> ageComparator = (person1, person2) -> Integer.compare(person1.age(), person2.age());

        int result = ageComparator.compare(p1, p2);
        String resultInWords = switch (result) {
            case -1 -> "p1 is younger than p2";
            case 1 -> "p1 is older than p2";
            case 0 -> "p1 has the same age as p2";
            default -> "Unexpected result";
        };
        System.out.println(resultInWords);
    }

    public static void exercise3() {
        List<String> names = new ArrayList<>(List.of("Ana", "João", "Alberto", "Diná", "Zé", "Maria", "Alicia", "Bia"));
        System.out.println("Before sorting: " + names);

        names.sort((a, b) -> a.compareTo(b));
        System.out.println("After sorting: " + names);

        Comparator<String> reverse = Comparator.reverseOrder();
        names.sort(reverse);
        System.out.println("After reverse sorting: " + names);
    }

    public static void exercise4() {
        Predicate<Integer> isEven = number -> number % 2 == 0;
        System.out.println("predicate.test(): ");
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));
    }

    public static void exercise5() {
        Function<String, Integer> returnsLength = text -> text.length();
        System.out.println("function.apply(): ");
        System.out.println("Length of 'Ambrósia': " + returnsLength.apply("Ambrósia"));
    }

    public static void exercise6() {
        var names = List.of(new Person("Alice", 25),
                new Person("Bob", 25));

        Consumer<Person> consumer = person -> System.out.println("Name: " + person.name());

        names.forEach(consumer);
    }

    public static void exercise7() {
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("randomSupplier.get(): " + randomSupplier.get());
    }

    public static void exercise8() {
        BinaryOperator<String> concat = (str1, str2) -> str1 + str2;
        String result = concat.apply("Binary ", "Operator");
        System.out.println("binaryOperator<String>.apply(): " + result);
    }

    public static void exercise9() {
        var names = List.of("Ana", "João", "Alberto", "Diná", "Zé", "Maria", "Alicia", "Bia");
        Predicate<String> namesMoreThanThree = name -> name.length() > 3;

        System.out.println("predicate<String>: " + customFilter(names, namesMoreThanThree));

        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Predicate<Integer> numberIsOdd = number -> number % 2 != 0;

        System.out.println("predicate<Integer>: " + customFilter(numbers, numberIsOdd));

        System.out.println("p -> p.length() > 5: " + customFilter(names, p -> p.length() > 5));
    }

    private static <T> List<T> customFilter(List<T> list, Predicate<T> predicate) {
        var newList = new ArrayList<T>();
        for (T item : list) {
            if (predicate.test(item)) {
                newList.add(item);
            }
        }
        return newList;
    }

    public static void exercise10() {
        int number = 10;
        Function<Integer, Integer> func = n -> n + number;
        System.out.println("Function<Integer, Integer> func = n -> n + number: " + func.apply(5));
    }

    public static void exercise11() {
        Function<String, String> func1 = s -> s.toUpperCase();
        Function<String, String> func2 = s -> s + "!";
        Function<String, String> combinedFunc = func1.andThen(func2);

        var result = combinedFunc.apply("Brazil");
        System.out.println("func1.andThen(func2): " + result);
    }

    public static void exercise12() {
        // abstract method with lambda, 'toImplementTime()'
        MyInterface myInterface = () -> LocalTime.of(20, 0);

        System.out.println("myInterface.toImplementTime(): " + myInterface.toImplementTime());
        System.out.println("myInterface.getImplementedTime(): " + myInterface.getImplementedTime());
    }

    @FunctionalInterface
    private interface MyInterface {
        LocalTime toImplementTime();

        default String getImplementedTime() {
            Predicate<LocalTime> isMorning = time -> time.getHour() < 12;
            return isMorning.test(LocalTime.now()) ? "Good morning!" : "Good afternoon!";
        }
    }

    public static void exercise13() {
        List<Runnable> list = List.of(
                () -> System.out.println("Hello from lambda 1!"),
                () -> System.out.println("Hello from lambda 2!"),
                () -> System.out.println("Hello from lambda 3!"));

        // list.forEach(Runnable::run);
        list.forEach(r -> r.run());
    }

    public static void exercise14() {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Predicate<String> isValidEmail = s -> s.matches(regex);
        System.out.println("String.matches(regex): " + isValidEmail.test("testes@gmail.com"));
        System.out.println("String.matches(regex): " + isValidEmail.test("teste53gmail.com"));
    }

    public static void exercise15() {
        List<String> names = List.of("Ana", "João", "Alberto", "Diná", "Zé", "Maria", "Alicia", "Bia");

        names.stream()
            .filter(name -> name.startsWith("A"))
            .map(name -> name + "!")
            .sorted(Comparator.reverseOrder())
            .forEach(System.out::println); // Using method reference
    }

}

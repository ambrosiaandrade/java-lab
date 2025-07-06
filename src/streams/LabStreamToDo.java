package src.streams;

import java.util.List;

import src.config.CustomPrint;
import src.config.MessageProvider;

public class LabStreamToDo {

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
        // ...
    }

    public static void exercise1() {
    }

    public static void exercise2() {
    }

    public static void exercise3() {
    }

    public static void exercise4() {
    }

    public static void exercise5() {
    }

    public static void exercise6() {
    }

    public static void exercise7() {
    }

    public static void exercise8() {
    }

    public static void exercise9() {
    }

    public static void exercise10() {
    }

    public static void exercise11() {
    }

    public static void exercise12() {
    }

    public static void exercise13() {
    }

    public static void exercise14() {
    }

}

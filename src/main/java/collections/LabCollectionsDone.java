package collections;

import java.util.*;
import java.util.function.Function;

import config.CustomPrint;
import config.MessageProvider;

public class LabCollectionsDone {
    static {
        MessageProvider.setModuleName("collections");
        CustomPrint.greeting();
        Collections.synchronizedList(null)
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
        Comparator
    }

    public static void exercise1() {
        var names = new ArrayList<String>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("Diana");
        names.add("Eve");
        System.out.println("Names: " + names);
    }

    public static void exercise2() {
        var numbers = new LinkedList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        System.out.println("Numbers: " + numbers);
        numbers.remove(1); // Remove the element at index 1, 2nd element
        System.out.println("After removing index 1: " + numbers);
        //numbers.remove(Integer.valueOf(4)); // Remove the element with value 4
    }

    public static void exercise3() {
        var numbers = new HashSet<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(4); // Duplicate, will not be added
        System.out.println("Numbers: " + numbers);
    }

    public static void exercise4() {
        var names = new TreeSet<String>();
        names.add("Júlia");
        names.add("Alice");
        names.add("Marcus");
        names.add("Beatriz");
        names.add("João");
        System.out.println("Names: " + names);
    }

    public static void exercise5() {
        var map = new HashMap<String, Integer>();
        map.put("Júlia", 15);
        map.put("Alice", 16);
        map.put("Marcus", 17);
        System.out.println("Júlia has " + map.get("Júlia") + " years old.");
    }

    public static void exercise6() {
        var map = new TreeMap<String, Integer>();
        map.put("Júlia", 15);
        map.put("Alice", 16);
        map.put("Marcus", 17);

        map.forEach((name, age) -> System.out.println(name + " has " + age + " years old."));
    }

    public static void exercise7() {
        var priorityQueue = new PriorityQueue<String>();
        priorityQueue.add("Júlia");
        priorityQueue.add("Alice");
        priorityQueue.add("Marcus");
        priorityQueue.add("Beatriz");
        priorityQueue.add("João");

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }

    public static void exercise8() {
        List<String> names = Arrays.asList("Bob", "Bob", "Eve", "Diana", "Eve");
        System.out.println("Names with duplicates: " + names);
        Set<String> namesWithoutDuplicates = new HashSet<>(names);
        System.out.println("Names without duplicates: " + namesWithoutDuplicates);
    }

    public static void exercise9() {
        try {
            List<String> immutableList = List.of("Alice", "Bob", "Charlie");
            // will throw UnsupportedOperationException
            immutableList.add("Diana");
        } catch (Exception e) {
            System.err.println("Can not add a new element on a immutable list, created as List.of()\n" + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public static void exercise10() {
        var map = new TreeMap<String, Integer>();
        map.put("Júlia", 15);
        map.put("Alice", 16);
        map.put("Marcus", 17);

        for (Map.Entry<String, Integer> m : map.entrySet()) {
            System.out.println(m.getKey() + " has " + m.getValue() + " years old.");
        }

    }

    public static void exercise11() {
        // Custom Comparator to order strings by their length
        Comparator<String> lengthComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(lengthComparator);

        priorityQueue.add("Júlia");
        priorityQueue.add("Alice");
        priorityQueue.add("Marcus");
        priorityQueue.add("Beatriz");
        priorityQueue.add("João");

        System.out.println("--- Elementos na ordem do iterador (não necessariamente prioridade) ---");
        priorityQueue.forEach(System.out::println); // Sua saída atual

        System.out.println("--- Elementos removidos na ordem de prioridade (menor comprimento primeiro) ---");
        // Para ver a ordem real de prioridade, precisamos usar poll()
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll()); // Isso sempre pega o elemento de maior prioridade
        }

        System.out.println("--- Compare with exercise7, which uses the natural order of strings ---");
    }

}

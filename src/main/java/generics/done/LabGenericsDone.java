package generics.done;

import config.CustomPrint;
import config.MessageProvider;

import java.util.List;
import java.util.ArrayList;

public class LabGenericsDone {

    static {
        MessageProvider.setModuleName("generics");
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

    }

    public static void exercise1() {
        Pair<String, Integer> stringInteger = new Pair<>("Hello", 10);
        out(":: Pair<String, Integer> :: " + stringInteger);
        out(stringInteger.getLeft());
        out(stringInteger.getRight());
        out("swap() " + stringInteger.swap());

        Pair<Double, String> doubleString = new Pair<>(55.0, "World");
        out("\n:: Pair<Double, String> :: " + doubleString);
        out(doubleString.getLeft());
        out(doubleString.getRight());
        out("swap() " + doubleString.swap());
    }

    public static void exercise2() {
        out(":: ListUtilities.printList() ::");
        List<String> names = List.of("Ambrósia", "Andrade");
        List<Integer> integers = List.of(178, 895);
        List<Double> doubles = List.of(10.1, 26.785);
        ListUtilities.printList(names);
        ListUtilities.printList(integers);
        ListUtilities.printList(doubles);

        out("\n:: ListUtilities.sumOfNumbers() ::");
        double resultIntegers = ListUtilities.sumOfNumbers(integers);
        double resultDoubles = ListUtilities.sumOfNumbers(doubles);
        out("[resultIntegers] " + resultIntegers);
        out("[resultDoubles] " + resultDoubles);

        out("\n:: ListUtilities.addNumbersToList() ::");
        List<Integer> newList = new ArrayList<>();
        ListUtilities.addNumbersToList(newList);
        out(newList);
        List<Number> newList2 = new ArrayList<>();
        ListUtilities.addNumbersToList(newList2);
        out(newList2);
    }

    public static void exercise3() {
        out(":: MeasurableItem.isGreaterThan() ::");
        MeasurableItem<String> measure1 = new MeasurableItem<>("Generics");
        MeasurableItem<String> measure2 = new MeasurableItem<>("Hello world");
        out("Is 'new MeasurableItem<>(\"Generics\")' greater than 'new MeasurableItem<>(\"Hello world\") ? " +
                measure1.isGreaterThan(measure2));

        MeasurableItem<Integer> measure3 = new MeasurableItem<>(50);
        MeasurableItem<Integer> measure4 = new MeasurableItem<>(20);
        out("Is 'new MeasurableItem<>(50)' greater than 'new MeasurableItem<>(20) ? " +
                measure3.isGreaterThan(measure4));

        // MeasurableItem<Object> invalidItem = new MeasurableItem<>(new Object());
        // ERRO DE COMPILAÇÃO, pois Object não implementa Comparable.
        // Isso prova que a delimitação 'extends Comparable<T>' funcionou!
    }

    public static void exercise4() {
        out(":: ReportProcessor - SalesProcessor ::");
        SalesProcessor salesProcessor = new SalesProcessor();
        List<Double> doubles = List.of(10.0, 20.2, 30.3);
        var resultSales = salesProcessor.process(doubles);
        out("Result: " + resultSales);

        out(":: ReportProcessor - ErrorLogProcessor ::");
        ErrorLogProcessor errorProcessor = new ErrorLogProcessor();
        List<String> logs = generateLogs();
        var resultError = errorProcessor.process(logs);
        out("Result: " + resultError);
    }

    private static void out(Object o) {
        System.out.println(o);
    }

    public static List<String> generateLogs() {
        return List.of(
                "INFO: User 'john.doe' logged in successfully from 192.168.1.100",
                "DEBUG: Processing request for endpoint /api/data",
                "WARNING: Disk space low on /dev/sda1. Free space: 15%",
                "ERROR: Failed to connect to database 'mydb' at jdbc:mysql://localhost:3306/mydb. Connection refused.",
                "INFO: Data saved to cache: key='userSession_123'",
                "DEBUG: Retrieved 500 records from 'products' table",
                "INFO: Application started successfully on port 8080",
                "ERROR: NullPointerException occurred in com.example.service.UserService.getUserById(UserService.java:75)",
                "WARNING: Deprecated API usage detected in legacy_module.jar",
                "INFO: Scheduled task 'CleanupService' executed in 125ms",
                "DEBUG: User 'jane.smith' requested profile update",
                "ERROR: File not found: /app/config/application.properties",
                "INFO: Email notification sent to admin@example.com",
                "WARNING: High CPU usage (95%) detected on server 'app-server-01'",
                "DEBUG: Query executed: SELECT * FROM orders WHERE status = 'PENDING'",
                "ERROR: OutOfMemoryError: Java heap space",
                "INFO: Batch process 'DailyReport' completed with 1000 items processed",
                "DEBUG: Cache hit for item 'product_456'",
                "WARNING: Unhandled exception in background thread pool",
                "ERROR: Authentication failed for user 'guest'",
                "INFO: Service shutdown initiated.");
    }

}

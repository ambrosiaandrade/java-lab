package generics.done;

import java.util.List;

public class ListUtilities {

    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static double sumOfNumbers(List<? extends Number> numbers) {
        // Necessário converter para StreamDouble porque o método .sum() não existem em Stream<T>
        return numbers.stream().mapToDouble(n -> n.doubleValue()).sum();
    }

    public static void addNumbersToList(List<? super Integer> list) {
        int count = 1;
        while(count <= 5) {
            list.add(count);
            count++;
        }
    }

}

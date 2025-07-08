package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonFactory {

    private static String[] colors = { "Red", "Green", "Blue", "Yellow", "Purple" };
    private static String[] names = {
            "Alice", "Bob", "Charlie", "Diana", "Ethan", "Akira", "Camila",
            "Pierre", "Lena", "Kai", "Sofia", "Mateo", "Elara", "Hiroshi",
            "Isabella", "Liam", "Noemi", "Ravi", "Zara", "Freya", "Javier",
            "Amara", "Stefan"
    };

    public static List<Person> generatePersons(int numberOfPersons) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < numberOfPersons; i++) {
            persons.add(new Person(getValue(names), getRandomAge(), getValue(colors), (i % 2 == 0 ? 'M' : 'F')));
        }
        return persons;
    }

    private static int getRandomAge() {
        Random random = new Random();
        int min = 15;
        int max = 100;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return randomNumber;
    }

    private static String getValue(String[] list) {
        return list[(int) (Math.random() * list.length)];
    }

}

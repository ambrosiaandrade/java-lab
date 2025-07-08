package records;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

import config.CustomPrint;
import config.MessageProvider;
import jakarta.validation.constraints.NotNull;

public class LabRecordsDone {

    static {
        MessageProvider.setModuleName("records");
        CustomPrint.greeting();
    }

    public static void main(String[] args) throws Exception {

        CustomPrint.colored("exercise2");
        exercise2();
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
        CustomPrint.colored("exercise13");
        exercise13();
        CustomPrint.colored("exercise14");
        exercise14();

    }

    // Exercise 1
    // public record Book(String title, String author) {}

    public static void exercise2() {
        Book book = new Book("1984", "George Orwell");
        System.out.println(book);
    }

    // Exercise 3
    /*
     * public record Book(String title, String author) {
     * public double calculatePriceWithDiscount(double price, int discount) {
     * if (discount < 0 || discount > 100) {
     * throw new IllegalArgumentException("Discount must be between 0 and 100");
     * }
     * return price - (price * discount / 100);
     * }
     * }
     */

    // Exercise 4
    public record Book(String title, String author) {
        public Book {
            if (author == null || author.length() == 0) {
                throw new IllegalArgumentException("Author cannot be null or empty");
            }
        }

        public double calculatePriceWithDiscount(double price, int discount) {
            if (discount < 0 || discount > 100) {
                throw new IllegalArgumentException("Discount must be between 0 and 100");
            }
            return price - (price * discount / 100);
        }
    }

    // Exercise 5
    public interface Publisher {
        String getPublisherName();
    }

    public record BookWithPublisher(String title, String author, String publisher) implements Publisher {
        @Override
        public String getPublisherName() {
            return publisher;
        }
    }

    public static void exercise6() {
        Book book1 = new Book("1984", "George Orwell");
        Book book2 = new Book("1984", "George Orwell");
        System.out.println("Are the books equal? " + book1.equals(book2));

        Book book3 = new Book("Brave New World", "Aldous Huxley");
        System.out.println("Are book1 and book3 equal? " + book1.equals(book3));
    }

    public static void exercise7() {
        Map<Book, String> bookMap = new HashMap<>();
        Book book = new Book("Brave New World", "Aldous Huxley");
        bookMap.put(book, "Dystopian novel");
        System.out.println("Book Map: " + bookMap);
        System.out.println("bookMap.get(book): " + bookMap.get(book));
    }

    // Exercise 8
    public record BookWithAnnotation(@NotNull String title) {
    }

    public static void exercise8() {
        try {
            BookWithAnnotation book1 = new BookWithAnnotation("Annotated Book");
            System.out.println("Book with annotation created: " + book1);
            BookWithAnnotation book2 = new BookWithAnnotation(null);
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    // Exercise 9
    public static void exercise9() {
        List<BookWithAnnotation> books = List.of(
                new BookWithAnnotation("book 1"),
                new BookWithAnnotation("book 2"),
                new BookWithAnnotation("book 3"));
        books.stream().forEach(System.out::println);
    }

    // Exercise 10
    public record Point(int x, int y) {
        public Point {
            if (x < 0 || y < 0)
                throw new IllegalArgumentException("Coordinates must be non-negative");
        }

        public double distanceTo(Point other) {
            return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
        }
    }

    public static void exercise10() {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(5, 6);
        System.out.println("Distance between " + p1 + " and " + p2 + " is: " + p1.distanceTo(p2));
    }

    // Exercise 11
    private static Book handleBook(BookWithAnnotation bookWithAnnotation) {
        boolean hasTitle = bookWithAnnotation.title() != null && !bookWithAnnotation.title().isEmpty();
        return hasTitle ? new Book(bookWithAnnotation.title(), "Unknown Author")
                : new Book("Unknown", "Unknown Author");
    }

    public static void exercise11() {
        BookWithAnnotation bookWithAnnotation = new BookWithAnnotation("Sample Book");
        System.out.println(handleBook(bookWithAnnotation));
    }

    // Exercise 12
    // All records are nested within the LabRecordsDone class.

    // Exercise 13
    public static void exercise13() {
        Random random = new Random();
        boolean randomBoolean = random.nextBoolean();
        Book book = switch (String.valueOf(randomBoolean)) {
            case "true" -> new Book("1984", "George Orwell");
            case "false" -> new Book("Brave New World", "Aldous Huxley");
            default -> { // 'default' has a block, so 'yield' IS needed here
                System.err.println("This case should ideally not be reached with boolean inputs.");
                yield new Book("Unknown", "Unknown Author");
            }
        };
        System.out.println("Randomly selected book: " + book);
    }

    // Exercise 14
    public static void exercise14() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Book book = new Book("1984", "Objec Mapper Example");

        // Serialize, Book object into byte array
        byte[] bookBytes = mapper.writeValueAsBytes(book);
        System.out.println("Serialized Book to byte array: " + bookBytes.length + " bytes");

        // Deserialize, byte array into Book object
        Book deserializedBook = mapper.readValue(bookBytes, Book.class);
        System.out.println("Deserialized Book: " + deserializedBook);
    }
}

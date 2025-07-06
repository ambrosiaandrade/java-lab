package src.config;

public class CustomPrint {

    final static String RESET = "\u001B[0m";
    final static String GREEN = "\u001B[32m";
    final static String PINK = "\u001B[95m";
    final static String BLUE = "\u001B[34m";
    final static String YELLOW = "\u001B[33m";

    public static void greeting() {
        String label = String.format("[%s]", MessageProvider.getModuleName().toUpperCase());
        int totalLength = 50; // comprimento total da linha
        int paddingSize = (totalLength - label.length()) / 2;

        String padding = "=".repeat(Math.max(0, paddingSize));

        String line = String.format("%s%s %s %s%s", PINK, padding, label, padding, RESET);

        // Se for ímpar, adiciona 1 "=" no final
        if (line.replaceAll("\u001B\\[[;\\d]*m", "").length() < totalLength)
            line = line.replace(RESET, "=" + RESET);

        System.out.println(line);
    }

    public static void of(String message) {
        String text = String.format("[%s] %s: %s",
                MessageProvider.getModuleName().toUpperCase(),
                message,
                MessageProvider.get(message));
        System.out.println(text);
    }

    public static void colored(String message) {
        String text = String.format("%s%s%s %s[%s]%s: %s%s%s",
                GREEN, MessageProvider.getModuleName().toUpperCase(), RESET,
                BLUE, message, RESET,
                YELLOW, MessageProvider.get(message), RESET);

        System.out.println(text);
    }

}

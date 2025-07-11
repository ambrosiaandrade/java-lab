package config;

/**
 * `CustomPrint` é uma classe utilitária estática projetada para aprimorar a saída no console
 * (log) em aplicações Java. Ela oferece métodos para exibir mensagens formatadas
 * e coloridas, tornando o log mais legível e informativo.
 *
 * Utiliza códigos ANSI para cores no terminal, o que pode não ser totalmente
 * compatível com todos os ambientes de console sem suporte a ANSI.
 *
 * **Funcionalidades Principais:**
 * - `greeting()`: Exibe uma mensagem de boas-vindas formatada com o nome do módulo.
 * - `of(String message)`: Imprime uma mensagem padrão do módulo, buscando seu conteúdo
 * em `MessageProvider`.
 * - `colored(String message)`: Imprime uma mensagem do módulo com cores vibrantes
 * para maior destaque.
 * - `error(String message)`: Exibe mensagens de erro em destaque (vermelho).
 *
 * Esta classe centraliza a lógica de formatação de saída, promovendo a consistência
 * visual e facilitando a manutenção dos logs da aplicação.
 */
public class CustomPrint {

    final static String RESET = "\u001B[0m";
    final static String GREEN = "\u001B[32m";
    final static String PINK = "\u001B[95m";
    final static String BLUE = "\u001B[34m";
    final static String YELLOW = "\u001B[33m";
    final static String RED = "\u001B[31m";

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

    public static void error(String message) {
        String text = String.format("%s[ERROR] %s%s", RED, message, RESET);
        System.out.println(text);
    }

}

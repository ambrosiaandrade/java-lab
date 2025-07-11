package func_prog;

import java.util.List;
import java.util.Random;
// Importe a interface Function
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * `OldAndNew` é uma classe didática projetada para **demonstrar e comparar**
 * a evolução da programação em Java, focando na transição do estilo
 * **imperativo/orientado a objetos tradicional** para o paradigma da
 * **Programação Funcional** introduzido a partir do Java 8.
 *
 * Esta classe exemplifica o uso das principais **interfaces funcionais**
 * (`Function`, `Predicate`, `Consumer`, `Supplier`, `UnaryOperator`,
 * `BinaryOperator`, `BiFunction`)
 * através de duas abordagens distintas:
 *
 * 1. **`OldWay` (Forma Tradicional):** Utiliza classes anônimas para
 * implementar
 * as interfaces funcionais, representando o estilo de programação predominante
 * antes do Java 8 ou em cenários onde a concisão das lambdas não é aplicada.
 * 2. **`NewWay` (Forma Funcional Moderna):** Emprega **expressões lambda** para
 * implementar as mesmas interfaces funcionais, ilustrando a sintaxe concisa,
 * a legibilidade aprimorada e a expressividade que a programação funcional
 * traz para o Java.
 *
 * Ao comparar lado a lado, `OldAndNew` facilita a compreensão de como
 * as lambdas e as interfaces funcionais simplificam o código, promovem a
 * imutabilidade e abrem caminho para padrões mais poderosos como a Streams API
 * no processamento de coleções.
 */
public class OldAndNew {

    public static void main(String[] args) {
        // Criamos instâncias dentro do método main, já que ele é estático.
        // Ou poderíamos tornar oldWay e newWay estáticos na classe OldAndNew.
        OldWay oldWay = new OldWay();
        NewWay newWay = new NewWay();

        // FUNCTION<T,R>
        System.out.println(":: FUNCTION");
        Function<String, Integer> myFunctionOld = oldWay.functionSize();
        Function<String, Integer> myFunctionNew = newWay.functionSize();
        System.out.println("Tamanho (Old Way): " + myFunctionOld.apply("text"));
        System.out.println("Tamanho (New Way): " + myFunctionNew.apply("text"));

        // PREDICATE<T>
        // calling from the object to avoid more variables in main
        System.out.println(":: PREDICATE");
        System.out.println("2 é par? " + oldWay.predicatePair().test(2));
        System.out.println("5 é par? " + newWay.predicatePair().test(5));

        // CONSUMER<T>
        System.out.println(":: CONSUMER");
        System.out.println("2 ao quadrado? ");
        oldWay.consumerSquare().accept(2);
        System.out.println("5 ao quadrado? ");
        newWay.consumerSquare().accept(5);

        // SUPPLIER<T>
        System.out.println(":: SUPPLIER");
        System.out.println(oldWay.supplierRandomNickname().get());
        System.out.println(newWay.supplierRandomNickname().get());

        // UNARY_OPERATOR<T>
        System.out.println(":: UNARY_OPERATOR");
        System.out.println(oldWay.unaryOperatorUpperCase().apply("hello world"));
        System.out.println(newWay.unaryOperatorUpperCase().apply("hello world"));

        // BINARY_OPERATOR<T>
        System.out.println(":: BINARY_OPERATOR");
        System.out.println(oldWay.binaryOperatorSum().apply(1, 2));
        System.out.println(newWay.binaryOperatorSum().apply(3, 4));

        // BI_FUNCTION<T, U, R>
        System.out.println(":: BI_FUNCTION");
        System.out.println(oldWay.biFunctionCombine().apply("Java", 2));
        System.out.println(newWay.biFunctionCombine().apply("Study", 4));
    }
}

class OldWay {
    // Retorna uma implementação de Function usando uma CLASSE ANÔNIMA
    protected Function<String, Integer> functionSize() {
        return new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
    }

    protected Predicate<Integer> predicatePair() {
        return new Predicate<Integer>() {
            @Override
            public boolean test(Integer i) {
                return i % 2 == 0;
            }
        };
    }

    protected Consumer<Integer> consumerSquare() {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println(Math.pow(i, 2));
            }
        };
    }

    protected Supplier<String> supplierRandomNickname() {
        return new Supplier<String>() {
            @Override
            public String get() {
                return Util.generateNickname();
            }
        };
    }

    protected UnaryOperator<String> unaryOperatorUpperCase() {
        return new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };
    }

    protected BinaryOperator<Integer> binaryOperatorSum() {
        return new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
    }

    protected BiFunction<String, Integer, Double> biFunctionCombine() {
        return new BiFunction<String, Integer, Double>() {
            @Override
            public Double apply(String s, Integer i) {
                return s.length() * Math.pow(i, 2);
            }
        };
    }

}

class NewWay {
    // Retorna uma implementação de Function usando uma EXPRESSÃO LAMBDA
    protected Function<String, Integer> functionSize() {
        return s -> s.length();
    }

    protected Predicate<Integer> predicatePair() {
        return i -> i % 2 == 0;
    }

    protected Consumer<Integer> consumerSquare() {
        return i -> System.out.println(Math.pow(i, 2));
    }

    protected Supplier<String> supplierRandomNickname() {
        return () -> Util.generateNickname();
    }

    protected UnaryOperator<String> unaryOperatorUpperCase() {
        return s -> s.toUpperCase();
    }

    protected BinaryOperator<Integer> binaryOperatorSum() {
        return (a, b) -> a + b;
    }

    protected BiFunction<String, Integer, Double> biFunctionCombine() {
        return (s, i) -> s.length() * Math.pow(i, 2);
    }
}

class Util {

    protected static String generateNickname() {
        List<String> nicknames = List.of("CoderNinja", "ByteMaster", "JavaWhiz", "LogicFlow", "PixelPioneer");
        Random random = new Random();
        return nicknames.get(random.nextInt(nicknames.size())) + random.nextInt(50);
    }

}

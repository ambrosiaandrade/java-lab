package design_patterns.singleton.todo;

import config.CustomPrint;
import config.MessageProvider;

public class SimpleMain {

    static {
        MessageProvider.setModuleName("design_patterns");
        CustomPrint.greeting();
    }

    public static void main(String[] args) {
        CustomPrint.colored("singleton1");
        exercise1();
        CustomPrint.colored("singleton2");
        exercise2();
        CustomPrint.colored("singleton3");
        exercise3();
    }

    private static void exercise1() {

    }

    private static void exercise2() {

    }

    private static void exercise3() {

    }

}

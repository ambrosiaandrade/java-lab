package design_patterns.singleton.done;

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
        Singleton1 singleton = Singleton1.getInstance();
        Singleton1 another = Singleton1.getInstance();
        printInformation(singleton, another);
    }

    private static void exercise2() {
        Singleton2 singleton = Singleton2.getInstance();
        Singleton2 another = Singleton2.getInstance();
        printInformation(singleton, another);
    }

    private static void exercise3() {
        Singleton3 singleton = Singleton3.getInstance();
        Singleton3 another = Singleton3.getInstance();
        printInformation(singleton, another);
    }

    private static void printInformation(ISingleton singleton, ISingleton another) {
        System.out.println("Singleton info: " + singleton.getInfo());
        System.out.println("Singleton instance: " + singleton);

        System.out.println(":: Information updated in another instance.");
        another.setInfo("Updated Info");
        System.out.println("Another Singleton info: " + another.getInfo());
        System.out.println("Updated Singleton instance: " + another);
        System.out.println(":: Are both instances equal? " + (singleton == another));
    }

}

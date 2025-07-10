package design_patterns.singleton.done;

/*
 * Implementar o padrão Singleton em Java mais simples
 */
public class Singleton1 implements ISingleton {

    private static Singleton1 instance;
    private String info = "Simple Singleton Instance";

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }

}

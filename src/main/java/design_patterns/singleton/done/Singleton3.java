package design_patterns.singleton.done;

/**
 * Implementar o padrão Singleton em Java com Lazy Initialization, Thread Safety
 * e Inner Static Helper Class
 */
public class Singleton3 implements ISingleton {

    private String info = "Singleton Instance with Inner Static Helper Class";

    private Singleton3() {
    }

    private static class SingletonHolder {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return SingletonHolder.INSTANCE;
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

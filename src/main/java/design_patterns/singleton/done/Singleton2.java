package design_patterns.singleton.done;

/**
 * Implementar o padrão Singleton em Java com `volatile`, `synchronized` e
 * `Double-Checked Locking`
 */
public class Singleton2 implements ISingleton  {

    private static volatile Singleton2 instance;
    private String info = "Singleton Instance with Double-Checked Locking";

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
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

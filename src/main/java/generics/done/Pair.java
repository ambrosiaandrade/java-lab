package generics.done;

public class Pair<T, U> {

    private T var0;
    private U var1;

    public Pair(T t, U u) {
        this.var0 = t;
        this.var1 = u;
    }

    public T getLeft() {
        return var0;
    }

    public U getRight() {
        return var1;
    }

    public Pair<U, T> swap() {
        return new Pair<>(var1, var0);
    }

    @Override
    public String toString() {
        return "Pair{left=" + var0 + ", right=" + var1 + "}";
    }

}

package generics.done;

public class MeasurableItem<T extends Comparable<T>> {

    private T item;

    public MeasurableItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public boolean isGreaterThan(MeasurableItem<T> otherItem) {
        return item.compareTo(otherItem.getItem()) > 0;
    }

    @Override
    public String toString() {
        return "MeasurableItem{" + "item=" + item + '}';
    }

}

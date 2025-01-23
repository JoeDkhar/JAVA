public class WasteItem<T> {
    private T item;
    private WasteType type;

    public WasteItem(T item, WasteType type) {
        this.item = item;
        this.type = type;
    }

    public T getItem() {
        return item;
    }

    public WasteType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "WasteItem{" +
                "item=" + item +
                ", type=" + type +
                '}';
    }
}

public class Waste {
    private int id;
    private String type;
    private int quantity;

    public Waste(int id, String type, int quantity) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
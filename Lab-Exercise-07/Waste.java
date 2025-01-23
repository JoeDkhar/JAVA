public class Waste {
    private WasteType type;
    private double weight;

    public Waste(WasteType type, double weight) {
        this.type = type;
        this.weight = weight;
    }

    public WasteType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "type=" + type +
                ", weight=" + weight +
                '}';
    }
}
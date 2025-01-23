import java.util.*;

class WasteItem implements Comparable<WasteItem> {
    private String id;
    private String type;
    private double weight;
    private boolean recyclable;

    public WasteItem(String id, String type, double weight, boolean recyclable) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.recyclable = recyclable;
    }

    // Getters
    public String getId() { return id; }
    public String getType() { return type; }
    public double getWeight() { return weight; }
    public boolean isRecyclable() { return recyclable; }

    @Override
    public String toString() {
        return String.format("%-15s | %-10s | %-6.2f | %-10s", 
            id, type, weight, recyclable);
    }

    @Override
    public int compareTo(WasteItem other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WasteItem wasteItem = (WasteItem) o;
        return id.equals(wasteItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
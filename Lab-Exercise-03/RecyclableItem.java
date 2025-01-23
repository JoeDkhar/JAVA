public class RecyclableItem extends DisposalItem {
    private String recyclingMethod;

    public RecyclableItem(String itemId, String itemType, int weight, String recyclingMethod) {
        super(itemId, itemType, weight);
        this.recyclingMethod = recyclingMethod;
    }

    @Override
    public void process() {
        System.out.println("Processing recyclable item: " + record.itemType() + " with ID: " + record.itemId());
        System.out.println("Using recycling method: " + recyclingMethod);
    }

    public RecyclableItem setRecyclingMethod(String recyclingMethod) { // Method chaining example
        this.recyclingMethod = recyclingMethod;
        return this;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Recyclable Item Report\n")
              .append(getItemInfo()).append("\n")
              .append("Recycling Method: ").append(recyclingMethod);
        return report.toString();
    }
}
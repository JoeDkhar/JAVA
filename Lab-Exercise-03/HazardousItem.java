public class HazardousItem extends DisposalItem {
    private String disposalMethod;

    public HazardousItem(String itemId, String itemType, int weight, String disposalMethod) {
        super(itemId, itemType, weight); // Constructor chaining with super
        this.disposalMethod = disposalMethod;
    }

    @Override
    public void process() {
        System.out.println("Processing hazardous item: " + record.itemType() + " with ID: " + record.itemId());
        System.out.println("Using disposal method: " + disposalMethod);
    }

    public HazardousItem setDisposalMethod(String disposalMethod) { // Method chaining example
        this.disposalMethod = disposalMethod;
        return this;
    }

    public final String generateReport() { // Using final to prevent overriding
        StringBuilder report = new StringBuilder();
        report.append("Hazardous Item Report\n")
              .append(getItemInfo()).append("\n")
              .append("Disposal Method: ").append(disposalMethod);
        return report.toString();
    }
}
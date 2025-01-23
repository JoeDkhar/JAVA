
package waste;

public class HazardousItem extends DisposalItem {
    private final String disposalMethod;

    public HazardousItem(String itemId, String itemType, int weight, String disposalMethod) {
        super(itemId, itemType, weight);
        this.disposalMethod = disposalMethod;
    }

    @Override
    public void process() {
        System.out.println("Processing hazardous item: " + itemType + " with ID: " + itemId);
        System.out.println("Using disposal method: " + disposalMethod);
    }

    @Override
    public String generateReport() {
        return "Hazardous Item Report\n" + getItemInfo() + "\nDisposal Method: " + disposalMethod;
    }
}

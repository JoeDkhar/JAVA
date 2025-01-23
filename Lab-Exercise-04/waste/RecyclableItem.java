
package waste;

public class RecyclableItem extends DisposalItem {
    private final String recyclingMethod;

    public RecyclableItem(String itemId, String itemType, int weight, String recyclingMethod) {
        super(itemId, itemType, weight);
        this.recyclingMethod = recyclingMethod;
    }

    @Override
    public void process() {
        System.out.println("Processing recyclable item: " + itemType + " with ID: " + itemId);
        System.out.println("Using recycling method: " + recyclingMethod);
    }

    @Override
    public String generateReport() {
        return "Recyclable Item Report\n" + getItemInfo() + "\nRecycling Method: " + recyclingMethod;
    }
}

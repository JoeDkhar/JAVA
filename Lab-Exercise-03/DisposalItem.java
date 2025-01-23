public abstract class DisposalItem {
    protected final DisposalItemRecord record;

    public DisposalItem(String itemId, String itemType, int weight) {
        this.record = new DisposalItemRecord(itemId, itemType, weight);
    }

    public abstract void process();

    public abstract String generateReport(); // Abstract method for generating a report

    public final String getItemInfo() {
        return "ID: " + record.itemId() + ", Type: " + record.itemType() + ", Weight: " + record.weight() + "kg";
    }
}
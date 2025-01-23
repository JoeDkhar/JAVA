
package waste;

import utilities.WasteProcessable;

public abstract class DisposalItem implements WasteProcessable {
    protected final String itemId;
    protected final String itemType;
    protected final int weight;

    public DisposalItem(String itemId, String itemType, int weight) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.weight = weight;
    }

    public String getItemInfo() {
        return "ID: " + itemId + ", Type: " + itemType + ", Weight: " + weight + "kg";
    }
}

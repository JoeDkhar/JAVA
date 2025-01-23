package management;

import waste.DisposalItem;
import waste.RecyclableItem;
import waste.HazardousItem;

public class WasteFactory {
    public DisposalItem createWaste(String type, String itemId, String itemType, int weight, String method) {
        return switch (type.toLowerCase()) {
            case "recyclable" -> new RecyclableItem(itemId, itemType, weight, method);
            case "hazardous" -> new HazardousItem(itemId, itemType, weight, method);
            default -> throw new IllegalArgumentException("Unknown waste type: " + type);
        };
    }
}

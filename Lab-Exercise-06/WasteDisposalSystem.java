import java.util.ArrayList;
import java.util.List;

public class WasteDisposalSystem {
    private List<WasteItem<?>> wasteItems;

    public WasteDisposalSystem() {
        this.wasteItems = new ArrayList<>();
    }

    public void addWasteItem(WasteItem<?> wasteItem) {
        wasteItems.add(wasteItem);
    }

    public void displayWasteItems() {
        for (WasteItem<?> wasteItem : wasteItems) {
            System.out.println(wasteItem);
        }
    }
}
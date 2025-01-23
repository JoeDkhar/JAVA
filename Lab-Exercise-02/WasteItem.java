public abstract class WasteItem {
    protected String wasteType;
    protected int quantity;
    protected String disposalMethod;
    protected String region;

    public WasteItem(String wasteType, int quantity, String disposalMethod, String region) {
        this.wasteType = wasteType;
        this.quantity = quantity;
        this.disposalMethod = disposalMethod;
        this.region = region;
    }

    public abstract void displayWasteDetails();

    public String getWasteType() {
        return wasteType;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public String getRegion() {
        return region;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod;
    }
}

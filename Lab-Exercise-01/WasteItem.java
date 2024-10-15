class WasteItem {

    private String wasteType;
    private double quantity;
    private String disposalMethod;
    private String region;


    private static double totalWasteCollected = 0;


    public WasteItem() {
        this.wasteType = "Unknown";
        this.quantity = 0;
        this.disposalMethod = "Unknown";
        this.region = "Unknown";
    }


    public WasteItem(String wasteType, double quantity, String disposalMethod, String region) {
        this.wasteType = wasteType;
        this.quantity = quantity;
        this.disposalMethod = disposalMethod;
        this.region = region;
        totalWasteCollected += quantity;
    }


    public String getWasteType() {
        return wasteType;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public String getRegion() {
        return region;
    }


    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        totalWasteCollected += quantity;
    }

    public static void displayTotalWasteCollected() {
        System.out.println("Total Waste Collected: " + totalWasteCollected + " kg");
    }


    public void displayWasteDetails() {
        System.out.println("Waste Type: " + this.wasteType);
        System.out.println("Quantity: " + this.quantity + " kg");
        System.out.println("Disposal Method: " + this.disposalMethod);
        System.out.println("Region: " + this.region);
    }
}

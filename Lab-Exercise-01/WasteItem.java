class WasteItem {

    private String wasteType;
    private double quantity;
    private String disposalMethod;
    private String region;


    private static double totalWasteCollected = 0;
    private static double totalPlasticWaste = 0;
    private static double totalOrganicWaste = 0;
    private static double totalHazardousWaste = 0;


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
        updateWasteTotals();
    }


    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
        updateWasteTotals();
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        updateWasteTotals();
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public static void displayTotalWasteCollected() {
        System.out.println("Total Waste Collected: " + totalWasteCollected + " kg");
    }

    public static void displayCategoryTotals() {
        System.out.println("Plastic Waste: " + totalPlasticWaste + " kg");
        System.out.println("Organic Waste: " + totalOrganicWaste + " kg");
        System.out.println("Hazardous Waste: " + totalHazardousWaste + " kg");
    }


    private void updateWasteTotals() {
        totalWasteCollected += this.quantity;
        if (this.wasteType.equalsIgnoreCase("plastic")) {
            totalPlasticWaste += this.quantity;
        } else if (this.wasteType.equalsIgnoreCase("organic")) {
            totalOrganicWaste += this.quantity;
        } else if (this.wasteType.equalsIgnoreCase("hazardous")) {
            totalHazardousWaste += this.quantity;
        }
    }


    public void displayWasteDetails() {
        System.out.println("Waste Type: " + this.wasteType);
        System.out.println("Quantity: " + this.quantity + " kg");
        System.out.println("Disposal Method: " + this.disposalMethod);
        System.out.println("Region: " + this.region);
    }


    public void updateDisposalMethod(String newDisposalMethod) {
        this.disposalMethod = newDisposalMethod;
        System.out.println("Disposal method updated to: " + this.disposalMethod);
    }
}

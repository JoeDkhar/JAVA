class WasteItem {
    private String wasteType;
    private int quantity;
    private StringBuffer disposalMethod;
    private String region;

    private static int totalWasteCollected = 0;
    private static int recyclingWaste = 0;
    private static int compostingWaste = 0;
    private static int incinerationWaste = 0;

    public WasteItem(String wasteType, int quantity, String disposalMethod, String region) {
        this.wasteType = wasteType;
        this.quantity = quantity;
        this.disposalMethod = new StringBuffer(disposalMethod);
        this.region = region;

        totalWasteCollected += quantity;
        updateCategoryTotals(disposalMethod, quantity);
    }

    public void displayWasteDetails() {
        System.out.println("Waste Type: " + wasteType);
        System.out.println("Quantity: " + quantity + " kg");
        System.out.println("Disposal Method: " + disposalMethod);
        System.out.println("Region: " + region);
    }

    public static void displayTotalWasteCollected() {
        System.out.println("Total Waste Collected: " + totalWasteCollected + " kg");
    }

    public static void displayCategoryTotals() {
        System.out.println("Recycling Waste: " + recyclingWaste + " kg");
        System.out.println("Composting Waste: " + compostingWaste + " kg");
        System.out.println("Incineration Waste: " + incinerationWaste + " kg");
    }

    private static void updateCategoryTotals(String disposalMethod, int quantity) {
        switch (disposalMethod.toLowerCase()) {
            case "recycling":
                recyclingWaste += quantity;
                break;
            case "composting":
                compostingWaste += quantity;
                break;
            case "incineration":
                incinerationWaste += quantity;
                break;
        }
    }

    public void setQuantity(int quantity) {
        totalWasteCollected -= this.quantity;
        totalWasteCollected += quantity;
        this.quantity = quantity;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public String getRegion() {
        return this.region;
    }

    public void updateDisposalMethod(String newMethod) {
        this.disposalMethod.replace(0, this.disposalMethod.length(), newMethod);
    }

    public void updateDisposalMethod(String methodPrefix, String methodSuffix) {
        this.disposalMethod.replace(0, this.disposalMethod.length(), methodPrefix + " " + methodSuffix);
    }
}

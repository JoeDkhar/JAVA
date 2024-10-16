class WasteItem {
    private String wasteType;
    private int quantity;
    private StringBuilder disposalMethod;
    private String region;

    private static int totalWasteCollected = 0;
    private static int recyclingWaste = 0;
    private static int compostingWaste = 0;
    private static int incinerationWaste = 0;

    public WasteItem(String wasteType, int quantity, String region) {
        this(wasteType, quantity, "Standard Disposal", region);
    }

    public WasteItem(String wasteType, int quantity, String disposalMethod, String region) {
        this.wasteType = wasteType;
        this.quantity = quantity;
        this.disposalMethod = new StringBuilder(disposalMethod);
        this.region = region;

        totalWasteCollected += quantity;
        updateCategoryTotals(disposalMethod, quantity);
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        totalWasteCollected -= this.quantity;
        totalWasteCollected += quantity;
        this.quantity = quantity;
    }

    public String getDisposalMethod() {
        return disposalMethod.toString();
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = new StringBuilder(disposalMethod);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void updateDisposalMethod(String newMethod) {
        this.disposalMethod = new StringBuilder(newMethod);
    }

    public void updateDisposalMethod(String methodPrefix, String methodSuffix) {
        this.disposalMethod = new StringBuilder(methodPrefix + " " + methodSuffix);
    }

    public WasteItem addQuantity(int additionalQuantity) {
        this.setQuantity(this.quantity + additionalQuantity);
        return this;
    }

    public WasteItem updateRegion(String newRegion) {
        this.setRegion(newRegion);
        return this;
    }

    public static void displayTotalWasteCollected() {
        System.out.println("Total Waste Collected: " + totalWasteCollected + " kg");
    }

    public static void displayCategoryTotals() {
        System.out.println("Recycling Waste: " + recyclingWaste + " kg");
        System.out.println("Composting Waste: " + compostingWaste + " kg");
        System.out.println("Incineration Waste: " + incinerationWaste + " kg");
    }

    public static int getTotalWasteCollected() {
        return totalWasteCollected;
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

    public void displayWasteDetails() {
        System.out.println("Waste Type: " + wasteType);
        System.out.println("Quantity: " + quantity + " kg");
        System.out.println("Disposal Method: " + disposalMethod);
        System.out.println("Region: " + region);
    }
}


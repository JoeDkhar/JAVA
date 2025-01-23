public class HazardousWaste extends WasteItem {
    public HazardousWaste(int quantity, String region) {
        super("Hazardous", quantity, "Incineration", region);
    }

    @Override
    public void displayWasteDetails() {
        System.out.println("Waste Type: " + wasteType + " | Quantity: " + quantity + " kg | Disposal Method: " + disposalMethod + " | Region: " + region);
    }
}

public class RecyclingWaste extends WasteItem 
{
    public RecyclingWaste(int quantity, String region) {
        super("Recycling", quantity, "Recycling Facility", region);
    }

    @Override
    public void displayWasteDetails() {
        System.out.println("Waste Type: " + wasteType + " | Quantity: " + quantity + " kg | Disposal Method: " + disposalMethod + " | Region: " + region);
    }
}

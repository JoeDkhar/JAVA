public class WasteFactory {
    public WasteItem createWaste(String type, int quantity, String region) {
        switch (type.toLowerCase()) {
            case "recycling":
                return new RecyclingWaste(quantity, region);
            case "hazardous":
                return new HazardousWaste(quantity, region);
            
            default:
                throw new IllegalArgumentException("Invalid waste type: " + type);
        }
    }
}

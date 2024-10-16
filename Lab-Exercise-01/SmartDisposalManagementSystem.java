public class SmartDisposalManagementSystem {
    public static void main(String[] args) {
        System.out.println("Initial Waste Items Creation:");
        WasteItem plastic = new WasteItem("Plastic", 500, "Recycling", "North Region");
        WasteItem organic = new WasteItem("Organic", 300, "Composting", "South Region");
        WasteItem metal = new WasteItem("Metal", 250, "North Region");
        WasteItem glass = new WasteItem("Glass", 150, "Recycling", "Central Region");
        WasteItem paper = new WasteItem("Paper", 180, "Recycling", "East Region");
        WasteItem hazardous = new WasteItem("Hazardous", 120, "Incineration", "West Region");


        System.out.println("\nDisplaying Individual Waste Details:");
        System.out.println("\nPlastic Waste Details:");
        plastic.displayWasteDetails();

        System.out.println("\nOrganic Waste Details:");
        organic.displayWasteDetails();

        System.out.println("\nMetal Waste Details:");
        metal.displayWasteDetails();

        System.out.println("\nGlass Waste Details:");
        glass.displayWasteDetails();

        System.out.println("\nPaper Waste Details:");
        paper.displayWasteDetails();

        System.out.println("\nHazardous Waste Details:");
        hazardous.displayWasteDetails();

        System.out.println("\nDisplaying Total Statistics:");
        WasteItem.displayTotalWasteCollected();
        WasteItem.displayCategoryTotals();
    }
}
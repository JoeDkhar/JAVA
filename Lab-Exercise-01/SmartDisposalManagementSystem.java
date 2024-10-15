public class SmartDisposalManagementSystem
{
    public static void main(String[] args)
    {

        WasteItem waste1 = new WasteItem("Plastic", 500, "Recycling", "North Region");
        WasteItem waste2 = new WasteItem("Organic", 300, "Composting", "South Region");
        WasteItem waste3 = new WasteItem("Hazardous", 150, "Incineration", "East Region");
        WasteItem waste4 = new WasteItem("Metal", 200, "Recycling", "West Region");
        WasteItem waste5 = new WasteItem("Glass", 100, "Recycling", "Central Region");


        System.out.println("Total Waste Data:");
        WasteItem.displayTotalWasteCollected();
        WasteItem.displayCategoryTotals();


        System.out.println("\nAll Waste Item Details:");
        waste1.displayWasteDetails();
        waste2.displayWasteDetails();
        waste3.displayWasteDetails();
        waste4.displayWasteDetails();
        waste5.displayWasteDetails();


        System.out.println("\nUpdating waste item details:");
        waste1.setQuantity(600);
        waste1.setWasteType("Recyclable Plastic");


        System.out.println("\nUpdated Waste Item 1 Details:");
        waste1.displayWasteDetails();


        System.out.println("\nQuerying by region (South Region):");
        if (waste2.getRegion().equals("South Region")) {
            waste2.displayWasteDetails();
        }


        System.out.println("\nUpdating disposal method for waste3:");
        waste3.updateDisposalMethod("Advanced Incineration");


        System.out.println("\nFinal Waste Data:");
        WasteItem.displayTotalWasteCollected();
        WasteItem.displayCategoryTotals();
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmartDisposalManagementSystem {
    private static WasteFactory factory = new WasteFactory();
    private static Scanner scanner = new Scanner(System.in);
    private static List<WasteItem> wasteItems = new ArrayList<>();
    private static final double PRICE_PER_KG = 5.0;  

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getChoice();
            handleChoice(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("Welcome to Smart Disposal Management System");
        System.out.println("1. Create Waste Item");
        System.out.println("2. Update Waste Item Quantity");
        System.out.println("3. Display Waste Details");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice (1-4): ");
    }

    private static int getChoice() {
        return scanner.nextInt();
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createWasteItem();
                break;
            case 2:
                updateWasteItemQuantity();
                break;
            case 3:
                displayWasteDetails();
                break;
            case 4:
                System.out.println("Exiting the system... Goodbye!");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }

    private static void createWasteItem() {
        System.out.print("Enter waste type (e.g., recycling, hazardous): ");
        scanner.nextLine(); // consume newline
        String type = scanner.nextLine();

        System.out.print("Enter quantity (in kg): ");
        int quantity = scanner.nextInt();

        System.out.print("Enter region: ");
        scanner.nextLine(); // consume newline
        String region = scanner.nextLine();

        try {
            WasteItem waste = factory.createWaste(type, quantity, region);
            wasteItems.add(waste);
            double price = calculatePrice(quantity);
            System.out.println("Waste item created successfully. Total Price: $" + price);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateWasteItemQuantity() {
        if (wasteItems.isEmpty()) {
            System.out.println("No waste items available to update.");
            return;
        }

        System.out.print("Enter the waste item number to update (1 to " + wasteItems.size() + "): ");
        int index = scanner.nextInt();
        if (index < 1 || index > wasteItems.size()) {
            System.out.println("Invalid item number. Please try again.");
            return;
        }

        System.out.print("Enter new quantity (in kg): ");
        int newQuantity = scanner.nextInt();
        WasteItem waste = wasteItems.get(index - 1);  // Adjust index to match user input
        waste.setQuantity(newQuantity);
        double newPrice = calculatePrice(newQuantity);
        System.out.println("Waste item quantity updated successfully. New Price: $" + newPrice);
    }

    private static void displayWasteDetails() {
        if (wasteItems.isEmpty()) {
            System.out.println("No waste items to display.");
            return;
        }

        for (int i = 0; i < wasteItems.size(); i++) {
            System.out.println("Waste Item " + (i + 1) + ":");
            wasteItems.get(i).displayWasteDetails();
            double price = calculatePrice(wasteItems.get(i).getQuantity());
            System.out.println("Price: $" + price);
        }
    }

    private static double calculatePrice(int quantity) {
        return quantity * PRICE_PER_KG;
    }
}

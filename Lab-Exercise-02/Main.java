import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmartDisposalManagementSystem {
    private static WasteFactory factory = new WasteFactory();
    private static Scanner scanner = new Scanner(System.in);
    private static List<WasteItem> wasteItems = new ArrayList<>();

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
        System.out.print("Enter your choice: ");
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
                System.out.println("Exiting...");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
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
            System.out.println("Waste item created successfully.");
        } catch (InvalidWasteTypeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateWasteItemQuantity() {
        System.out.print("Enter the index of the waste item to update: ");
        int index = scanner.nextInt();
        if (index < 0 || index >= wasteItems.size()) {
            System.out.println("Invalid index. Please try again.");
            return;
        }

        System.out.print("Enter new quantity (in kg): ");
        int newQuantity = scanner.nextInt();
        WasteItem waste = wasteItems.get(index);
        waste.setQuantity(newQuantity);
        System.out.println("Waste item quantity updated successfully.");
    }

    private static void displayWasteDetails() {
        if (wasteItems.isEmpty()) {
            System.out.println("No waste items to display.");
            return;
        }

        for (int i = 0; i < wasteItems.size(); i++) {
            System.out.println("Waste Item " + (i + 1) + ":");
            wasteItems.get(i).displayWasteDetails();
        }
    }
}
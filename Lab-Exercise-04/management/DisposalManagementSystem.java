package management;

import waste.DisposalItem;
import java.util.Scanner;

public class DisposalManagementSystem {
    private static WasteFactory factory = new WasteFactory();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Smart Disposal Management System");
        
        System.out.println("Choose the type of disposal:");
        System.out.println("1 - Recyclable");
        System.out.println("2 - Hazardous");
        System.out.print("Enter disposal type (1 or 2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter Item Type: ");
        String itemType = scanner.nextLine();
        System.out.print("Enter Weight (kg): ");
        int weight = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        String method;
        DisposalItem item = null;
        
        if (choice == 1) {
            System.out.print("Enter Recycling Method: ");
            method = scanner.nextLine();
            item = factory.createWaste("recyclable", itemId, itemType, weight, method);
        } else if (choice == 2) {
            System.out.print("Enter Disposal Method: ");
            method = scanner.nextLine();
            item = factory.createWaste("hazardous", itemId, itemType, weight, method);
        } else {
            System.out.println("Invalid choice.");
            return;
        }
        
        System.out.println("\nProcessing item...");
        item.process();
        System.out.println("\nGenerated Report:\n" + item.generateReport());
    }
}

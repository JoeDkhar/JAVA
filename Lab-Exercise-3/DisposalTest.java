import java.util.Scanner;

public class DisposalTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Smart Disposal Management System");
        System.out.println("Choose the type of disposal:");
        System.out.println("1 - Recyclable (e.g., Plastic, Metal, Glass)");
        System.out.println("2 - Hazardous (e.g., Battery, Chemicals, Medical Waste)");
        System.out.print("Enter disposal type (1 or 2): ");
        int disposalType = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        DisposalItem item = null;

        if (disposalType == 1) {
            System.out.print("Enter Recyclable Item ID (e.g., R001): ");
            String itemId = scanner.nextLine();
            System.out.print("Enter recyclable material type (e.g., Plastic, Metal, Glass): ");
            String itemType = scanner.nextLine();
            System.out.print("Enter weight in kilograms (e.g., 5): ");
            int weight = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            System.out.print("Enter recycling method (e.g., Thermal Recycling, Chemical Recycling): ");
            String recyclingMethod = scanner.nextLine();

            item = new RecyclableItem(itemId, itemType, weight, recyclingMethod);

        } else if (disposalType == 2) {
            System.out.print("Enter Hazardous Item ID (e.g., H001): ");
            String itemId = scanner.nextLine();
            System.out.print("Enter hazardous material type (e.g., Battery, Chemicals, Medical Waste): ");
            String itemType = scanner.nextLine();
            System.out.print("Enter weight in kilograms (e.g., 3): ");
            int weight = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            System.out.print("Enter disposal method (e.g., Incineration, Secure Landfill): ");
            String disposalMethod = scanner.nextLine();

            item = new HazardousItem(itemId, itemType, weight, disposalMethod);
        } else {
            System.out.println("Invalid choice. Please restart the program and select a valid option.");
            scanner.close();
            return;
        }

        // Processing the item and generating a report
        System.out.println("\nProcessing item...");
        item.process();
        System.out.println("\nGenerated Report:");
        System.out.println(item.generateReport());

        scanner.close();
    }
}
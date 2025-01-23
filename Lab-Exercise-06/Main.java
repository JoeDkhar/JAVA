import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WasteDisposalSystem system = new WasteDisposalSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter waste type (ORGANIC, PLASTIC, METAL, PAPER, GLASS) or 'exit' to quit:");
            String typeInput = scanner.nextLine().toUpperCase();

            if (typeInput.equals("EXIT")) {
                break;
            }

            WasteType type;
            try {
                type = WasteType.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid waste type. Please try again.");
                continue;
            }

            System.out.println("Enter waste description:");
            String description = scanner.nextLine();

            WasteItem<String> wasteItem = new WasteItem<>(description, type);
            system.addWasteItem(wasteItem);
        }

        System.out.println("Waste items in the system:");
        system.displayWasteItems();
    }
}
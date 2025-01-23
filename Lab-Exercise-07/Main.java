import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WasteDisposalSystem system = new WasteDisposalSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of waste items:");
        int numberOfItems = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numberOfItems; i++) {
            System.out.println("Enter waste type (ORGANIC, PLASTIC, METAL, GLASS, PAPER, OTHER):");
            String typeInput = scanner.nextLine();
            WasteType type = WasteType.valueOf(typeInput.toUpperCase());
            System.out.println("Enter waste weight:");
            double weight = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            system.addWaste(new Waste(type, weight));
        }

        system.processWaste();
        scanner.close();
    }
}


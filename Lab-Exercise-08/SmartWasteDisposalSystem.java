import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SmartWasteDisposalSystem {
    // Collections
    private List<WasteItem> wasteList = new ArrayList<>();
    private Set<WasteItem> wasteSet = new HashSet<>();
    private Map<String, WasteItem> wasteMap = new HashMap<>();
    
    // Regex patterns
    private static final Pattern ID_PATTERN = Pattern.compile("WD-\\d{4}-[A-Z]{2}");
    private static final Pattern TYPE_PATTERN = Pattern.compile("^(Organic|Plastic|Metal|Electronic|Paper)$");
    private static final Pattern WEIGHT_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?$");

    private static Scanner scanner = new Scanner(System.in);

    // Validation methods using regex
    private boolean validateId(String id) {
        Matcher matcher = ID_PATTERN.matcher(id);
        return matcher.matches();
    }

    private boolean validateType(String type) {
        Matcher matcher = TYPE_PATTERN.matcher(type);
        return matcher.matches();
    }

    private boolean validateWeight(String weight) {
        Matcher matcher = WEIGHT_PATTERN.matcher(weight);
        return matcher.matches();
    }

    public void addWasteItem() {
        try {
            System.out.println("\n--- Add Waste Item ---");
            
            // ID validation
            System.out.print("Enter Waste ID (WD-NNNN-CC): ");
            String id = scanner.nextLine();
            if (!validateId(id)) {
                System.out.println("Invalid ID format! Must be WD-NNNN-CC where N is digit and C is uppercase letter");
                return;
            }

            // Type validation
            System.out.print("Enter Waste Type (Organic/Plastic/Metal/Electronic/Paper): ");
            String type = scanner.nextLine();
            if (!validateType(type)) {
                System.out.println("Invalid waste type! Must be one of: Organic, Plastic, Metal, Electronic, Paper");
                return;
            }

            // Weight validation
            System.out.print("Enter Weight: ");
            String weightStr = scanner.nextLine();
            if (!validateWeight(weightStr)) {
                System.out.println("Invalid weight format! Must be a positive number");
                return;
            }
            double weight = Double.parseDouble(weightStr);

            // Create and add waste item
            WasteItem item = new WasteItem(id, type, weight, false);
            wasteList.add(item);
            wasteSet.add(item);
            wasteMap.put(id, item);
            System.out.println("Waste item added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding waste item: " + e.getMessage());
        }
    }

    
    public void displayMenu() {
        System.out.println("\n--- Smart Waste Disposal System ---");
        System.out.println("1. Add Waste Item");
        System.out.println("2. List Collection Operations");
        System.out.println("3. Set Collection Operations");
        System.out.println("4. Map Collection Operations");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Main 
    public static void main(String[] args) {
        SmartWasteDisposalSystem system = new SmartWasteDisposalSystem();

        while (true) {
            system.displayMenu();
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    system.addWasteItem();
                    break;
                case 2:
                    CollectionOperationsUtil.performListOperations(system.wasteList);
                    break;
                case 3:
                    Set<WasteItem> sampleSet = new HashSet<>();
                    sampleSet.add(new WasteItem("WD-0001-OR", "Organic", 5.0, true));
                    CollectionOperationsUtil.performSetOperations(system.wasteSet, sampleSet);
                    break;
                case 4:
                    CollectionOperationsUtil.performMapOperations(system.wasteMap);
                    break;
                case 5:
                    System.out.println("Exiting Smart Waste Disposal System...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class WasteItem implements Comparable<WasteItem> {
    private String id;
    private String type;
    private double weight;
    private boolean recyclable;

    public WasteItem(String id, String type, double weight, boolean recyclable) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.recyclable = recyclable;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public double getWeight() { return weight; }
    public boolean isRecyclable() { return recyclable; }

    @Override
    public int compareTo(WasteItem other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return String.format("WasteItem{id='%s', type='%s', weight=%.2f, recyclable=%b}", 
                           id, type, weight, recyclable);
    }
}

class CollectionOperationsUtil {
    // List Operations
    public static <T> void performListOperations(List<T> list) {
        System.out.println("\n--- List Collection Operations ---");
        System.out.println("Original List: " + list);

        // Reverse the list
        Collections.reverse(list);
        System.out.println("Reversed List: " + list);

        // Find frequency of an element (assuming first element)
        if (!list.isEmpty()) {
            T firstElement = list.get(0);
            int frequency = Collections.frequency(list, firstElement);
            System.out.println("Frequency of " + firstElement + ": " + frequency);
        }

        // Replace all occurrences of an element
        if (!list.isEmpty()) {
            T oldVal = list.get(0);
            System.out.print("Enter new value to replace " + oldVal + ": ");
            Scanner scanner = new Scanner(System.in);
            T newVal = (T) scanner.nextLine();
            Collections.replaceAll(list, oldVal, newVal);
            System.out.println("List after replaceAll: " + list);
        }
    }

    // Set Operations
    public static <T> void performSetOperations(Set<T> set1, Set<T> set2) {
        System.out.println("\n--- Set Collection Operations ---");
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        // Check if sets are disjoint
        boolean disjoint = Collections.disjoint(set1, set2);
        System.out.println("Sets are disjoint: " + disjoint);
    }

    // Map Operations
    public static <K, V> void performMapOperations(Map<K, V> map) {
        System.out.println("\n--- Map Collection Operations ---");
        System.out.println("Map Contents:");
        map.forEach((k, v) -> System.out.println("  " + k + " = " + v));

        System.out.println("Key Set:");
        map.keySet().forEach(k -> System.out.println("  " + k));

        System.out.println("Values:");
        map.values().forEach(v -> System.out.println("  " + v));
    }
}

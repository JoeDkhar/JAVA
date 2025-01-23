import java.util.*;

class CollectionOperationsUtil {
    
    public static void performListOperations(List<WasteItem> list) {
        // Header
        System.out.println("\n--- List Collection Operations ---");
        System.out.println("ID              | Type       | Weight | Recyclable");
        System.out.println("-" .repeat(50));
        
        // Original List
        System.out.println("Original List:");
        list.forEach(System.out::println);
        
        // Reverse List
        List<WasteItem> reversedList = new ArrayList<>(list);
        Collections.reverse(reversedList);
        System.out.println("\nReversed List:");
        reversedList.forEach(System.out::println);
        
        // Frequency of first item
        if (!list.isEmpty()) {
            WasteItem firstItem = list.get(0);
            int frequency = Collections.frequency(list, firstItem);
            System.out.printf("\nFrequency of first item (%s): %d\n", 
                firstItem.getId(), frequency);
        }
    }

    // Set Operations 
    public static void performSetOperations(Set<WasteItem> set1, Set<WasteItem> set2) {
        // Header
        System.out.println("\n--- Set Collection Operations ---");
        System.out.println("ID              | Type       | Weight | Recyclable");
        System.out.println("-" .repeat(50));
        
        // Set 1 Display
        System.out.println("Set 1:");
        set1.forEach(System.out::println);
        
        // Set 2 Display
        System.out.println("\nSet 2:");
        set2.forEach(System.out::println);
        
        // Disjoint Check
        boolean isDisjoint = Collections.disjoint(set1, set2);
        System.out.printf("\nSets are disjoint: %b\n", isDisjoint);
    }

    // Map Operations 
    public static void performMapOperations(Map<String, WasteItem> map) {
        // Header
        System.out.println("\n--- Map Collection Operations ---");
        System.out.println("Key             | ID              | Type       | Weight | Recyclable");
        System.out.println("-" .repeat(65));
        
        // Map Contents
        map.forEach((key, value) -> 
            System.out.printf("%-15s | %s\n", key, value)
        );
        
        // Key Set
        System.out.println("\nKey Set:");
        System.out.println(map.keySet());
        
        // Values
        System.out.println("\nValues:");
        map.values().forEach(System.out::println);
    }
}


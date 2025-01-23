import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WasteDisposalSystem {
    private List<Waste> wasteList = new ArrayList<>();

    public void addWaste(Waste waste) {
        wasteList.add(waste);
    }

    public void processWaste() {
        // Add a new waste item using Supplier
        addNewWasteItem(() -> new Waste(WasteType.PLASTIC, 2.5));

        // Define predicates, functions, and consumers
        Predicate<Waste> isRecyclable = waste -> waste.getType() == WasteType.PLASTIC;
        Predicate<Waste> isHeavy = waste -> waste.getWeight() > 2.0;
        Function<Waste, Double> disposalCost = waste -> waste.getWeight() * 1.5;
        Function<Waste, String> categorizeWaste = waste -> isRecyclable.test(waste) ? "Recyclable" : "Non-Recyclable";
        Consumer<Waste> printWaste = waste -> System.out.println("Processing waste: " + waste);
        Consumer<Waste> printCategory = waste -> System.out.println("Category: " + categorizeWaste.apply(waste));

        // Calculate and print total weight of recyclable waste
        double totalRecyclableWeight = calculateTotalWeight(isRecyclable);
        System.out.println("Total weight of recyclable waste: " + totalRecyclableWeight + " kg");

        // Process each waste item
        wasteList.stream()
                .filter(isRecyclable.and(isHeavy))
                .forEach(waste -> {
                    printWaste.accept(waste);
                    printCategory.accept(waste);
                    double cost = disposalCost.apply(waste);
                    System.out.println("Disposal cost: $" + cost);
                });
    }

    private void addNewWasteItem(Supplier<Waste> wasteSupplier) {
        Waste newWaste = wasteSupplier.get();
        addWaste(newWaste);
    }

    private double calculateTotalWeight(Predicate<Waste> filter) {
        return wasteList.stream()
                .filter(filter)
                .mapToDouble(Waste::getWeight)
                .sum();
    }
}
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Class 1: Enum for waste types
enum WasteType {
    ORGANIC, RECYCLABLE, PLASTIC, ELECTRONIC, HAZARDOUS
}

// Class 2: Abstract base class
abstract class WasteContainer {
    protected String id;
    protected double currentCapacity;
    protected double maxCapacity;
    protected WasteType wasteType;

    public WasteContainer(String id, double maxCapacity, WasteType wasteType) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.wasteType = wasteType;
        this.currentCapacity = 0;
    }

    public abstract boolean addWaste(double amount);
    public abstract void displayStatus();

    public String getId() {
        return this.id;
    }
}

// Class 3: SmartBin implementation
class SmartBin extends WasteContainer implements Runnable {
    private boolean isFull;
    private Lock lock;
    private WasteManagementSystem managementSystem;
    private volatile boolean suspended = false;
    private final Object suspendLock = new Object();
    private volatile boolean running = true;
    private boolean underMaintenance;
    private long lastMaintenanceTime;

    public SmartBin(String id, double maxCapacity, WasteType wasteType, WasteManagementSystem managementSystem) {
        super(id, maxCapacity, wasteType);
        this.isFull = false;
        this.lock = new ReentrantLock();
        this.managementSystem = managementSystem;
        this.underMaintenance = false;
        this.lastMaintenanceTime = System.currentTimeMillis();
    }

    // Synchronized method example
    @Override
    public synchronized boolean addWaste(double amount) {
        if (currentCapacity + amount <= maxCapacity) {
            currentCapacity += amount;
            System.out.println("Added " + amount + " kg to Bin " + id + " (Synchronized)");
            return true;
        }
        return false;
    }

    // Non-synchronized method example
    public void checkStatus() {
        double fillPercentage = (currentCapacity / maxCapacity) * 100;
        System.out.println("Bin " + id + " fill level: " + fillPercentage + "% (Non-synchronized)");
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Wait mechanism
                synchronized (suspendLock) {
                    while (suspended) {
                        System.out.println("Bin " + id + " suspended...");
                        suspendLock.wait(); // Thread waits here when suspended
                    }
                }

                // Sleep mechanism
                Thread.sleep(2000);
                
                // Monitor status
                monitorBinStatus();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private synchronized void monitorBinStatus() {
        if (currentCapacity >= maxCapacity * 0.8) {
            System.out.println("WARNING: Bin " + id + " is near capacity!");
        }
    }

    public void suspend() {
        suspended = true;
    }

    public void resume() {
        synchronized (suspendLock) {
            suspended = false;
            suspendLock.notifyAll();
        }
    }

    @Override
    public void displayStatus() {
        double fillPercentage = (currentCapacity / maxCapacity) * 100;
        System.out.println("\nBin Status:");
        System.out.println("ID: " + id);
        System.out.println("Type: " + wasteType);
        System.out.println("Fill Level: " + String.format("%.1f", fillPercentage) + "%");
        System.out.println("Thread Status: " + (suspended ? "Suspended" : "Running"));
    }

    public synchronized boolean emptyBin() {
        if (!underMaintenance) {
            System.out.println("Starting to empty bin " + id);
            currentCapacity = 0;
            isFull = false;
            System.out.println("Bin " + id + " has been emptied");
            return true;
        }
        return false;
    }

    public synchronized boolean startMaintenance() {
        if (!underMaintenance) {
            underMaintenance = true;
            suspend(); // Suspend thread during maintenance
            System.out.println("Maintenance started for bin " + id);
            lastMaintenanceTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public synchronized boolean endMaintenance() {
        if (underMaintenance) {
            underMaintenance = false;
            resume(); // Resume thread after maintenance
            System.out.println("Maintenance completed for bin " + id);
            return true;
        }
        return false;
    }
}

// Class 4: Thread Manager
class WasteManagementSystem {
    private static final int MIN_THREADS = 5;
    private List<SmartBin> bins = new ArrayList<>();
    private List<Thread> binThreads = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void createBins() {
        // Create and start 5 threads
        for (int i = 0; i < MIN_THREADS; i++) {
            SmartBin bin = new SmartBin(
                "BIN-" + i, 
                100.0, 
                WasteType.values()[i % WasteType.values().length], 
                this
            );
            Thread thread = new Thread(bin);
            bins.add(bin);
            binThreads.add(thread);
            thread.start();
            System.out.println("Created and started Bin " + bin.getId());
        }
    }

    public void addWasteInteractive() {
        if (bins.isEmpty()) {
            System.out.println("No bins available. Please create bins first.");
            return;
        }

        try {
            // Display available bins
            System.out.println("\nAvailable Bins:");
            for (int i = 0; i < bins.size(); i++) {
                SmartBin bin = bins.get(i);
                System.out.println((i + 1) + ". " + bin.getId() + " (" + bin.wasteType + ")");
            }

            // Get bin selection
            System.out.print("Select bin number: ");
            int binIndex = scanner.nextInt() - 1;
            
            if (binIndex < 0 || binIndex >= bins.size()) {
                System.out.println("Invalid bin selection!");
                return;
            }

            // Get waste amount
            System.out.print("Enter waste amount (kg): ");
            double amount = scanner.nextDouble();
            
            if (amount <= 0) {
                System.out.println("Invalid amount!");
                return;
            }

            // Add waste to selected bin
            SmartBin selectedBin = bins.get(binIndex);
            if (selectedBin.addWaste(amount)) {
                System.out.println("Waste added successfully!");
            } else {
                System.out.println("Bin is full!");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter valid numbers.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    public List<SmartBin> getBins() {
        return bins;
    }

    public void suspendAllBins() {
        bins.forEach(SmartBin::suspend);
    }

    public void resumeAllBins() {
        bins.forEach(SmartBin::resume);
    }

    public void shutdown() {
        bins.forEach(bin -> {
            try {
                bin.suspend();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public void performBinMaintenance() {
        if (bins.isEmpty()) {
            System.out.println("No bins available.");
            return;
        }

        try {
            System.out.println("\nAvailable Bins for Maintenance:");
            for (int i = 0; i < bins.size(); i++) {
                SmartBin bin = bins.get(i);
                System.out.println((i + 1) + ". " + bin.getId());
            }

            System.out.print("Select bin number for maintenance: ");
            int binIndex = scanner.nextInt() - 1;
            
            if (binIndex < 0 || binIndex >= bins.size()) {
                System.out.println("Invalid bin selection!");
                return;
            }

            SmartBin selectedBin = bins.get(binIndex);
            System.out.println("\nMaintenance Options:");
            System.out.println("1. Empty Bin");
            System.out.println("2. Start Maintenance");
            System.out.println("3. End Maintenance");
            
            System.out.print("Select option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    if (selectedBin.emptyBin()) {
                        System.out.println("Bin emptied successfully!");
                    } else {
                        System.out.println("Cannot empty bin - maintenance in progress");
                    }
                    break;
                case 2:
                    if (selectedBin.startMaintenance()) {
                        System.out.println("Maintenance started");
                    } else {
                        System.out.println("Maintenance already in progress");
                    }
                    break;
                case 3:
                    if (selectedBin.endMaintenance()) {
                        System.out.println("Maintenance completed");
                    } else {
                        System.out.println("No maintenance in progress");
                    }
                    break;
                default:
                    System.out.println("Invalid option!");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }
}

public class InteractiveSmartWasteDisposalSystem {
    public static void main(String[] args) {
        WasteManagementSystem managementSystem = new WasteManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Smart Waste Disposal System ---");
            System.out.println("1. Create Bins");
            System.out.println("2. Add Waste");
            System.out.println("3. View Bin Status");
            System.out.println("4. Perform Bin Maintenance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        managementSystem.createBins();
                        break;
                    case 2:
                        managementSystem.addWasteInteractive();
                        break;
                    case 3:
                        System.out.println("\n--- Bin Status ---");
                        managementSystem.getBins().forEach(SmartBin::displayStatus);
                        break;
                    case 4:
                        managementSystem.performBinMaintenance();
                        break;
                    case 5:
                        System.out.println("Exiting Smart Waste Disposal System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a specific type of borrowable item: a workshop tool.
 * This class inherits from BorrowableItem and adds properties specific to tools.
 * It also demonstrates composition by having a list of required equipment.
 */
public class WorkshopTool extends BorrowableItem {

    // --- Properties ---
    private String powerType; // e.g., "Manual", "Electric", "Battery"

    // For demonstrating Composition ("has-a" relationship)
    private List<Equipment> requiredEquipment;

    /**
     * Constructor for creating a new WorkshopTool.
     *
     * @param itemID    The unique identifier for the tool.
     * @param name      The user-friendly name of the tool.
     * @param powerType The power source of the tool.
     */
    public WorkshopTool(String itemID, String name, String powerType) {
        // Call the constructor of the parent class (BorrowableItem) first.
        super(itemID, name);

        // Initialize properties specific to this class.
        this.powerType = powerType;
        this.requiredEquipment = new ArrayList<>(); // Initialize the list as empty.
    }

    /**
     * Getter method required for the Sprint 3 GUI to display details.
     * @return The power type of the tool.
     */
    public String getPowerType() {
        return powerType;
    }

    /**
     * Adds a piece of required equipment to this tool's list.
     * @param equipment The Equipment object to add.
     */
    public void addRequiredEquipment(Equipment equipment) {
        this.requiredEquipment.add(equipment);
    }

    // --- Overridden Method ---
    /**
     * Provides a specific implementation for the print() method.
     * It prints all details of the tool, including details from the parent class.
     */
    @Override
    public void print() {
        System.out.println("-------------------------");
        System.out.println("Item Type: Workshop Tool");
        // Use getters to access parent properties
        System.out.println("ID: " + getItemID());
        System.out.println("Name: " + getName());
        System.out.println("Power Type: " + this.powerType);
        System.out.println("Status: " + (isAvailable() ? "Available" : "On Loan"));

        // Print the list of required equipment
        if (requiredEquipment.isEmpty()) {
            System.out.println("Required Equipment: None");
        } else {
            System.out.println("Required Equipment:");
            for (Equipment eq : requiredEquipment) {
                System.out.println("- " + eq.getName());
            }
        }
        System.out.println("-------------------------");
    }
}
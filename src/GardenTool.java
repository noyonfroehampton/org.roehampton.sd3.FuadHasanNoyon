/**
 * Represents a garden tool that can be borrowed.
 * This class inherits from BorrowableItem and adds properties specific to garden tools.
 */
public class GardenTool extends BorrowableItem {

    // --- Properties ---
    private String material; // e.g., "Steel", "Wood", "Plastic"

    /**
     * Constructor for creating a new GardenTool.
     *
     * @param itemID   The unique identifier for the tool.
     * @param name     The user-friendly name of the tool.
     * @param material The primary material the tool is made of.
     */
    public GardenTool(String itemID, String name, String material) {
        // Call the constructor of the parent class (BorrowableItem) first.
        super(itemID, name);

        // Initialize the property specific to this class.
        this.material = material;
    }

    // --- Overridden Method ---
    /**
     * Provides a specific implementation for the print() method.
     * It prints all details of the tool, including details from the parent class.
     */
    @Override
    public void print() {
        System.out.println("-------------------------");
        System.out.println("Item Type: Garden Tool");
        // Use getters to access parent properties
        System.out.println("ID: " + getItemID());
        System.out.println("Name: " + getName());
        System.out.println("Material: " + this.material);
        System.out.println("Status: " + (isAvailable() ? "Available" : "On Loan"));
        System.out.println("-------------------------");
    }
}
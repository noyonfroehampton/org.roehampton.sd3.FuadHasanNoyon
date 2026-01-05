/**
 * Represents a kitchen appliance that can be borrowed.
 * This class inherits from BorrowableItem and adds properties specific to appliances.
 */
public class KitchenAppliance extends BorrowableItem {

    // --- Properties ---
    private String capacity; // e.g., "1.7 Litres", "5 Quarts", "2 Slices"

    /**
     * Constructor for creating a new KitchenAppliance.
     *
     * @param itemID   The unique identifier for the appliance.
     * @param name     The user-friendly name of the appliance.
     * @param capacity The capacity or size of the appliance.
     */
    public KitchenAppliance(String itemID, String name, String capacity) {
        // Call the constructor of the parent class (BorrowableItem) first.
        super(itemID, name);

        // Initialize the property specific to this class.
        this.capacity = capacity;
    }

    /**
     * Getter method required for the Sprint 3 GUI to display details.
     * @return The capacity of the appliance.
     */
    public String getCapacity() {
        return capacity;
    }

    // --- Overridden Method ---
    /**
     * Provides a specific implementation for the print() method.
     * It prints all details of the appliance, including details from the parent class.
     */
    @Override
    public void print() {
        System.out.println("-------------------------");
        System.out.println("Item Type: Kitchen Appliance");
        // Use getters to access parent properties
        System.out.println("ID: " + getItemID());
        System.out.println("Name: " + getName());
        System.out.println("Capacity: " + this.capacity);
        System.out.println("Status: " + (isAvailable() ? "Available" : "On Loan"));
        System.out.println("-------------------------");
    }
}
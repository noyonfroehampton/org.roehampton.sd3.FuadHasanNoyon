/**
 * Represents an abstract item that can be borrowed from the library.
 * This class serves as a parent for all specific item types and cannot be instantiated on its own.
 */
public abstract class BorrowableItem {

    // --- Properties ---
    private String itemID;
    private String name;
    private boolean isAvailable;

    /**
     * Constructor for creating a new BorrowableItem.
     * It initializes the item's ID and name, and sets its availability to true by default.
     *
     * @param itemID The unique identifier for the item.
     * @param name   The user-friendly name of the item.
     */
    public BorrowableItem(String itemID, String name) {
        this.itemID = itemID;
        this.name = name;
        this.isAvailable = true; // All new items are available by default.
    }

    // --- Abstract Method ---
    /**
     * An abstract method to print the details of the item to the console.
     * Each subclass must provide its own implementation of this method.
     */
    public abstract void print();

    // --- Getters and Setters ---
    /**
     * Gets the unique ID of the item.
     * @return A String representing the item's ID.
     */
    public String getItemID() {
        return this.itemID;
    }

    /**
     * Gets the name of the item.
     * @return A String representing the item's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the item is currently available for borrowing.
     * @return true if the item is available, false otherwise.
     */
    public boolean isAvailable() {
        return this.isAvailable;
    }

    /**
     * Sets the availability status of the item.
     * This will be used in Sprint 2 for booking functionality.
     * @param available The new availability status (true or false).
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
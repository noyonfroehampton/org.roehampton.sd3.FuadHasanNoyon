
/**
 * A simple class representing a piece of equipment.
 * Its purpose is to be used inside other classes to demonstrate composition.
 * Updated to meet the Sprint 2 requirement: "Each class containing at least 3 properties and 3 methods."
 */
public class Equipment {

    // --- Properties ---
    // 1. The name of the equipment
    private String name;

    // 2. The current physical condition (e.g., "New", "Good", "Worn")
    private String condition;

    // 3. Flag to indicate if this is protective gear (e.g., Goggles, Gloves)
    private boolean isSafetyGear;

    /**
     * Constructor for creating a new Equipment object.
     * We keep the constructor simple to avoid breaking your existing calls in Library.java.
     * * @param name The name of the piece of equipment (e.g., "Safety Goggles").
     */
    public Equipment(String name) {
        this.name = name;
        // Initialize new properties with default values
        this.condition = "Good";
        this.isSafetyGear = false;
    }

    // --- Methods ---

    // Method 1: Get the name
    public String getName() {
        return this.name;
    }

    // Method 2: Get the condition
    public String getCondition() {
        return condition;
    }

    // Method 3: Update the condition (e.g., after it is returned)
    public void setCondition(String condition) {
        this.condition = condition;
    }

    // Method 4: Check if it is safety gear
    public boolean isSafetyGear() {
        return isSafetyGear;
    }

    // Method 5: Mark as safety gear
    public void setSafetyGear(boolean isSafetyGear) {
        this.isSafetyGear = isSafetyGear;
    }
}
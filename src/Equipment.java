/**
 * A simple class representing a piece of equipment.
 * Its purpose is to be used inside other classes to demonstrate composition.
 */
public class Equipment {

    // --- Properties ---
    private String name;

    /**
     * Constructor for creating a new Equipment object.
     * @param name The name of the piece of equipment (e.g., "Safety Goggles").
     */
    public Equipment(String name) {
        this.name = name;
    }

    // --- Getter ---
    /**
     * Gets the name of the equipment.
     * @return A String representing the equipment's name.
     */
    public String getName() {
        return this.name;
    }
}
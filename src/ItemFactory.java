/**
 * Factory Pattern: Centralizes the creation of objects.
 * This helps satisfy the Advanced Requirement for Design Patterns.
 */
public class ItemFactory {

    public static BorrowableItem createItem(String type, String id, String name, String extraInfo) {
        if (type.equalsIgnoreCase("workshop")) {
            // extraInfo is treated as Power Type
            return new WorkshopTool(id, name, extraInfo);
        } else if (type.equalsIgnoreCase("kitchen")) {
            // extraInfo is treated as Capacity
            return new KitchenAppliance(id, name, extraInfo);
        } else if (type.equalsIgnoreCase("garden")) {
            // extraInfo is treated as Material
            return new GardenTool(id, name, extraInfo);
        }
        return null; // Or throw an exception
    }
}
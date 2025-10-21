import java.util.ArrayList;
import java.util.List;

/**
 * The main entry point for the Library of Stuff application.
 * This class is used to create and display a list of borrowable items,
 * demonstrating the core OOP principles of Sprint 1.
 */
public class Main {

    public static void main(String[] args) {
        
        // --- 1. Create a list to hold all our items ---
        // We use the parent class type to demonstrate polymorphism.
        List<BorrowableItem> allItems = new ArrayList<>();

        // --- 2. Create instances of each specific item ---

        // Create a WorkshopTool and add required equipment to it (Composition)
        WorkshopTool drill = new WorkshopTool("WT001", "Cordless Drill", "Battery");
        drill.addRequiredEquipment(new Equipment("Safety Goggles"));
        drill.addRequiredEquipment(new Equipment("Drill Bit Set"));
        
        // Create a KitchenAppliance
        KitchenAppliance blender = new KitchenAppliance("KA001", "High-Speed Blender", "1.5 Litres");

        // Create a GardenTool
        GardenTool spade = new GardenTool("GT001", "Digging Spade", "Carbon Steel");

        // --- 3. Add all created items to the main list ---
        allItems.add(drill);
        allItems.add(blender);
        allItems.add(spade);

        // --- 4. Print a user-friendly listing of all items ---
        System.out.println("=====================================");
        System.out.println("   LIBRARY OF STUFF - INVENTORY      ");
        System.out.println("=====================================");
        
        // Loop through the list and call the print() method on each item.
        // Java will automatically call the correct version of print() for each object.
        // This is polymorphism in action!
        for (BorrowableItem item : allItems) {
            item.print(); // Polymorphic call
        }
    }
}
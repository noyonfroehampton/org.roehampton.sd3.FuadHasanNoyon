import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import Scanner for user input

/**
 * Manages all the items in the library and handles the main
 * application loop for user interaction.
 */
public class Library {

    // --- Properties ---
    private List<BorrowableItem> allItems; // Holds all items
    private Scanner inputScanner;          // To read user input

    /**
     * Constructor for the Library.
     * Initializes the item list, scanner, and populates
     * the library with initial items.
     */
    public Library() {
        this.allItems = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);
        populateInitialItems(); // Add the default items
    }

    /**
     * The main application loop.
     * This method runs the menu until the user decides to exit.
     */
    public void startApplication() {
        System.out.println("=====================================");
        System.out.println("   WELCOME TO THE LIBRARY OF STUFF   ");
        System.out.println("=====================================");

        // Use a while(true) loop to keep the menu running
        while (true) {
            printMenu();
            int choice = getUserChoice();

            // Use a switch to decide what to do
            switch (choice) {
                case 1:
                    printAllItems();
                    break;
                case 2:
                    borrowItem();
                    break;
                case 3:
                    returnItem();
                    break;
                case 4:
                    System.out.println("\nThank you for using the Library of Stuff. Goodbye!");
                    inputScanner.close(); // Good practice to close the scanner
                    return; // This exits the method, ending the loop
                default:
                    System.out.println("\n[ERROR] Invalid choice. Please select a number from 1 to 4.");
            }
        }
    }

    // --- Private Helper Methods ---

    /**
     * Prints the main menu options to the console.
     */
    private void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. View All Items");
        System.out.println("2. Borrow an Item");
        System.out.println("3. Return an Item");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice (1-4): ");
    }

    /**
     * Reads and validates the user's menu choice.
     * @return An integer representing the user's choice.
     */
    private int getUserChoice() {
        try {
            String line = inputScanner.nextLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            // If the user types "one" or "abc", this catches it
            return -1; // Return an invalid choice
        }
    }

    /**
     * Creates all the initial items and adds them to the list.
     * This is the same logic from your original Main.java.
     */
    private void populateInitialItems() {
        // Create a WorkshopTool and add required equipment to it
        WorkshopTool drill = new WorkshopTool("WT001", "Cordless Drill", "Battery");
        drill.addRequiredEquipment(new Equipment("Safety Goggles"));
        drill.addRequiredEquipment(new Equipment("Drill Bit Set"));

        // Create a KitchenAppliance
        KitchenAppliance blender = new KitchenAppliance("KA001", "High-Speed Blender", "1.5 Litres");

        // Create a GardenTool
        GardenTool spade = new GardenTool("GT001", "Digging Spade", "Carbon Steel");

        // Add all created items to the main list
        allItems.add(drill);
        allItems.add(blender);
        allItems.add(spade);
    }

    /**
     * Prints the details of all items in the library.
     * This is the printing logic from your original Main.java.
     */
    private void printAllItems() {
        System.out.println("\n=====================================");
        System.out.println("   LIBRARY OF STUFF - INVENTORY      ");
        System.out.println("=====================================");

        if (allItems.isEmpty()) {
            System.out.println("The library is currently empty.");
        } else {
            for (BorrowableItem item : allItems) {
                item.print(); // Polymorphic call
            }
        }
    }

    // --- Sprint 2 Methods (Stubs) ---

    /**
     * Placeholder for the "Borrow Item" functionality.
     * TODO: Implement this for Sprint 2.
     */
    private void borrowItem() {
        System.out.println("\n--- Borrow an Item ---");
        System.out.println("[INFO] This functionality is not yet implemented.");
        //
        // Your logic will go here, e.g.:
        // 1. Show a list of AVAILABLE items.
        // 2. Ask the user to enter an Item ID.
        // 3. Find the item in the allItems list.
        // 4. If found and available, call item.setAvailable(false).
        //
    }

    /**
     * Placeholder for the "Return Item" functionality.
     * TODO: Implement this for Sprint 2.
     */
    private void returnItem() {
        System.out.println("\n--- Return an Item ---");
        System.out.println("[INFO] This functionality is not yet implemented.");
        //
        // Your logic will go here, e.g.:
        // 1. Show a list of ON LOAN items.
        // 2. Ask the user to enter an Item ID.
        // 3. Find the item in the allItems list.
        // 4. If found and on loan, call item.setAvailable(true).
        //
    }
}
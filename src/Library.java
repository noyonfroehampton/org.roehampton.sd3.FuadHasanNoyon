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
    /**
     * Handles the process of borrowing an item.
     * It shows available items, asks for an ID, validates it,
     * and updates the item's status.
     */
    private void borrowItem() {
        System.out.println("\n--- Borrow an Item ---");

        // 1. Show only available items.
        // We use a helper method to keep this code clean.
        // It returns 'true' if items were found, 'false' otherwise.
        boolean hasAvailableItems = printAvailableItems();

        if (!hasAvailableItems) {
            // If no items are available, just return to the main menu.
            return;
        }

        // 2. Ask the user for an ID
        System.out.print("\nPlease enter the ID of the item you wish to borrow: ");
        String itemIDToBorrow = inputScanner.nextLine().trim(); // .trim() removes extra spaces

        // 3. Find the item
        BorrowableItem itemToBorrow = null;
        for (BorrowableItem item : allItems) {
            // Use equalsIgnoreCase for a more robust check
            if (item.getItemID().equalsIgnoreCase(itemIDToBorrow)) {
                itemToBorrow = item;
                break; // Stop looping once we find it
            }
        }

        // 4. Check and update the item
        if (itemToBorrow == null) {
            // Case 1: Item ID does not exist
            System.out.println("[ERROR] No item found with ID: " + itemIDToBorrow);
        } else if (!itemToBorrow.isAvailable()) {
            // Case 2: Item exists but is already on loan
            System.out.println("[SORRY] '" + itemToBorrow.getName() + "' is already on loan.");
        } else {
            // Case 3: Success!
            itemToBorrow.setAvailable(false);
            System.out.println("\n[SUCCESS] You have borrowed: " + itemToBorrow.getName());
            System.out.println("Please remember to use it safely!");
        }
    }

    /**
     * A helper method that lists only the items that are currently available.
     *
     * @return true if available items were printed, false if none were found.
     */
    private boolean printAvailableItems() {
        System.out.println("--- Items Available to Borrow ---");
        boolean foundItems = false;

        // Loop through all items and check their status
        for (BorrowableItem item : allItems) {
            if (item.isAvailable()) {
                // We don't need the full print(). A simple line is better here.
                System.out.printf("  [ID: %s] %s (%s)\n",
                        item.getItemID(), item.getName(), item.getClass().getSimpleName());
                foundItems = true;
            }
        }

        if (!foundItems) {
            System.out.println("Sorry, no items are currently available for loan.");
        }

        return foundItems; // Return whether we found anything
    }

    /**
     * Handles the process of returning an item.
     * It shows on-loan items, asks for an ID, validates it,
     * and updates the item's status.
     */
    private void returnItem() {
        System.out.println("\n--- Return an Item ---");

        // 1. Show only "On Loan" items.
        // We use a new helper method to keep this code clean.
        boolean hasOnLoanItems = printOnLoanItems();

        if (!hasOnLoanItems) {
            // If no items are on loan, just return to the main menu.
            return;
        }

        // 2. Ask the user for an ID
        System.out.print("\nPlease enter the ID of the item you are returning: ");
        String itemIDToReturn = inputScanner.nextLine().trim();

        // 3. Find the item
        BorrowableItem itemToReturn = null;
        for (BorrowableItem item : allItems) {
            // Use equalsIgnoreCase for a more robust check
            if (item.getItemID().equalsIgnoreCase(itemIDToReturn)) {
                itemToReturn = item;
                break; // Stop looping once we find it
            }
        }

        // 4. Check and update the item
        if (itemToReturn == null) {
            // Case 1: Item ID does not exist
            System.out.println("[ERROR] No item found with ID: " + itemIDToReturn);
        } else if (itemToReturn.isAvailable()) {
            // Case 2: Item exists but is already available
            System.out.println("[INFO] That item '" + itemToReturn.getName() + "' is already in the library.");
        } else {
            // Case 3: Success!
            itemToReturn.setAvailable(true);
            System.out.println("\n[SUCCESS] Thank you for returning: " + itemToReturn.getName());
        }
    }

    /**
     * A helper method that lists only the items that are currently on loan.
     *
     * @return true if on-loan items were printed, false if none were found.
     */
    private boolean printOnLoanItems() {
        System.out.println("--- Items Currently On Loan ---");
        boolean foundItems = false;

        // Loop through all items and check their status
        for (BorrowableItem item : allItems) {
            if (!item.isAvailable()) {
                // We don't need the full print(). A simple line is better here.
                System.out.printf("  [ID: %s] %s (%s)\n",
                        item.getItemID(), item.getName(), item.getClass().getSimpleName());
                foundItems = true;
            }
        }

        if (!foundItems) {
            System.out.println("No items are currently on loan.");
        }

        return foundItems; // Return whether we found anything
    }
}
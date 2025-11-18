import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    // --- Singleton Pattern Implementation ---
    private static Library instance;

    // --- Properties ---
    private List<BorrowableItem> allItems;
    private List<User> registeredUsers; // New: List of users
    private User activeUser;            // New: The user currently using the menu
    private Scanner inputScanner;

    // Private constructor prevents "new Library()" from outside
    private Library() {
        this.allItems = new ArrayList<>();
        this.registeredUsers = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);
        populateInitialItems();
        populateUsers(); // Create the 3 required users
    }

    // Public method to get the single instance
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void startApplication() {
        System.out.println("=====================================");
        System.out.println("   WELCOME TO THE LIBRARY OF STUFF   ");
        System.out.println("=====================================");

        // Simple login simulation to select a user
        selectActiveUser();

        while (true) {
            printMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1 -> printAllItems();
                case 2 -> borrowItem();
                case 3 -> returnItem();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void selectActiveUser() {
        System.out.println("\n--- User Selection ---");
        for (User u : registeredUsers) {
            System.out.println("- " + u.getUserId() + ": " + u.getName());
        }
        System.out.print("Enter User ID to login: ");
        String id = inputScanner.nextLine().trim();

        for (User u : registeredUsers) {
            if (u.getUserId().equalsIgnoreCase(id)) {
                this.activeUser = u;
                System.out.println("Welcome, " + activeUser.getName() + "!");
                System.out.println("Current Loyalty Points: " + activeUser.getLoyaltyPoints());
                return;
            }
        }
        System.out.println("User not found. Defaulting to Guest.");
        // Create a temp guest if not found (safety fallback)
        this.activeUser = new User("guest", "Guest User");
    }

    private void populateInitialItems() {
        // Use the FACTORY PATTERN to create items
        BorrowableItem drill = ItemFactory.createItem("workshop", "WT001", "Cordless Drill", "Battery");
        // Cast to access specific methods not in the parent class (optional, for composition)
        if (drill instanceof WorkshopTool) {
            ((WorkshopTool) drill).addRequiredEquipment(new Equipment("Safety Goggles"));
        }

        allItems.add(drill);
        allItems.add(ItemFactory.createItem("kitchen", "KA001", "High-Speed Blender", "1.5 Litres"));
        allItems.add(ItemFactory.createItem("garden", "GT001", "Digging Spade", "Carbon Steel"));
    }

    private void populateUsers() {
        // Requirement: "At least 3 users are created directly in your code"
        registeredUsers.add(new User("U001", "Alice Smith"));
        registeredUsers.add(new User("U002", "Bob Jones"));
        registeredUsers.add(new User("U003", "Charlie Brown"));
    }

    private void borrowItem() {
        System.out.println("\n--- Borrow an Item ---");
        // Show available items
        for (BorrowableItem item : allItems) {
            if (item.isAvailable()) {
                System.out.println(item.getItemID() + ": " + item.getName());
            }
        }

        System.out.print("Enter ID to borrow: ");
        String id = inputScanner.nextLine().trim();

        for (BorrowableItem item : allItems) {
            if (item.getItemID().equalsIgnoreCase(id)) {
                if (item.isAvailable()) {
                    // ASSOCIATION & AGGREGATION LOGIC
                    item.setBorrowedBy(activeUser); // Item knows User
                    activeUser.borrow(item);        // User knows Item

                    // LOYALTY POINTS (Additional Feature)
                    activeUser.addLoyaltyPoints(10);

                    System.out.println("Success! You borrowed " + item.getName());
                    System.out.println("You earned 10 Loyalty Points! Total: " + activeUser.getLoyaltyPoints());
                } else {
                    System.out.println("Item is not available.");
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }

    private void returnItem() {
        System.out.println("\n--- Return an Item ---");
        // Show items currently borrowed by THIS user
        List<BorrowableItem> userItems = activeUser.getBorrowedItems();

        if (userItems.isEmpty()) {
            System.out.println("You have no items to return.");
            return;
        }

        for (BorrowableItem item : userItems) {
            System.out.println(item.getItemID() + ": " + item.getName());
        }

        System.out.print("Enter ID to return: ");
        String id = inputScanner.nextLine().trim();

        // Use a temporary variable to avoid ConcurrentModificationException
        BorrowableItem toReturn = null;
        for (BorrowableItem item : userItems) {
            if (item.getItemID().equalsIgnoreCase(id)) {
                toReturn = item;
                break;
            }
        }

        if (toReturn != null) {
            toReturn.returnToLibrary(); // Clear user from item
            activeUser.returnItem(toReturn); // Remove item from user
            System.out.println("Item returned successfully.");
        } else {
            System.out.println("You don't have that item.");
        }
    }

    // --- Boilerplate helper methods ---
    private void printMenu() {
        System.out.println("\n1. View All Items\n2. Borrow Item\n3. Return Item\n4. Exit");
        System.out.print("Choose: ");
    }

    private int getUserChoice() {
        try { return Integer.parseInt(inputScanner.nextLine()); }
        catch (Exception e) { return -1; }
    }

    private void printAllItems() {
        for (BorrowableItem item : allItems) item.print();
    }
}
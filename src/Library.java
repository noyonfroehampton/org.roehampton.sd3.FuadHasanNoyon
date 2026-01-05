import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    // --- Singleton Pattern Implementation ---
    private static Library instance;

    // --- Properties ---
    private List<BorrowableItem> allItems;
    private List<User> registeredUsers;
    private User activeUser;
    private Scanner inputScanner;

    // Private constructor prevents "new Library()" from outside
    private Library() {
        this.allItems = new ArrayList<>();
        this.registeredUsers = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);
        populateInitialItems();
        populateUsers();
    }

    // Public method to get the single instance
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // --- Sprint 3 Helper Methods ---
    // These allow the GUI to access your data
    public List<BorrowableItem> getAllItems() {
        return allItems;
    }

    public User getActiveUser() {
        return activeUser;
    }

    // Added for User Switcher feature
    public List<User> getAllUsers() {
        return registeredUsers;
    }

    public void setActiveUser(User user) {
        this.activeUser = user;
    }

    // --- Initialization Logic ---
    private void populateInitialItems() {
        // Use the FACTORY PATTERN to create items
        BorrowableItem drill = ItemFactory.createItem("workshop", "WT001", "Cordless Drill", "Battery");
        if (drill instanceof WorkshopTool) {
            ((WorkshopTool) drill).addRequiredEquipment(new Equipment("Safety Goggles"));
        }

        allItems.add(drill);
        allItems.add(ItemFactory.createItem("kitchen", "KA001", "High-Speed Blender", "1.5 Litres"));
        allItems.add(ItemFactory.createItem("garden", "GT001", "Digging Spade", "Carbon Steel"));
    }

    private void populateUsers() {
        registeredUsers.add(new User("U001", "Alice Smith"));
        registeredUsers.add(new User("U002", "Bob Jones"));
        registeredUsers.add(new User("U003", "Charlie Brown"));

        // Default active user for the GUI to start with
        this.activeUser = registeredUsers.get(0);
    }

    // --- Legacy Console Logic (Sprint 2) ---
    // You can keep these for reference or if you still want to run the console version
    public void startApplication() {
        System.out.println("=====================================");
        System.out.println("   WELCOME TO THE LIBRARY OF STUFF   ");
        System.out.println("=====================================");

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
        String id = inputScanner.hasNextLine() ? inputScanner.nextLine().trim() : "";

        for (User u : registeredUsers) {
            if (u.getUserId().equalsIgnoreCase(id)) {
                this.activeUser = u;
                System.out.println("Welcome, " + activeUser.getName() + "!");
                return;
            }
        }
        System.out.println("User not found. Defaulting to Alice Smith.");
        this.activeUser = registeredUsers.get(0);
    }

    private void borrowItem() {
        System.out.println("\n--- Borrow an Item ---");
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
                    item.setBorrowedBy(activeUser);
                    activeUser.borrow(item);
                    activeUser.addLoyaltyPoints(10);
                    System.out.println("Success! You borrowed " + item.getName());
                } else {
                    System.out.println("Item is not available.");
                }
                return;
            }
        }
    }

    private void returnItem() {
        List<BorrowableItem> userItems = activeUser.getBorrowedItems();
        if (userItems.isEmpty()) {
            System.out.println("You have no items to return.");
            return;
        }
        System.out.print("Enter ID to return: ");
        String id = inputScanner.nextLine().trim();

        BorrowableItem toReturn = null;
        for (BorrowableItem item : userItems) {
            if (item.getItemID().equalsIgnoreCase(id)) {
                toReturn = item;
                break;
            }
        }

        if (toReturn != null) {
            toReturn.returnToLibrary();
            activeUser.returnItem(toReturn);
            System.out.println("Item returned successfully.");
        }
    }

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
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    // Aggregation: A User "has" a list of borrowed items
    private List<BorrowableItem> borrowedItems;
    // Additional Feature: Loyalty Points
    private int loyaltyPoints;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
        this.loyaltyPoints = 0; // Start with 0 points
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<BorrowableItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void borrow(BorrowableItem item) {
        borrowedItems.add(item);
    }

    public void returnItem(BorrowableItem item) {
        borrowedItems.remove(item);
    }

    // --- Loyalty Points Methods ---
    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}
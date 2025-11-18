import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    // Aggregation: A User "has" a list of borrowed items
    private List<BorrowableItem> borrowedItems;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedItems = new ArrayList<>();
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
}
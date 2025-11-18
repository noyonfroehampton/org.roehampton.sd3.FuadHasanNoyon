/**
 * Represents an abstract item that can be borrowed from the library.
 */
public abstract class BorrowableItem {

    private String itemID;
    private String name;
    private boolean isAvailable;
    // Association: An item "knows" who borrowed it
    private User currentUser;

    public BorrowableItem(String itemID, String name) {
        this.itemID = itemID;
        this.name = name;
        this.isAvailable = true;
        this.currentUser = null;
    }

    public abstract void print();

    public String getItemID() {
        return this.itemID;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // Update availability AND link the user
    public void setBorrowedBy(User user) {
        this.currentUser = user;
        this.isAvailable = false;
    }

    public void returnToLibrary() {
        this.currentUser = null;
        this.isAvailable = true;
    }
}
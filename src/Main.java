public class Main {
    public static void main(String[] args) {
        // Use the Singleton instance
        Library myLibrary = Library.getInstance();
        myLibrary.startApplication();
    }
}
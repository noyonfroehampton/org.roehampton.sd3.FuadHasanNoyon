/**
 * The main entry point for the Library of Stuff application.
 * This class is now only responsible for creating the Library
 * object and starting the application.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Create an instance of the Library
        Library myLibrary = new Library();

        // 2. Start the interactive application loop
        myLibrary.startApplication();
    }
}
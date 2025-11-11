# 📖 Library of Stuff
This repository contains the Java console application for the "Library of Stuff" project. The application is designed to manage a lending library for various household and workshop items.

## Sprint 2 Submission
Deadline: Monday, 24 November 2025

This submission builds upon the Sprint 1 foundation by adding a complete, interactive booking system.

### 🏗️ Key Features Implemented
* **Interactive Console Menu:** The `Main` class now launches an interactive menu, managed by a new `Library` class.
* **Central Library Class:** A new `Library` class was created to manage the `List` of items, handle user input (`Scanner`), and contain all application logic. This separates concerns and keeps the `Main` class clean.
* **Booking System (State Management):** Users can now select options to:
    * **View All Items:** Shows the complete inventory and the status of each item ("Available" or "On Loan").
    * **Borrow an Item:** Presents a list of only available items. Users can select an item by its ID, which updates its status using `setAvailable(false)`.
    * **Return an Item:** Presents a list of only "On Loan" items. Users can select an item by its ID, which updates its status using `setAvailable(true)`.
* **Robust Input Handling:** The menu gracefully handles invalid inputs (like letters instead of numbers or non-existent IDs).
* **Clean Code (Refactoring):** The `borrowItem` and `returnItem` methods were refactored to use a single `findItemByID` helper method, adhering to the DRY (Don't Repeat Yourself) principle.

---

## Sprint 1 Submission
Deadline: 24-October-2025

This submission covers all basic and advanced requirements for Sprint 1. The focus is on building a solid Object-Oriented foundation for the application.

### 🏗️ Key Features Implemented
The project successfully demonstrates the core principles of Object-Oriented Programming:

* **Abstraction:** An abstract `BorrowableItem` class is used as a blueprint, defining common attributes and behaviors for all items.
* **Inheritance:** Specific item classes (`WorkshopTool`, `KitchenAppliance`, `GardenTool`) inherit from the `BorrowableItem` parent class.
* **Encapsulation:** All class properties are kept `private` and are only accessible through public getter methods.
* **Polymorphism:** The `Main` class treats all objects as their parent type (`BorrowableItem`) and calls the same `.print()` method, which executes the correct overridden version for each object.
* **Composition:** To meet the advanced requirement for higher marks, a `WorkshopTool` "has-a" `List` of `Equipment` objects, demonstrating the composition relationship.

---

## 📁 File Structure
The `src` folder contains all the necessary Java source code:

* `Main.java`: The entry point of the application. It creates the `Library` object and starts the program.
* `Library.java`: (New in S2) Manages the inventory and all user interactions (menu, borrowing, returning).
* `BorrowableItem.java`: The abstract parent class that defines a generic borrowable item.
* `WorkshopTool.java`: A child class for workshop tools. It also demonstrates composition.
* `KitchenAppliance.java`: A child class for kitchen appliances.
* `GardenTool.java`: A child class for garden tools.
* `Equipment.java`: A simple helper class used to demonstrate the "has-a" composition relationship.

---

## 🚀 How to Run the Project
### Prerequisites:
* Java Development Kit (JDK) 17 or newer.
* An IDE like IntelliJ IDEA (Community Edition) or Visual Studio Code with the Extension Pack for Java.

### Execution Steps:
1.  Open the project's root folder in your IDE.
2.  Navigate to the `src/Main.java` file.
3.  Click the "Run" button that appears above the `public static void main(String[] args)` method.
4.  The program will launch in the integrated terminal, displaying the interactive main menu.

---

## Expected Output
The program will no longer just print a static list. It will now show a menu and wait for user input:

```text
=====================================
   WELCOME TO THE LIBRARY OF STUFF   
=====================================

--- MAIN MENU ---
1. View All Items
2. Borrow an Item
3. Return an Item
4. Exit
Please enter your choice (1-4):


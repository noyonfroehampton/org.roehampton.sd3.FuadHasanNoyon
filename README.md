# 📖 Library of Stuff

This repository contains the Java console application for the "Library of Stuff" project. The application is designed to manage a lending library for various household and workshop items.

## Sprint 1 Submission
**Deadline:** 24-October-2025

This submission covers all basic and advanced requirements for Sprint 1. The focus is on building a solid Object-Oriented foundation for the application.

---

## 🏗️ Key Features Implemented

The project successfully demonstrates the core principles of Object-Oriented Programming:

* **Abstraction:** An abstract `BorrowableItem` class is used as a blueprint, defining common attributes and behaviors for all items.
* **Inheritance:** Specific item classes (`WorkshopTool`, `KitchenAppliance`, `GardenTool`) inherit from the `BorrowableItem` parent class.
* **Encapsulation:** All class properties are kept `private` and are only accessible through `public` getter methods.
* **Polymorphism:** The `Main` class treats all objects as their parent type (`BorrowableItem`) and calls the same `.print()` method, which executes the correct overridden version for each object.
* **Composition:** To meet the advanced requirement for higher marks, a `WorkshopTool` "has-a" `List` of `Equipment` objects, demonstrating the composition relationship.

---

## 📁 File Structure

The `src` folder contains all the necessary Java source code:

* **`BorrowableItem.java`**: The `abstract` parent class that defines a generic borrowable item.
* **`WorkshopTool.java`**: A child class for workshop tools. It also demonstrates **composition**.
* **`KitchenAppliance.java`**: A child class for kitchen appliances.
* **`GardenTool.java`**: A child class for garden tools.
* **`Equipment.java`**: A simple helper class used to demonstrate the "has-a" composition relationship.
* **`Main.java`**: The entry point of the application. It creates several items and prints their details to the console to demonstrate that all classes are working correctly.

---

## 🚀 How to Run the Project

1.  **Prerequisites:**
    * Java Development Kit (JDK) 17 or newer.
    * Visual Studio Code with the **Extension Pack for Java** installed.

2.  **Execution Steps:**
    * Open the project's root folder in VS Code.
    * Navigate to the `src/Main.java` file in the Explorer.
    * Click the **"Run"** button that appears above the `public static void main(String[] args)` method.
    * The program's output will be displayed in the integrated terminal.

### Expected Output

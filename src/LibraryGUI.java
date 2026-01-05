import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryGUI extends Application {

    private Library library = Library.getInstance();
    private ListView<BorrowableItem> itemListView = new ListView<>();
    private Label statusBar = new Label("System Ready");
    private Label userInfoLabel = new Label();
    private Label detailsLabel = new Label("Select an item to see details");
    private ComboBox<User> userComboBox = new ComboBox<>();

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(0)); // Padding handled by internal containers

        // --- TOP: Header & User Info ---
        HBox topBar = new HBox(20);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(20));

        Label title = new Label("Library of Stuff");
        title.getStyleClass().add("header-label"); // CSS Class

        userComboBox.setItems(FXCollections.observableArrayList(library.getAllUsers()));
        userComboBox.setValue(library.getActiveUser());
        userComboBox.setOnAction(e -> {
            library.setActiveUser(userComboBox.getValue());
            updateUserInfo();
            statusBar.setText("Current User: " + library.getActiveUser().getName());
        });

        userInfoLabel.getStyleClass().add("user-info"); // CSS Class
        updateUserInfo();

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topBar.getChildren().addAll(title, new Label("Switch User:"), userComboBox, spacer, userInfoLabel);
        root.setTop(topBar);

        // --- CENTER: Inventory Management ---
        VBox centerArea = new VBox(10);
        centerArea.setPadding(new Insets(0, 10, 10, 20));
        Label inventoryTitle = new Label("Inventory Management");
        inventoryTitle.setStyle("-fx-font-weight: bold;");
        refreshList(library.getAllItems());
        centerArea.getChildren().addAll(inventoryTitle, itemListView);
        root.setCenter(centerArea);

        // --- LEFT: Filters ---
        VBox leftFilters = new VBox(15);
        leftFilters.setPadding(new Insets(10, 0, 10, 20));
        Button btnAll = new Button("All Items");
        Button btnAvailable = new Button("Available Only");

        btnAll.getStyleClass().add("filter-button");
        btnAvailable.getStyleClass().add("filter-button");
        btnAll.setPrefWidth(140);
        btnAvailable.setPrefWidth(140);

        btnAvailable.setOnAction(e -> {
            List<BorrowableItem> filtered = library.getAllItems().stream()
                    .filter(BorrowableItem::isAvailable)
                    .collect(Collectors.toList());
            refreshList(filtered);
            statusBar.setText("Filtered: Showing available items only.");
        });
        btnAll.setOnAction(e -> {
            refreshList(library.getAllItems());
            statusBar.setText("Showing all library items.");
        });

        leftFilters.getChildren().addAll(new Label("FILTERS"), btnAll, btnAvailable);
        root.setLeft(leftFilters);

        // --- RIGHT: Details & Actions ---
        VBox rightActions = new VBox(15);
        rightActions.setPadding(new Insets(10, 20, 10, 15));
        rightActions.setPrefWidth(300);

        VBox detailsArea = new VBox(10);
        detailsArea.getStyleClass().add("details-box"); // CSS Class
        detailsArea.setPadding(new Insets(15));
        detailsLabel.setWrapText(true);
        detailsArea.getChildren().addAll(new Label("ITEM SPECIFICATIONS"), detailsLabel);

        Button btnBorrow = new Button("BORROW ITEM");
        Button btnReturn = new Button("RETURN ITEM");
        btnBorrow.getStyleClass().add("borrow-button");
        btnReturn.getStyleClass().add("return-button");
        btnBorrow.setMaxWidth(Double.MAX_VALUE);
        btnReturn.setMaxWidth(Double.MAX_VALUE);

        itemListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateDetailsArea(newVal));

        btnBorrow.setOnAction(e -> handleBorrow());
        btnReturn.setOnAction(e -> handleReturn());

        rightActions.getChildren().addAll(detailsArea, btnBorrow, btnReturn);
        root.setRight(rightActions);

        // --- BOTTOM: Feedback System ---
        HBox bottomBar = new HBox();
        bottomBar.getStyleClass().add("status-bar"); // CSS Class
        statusBar.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(statusBar, Priority.ALWAYS);
        bottomBar.getChildren().add(statusBar);
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 1100, 650);

        // --- LOAD THE CSS FILE ---
        try {
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS could not be loaded. Ensure style.css is in the correct folder.");
        }

        primaryStage.setTitle("Library of Stuff | Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateUserInfo() {
        User active = library.getActiveUser();
        userInfoLabel.setText("Logged in: " + active.getName() + " | Loyalty Points: " + active.getLoyaltyPoints());
    }

    private void updateDetailsArea(BorrowableItem item) {
        if (item == null) {
            detailsLabel.setText("Please select an item from the list to view its unique properties.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Item ID: ").append(item.getItemID()).append("\n");
        sb.append("Display Name: ").append(item.getName()).append("\n");
        sb.append("Availability: ").append(item.isAvailable() ? "AVAILABLE" : "ON LOAN").append("\n\n");

        if (item instanceof WorkshopTool tool) {
            sb.append("Category: WORKSHOP TOOL\n");
            sb.append("Power Source: ").append(tool.getPowerType());
        } else if (item instanceof KitchenAppliance appliance) {
            sb.append("Category: KITCHEN APPLIANCE\n");
            sb.append("Capacity: ").append(appliance.getCapacity());
        } else if (item instanceof GardenTool gardenTool) {
            sb.append("Category: GARDEN TOOL\n");
            sb.append("Material: ").append(gardenTool.getMaterial());
        }

        detailsLabel.setText(sb.toString());
    }

    private void refreshList(List<BorrowableItem> items) {
        itemListView.setItems(FXCollections.observableArrayList(items));
        itemListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(BorrowableItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getItemID() + ": " + item.getName() + (item.isAvailable() ? " (Available)" : " (On Loan)"));
                    if (item.isAvailable()) setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
                    else setStyle("-fx-text-fill: #c0392b; -fx-font-style: italic;");
                }
            }
        });
    }

    private void handleBorrow() {
        BorrowableItem selected = itemListView.getSelectionModel().getSelectedItem();
        if (selected != null && selected.isAvailable()) {
            selected.setBorrowedBy(library.getActiveUser());
            library.getActiveUser().addLoyaltyPoints(10);
            statusBar.setText("SUCCESS: Successfully borrowed " + selected.getName() + ". You earned 10 loyalty points!");
            updateUserInfo();
            updateDetailsArea(selected);
            itemListView.refresh();
        } else if (selected != null) {
            statusBar.setText("ERROR: The item '" + selected.getName() + "' is currently unavailable.");
        }
    }

    private void handleReturn() {
        BorrowableItem selected = itemListView.getSelectionModel().getSelectedItem();
        if (selected != null && !selected.isAvailable()) {
            selected.returnToLibrary();
            library.getActiveUser().returnItem(selected);
            statusBar.setText("SUCCESS: Successfully returned " + selected.getName() + " to the inventory.");
            updateUserInfo();
            updateDetailsArea(selected);
            itemListView.refresh();
        }
    }
}
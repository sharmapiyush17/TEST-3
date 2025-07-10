import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    TableView<PizzaOrder> table;
    ObservableList<PizzaOrder> orders = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pizza Ordering System - Piyush");

        // Title
        Label titleLabel = new Label("Pizza Ordering System");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Customer Name");
        TextField mobileField = new TextField();
        mobileField.setPromptText("Mobile Number");
        TextField toppingsField = new TextField();
        toppingsField.setPromptText("Number of Toppings");

        // Size checkboxes (choose one)
        ToggleGroup sizeGroup = new ToggleGroup();
        RadioButton xl = new RadioButton("XL");
        xl.setToggleGroup(sizeGroup);
        RadioButton l = new RadioButton("L");
        l.setToggleGroup(sizeGroup);
        RadioButton m = new RadioButton("M");
        m.setToggleGroup(sizeGroup);
        RadioButton s = new RadioButton("S");
        s.setToggleGroup(sizeGroup);
        HBox sizeBox = new HBox(10, xl, l, m, s);

        // Buttons
        Button insert = new Button("Insert");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button view = new Button("View");
        Button clear = new Button("Clear");
        HBox buttons = new HBox(10, insert, update, delete, view, clear);

        // TableView
        table = new TableView<>();
        TableColumn<PizzaOrder, String> nameCol = new TableColumn<>("Customer Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<PizzaOrder, String> mobileCol = new TableColumn<>("Mobile Number");
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        TableColumn<PizzaOrder, String> sizeCol = new TableColumn<>("Size");
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));

        TableColumn<PizzaOrder, Integer> topCol = new TableColumn<>("Toppings");
        topCol.setCellValueFactory(new PropertyValueFactory<>("toppings"));

        TableColumn<PizzaOrder, Double> billCol = new TableColumn<>("Total Bill");
        billCol.setCellValueFactory(new PropertyValueFactory<>("totalBill"));

        table.getColumns().addAll(nameCol, mobileCol, sizeCol, topCol, billCol);
        table.setItems(orders);

        // Button Actions
        insert.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String mobile = mobileField.getText();
                int toppings = Integer.parseInt(toppingsField.getText());
                String size = ((RadioButton) sizeGroup.getSelectedToggle()).getText();
                double total = calculateTotalBill(size, toppings);

                PizzaOrder order = new PizzaOrder(name, mobile, size, toppings, total);
                orders.add(order);
                clearFields(nameField, mobileField, toppingsField, sizeGroup);
            } catch (Exception ex) {
                showAlert("Error", "Please fill all fields correctly.");
            }
        });

        update.setOnAction(e -> {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    String name = nameField.getText();
                    String mobile = mobileField.getText();
                    int toppings = Integer.parseInt(toppingsField.getText());
                    String size = ((RadioButton) sizeGroup.getSelectedToggle()).getText();
                    double total = calculateTotalBill(size, toppings);

                    selected.setName(name);
                    selected.setMobile(mobile);
                    selected.setSize(size);
                    selected.setToppings(toppings);
                    selected.setTotalBill(total);
                    table.refresh();
                    clearFields(nameField, mobileField, toppingsField, sizeGroup);
                } catch (Exception ex) {
                    showAlert("Error", "Please fill all fields correctly.");
                }
            }
        });

        delete.setOnAction(e -> {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                orders.remove(selected);
            }
        });

        view.setOnAction(e -> table.refresh());

        clear.setOnAction(e -> clearFields(nameField, mobileField, toppingsField, sizeGroup));

        VBox layout = new VBox(10, titleLabel, nameField, mobileField, sizeBox, toppingsField, buttons, table);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout, 750, 550);
        stage.setScene(scene);
        stage.show();
    }

    public static double calculateTotalBill(String size, int toppings) {
        double basePrice;
        switch (size.toUpperCase()) {
            case "XL": basePrice = 15.00; break;
            case "L":  basePrice = 12.00; break;
            case "M":  basePrice = 10.00; break;
            case "S":  basePrice = 8.00; break;
            default:   basePrice = 0.00; break;
        }
        double toppingsCost = toppings * 1.50;
        double subtotal = basePrice + toppingsCost;
        double hst = subtotal * 0.15;
        return Math.round((subtotal + hst) * 100.0) / 100.0;
    }

    private void clearFields(TextField name, TextField mobile, TextField toppings, ToggleGroup group) {
        name.clear();
        mobile.clear();
        toppings.clear();
        group.selectToggle(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

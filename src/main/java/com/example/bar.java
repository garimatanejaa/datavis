package com.example;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Priority;



public class bar extends Application {
    @Override
    public void start(Stage primarystage){
        Label label1 = new Label("Enter data for the bar chart:");
        label1.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        TextField nameInput = new TextField();
        nameInput.setStyle("-fx-font-size: 14px;");
        TextField dataInput = new TextField();
        dataInput.setStyle("-fx-font-size: 14px;");
        Button addButton = new Button("Add Data");
        addButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4EA699; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
        BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        TableView<ItemData> tableView= new TableView<>();

        TableColumn<ItemData, String> itemNameCol = new TableColumn<>("Item Name");
        itemNameCol.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        TableColumn<ItemData, Number> itemValueCol = new TableColumn<>("Value");
        itemValueCol.setCellValueFactory(cellData -> cellData.getValue().itemValueProperty());
        tableView.getColumns().addAll(itemNameCol, itemValueCol);
        ObservableList<ItemData> itemDataList = FXCollections.observableArrayList();
        addButton.setOnAction(event->{
            try{
                String itemName= nameInput.getText();
                double dataValue = Double.parseDouble(dataInput.getText());
                boolean itemExists = false;
                for (ItemData item : itemDataList) {
                    if (item.getItemName().equals(itemName)) {
                        itemExists = true;
                        break;
                    }
                }
            
            if (!itemExists) {
                ItemData itemData = new ItemData(itemName, dataValue);
                itemDataList.add(itemData);

                tableView.setItems(itemDataList);

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(itemName, dataValue));
                barChart.getData().add(series);
            } else {
                System.out.println("Item already exists. Please enter a unique item name.");
            }
            nameInput.clear();
            dataInput.clear();
        }catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }


        });
        //backbutton
        Button backButton = new Button("Home");
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white;");

        backButton.setOnAction(event -> {
            // Handle navigation back to App.java
            App app = new App();
            try {
                app.start(primarystage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        HBox topBar = new HBox(backButton);
        topBar.setStyle("-fx-alignment: top-right;");
        HBox.setHgrow(topBar, Priority.ALWAYS);
        VBox root = new VBox(10, topBar, label1, new HBox(10, new Label("Item Name:"), nameInput),
                new HBox(10, new Label("Value:"), dataInput), addButton, tableView, barChart);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f4;-fx-font-family: Cambria");
        Scene scene = new Scene(root, 800, 600);
        primarystage.setTitle("Bar Chart");
        primarystage.setScene(scene);
        primarystage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static class ItemData {
        private final SimpleStringProperty itemName;
        private final SimpleDoubleProperty itemValue;

        public ItemData(String itemName, double itemValue) {
            this.itemName = new SimpleStringProperty(itemName);
            this.itemValue = new SimpleDoubleProperty(itemValue);
        }

        public String getItemName() {
            return itemName.get();
        }

        public SimpleStringProperty itemNameProperty() {
            return itemName;
        }

        public double getItemValue() {
            return itemValue.get();
        }

        public SimpleDoubleProperty itemValueProperty() {
            return itemValue;
        }
    }
    
}

package com.example;
import com.example.bar.ItemData;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;

public class pie extends Application {
    @Override 
    public void start(Stage primarystage){
        Label label1 = new Label("Enter data for the pie chart:");
        label1.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        TextField nameInput = new TextField();
        nameInput.setStyle("-fx-font-size: 14px;");
        TextField dataInput = new TextField();
        dataInput.setStyle("-fx-font-size: 14px;");
        Button addButton = new Button("Add Data");
        addButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4EA699; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
        PieChart piechart= new PieChart(); //create pie chat instance
        TableView<ItemData> tableView = new TableView<>();//creating an instance of table view, it is under controls
        TableColumn<ItemData,String> itemNameCol = new TableColumn<>("item Name");
        itemNameCol.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());//setCellFactory is an in built function that is used to determine cell values
        //getValue() is used to retrieve the values in the cell
        TableColumn<ItemData, Number> itemValueCol = new TableColumn<>("Value");
        itemValueCol.setCellValueFactory(cellData -> cellData.getValue().itemValueProperty());
        tableView.getColumns().addAll(itemNameCol, itemValueCol);
        ObservableList<ItemData> itemDataList= FXCollections.observableArrayList(); //stores the object of ItemData
        addButton.setOnAction(event->{
            try{
                String itemName = nameInput.getText();
                double dataValue = Double.parseDouble(dataInput.getText());
                ItemData itemData= new ItemData(itemName,dataValue);
                itemDataList.add(itemData); //adding to table
                tableView.setItems(itemDataList); //to integrate the object list visible to us
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                double totalValue = 0.0;
                for (ItemData item : itemDataList) {
                    totalValue += item.getItemValue();
                }
                for (ItemData item : itemDataList)//to display percentage
                {
                    double percentage = (item.getItemValue() / totalValue) * 100;
                    pieChartData.add(new PieChart.Data(item.getItemName() + " (" + String.format("%.2f", percentage) + "%)", item.getItemValue()));
                }
                piechart.setData(pieChartData);

                nameInput.clear();
                dataInput.clear();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

        });
        

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
        VBox root = new VBox(10,topBar, label1, new HBox(10, new Label("Item Name:"), nameInput),
        new HBox(10, new Label("Value:"), dataInput), addButton, tableView, piechart);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f4;-fx-font-family: Cambria");
        Scene scene = new Scene(root, 800, 600);
        primarystage.setTitle("PIE CHART GENERATOR");
        primarystage.setScene(scene);
        primarystage.show(); 
    }
    public static void main(String[] args)
    {
        launch(args);
    }
    public static class ItemData
    {
        private final SimpleStringProperty itemName;
        private final SimpleDoubleProperty itemValue;

        public ItemData(String itemName, double itemValue) //constructor or setter
        {
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

package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DataVis");

        Label headerLabel1 = new Label("Data Visualization Tool");

        Button barButton = new Button("Bar Chart");
        Button lineButton = new Button("Line Chart");
        Button pieButton = new Button("Pie Chart");

        pieButton.setStyle("-fx-font-size: 18px; -fx-min-width: 300px; -fx-min-height: 60px; -fx-background-color: #2DD881; -fx-text-fill: white;-fx-font-weight: bold;-fx-font-family: Cambria; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
        barButton.setStyle("-fx-font-size: 18px; -fx-min-width: 300px; -fx-min-height: 60px; -fx-background-color: #4EA699; -fx-text-fill: white;-fx-font-weight: bold;-fx-font-family: Cambria; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");
        lineButton.setStyle("-fx-font-size: 18px; -fx-min-width: 300px; -fx-min-height: 60px; -fx-background-color: #140D4F; -fx-text-fill: white;-fx-font-weight: bold;-fx-font-family: Cambria; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 2);");

        barButton.setOnAction(event -> {
            bar BarChartEx = new bar();
            BarChartEx.start(new Stage());
        });

        lineButton.setOnAction(event -> {
            line lineChartEx = new line();
            lineChartEx.start(new Stage());
        });

        pieButton.setOnAction(event -> {
            pie pieChartEx = new pie();
            pieChartEx.start(new Stage());
        });

        VBox buttonsVBox = new VBox(20, pieButton, barButton, lineButton);
        buttonsVBox.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        VBox root = new VBox();
        root.getChildren().addAll(headerLabel1, buttonsVBox);
        root.setStyle("-fx-alignment: center; -fx-background-color: white; -fx-font-family: Cambria");

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

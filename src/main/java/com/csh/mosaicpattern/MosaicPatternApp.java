package com.csh.mosaicpattern;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main JavaFX application class for the Mosaic Crochet Pattern Generator.
 */
public class MosaicPatternApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mosaic Crochet Pattern Generator");

        Label welcomeLabel = new Label("Welcome to Mosaic Crochet Pattern Generator");
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");

        StackPane root = new StackPane();
        root.getChildren().add(welcomeLabel);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

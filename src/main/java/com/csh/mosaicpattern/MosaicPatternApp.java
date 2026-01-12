package com.csh.mosaicpattern;

import com.csh.mosaicpattern.ui.PatternGeneratorView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX application class for the Mosaic Crochet Pattern Generator.
 */
public class MosaicPatternApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mosaic Crochet Pattern Generator");

        PatternGeneratorView root = new PatternGeneratorView();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

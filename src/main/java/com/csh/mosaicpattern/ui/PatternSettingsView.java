package com.csh.mosaicpattern.ui;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.csh.mosaicpattern.backend.PatternGenerator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PatternSettingsView extends VBox {

    // UI elements
    private Button generateButton;
    private ColorSectionView color1Section;
    private ColorSectionView color2Section;
    private ProgressBar progressBar;
    private Button cancelButton;
    private HBox progressBox;

    // Background task
    private PatternGenerator currentTask;
    private Supplier<BufferedImage> getUploadedImage;
    private Consumer<Image> onPatternGenerated;
    private Consumer<Void> onGenerationCancelled;
    private Consumer<Exception> onGenerationFailed;
    private Consumer<Boolean> disableMainframe;

    public PatternSettingsView(double size, Supplier<BufferedImage> getUploadedImage,
            Consumer<Image> onPatternGenerated,
            Consumer<Void> onGenerationCancelled,
            Consumer<Exception> onGenerationFailed, Consumer<Boolean> disableMainframe) {
        super(size);
        this.getUploadedImage = getUploadedImage;
        this.onPatternGenerated = onPatternGenerated;
        this.onGenerationCancelled = onGenerationCancelled;
        this.onGenerationFailed = onGenerationFailed;
        this.disableMainframe = disableMainframe;
        initializeUI();
    }

    private void initializeUI() {
        this.setPadding(new Insets(15));
        this.setStyle(
                "-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5;");
        VBox.setVgrow(this, Priority.ALWAYS);

        Label settingsLabel = new Label("Pattern Generation");
        settingsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Color controls
        color1Section = new ColorSectionView(8, "Color 1", Color.BLACK);
        color2Section = new ColorSectionView(8, "Color 2", Color.WHITE);

        // Generate button
        generateButton = new Button("Generate Pattern");
        generateButton.setPrefWidth(200);
        generateButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        generateButton.setOnAction(e -> handleGeneratePattern());

        // Progress bar (initially hidden)
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(200);
        progressBar.setVisible(false);

        // Cancel button (initially hidden)
        cancelButton = new Button("Cancel");
        cancelButton.setVisible(false);
        cancelButton.setOnAction(e -> handleCancel());

        // Progress box with progress bar and cancel button
        progressBox = new HBox(10);
        progressBox.setAlignment(Pos.CENTER_LEFT);
        progressBox.getChildren().addAll(progressBar, cancelButton);
        progressBox.setVisible(false);

        getChildren().addAll(settingsLabel, color1Section, color2Section, generateButton, progressBox);
    }

    private void handleGeneratePattern() {
        // Disable UI
        generateButton.setVisible(false);
        progressBox.setVisible(true);
        progressBar.setVisible(true);
        cancelButton.setVisible(true);
        disableMainframe.accept(true);

        // Create background task
        currentTask = new PatternGenerator(getUploadedImage.get(), color1Section.getSelectedColor(),
                color2Section.getSelectedColor());

        // Bind progress bar to task
        progressBar.progressProperty().bind(currentTask.progressProperty());

        // Handle task completion
        currentTask.setOnSucceeded(e -> {
            Image result = currentTask.getValue();
            if (onPatternGenerated != null) {
                onPatternGenerated.accept(result);
            }
        });

        currentTask.setOnCancelled(e -> {
            if (onGenerationCancelled != null) {
                onGenerationCancelled.accept(null);
            }
        });

        currentTask.setOnFailed(e -> {
            if (onGenerationFailed != null) {
                onGenerationFailed.accept((Exception) currentTask.getException());
            }
        });

        // Start task in background thread
        Thread thread = new Thread(currentTask);
        thread.setDaemon(true);
        thread.start();
    }

    private void handleCancel() {
        if (currentTask != null) {
            currentTask.cancel();
        }
    }

    protected void disableUI(boolean disable) {
        generateButton.setDisable(disable);
        color1Section.disableUI(disable);
        color2Section.disableUI(disable);
    }

    protected void resetUI() {
        progressBar.progressProperty().unbind();
        progressBar.setProgress(0);
        progressBox.setVisible(false);
        progressBar.setVisible(false);
        cancelButton.setVisible(false);
        generateButton.setVisible(true);
        disableUI(false);
        currentTask = null;
    }

}

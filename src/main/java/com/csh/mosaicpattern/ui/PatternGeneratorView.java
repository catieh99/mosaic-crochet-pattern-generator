package com.csh.mosaicpattern.ui;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PatternGeneratorView extends BorderPane {

    private ImageView imagePreview;
    private UploadPhotoView uploadSection;
    private PatternSettingsView settingsSection;

    // Uploaded Image
    private BufferedImage uploadedImage;

    public PatternGeneratorView() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        // Left side: Image preview
        imagePreview = new ImageView();
        imagePreview.setPreserveRatio(true);
        imagePreview.setFitWidth(400);
        imagePreview.setFitHeight(400);

        // Right side: Upload section
        uploadSection = new UploadPhotoView(10, this::onUploadedImage);

        // Right side: Settings section
        settingsSection = new PatternSettingsView(15, this::getUploadedImage, this::onPatternGenerated,
                this::onGenerationCancelled,
                this::onGenerationFailed, this::disableUI);
    }

    private void setupLayout() {
        // Left panel with image preview
        StackPane leftPanel = new StackPane(imagePreview);
        leftPanel.setPadding(new Insets(20));
        leftPanel.setStyle("-fx-background-color: #f0f0f0;");
        leftPanel.setMinWidth(450);

        // Right panel with upload and settings
        VBox rightPanel = new VBox(20);
        rightPanel.setPadding(new Insets(20));
        rightPanel.getChildren().addAll(uploadSection, settingsSection);
        rightPanel.setMinWidth(300);

        // Set panels in BorderPane
        this.setLeft(leftPanel);
        this.setCenter(rightPanel);
    }

    private void onUploadedImage(Image image) {
        this.uploadedImage = SwingFXUtils.fromFXImage(image, null);
        setImagePreview(image);
    }

    private void onPatternGenerated(Image image) {
        setImagePreview(image);
        resetUI();
    }

    private void onGenerationCancelled(Void unused) {
        resetUI();
    }

    private void onGenerationFailed(Exception e) {
        resetUI();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pattern Generation Failed");
        alert.setHeaderText("An error occurred while generating the pattern.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    private void disableUI(boolean disable) {
        uploadSection.disableUI(disable);
        settingsSection.disableUI(disable);
    }

    private void resetUI() {
        settingsSection.resetUI();
        uploadSection.disableUI(false);
    }

    private void setImagePreview(Image image) {
        imagePreview.setImage(image);
    }

    public ImageView getImagePreview() {
        return imagePreview;
    }

    public VBox getSettingsSection() {
        return settingsSection;
    }

    private BufferedImage getUploadedImage() {
        return uploadedImage;
    }
}

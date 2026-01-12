package com.csh.mosaicpattern.ui;

import java.io.File;
import java.util.function.Consumer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * UI component for uploading photos.
 */
public class UploadPhotoView extends VBox {

    // UI Elements
    private Label fileNameLabel;
    private Button uploadButton;

    private Consumer<Image> onImageUploaded;

    public UploadPhotoView(double size, Consumer<Image> onImageUploaded) {
        super(size);
        this.onImageUploaded = onImageUploaded;
        initializeUI();
    }

    private void initializeUI() {
        setPadding(new Insets(15));
        setStyle(
                "-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        Label uploadLabel = new Label("Upload Image");
        uploadLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER_LEFT);

        uploadButton = new Button("Choose Image...");
        uploadButton.setOnAction(e -> handleImageUpload());

        fileNameLabel = new Label("No file selected");
        fileNameLabel.setStyle("-fx-text-fill: #888888;");

        buttonRow.getChildren().addAll(uploadButton, fileNameLabel);
        getChildren().addAll(uploadLabel, buttonRow);
    }

    private void handleImageUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp"));

        File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            fileNameLabel.setText(selectedFile.getName());
            fileNameLabel.setStyle("-fx-text-fill: #666666; -fx-underline: true;");
            if (onImageUploaded != null) {
                onImageUploaded.accept(image);
            }
        }
    }

    protected void disableUI(boolean disable) {
        uploadButton.setDisable(disable);
    }

}

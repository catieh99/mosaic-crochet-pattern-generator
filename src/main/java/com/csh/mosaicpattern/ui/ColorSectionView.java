package com.csh.mosaicpattern.ui;

import com.csh.mosaicpattern.util.ColorUtil;

import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorSectionView extends VBox {

    private ColorPicker colorPicker;
    private TextField hexField;
    private TextField rgbField;
    private Rectangle colorPreview;

    public ColorSectionView(double size, String colorLabel, Color defaultColor) {
        super(size);

        Label label = new Label(colorLabel);
        label.setStyle("-fx-font-weight: bold;");

        // Color preview rectangle
        colorPreview = new Rectangle(40, 40);
        colorPreview.setFill(defaultColor);
        colorPreview.setStroke(Color.GRAY);
        colorPreview.setStrokeWidth(1);

        // Color picker
        colorPicker = new ColorPicker(defaultColor);
        colorPicker.setPrefWidth(150);

        // Hex field
        HBox hexBox = new HBox(5);
        hexBox.setAlignment(Pos.CENTER_LEFT);
        Label hexLabel = new Label("Hex:");
        hexField = new TextField(ColorUtil.toHexString(defaultColor));
        hexField.setPrefWidth(100);
        hexBox.getChildren().addAll(hexLabel, hexField);

        // RGB field
        HBox rgbBox = new HBox(5);
        rgbBox.setAlignment(Pos.CENTER_LEFT);
        Label rgbLabel = new Label("RGB:");
        rgbField = new TextField(ColorUtil.toRgbString(defaultColor));
        rgbField.setPrefWidth(120);
        rgbBox.getChildren().addAll(rgbLabel, rgbField);

        // Setup event handlers
        setupColorEventHandlers(colorPicker, hexField, rgbField, colorPreview);

        HBox previewAndPicker = new HBox(10);
        previewAndPicker.setAlignment(Pos.CENTER_LEFT);
        previewAndPicker.getChildren().addAll(colorPreview, colorPicker);

        getChildren().addAll(label, previewAndPicker, hexBox, rgbBox);
    }

    private void setupColorEventHandlers(ColorPicker picker, TextField hexField, TextField rgbField,
            Rectangle preview) {
        // Color picker changes update fields and preview
        picker.setOnAction(e -> {
            Color color = picker.getValue();
            hexField.setText(ColorUtil.toHexString(color));
            rgbField.setText(ColorUtil.toRgbString(color));
            preview.setFill(color);
        });

        // Hex field changes update picker, RGB field, and preview
        hexField.setOnAction(e -> {
            try {
                Color color = Color.web(hexField.getText());
                picker.setValue(color);
                rgbField.setText(ColorUtil.toRgbString(color));
                preview.setFill(color);
            } catch (Exception ex) {
                // Invalid hex, revert to current color
                hexField.setText(ColorUtil.toHexString(picker.getValue()));
            }
        });

        // RGB field changes update picker, hex field, and preview
        rgbField.setOnAction(e -> {
            try {
                String[] parts = rgbField.getText().replaceAll("[^0-9,]", "").split(",");
                if (parts.length == 3) {
                    int r = Integer.parseInt(parts[0].trim());
                    int g = Integer.parseInt(parts[1].trim());
                    int b = Integer.parseInt(parts[2].trim());
                    Color color = Color.rgb(r, g, b);
                    picker.setValue(color);
                    hexField.setText(ColorUtil.toHexString(color));
                    preview.setFill(color);
                }
            } catch (Exception ex) {
                // Invalid RGB, revert to current color
                rgbField.setText(ColorUtil.toRgbString(picker.getValue()));
            }
        });
    }

    protected void disableUI(boolean disable) {
        colorPicker.setDisable(disable);
        hexField.setDisable(disable);
        rgbField.setDisable(disable);

    }

    public Color getSelectedColor() {
        return colorPicker.getValue();
    }
}

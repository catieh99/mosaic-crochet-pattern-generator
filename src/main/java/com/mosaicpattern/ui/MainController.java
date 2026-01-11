package com.mosaicpattern.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for the main UI view.
 */
public class MainController {

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // Initialize UI components
    }

    /**
     * Updates the status label with the given message.
     *
     * @param message the status message to display
     */
    public void updateStatus(String message) {
        if (statusLabel != null) {
            statusLabel.setText(message);
        }
    }
}

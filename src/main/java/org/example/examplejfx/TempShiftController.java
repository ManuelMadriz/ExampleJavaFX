package org.example.examplejfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TempShiftController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
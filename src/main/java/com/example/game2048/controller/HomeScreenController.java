package com.example.game2048.controller;

import com.example.game2048.Game2048App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

// Controller for the Home Screen
public class HomeScreenController {

    @FXML
    private Button startButton; // Start button from FXML

    private Game2048App app; // Reference to the main application

    // Setter to inject the application instance
    public void setApp(Game2048App app) {
        this.app = app;
    }

    // Initialize method: sets action for Start button
    @FXML
    private void initialize() {
        startButton.setOnAction(event -> {
            try {
                app.showGameScreen(); // Launch the game screen
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
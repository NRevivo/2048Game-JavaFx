package com.example.game2048.controller;

import com.example.game2048.Game2048App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeScreenController {

    @FXML
    private Button startButton;

    private Game2048App app;

    public void setApp(Game2048App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        startButton.setOnAction(event -> {
            try {
                app.showGameScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
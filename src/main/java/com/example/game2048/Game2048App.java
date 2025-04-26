package com.example.game2048;

import com.example.game2048.controller.Game2048Controller;
import com.example.game2048.controller.HomeScreenController;
import com.example.game2048.model.Game2048Model;
import com.example.game2048.view.Game2048View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// Main application class for the 2048 game
public class Game2048App extends Application {

    private static Game2048App instance; // Singleton instance

    // Constructor: sets the static instance
    public Game2048App() {
        instance = this;
    }

    // Returns the singleton instance
    public static Game2048App getInstance() {
        return instance;
    }

    private Stage primaryStage;  // Main application window

    // Entry point: starts the app and shows the home screen
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        showHomeScreen();
    }

    // Loads and displays the Home screen
    private void showHomeScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/game2048/HomeScreen.fxml"));
        Parent root = loader.load();

        HomeScreenController controller = loader.getController();
        controller.setApp(Game2048App.getInstance());

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048 Game");
        primaryStage.show();
    }

    // Loads and displays the Game screen
    public void showGameScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/game2048/Game2048View.fxml"));
        Parent root = loader.load();

        Game2048View view = loader.getController();
        Game2048Model model = new Game2048Model();
        Game2048Controller controller = new Game2048Controller(model, view);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048 Game");

        controller.play();
    }

    // Launches the application
    public static void main(String[] args) {
        launch();
    }
}
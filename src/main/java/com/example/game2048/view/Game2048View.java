package com.example.game2048.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.util.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.game2048.controller.HomeScreenController;
import com.example.game2048.Game2048App;

// View class responsible for displaying the board and handling user input
public class Game2048View extends Observable {


    @FXML
    private Pane gamePane; // Pane that holds the tiles
    @FXML
    private Label scoreLabel; // Displays the current score

    private TileNode[][] tiles = new TileNode[4][4]; // 2D array for the visual tiles

    // Initializes the game board and key event handling
    @FXML
    public void initialize() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                TileNode tile = new TileNode(0);
                tile.setLayoutX(j * 100);
                tile.setLayoutY(i * 100);
                tiles[i][j] = tile;
                gamePane.getChildren().add(tile);
            }
        }

        gamePane.setFocusTraversable(true);

        // Handle keyboard arrow key events
        gamePane.setOnKeyPressed(event -> {
            int command = -1;
            switch (event.getCode()) {
                case UP: command = 0; break;
                case DOWN: command = 1; break;
                case LEFT: command = 2; break;
                case RIGHT: command = 3; break;
                default: break;
            }

            if (command != -1) {
                setChanged();
                notifyObservers(command); // Send user command to the Controller
            }
        });
    }

    // Updates the tile display based on the current board state
    public void displayData(int[][] data) {
        System.out.println("Data to display:");
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            System.out.print(data[i][j] + " ");
        }
        System.out.println();
    }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j].setValue(data[i][j]);
            }
        }
    }

    // Shows a pop-up message when the player wins
    public void showWinMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victory!");
        alert.setHeaderText(null);
        alert.setContentText("🎉 Congratulations! You won 2048! 🎉");
        alert.showAndWait();
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/game2048/HomeScreen.fxml"));
             Parent root = loader.load();
             HomeScreenController controller = loader.getController();
             controller.setApp(Game2048App.getInstance());
             Scene scene = new Scene(root, 500, 500);
             Stage stage = (Stage) gamePane.getScene().getWindow();
             stage.setScene(scene);

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    // Shows a pop-up message when the player loses
    public void showGameOverMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over!");
        alert.setHeaderText("Loser");
        alert.setContentText("try again next time!");
        alert.showAndWait();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/game2048/HomeScreen.fxml"));
            Parent root = loader.load();
            HomeScreenController controller = loader.getController();
            controller.setApp(Game2048App.getInstance());
            Scene scene = new Scene(root, 500, 500);
            Stage stage = (Stage) gamePane.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Updates the score label
    public void updateScore(int score) {
    scoreLabel.setText("Score: " + score);
    }

    // Getter for the game pane (used by the controller)
    public Pane getGamePane() {
        return gamePane;
    }




}

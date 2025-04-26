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

public class Game2048View extends Observable {


    @FXML
    private Pane gamePane;
    @FXML
    private Label scoreLabel;

    private TileNode[][] tiles = new TileNode[4][4];

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
                notifyObservers(command); // שליחת פקודת המשתמש ל-Controller
            }
        });
    }

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
             Stage stage = (Stage) gamePane.getScene().getWindow();  // לוקחים את החלון הנוכחי
             stage.setScene(scene);

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

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
    public void updateScore(int score) {
    scoreLabel.setText("Score: " + score);
    }

    public Pane getGamePane() {
        return gamePane;
    }




}

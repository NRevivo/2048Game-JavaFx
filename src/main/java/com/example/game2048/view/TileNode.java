package com.example.game2048.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// TileNode represents a visual tile in the game board
public class TileNode extends StackPane {

    private Rectangle background; // Background rectangle
    private Text valueText;        // Text showing the tile's value
    private int value;             // Numeric value of the tile

    // Constructor: initializes the tile node with a given value
    public TileNode(int value) {
        this.value = value;
        background = new Rectangle(80, 80);
        background.setArcWidth(15);
        background.setArcHeight(15);
        updateStyle();

        valueText = new Text(value > 0 ? String.valueOf(value) : "");
        valueText.setFont(Font.font(24));
        valueText.setFill(Color.BLACK);

        getChildren().addAll(background, valueText);
    }

    // Updates the value of the tile and refreshes its style
    public void setValue(int value) {
        this.value = value;
        valueText.setText(value > 0 ? String.valueOf(value) : "");
        updateStyle();
    }

    // Updates the tile background color based on its value
    private void updateStyle() {
        Color color;
        switch (value) {
            case 2: color = Color.BEIGE; break;
            case 4: color = Color.BISQUE; break;
            case 8: color = Color.ORANGE; break;
            case 16: color = Color.DARKORANGE; break;
            case 32: color = Color.CORAL; break;
            case 64: color = Color.TOMATO; break;
            case 128: color = Color.GOLD; break;
            case 256: color = Color.GOLDENROD; break;
            case 512: color = Color.LIGHTGREEN; break;
            case 1024: color = Color.DARKSEAGREEN; break;
            case 2048: color = Color.LIGHTBLUE; break;
            default: color = Color.LIGHTGRAY; break;
        }
        background.setFill(color);
    }
}

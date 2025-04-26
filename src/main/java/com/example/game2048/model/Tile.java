package com.example.game2048.model;

// Tile class representing a single tile in the game
public class Tile {
    private int value;    // Tile value (0 if empty)
    private boolean merged; // Whether this tile was merged in the current move

    // Constructor: initializes an empty tile
    public Tile() {
        value = 0;
        merged = false;
    }

    // Returns the value of the tile
    public int getValue() {
        return value;
    }
    // Sets the value of the tile
    public void setValue(int value) {
        this.value = value;
    }
    // Returns whether the tile has been merged
    public boolean isMerged() {
        return merged;
    }
    // Sets the merged status
    public void setMerged(boolean merged) {
        this.merged = merged;
    }


}

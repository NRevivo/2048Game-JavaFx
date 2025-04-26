package com.example.game2048.model;

public class Tile {
    private int value;
    private boolean merged;

    public Tile() {
        value = 0;
        merged = false;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public boolean isMerged() {
        return merged;
    }
    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public void clear() {
        value = 0;
        merged = false;
    }


}

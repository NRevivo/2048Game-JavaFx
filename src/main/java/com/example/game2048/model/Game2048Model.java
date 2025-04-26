package com.example.game2048.model;

import java.util.Observable;

// Model class representing the game logic and state
public class Game2048Model extends Observable {
    private Board board; // Board that manages the grid and moves

    // Constructor: Initializes the board and notifies observers
    public Game2048Model() {
        board = new Board();
        setChanged();
        notifyObservers();
    }

    // Performs a move to the left; notifies observers if successful
    public boolean moveLeft() {
        boolean moved = board.moveLeft();
        if (moved) {
            setChanged();
            notifyObservers();
        }
        return moved;
    }

    // Performs a move to the right; notifies observers if successful
    public boolean moveRight() {
        boolean moved = board.moveRight();
        if (moved) {
            setChanged();
            notifyObservers();
        }
        return moved;
    }

    // Performs a move upward; notifies observers if successful
    public boolean moveUp() {
        boolean moved = board.moveUp();
        if (moved) {
            setChanged();
            notifyObservers();
        }
        return moved;
    }

    // Performs a move downward; notifies observers if successful
    public boolean moveDown() {
        boolean moved = board.moveDown();
        if (moved) {
            setChanged();
            notifyObservers();
        }
        return moved;
    }

    // Returns the current board state as a 2D array of tile values
    public int[][] getData() {
        Tile[][] tiles = board.getBoard();
        int[][] data = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = tiles[i][j].getValue();
            }
        }
        return data;
    }

    // Checks if the player has reached the winning tile (2048)
    public boolean isWinner() {
        return board.isWinner();
    }

    // Checks if no more moves are available (game over)
    public boolean isGameOver() {
        return board.isGameOver();
    }

    // Returns the current score
    public int getScore() {
        return board.getScore();
    }
}
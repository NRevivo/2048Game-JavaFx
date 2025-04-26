package com.example.game2048.model;

import java.util.Observable;

public class Game2048Model extends Observable {
    private Board board;

    public Game2048Model() {
        board = new Board();
        setChanged();
        notifyObservers();
    }

    public boolean moveLeft() {
        boolean moved = board.moveLeft();
        if (moved) {
            setChanged();
            notifyObservers();
        }
        return moved;
    }

    public boolean moveRight() {
        boolean moved = board.moveRight();
        if (moved) {
            setChanged();
            notifyObservers();
        }
        return moved;
    }

    public boolean moveUp() {
        boolean moved = board.moveUp();
        if (moved) {
            setChanged();
            notifyObservers();
        }
        return moved;
    }

    public boolean moveDown() {
        boolean moved = board.moveDown();
        if (moved) {
            setChanged();
            notifyObservers();
        }
        return moved;
    }

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

    public boolean isWinner() {
        return board.isWinner();
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    public int getScore() {
        return board.getScore();
    }
}
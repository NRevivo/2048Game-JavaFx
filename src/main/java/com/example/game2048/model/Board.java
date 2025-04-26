package com.example.game2048.model;


import java.util.*;

public class Board {
    private Tile[][] board;
    private static final int size = 4;
    private int score;
    private Random random = new Random();

    // Constructors
    public Board() {
        board = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Tile();
            }
        }
        score = 0;
        addRandomTile();
        addRandomTile();
    }

    // Getters and Setters
    public Tile[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Public Methods
    public boolean moveLeft(){
        boolean moved = false;
        resetMerged();

        for (int i = 0; i < size; i++) {
            Tile[] row = board[i];
            List<Tile> filtered = extractNotEmptyRow(row);
            List<Integer> merged = mergeTiles(filtered);
            boolean rowMoved = applyMergedRow(row, merged);

            if (rowMoved) {
                moved = true;
            }
        }

        if (moved) {
            addRandomTile();
        }


        return moved;
    }

    public boolean moveRight(){
        boolean moved = false;
        resetMerged();

        for (int i = 0; i < size; i++) {
            Tile[] row = board[i];
            List<Tile> reversed = reverseRow(row);

            List<Tile> filtered = extractNotEmptyRow(reversed.toArray(new Tile[0]));
            List<Integer> merged = mergeTiles(filtered);
            Collections.reverse(merged);

            boolean rowMoved = applyMergedRow(row, merged);
            if (rowMoved) {
                moved = true;
            }
        }
        if (moved)
            addRandomTile();


        return moved;
    }

    public boolean moveUp(){
        boolean moved = false;
        resetMerged();

        for (int col = 0; col < size; col++) {
            List<Tile> column = extractNotEmptyColumn(col);
            List<Integer> merged = mergeTiles(column);
            boolean colMoved = applyMergedColumn(col, merged);
            if (colMoved) {
                moved = true;
            }
        }

        if (moved) {
            addRandomTile();
        }


        return moved;
    }

    public boolean moveDown(){
        boolean moved = false;
        resetMerged();

        for (int col = 0; col < size; col++) {
            List<Tile> reversed = reverseColumn(col);
            List<Tile> filtered = new ArrayList<>();
            for (Tile tile : reversed) {
                if (tile.getValue() != 0) {
                    filtered.add(tile);
                }
            }
            List<Integer> merged = mergeTiles(filtered);
            Collections.reverse(merged);
            boolean colMoved = applyMergedColumn(col, merged);
            if (colMoved) {
                moved = true;
            }
        }

        if (moved) {
            addRandomTile();
        }


        return moved;
    }

     public void resetMerged(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].setMerged(false);
            }
        }
    }

    public boolean isWinner(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isGameOver(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile tile = board[i][j];
                if (tile.getValue() == 0) {
                    return false;
                }
                if (i < size - 1 && tile.getValue() == board[i + 1][j].getValue()) {
                    return false;
                }
                if (j < size - 1 && tile.getValue() == board[i][j + 1].getValue()) {
                    return false;
                }
            }
        }
        return true;
}


    // Private Helpers

    // Tile manipulation
    private void addRandomTile(){
        ArrayList<int[]> emtypos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null || board[i][j].getValue() == 0) {
                    emtypos.add(new int[]{i, j});
                }
            }
        }
        if (emtypos.size() > 0) {
            int[] pos = emtypos.get(random.nextInt(emtypos.size()));
            board[pos[0]][pos[1]].setValue(2);
        }
    }



    // Row/Column operations

    List<Tile> extractNotEmptyRow(Tile[] row){
        ArrayList<Tile> filtered = new ArrayList<>();
        for (Tile tile : row) {
            if (tile.getValue() != 0) {
                filtered.add(tile);
            }
        }
        return filtered;
    }

    List<Tile> extractNotEmptyColumn(int colIndex) {
        ArrayList<Tile> filtered = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (board[i][colIndex].getValue() != 0) {
                filtered.add(board[i][colIndex]);
            }
        }
        return filtered;
    }

    List<Tile> reverseRow(Tile[] row){
        int size = row.length;
        List<Tile> reversed = new ArrayList<>(size);
        for (int i = size - 1; i >= 0; i--) {
            reversed.add(row[i]);
        }
        return reversed;
    }

    List<Tile> reverseColumn(int colIndex){
        List<Tile> reversed = new ArrayList<>(size);
        for (int i = size - 1; i >= 0; i--) {
            reversed.add(board[i][colIndex]);
        }
        return reversed;
    }

    boolean applyMergedRow(Tile[] row, List<Integer> merged){
        boolean moved = false;

        for (int j = 0; j < size; j++) {
            if (row[j].getValue() != merged.get(j)) {
                moved = true;
                row[j].setValue(merged.get(j));
                row[j].setMerged(false);
            }
        }

        return moved;
    }

    boolean applyMergedColumn(int colIndex, List<Integer> merged) {
        boolean moved = false;
        for (int i = 0; i < size; i++) {
            if (board[i][colIndex].getValue() != merged.get(i)) {
                moved = true;
                board[i][colIndex].setValue(merged.get(i));
                board[i][colIndex].setMerged(false);
            }
        }
        return moved;
    }

    List<Integer> mergeTiles(List<Tile> filtered){
        ArrayList<Integer> merged = new ArrayList<>();
        for (int j = 0; j < filtered.size(); j++) {
            int currentValue = filtered.get(j).getValue();
            if (j + 1 < filtered.size()
                    && currentValue == filtered.get(j + 1).getValue()
                    && !filtered.get(j).isMerged()
                    && !filtered.get(j + 1).isMerged()) {
                merged.add(currentValue * 2);
                score += currentValue * 2;
                filtered.get(j).setMerged(true);
                filtered.get(j + 1).setMerged(true);
                j++;
            }
            else {
                merged.add(currentValue);
            }
        }
        while (merged.size() < size) {merged.add(0);}

        return merged;
    }

}

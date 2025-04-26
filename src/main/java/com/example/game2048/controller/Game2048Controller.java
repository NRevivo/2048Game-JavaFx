package com.example.game2048.controller;

import com.example.game2048.model.Game2048Model;
import com.example.game2048.view.Game2048View;

import java.util.Observable;
import java.util.Observer;

public class Game2048Controller implements Observer {
    private Game2048Model model;
    private Game2048View view;

    public Game2048Controller(Game2048Model model, Game2048View view) {
        this.model = model;
        this.view = view;
        model.addObserver(this);
        view.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o == view) {
            if (arg instanceof Integer) {
                int command = (Integer) arg;
                boolean moved = false;
                switch (command) {
                    case 0: moved = model.moveUp(); break;
                    case 1: moved = model.moveDown(); break;
                    case 2: moved = model.moveLeft(); break;
                    case 3: moved = model.moveRight(); break;
                    default: System.out.println("Invalid command");
                }
                if (moved) {
                    if (model.isWinner()) {
                        view.showWinMessage();
                        stopGame();
                    }

                    if (model.isGameOver()){
                        view.showGameOverMessage();
                        stopGame();
                    }
                }
            }
        } else if (o == model) {
            view.displayData(model.getData());
            view.updateScore(model.getScore());
        }
    }


    public void play() {
        view.displayData(model.getData());
        view.updateScore(model.getScore());
    }
    public void stopGame() {
        if (view.getGamePane() != null && view.getGamePane().getScene() != null) {
                view.getGamePane().getScene().setOnKeyPressed(null);
        }
        model.deleteObserver(this);
        view.deleteObserver(this);
    }

}

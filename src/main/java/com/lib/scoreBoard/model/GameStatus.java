package com.lib.scoreBoard.model;

public enum GameStatus {
    INIT(1), //Initiated
    STARTED(2), // Started
    FINISHED(3); // Finished
    private final int number;

    GameStatus(int i) {
        number = i;
    }
}

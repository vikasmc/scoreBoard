package com.lib.scoreBoard.model;

public enum GameResult {
    WIN(1), //Win
    LOSS(2),// Loss
    TIE(3), //Both Team Won.
    UNDECIDED(4) // Not Yet Decided.
    ;
    private final int number;

    GameResult(int i) {
        number = i;
    }
}

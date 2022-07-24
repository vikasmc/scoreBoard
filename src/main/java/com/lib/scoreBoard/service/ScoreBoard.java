package com.lib.scoreBoard.service;

import java.util.List;

public interface ScoreBoard {

    void startGame(String homeTeam, String awayTeam);

    boolean finishGame();

    boolean updateScore(int homeScore, int awayScore);

    List<Game> displaySummary();

}

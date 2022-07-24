package com.lib.scoreBoard.service;

public interface Game {

    boolean startGame(String homeTeamName, String awayTeamName);

    boolean finishGame();

    String displayStatistics();

    boolean updateScore(int homeScore, int awayScore);

    int getTotalScore();


}

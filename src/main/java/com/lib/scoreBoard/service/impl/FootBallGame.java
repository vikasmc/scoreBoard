package com.lib.scoreBoard.service.impl;

import com.lib.scoreBoard.service.Game;
import lombok.Getter;

@Getter
public class FootBallGame implements Game {

    private String awayTeam;
    private String homeTeam;
    private int totalScore;

    public FootBallGame() {
    }

    @Override
    public boolean startGame(String homeTeamName, String awayTeamName) {
        this.awayTeam = awayTeamName;
        this.homeTeam = homeTeamName;
        this.totalScore = 0;
        return true;
    }

    @Override
    public boolean finishGame() {
        return true;
    }

    @Override
    public String displayStatistics() {
        return this.homeTeam + " " + this.homeTeam + " : " + this.awayTeam + " " + this.awayTeam;
    }

    @Override
    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public boolean updateScore(int homeScore, int awayScore) {
        return false;
    }

}

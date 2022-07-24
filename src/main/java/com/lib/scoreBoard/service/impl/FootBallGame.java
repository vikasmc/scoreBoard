package com.lib.scoreBoard.service.impl;

import com.lib.scoreBoard.model.AwayTeam;
import com.lib.scoreBoard.model.GameResult;
import com.lib.scoreBoard.model.GameStatus;
import com.lib.scoreBoard.model.HomeTeam;
import com.lib.scoreBoard.service.Game;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FootBallGame implements Game {

    private AwayTeam awayTeam;
    private HomeTeam homeTeam;
    private String gameId;
    private int totalScore;
    private GameStatus gameStatus;

    public FootBallGame() {
        this.gameStatus = GameStatus.INIT;
    }

    @Override
    public String startGame(String homeTeamName, String awayTeamName) {
        this.gameStatus = GameStatus.STARTED;
        this.awayTeam = new AwayTeam(awayTeamName);
        this.homeTeam = new HomeTeam(homeTeamName);
        this.totalScore = 0;
        this.gameId = UUID.randomUUID().toString();
        return gameId;
    }

    @Override
    public boolean finishGame() {
        if (GameStatus.STARTED.equals(gameStatus)) {
            this.gameStatus = GameStatus.FINISHED;
            this.finalizeResult();
            return true;
        }
        return false;
    }

    @Override
    public String displayStatistics() {
        return this.homeTeam.getName() + " " + this.homeTeam.getScore() + " : " + this.awayTeam.getName() + " " + this.awayTeam.getScore();
    }

    @Override
    public GameStatus getStatus() {
        return this.gameStatus;
    }

    @Override
    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public void finalizeResult() {
        if (homeTeam.getScore() > awayTeam.getScore()) {
            homeTeam.finalizeResult(GameResult.WIN);
            awayTeam.finalizeResult(GameResult.LOSS);
        } else if (homeTeam.getScore() < awayTeam.getScore()) {
            homeTeam.finalizeResult(GameResult.LOSS);
            awayTeam.finalizeResult(GameResult.WIN);
        } else {
            homeTeam.finalizeResult(GameResult.TIE);
            awayTeam.finalizeResult(GameResult.TIE);
        }
    }

    @Override
    public boolean updateScore(int homeScore, int awayScore) {
        if (GameStatus.STARTED.equals(gameStatus)) {
            totalScore = homeScore + awayScore;
            homeTeam.updateScore(homeScore);
            awayTeam.updateScore(awayScore);
            return true;
        }
        return false;
    }

}

package com.lib.scoreBoard.service.impl;

import com.lib.scoreBoard.factory.GameFactory;
import com.lib.scoreBoard.service.Game;
import com.lib.scoreBoard.model.GameType;
import com.lib.scoreBoard.service.ScoreBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class FootBallBoard implements ScoreBoard {

    public List<Game> gameBoard = new ArrayList<>();
    public String ScoreBoardId;

    @Override
    public void startGame(String homeTeamName, String awayTeamName) {
        Game newFootBallGame = new GameFactory().getNewGame(GameType.FOOTBALL);
        newFootBallGame.startGame(homeTeamName, awayTeamName);
        gameBoard.add(newFootBallGame);
    }

    @Override
    public boolean finishGame() {
        return true;
    }

    @Override
    public boolean updateScore(int homeScore, int awayScore) {
        return true;
    }

    @Override
    public List<Game> displaySummary() {
        return gameBoard;
    }

}
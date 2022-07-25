package com.lib.scoreBoard.service.impl;

import com.lib.scoreBoard.factory.GameFactory;
import com.lib.scoreBoard.model.GameType;
import com.lib.scoreBoard.service.Game;
import com.lib.scoreBoard.service.ScoreBoard;
import com.lib.scoreBoard.util.Utility;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class FootBallBoard implements ScoreBoard {

    public Map<String, Game> gameBoard = Collections.synchronizedMap(new LinkedHashMap<>());
    public String ScoreBoardId;

    @Override
    public String startGame(String homeTeamName, String awayTeamName) {
        Game newFootBallGame = new GameFactory().getNewGame(GameType.FOOTBALL);
        String gameKey = newFootBallGame.startGame(homeTeamName, awayTeamName);
        gameBoard.put(gameKey, newFootBallGame);
        return gameKey;
    }

    @Override
    public boolean finishGame(String gameId) {
        Game footBallGame = gameBoard.get(gameId);
        if (footBallGame != null) {
            footBallGame.finishGame();
        }
        return gameBoard.remove(gameId) != null;
    }

    @Override
    public synchronized boolean updateScore(String gameId, int homeScore, int awayScore) {
        if (homeScore > -1 && awayScore > -1) {
            Game footBallGame = gameBoard.get(gameId);
            if (footBallGame != null) {
                footBallGame.updateScore(homeScore, awayScore);
                gameBoard.remove(gameId);
                gameBoard.put(gameId, footBallGame);
                return true;
            }
        }
        return false;
    }

    @Override
    public Game getGame(String gameId) {
        return gameBoard.get(gameId);
    }

    @Override
    public synchronized List<Game> displaySummary() {
        return gameBoard.entrySet().stream().map(e -> e.getValue()).sorted(Utility.GAME_COMPARATOR).collect(Collectors.toList());
    }

}
package com.lib.scoreBoard.factory;

import com.lib.scoreBoard.service.Game;
import com.lib.scoreBoard.model.GameType;
import com.lib.scoreBoard.service.impl.FootBallGame;

public class GameFactory {

    public Game getNewGame(GameType gameType) {
        if (GameType.FOOTBALL.equals(gameType)) {
            return new FootBallGame();
        } else {
            return null;
        }

    }
}

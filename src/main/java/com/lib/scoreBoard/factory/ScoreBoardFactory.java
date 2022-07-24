package com.lib.scoreBoard.factory;

import com.lib.scoreBoard.model.BoardGameType;
import com.lib.scoreBoard.service.ScoreBoard;
import com.lib.scoreBoard.service.impl.FootBallBoard;

public class ScoreBoardFactory {

    public ScoreBoard getScoreBoard(BoardGameType boardGameType) {
        if (BoardGameType.FOOTBALL.equals(boardGameType)) {
            return new FootBallBoard();
        } else {
            return null;
        }

    }
}

package com.lib.scoreBoard.factory;

import com.lib.scoreBoard.model.BoardGameType;
import com.lib.scoreBoard.service.ScoreBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreBoardFactoryTest {

    ScoreBoardFactory scoreBoardFactory = new ScoreBoardFactory();

    @Test
    void getScoreBoard() {
        ScoreBoard scoreBoard = scoreBoardFactory.getScoreBoard(BoardGameType.FOOTBALL);
        Assertions.assertNotNull(scoreBoard);
    }

    @Test
    public void testScoreBoardWithIncorrectValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardFactory.getScoreBoard(BoardGameType.valueOf("123"));
        });
    }
}
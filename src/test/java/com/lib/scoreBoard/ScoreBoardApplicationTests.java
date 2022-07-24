package com.lib.scoreBoard;

import com.lib.scoreBoard.factory.ScoreBoardFactory;
import com.lib.scoreBoard.model.BoardGameType;
import com.lib.scoreBoard.service.ScoreBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreBoardApplicationTests {

    @Test
    void createScoreBoard() {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        Assertions.assertNotNull(scoreBoard.displaySummary());
    }

    @Test
    void addAndRemoveGameFromScoreBoard() {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        scoreBoard.startGame("Arsenal", "ManU");
        Assertions.assertEquals(1, scoreBoard.displaySummary().size());
    }

}

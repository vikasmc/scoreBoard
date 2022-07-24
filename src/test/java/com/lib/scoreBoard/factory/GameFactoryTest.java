package com.lib.scoreBoard.factory;

import com.lib.scoreBoard.model.GameResult;
import com.lib.scoreBoard.model.GameStatus;
import com.lib.scoreBoard.model.GameType;
import com.lib.scoreBoard.service.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameFactoryTest {

    private final GameFactory gameFactory = new GameFactory();

    @Test
    void getNewGame() {
        Game newGame = gameFactory.getNewGame(GameType.FOOTBALL);
        Assertions.assertNotNull(newGame);
        Assertions.assertEquals(GameStatus.INIT,newGame.getStatus());
    }

    @Test
    public void testNewGameWithIncorrectValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameFactory.getNewGame(GameType.valueOf("123"));
        });
    }
}
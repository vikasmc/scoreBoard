package com.lib.scoreBoard;

import com.lib.scoreBoard.factory.ScoreBoardFactory;
import com.lib.scoreBoard.model.BoardGameType;
import com.lib.scoreBoard.model.GameStatus;
import com.lib.scoreBoard.service.Game;
import com.lib.scoreBoard.service.ScoreBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        String gameId = scoreBoard.startGame("Arsenal", "ManU");
        System.out.println(gameId);
        Assertions.assertNotNull(scoreBoard.getGame(gameId));
        Assertions.assertEquals(1, scoreBoard.displaySummary().size());
        scoreBoard.finishGame(gameId);
        Assertions.assertNull(scoreBoard.getGame(gameId));
        Assertions.assertEquals(0, scoreBoard.displaySummary().size());
    }

    @Test
    void startAndFinishGame() {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        String gameId = scoreBoard.startGame("Arsenal", "ManU");
        Game footBallGame = scoreBoard.getGame(gameId);
        Assertions.assertEquals(GameStatus.STARTED, footBallGame.getStatus());
        Assertions.assertEquals(0, footBallGame.getTotalScore());
        Assertions.assertEquals("Arsenal 0 : ManU 0", footBallGame.displayStatistics());
        scoreBoard.finishGame(gameId);
        Assertions.assertEquals(GameStatus.FINISHED, footBallGame.getStatus());
    }

    @Test
    void addScoreToGame() {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        String gameId = scoreBoard.startGame("Arsenal", "ManU");
        Game footBallGame = scoreBoard.getGame(gameId);
        Assertions.assertEquals(GameStatus.STARTED, footBallGame.getStatus());
        scoreBoard.updateScore(gameId, 0, 1);
        Assertions.assertEquals(1, footBallGame.getTotalScore());
        scoreBoard.updateScore(gameId, 1, 1);
        Assertions.assertEquals(2, footBallGame.getTotalScore());
        scoreBoard.updateScore(gameId, 2, 1);
        Assertions.assertEquals(3, footBallGame.getTotalScore());
        scoreBoard.updateScore(gameId, 2, 2);
        Assertions.assertEquals(4, footBallGame.getTotalScore());
        scoreBoard.finishGame(gameId);
        Assertions.assertEquals(GameStatus.FINISHED, footBallGame.getStatus());
    }

    @Test
    void testDisplayOrderAsMostRecentGameForSameScore() throws InterruptedException {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        String gameId1 = scoreBoard.startGame("Leopards", "Cougars");
        String gameId2 = scoreBoard.startGame("Crusaders", "Accelerate");
        String gameId3 = scoreBoard.startGame("Bonnies", "Fighting Crusaders");
        String gameId4 = scoreBoard.startGame("The Boozers", "Kicking You");
        String gameId5 = scoreBoard.startGame("Cheetahs", "Volcanoes");
        String gameId6 = scoreBoard.startGame("Unicorns", "Madd Dogs");
        Assertions.assertEquals(6, scoreBoard.displaySummary().size());
        Assertions.assertEquals("Unicorns 0 : Madd Dogs 0", scoreBoard.displaySummary().get(0).displayStatistics());
        Assertions.assertEquals("Cheetahs 0 : Volcanoes 0", scoreBoard.displaySummary().get(1).displayStatistics());
        scoreBoard.updateScore(gameId1, 1, 5);
        scoreBoard.updateScore(gameId2, 1, 4);
        scoreBoard.updateScore(gameId3, 1, 4);
        scoreBoard.updateScore(gameId4, 1, 3);
        scoreBoard.updateScore(gameId5, 1, 2);
        scoreBoard.updateScore(gameId6, 1, 1);
        Assertions.assertEquals("Leopards 1 : Cougars 5", scoreBoard.displaySummary().get(0).displayStatistics());
        Assertions.assertEquals("Bonnies 1 : Fighting Crusaders 4", scoreBoard.displaySummary().get(1).displayStatistics());
        Assertions.assertEquals("Crusaders 1 : Accelerate 4", scoreBoard.displaySummary().get(2).displayStatistics());
        Assertions.assertEquals("The Boozers 1 : Kicking You 3", scoreBoard.displaySummary().get(3).displayStatistics());
        scoreBoard.finishGame(gameId1);
        scoreBoard.updateScore(gameId6, 1, 1);
        scoreBoard.updateScore(gameId2, 1, 4);
        scoreBoard.updateScore(gameId3, 1, 4);
        scoreBoard.updateScore(gameId4, 1, 3);
        scoreBoard.updateScore(gameId5, 1, 2);
        scoreBoard.updateScore(gameId6, 1, 1);
        Assertions.assertEquals("Bonnies 1 : Fighting Crusaders 4", scoreBoard.displaySummary().get(0).displayStatistics());
        Assertions.assertEquals("Crusaders 1 : Accelerate 4", scoreBoard.displaySummary().get(1).displayStatistics());
        Assertions.assertEquals("The Boozers 1 : Kicking You 3", scoreBoard.displaySummary().get(2).displayStatistics());
        scoreBoard.updateScore(gameId3, 1, 4);
        scoreBoard.updateScore(gameId2, 1, 4);
        Assertions.assertEquals("Crusaders 1 : Accelerate 4", scoreBoard.displaySummary().get(0).displayStatistics());
        Assertions.assertEquals("Bonnies 1 : Fighting Crusaders 4", scoreBoard.displaySummary().get(1).displayStatistics());
    }

    @Test
    public void testScoreBoardReturnFalseForInvalidScore() {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        String gameId = scoreBoard.startGame("USA", "UK");
        boolean result = scoreBoard.updateScore(gameId, -1, 0);
        Assertions.assertFalse(result);
        result = scoreBoard.updateScore(gameId, 0, -1);
        Assertions.assertFalse(result);
        result = scoreBoard.updateScore(gameId, -1, -1);
        Assertions.assertFalse(result);
        result = scoreBoard.updateScore(gameId, 0, 0);
        Assertions.assertTrue(result);
        result = scoreBoard.updateScore(gameId, 1, 1);
        Assertions.assertTrue(result);
    }

    @Test
    public void testInvalidInput() {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        String gameId = scoreBoard.startGame("USA", "UK");
        boolean flag = scoreBoard.updateScore("randomNumber", 0, 1);
        Assertions.assertFalse(flag);
        flag = scoreBoard.updateScore(gameId, 0, 1);
        Assertions.assertTrue(flag);
        Game randomGame = scoreBoard.getGame("randomNumber");
        Assertions.assertNull(randomGame);
        randomGame = scoreBoard.getGame(gameId);
        Assertions.assertNotNull(randomGame);
        flag = scoreBoard.finishGame("randomNumber");
        Assertions.assertFalse(flag);
        flag = scoreBoard.finishGame(gameId);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testBoardWithMultiThread() throws InterruptedException {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<String> runnableTask = () -> {
            String gameId = scoreBoard.startGame("USA-1", "IND-1");
            String gameId2 = scoreBoard.startGame("USA-2", "IND-2");
            String gameId3 = scoreBoard.startGame("USA-3", "IND-3");
            String gameId4 = scoreBoard.startGame("USA-4", "IND-4");
            String gameId5 = scoreBoard.startGame("USA-4", "IND-5");
            scoreBoard.displaySummary();
            scoreBoard.updateScore(gameId, 0, 1);
            scoreBoard.displaySummary();
            scoreBoard.updateScore(gameId, 1, 2);
            scoreBoard.displaySummary();
            scoreBoard.finishGame(gameId);
            scoreBoard.displaySummary();
            String gameId6 = scoreBoard.startGame("USA-4", "IND-6");
            String gameId7 = scoreBoard.startGame("USA-4", "IND-7");
            scoreBoard.finishGame(gameId5);
            scoreBoard.updateScore(gameId2, 1, 1);
            scoreBoard.displaySummary();
            scoreBoard.updateScore(gameId2, 2, 2);
            scoreBoard.displaySummary();
            scoreBoard.finishGame(gameId2);
            scoreBoard.finishGame(gameId7);
            scoreBoard.finishGame(gameId6);
            scoreBoard.displaySummary();
            System.out.println();
            return "SUCCESS";
        };
        Future<String> submit1 = executorService.submit(runnableTask);
        Future<String> submit2 = executorService.submit(runnableTask);
        Thread.sleep(800);
        Future<String> submit3 = executorService.submit(runnableTask);
        Thread.sleep(800);
        Future<String> submit4 = executorService.submit(runnableTask);
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }
        Assertions.assertEquals(8, scoreBoard.displaySummary().size());

    }

    @Test
    public void testBoardWithMultiThreadsWaitTime() throws InterruptedException {
        ScoreBoardFactory factory = new ScoreBoardFactory();
        ScoreBoard scoreBoard = factory.getScoreBoard(BoardGameType.FOOTBALL);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<String> runnableTask = () -> {
            String name = Thread.currentThread().getName();
            String gameId1 = scoreBoard.startGame("USA-1-" + name, "IND-1" + name);
            String gameId2 = scoreBoard.startGame("USA-2" + name, "IND-2" + name);
            scoreBoard.displaySummary();
            scoreBoard.displaySummary();
            scoreBoard.updateScore(gameId1, 0, 1);
            scoreBoard.displaySummary();
            scoreBoard.updateScore(gameId1, 0, 1);
            scoreBoard.displaySummary();
            scoreBoard.updateScore(gameId1, 0, 1);
            scoreBoard.displaySummary();
            scoreBoard.updateScore(gameId1, 0, 1);
            scoreBoard.displaySummary();
            return "SUCCESS";
        };
        Future<String> submit1 = executorService.submit(runnableTask);
        Thread.sleep(800);
        Future<String> submit2 = executorService.submit(runnableTask);
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }
        Assertions.assertEquals(4, scoreBoard.displaySummary().size());

    }


}

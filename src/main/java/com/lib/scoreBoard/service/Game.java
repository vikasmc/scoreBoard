package com.lib.scoreBoard.service;

import com.lib.scoreBoard.model.GameStatus;

public interface Game {

    /**
     * Method to create a Game.
     * The Game will be started automatically and the sore will be set to 0 by Default.
     *
     * @param homeTeamName String
     * @param awayTeamName String
     * @return Uid for the game created.
     */
    String startGame(String homeTeamName, String awayTeamName);

    /**
     * Method to finish the Game and finalize the result of the game.
     * This will remove the Game from the Score Board.
     *
     * @return boolean to see the Game has been finished or not.
     */
    boolean finishGame();

    /**
     * Method to Display the statistics of the Game.
     * The Game should be Active for this to work.
     *
     * @return String which has team and its Score information.
     */
    String displayStatistics();

    /**
     * Method to Check the Status of the Game.
     *
     * @return INIT, STARTED, FINISHED.
     */
    GameStatus getStatus();

    /**
     * Method to Update the Score of the Game.
     *
     * @param homeScore Score for the Home Team.
     * @param awayScore Score for the Away Team.
     * @return boolean to see the Game has been updated or not.
     */
    boolean updateScore(int homeScore, int awayScore);

    /**
     * Method to return Total score of the Game.
     *
     * @return TotalScore (HomeScore + AwayScore)
     */
    int getTotalScore();

    /**
     * Method to finalize who won and who lost the Game.
     */
    void finalizeResult();

}
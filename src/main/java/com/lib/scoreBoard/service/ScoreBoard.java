package com.lib.scoreBoard.service;

import java.util.List;

public interface ScoreBoard {

    /**
     * Method to create a Game.
     * The Game will be started automatically and the sore will be set to 0 by Default.
     *
     * @param homeTeam String
     * @param awayTeam String
     * @return Uid for the game created.
     */
    String startGame(String homeTeam, String awayTeam);

    /**
     * Method to finish the Game and finalize the result of the game.
     * This will remove the Game from the Score Board.
     *
     * @return boolean to see the Game has been finished or not.
     */
    boolean finishGame(String gameId);

    /**
     * Method to Update the Score of the Game.
     *
     * @param gameId Unique ID of the Game for which we need to update the Score for.
     * @param homeScore Score for the Home Team. Must be grater than -1.
     * @param awayScore Score for the Away Team. Must be Grater than -1.
     * @return boolean to see the Game has been updated or not.
     */
    boolean updateScore(String gameId, int homeScore, int awayScore);


    /**
     * Method to Get the Game Information.
     *
     * @param gameId Unique ID of the Game for which we need to update the Score for.
     * @return Game Get All the information about the Game.
     */
    Game getGame(String gameId);

    /**
     * Method to Display the statistics of all the Games.
     * The Game should be Started for it to be included in the List.
     *
     * @return List of Game information.
     */
    List<Game> displaySummary();

}

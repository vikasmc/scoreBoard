package com.lib.scoreBoard.model;

import lombok.Getter;

@Getter
public class HomeTeam extends Team {

    private GameResult gameResult = GameResult.UNDECIDED;

    public HomeTeam(String teamName) {
        super(teamName);
    }

    @Override
    public GameResult getStatus() {
        return gameResult;
    }

    public void finalizeResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }
}

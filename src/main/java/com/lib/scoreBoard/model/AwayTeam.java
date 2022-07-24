package com.lib.scoreBoard.model;

import lombok.Getter;

@Getter
public class AwayTeam extends Team {

    private GameResult gameResult = GameResult.UNDECIDED;

    public AwayTeam(String teamName) {
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

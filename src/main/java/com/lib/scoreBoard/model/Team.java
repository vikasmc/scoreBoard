package com.lib.scoreBoard.model;

import lombok.Getter;

@Getter
public abstract class Team {

    private final String name;
    private int score;

    public Team(String teamName) {
        this.name = teamName;
    }

    public abstract GameResult getStatus();

    public void updateScore(int score) {
        this.score = score;
    }

}

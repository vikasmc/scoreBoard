package com.lib.scoreBoard.util;

import com.lib.scoreBoard.service.Game;

import java.util.Comparator;

public class Utility {

    public static final Comparator<Game> GAME_COMPARATOR = (o1, o2) -> {
        if (o2.getTotalScore() == o1.getTotalScore()) {
            return -1;
        }
        return o2.getTotalScore() - o1.getTotalScore();
    };

}
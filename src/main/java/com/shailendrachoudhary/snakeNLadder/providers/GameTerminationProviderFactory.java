package com.shailendrachoudhary.snakeNLadder.providers;

import com.shailendrachoudhary.snakeNLadder.model.Game;

public class GameTerminationProviderFactory {

    public static GameTerminationProvider getDefault(Game game){
        return new FirstPlayerWinTerminationProvider(game);
    }
}

package com.shailendrachoudhary.snakeNLadder.providers;

import com.shailendrachoudhary.snakeNLadder.model.Player;

import java.util.List;

public class PlayerSelectionProviderFactory {

    public static PlayerSelectionProvider getDefault(List<Player> players){
        return new QueueBasedPlayerSelectionProvider(players);
    }
}

package com.shailendrachoudhary.snakeNLadder.providers;

import com.shailendrachoudhary.snakeNLadder.model.Player;

public interface PlayerSelectionProvider {

    /**
     * @return current Player playing game
     */
    Player currentPlayer();

    Player nextPlayer();
}

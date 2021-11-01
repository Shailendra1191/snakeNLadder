package com.shailendrachoudhary.snakeNLadder.providers;

import com.shailendrachoudhary.snakeNLadder.constants.GameStatus;
import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import com.shailendrachoudhary.snakeNLadder.model.Game;

class FirstPlayerWinTerminationProvider implements GameTerminationProvider{
    private Game game;

    public FirstPlayerWinTerminationProvider(Game game){
        this.game = game;
    }

    @Override
    public boolean canTerminate() {
        return game.getPlayers().stream().anyMatch(p-> p.getPlayerStatus()== PlayerStatus.WON);
    }

    @Override
    public void terminate() {
        game.setGameStatus(GameStatus.TERMINATED);
    }
}

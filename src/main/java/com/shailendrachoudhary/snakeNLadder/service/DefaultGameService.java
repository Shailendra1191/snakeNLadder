package com.shailendrachoudhary.snakeNLadder.service;
import com.shailendrachoudhary.snakeNLadder.exceptions.GameOverException;
import com.shailendrachoudhary.snakeNLadder.exceptions.NoActiveGameFoundException;
import com.shailendrachoudhary.snakeNLadder.model.Game;
import com.shailendrachoudhary.snakeNLadder.model.Player;

public class DefaultGameService implements GameService{
    private Game game;

    public DefaultGameService(Game game){
        this.game = game;
    }

    @Override
    public Player play() {
        if(game == null){
            throw new NoActiveGameFoundException();
        }
        if(game.isGameTerminated()){
            throw new GameOverException();
        }

        Player player = game.getCurrentPlayer();

        int count = game.rollADice();
        int nextPos = player.getCurrentPosition()+count;
        game.movePlayer(player,nextPos);

        game.nextPlayer(player);

        return player;
        
    }

    public Player getCurrentPlayer(){
        if(game.isGameTerminated()){
            throw new GameOverException();
        }
        return game.getCurrentPlayer();
    }

}

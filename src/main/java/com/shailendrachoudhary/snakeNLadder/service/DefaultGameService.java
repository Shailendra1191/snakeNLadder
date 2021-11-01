package com.shailendrachoudhary.snakeNLadder.service;
import com.shailendrachoudhary.snakeNLadder.constants.GameStatus;
import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import com.shailendrachoudhary.snakeNLadder.exceptions.GameOverException;
import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidGameException;
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
            throw new InvalidGameException("No Active Game Exists. Please start a new one");
        }
        if(game.isGameTerminated()){
            throw new GameOverException();
        }

        Player player = game.getCurrentPlayer();

        int count = game.rollADice();
        int nextPos = player.getCurrentPosition()+count;
        game.movePlayer(player,nextPos);

        game.nextPlayer();

        return player;
        
    }

    public Player getCurrentPlayer(){
        if(game.isGameTerminated()){
            throw new GameOverException();
        }
        return game.getCurrentPlayer();
    }

    public void terminateGame(){
        game.setGameStatus(GameStatus.TERMINATED);
    }

}

package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.constants.GameStatus;
import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import com.shailendrachoudhary.snakeNLadder.exceptions.GameOverException;
import com.shailendrachoudhary.snakeNLadder.providers.GameTerminationProvider;
import com.shailendrachoudhary.snakeNLadder.providers.GameTerminationProviderFactory;
import com.shailendrachoudhary.snakeNLadder.providers.PlayerSelectionProvider;
import com.shailendrachoudhary.snakeNLadder.providers.PlayerSelectionProviderFactory;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class Game {

    @Getter
    private Board board;
    private List<Player> players;
    private Dice dice;
    @Getter @Setter
    private GameStatus gameStatus;

    private PlayerSelectionProvider playerSelector;
    private GameTerminationProvider gameTerminator;

    public Game(@NonNull Board board, @NonNull List<Player> players, @NonNull Dice dice){
        this.board = board;
        this.players = new LinkedList<>(players);
        this.dice = dice;
        gameStatus = GameStatus.ACTIVE;
        playerSelector = PlayerSelectionProviderFactory.getDefault(players);
        gameTerminator = GameTerminationProviderFactory.getDefault(this);
    }

    public Game(@NonNull Board board, @NonNull List<Player> players, @NonNull Dice dice, @NonNull PlayerSelectionProvider selector){
        this(board,players,dice);
        playerSelector = selector;
    }

    public Game(@NonNull Board board, @NonNull List<Player> players, @NonNull Dice dice,
                @NonNull PlayerSelectionProvider selector, @NonNull GameTerminationProvider terminator){
        this(board,players,dice,selector);
        gameTerminator = terminator;
    }

    public int rollADice() {
        return dice.roll();
    }

    public void movePlayer(Player player, int nextPos){
        if(isGameTerminated()){
            throw new GameOverException();
        }

        if(board.isValidSpot(nextPos)){
            player.moveTo(nextPos);
            if(board.hasSnake(nextPos)){
                nextPos = board.getSnake(nextPos).bite();
                movePlayer(player,nextPos);
            }else if(board.hasLadder(nextPos)){
                nextPos = board.getLadder(nextPos).climb();
                movePlayer(player,nextPos);
            }
        }

        if(board.isFinalSpot(player.getCurrentPosition())){
            player.setPlayerStatus(PlayerStatus.WON);
            if(gameTerminator.canTerminate()){
                gameTerminator.terminate();
            }
        }

    }

    public Player getCurrentPlayer(){
        return playerSelector.currentPlayer();
    }

    public Player nextPlayer() {
        return playerSelector.nextPlayer();
    }

    public boolean isGameTerminated(){
        return gameStatus== GameStatus.TERMINATED;
    }

    public List<Player> getPlayers(){
        return players;
    }
    
}

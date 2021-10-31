package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.constants.GameStatus;
import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import com.shailendrachoudhary.snakeNLadder.exceptions.GameOverException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Game {

    @Getter
    private Board board;
    private Queue<Player> players;
    private Dice dice;
    @Getter @Setter
    private GameStatus gameStatus;

    public Game(@NonNull Board board, @NonNull List<Player> players, @NonNull Dice dice){
        this.board = board;
        this.players = new LinkedList<>(players);
        this.dice = dice;
        gameStatus = GameStatus.ACTIVE;
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
                board.getSnake(nextPos).bite(player);
            }else if(board.hasLadder(nextPos)){
                board.getLadder(nextPos).climb(player);
            }
        }

        if(board.isFinalSpot(nextPos)){
            player.setPlayerStatus(PlayerStatus.WON);
        }

    }

    public Player getCurrentPlayer(){
        return players.peek();
    }

    public Player nextPlayer(Player currentPlayer) {
        if(currentPlayer != null && currentPlayer.getPlayerStatus() == PlayerStatus.ACTIVE){
            players.add(currentPlayer);
        }
        return players.poll();
    }

    public boolean isGameTerminated(){
        return gameStatus== GameStatus.TERMINATED;
    }

    public List<Player> getPlayers(){
        return players.stream().collect(Collectors.toList());
    }
    
}

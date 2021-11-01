package com.shailendrachoudhary.snakeNLadder.providers;

import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidGameException;
import com.shailendrachoudhary.snakeNLadder.model.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class QueueBasedPlayerSelectionProvider implements PlayerSelectionProvider{
    Queue<Player> players;

    public QueueBasedPlayerSelectionProvider(List<Player> players){
        this.players = new LinkedList<>(players);
    }

    @Override
    public Player currentPlayer() {
        if(players.isEmpty()){
            throw new InvalidGameException("empty player List");
        }
        return players.peek();
    }

    @Override
    public Player nextPlayer() {
        if(players.isEmpty()){
            throw new InvalidGameException("empty player List");
        }

        Player currentPlayer = players.poll();
        if(currentPlayer.getPlayerStatus()== PlayerStatus.ACTIVE){
            players.add(currentPlayer);
        }
        return players.peek();
    }
}

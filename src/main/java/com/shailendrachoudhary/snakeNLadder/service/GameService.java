package com.shailendrachoudhary.snakeNLadder.service;

import com.shailendrachoudhary.snakeNLadder.constants.GameStatus;
import com.shailendrachoudhary.snakeNLadder.model.Dice;
import com.shailendrachoudhary.snakeNLadder.model.Player;

public interface GameService {

    void newGame(int numOfPlayers, Dice dice);

    Player play();

    Player getCurrentPlayer();

    void terminateGame();

    GameStatus getGameStatus();
}

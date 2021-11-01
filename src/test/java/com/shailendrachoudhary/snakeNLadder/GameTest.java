package com.shailendrachoudhary.snakeNLadder;

import com.shailendrachoudhary.snakeNLadder.constants.GameStatus;
import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import com.shailendrachoudhary.snakeNLadder.exceptions.GameOverException;
import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidGameException;
import com.shailendrachoudhary.snakeNLadder.model.*;
import com.shailendrachoudhary.snakeNLadder.service.DefaultGameService;
import com.shailendrachoudhary.snakeNLadder.service.GameService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameTest {
    GameService gameService;
    Dice mockDice;

    @BeforeAll
    public void setUp(){
        List<Player> palyers = Arrays.asList(new Player("player1"),
                new Player("player2"));

        List<Snake> snakes = Arrays.asList(new Snake(98,2),
                new Snake(92,70),
                new Snake(76,45),
                new Snake(23,5),
                new Snake(50,28)
        );

        List<Ladder> ladders = Arrays.asList(
                new Ladder(9,13),
                new Ladder(6,97),
                new Ladder(26,48),
                new Ladder(73,91),
                new Ladder(25,79)
        );

        Board board = new Board(100, snakes, ladders);

        mockDice = mock(Dice.class);

        Game game = new Game(board,palyers,mockDice);

        gameService = new DefaultGameService(game);

    }

    @Test
    public void testTwoPlayerGame(){
        System.out.println("Starting a new game...");

        Player p = gameService.getCurrentPlayer();

        System.out.println("Player "+p.getName()+ " turn");
        System.out.println("Player "+p.getName()+ " throws 6");
        when(mockDice.roll()).thenReturn(6);
        gameService.play();

        assertEquals(p.getCurrentPosition(),97);
        System.out.println("Player "+p.getName()+ " moved to "+p.getCurrentPosition());

        p = gameService.getCurrentPlayer();

        assertEquals(p.getName(),"player2");
        System.out.println("Player "+p.getName()+ " turn");

        when(mockDice.roll()).thenReturn(23);
        System.out.println("Player "+p.getName()+ " throws 23");

        p = gameService.play();

        assertEquals(p.getCurrentPosition(),5);
        System.out.println("Player "+p.getName()+ " moved to "+p.getCurrentPosition());

        p = gameService.getCurrentPlayer();
        assertEquals(p.getName(),"player1");

        when(mockDice.roll()).thenReturn(2);

        p=gameService.play();
        assertEquals(p.getCurrentPosition(),99);
        assertEquals(p.getPlayerStatus(), PlayerStatus.WON);

        System.out.println("Player "+p.getName()+" player status "+p.getPlayerStatus());
    }

    @Test
    public void testGameOver(){
        Game game = new Game(mock(Board.class),Arrays.asList(),mock(Dice.class));

        game.setGameStatus(GameStatus.TERMINATED);

        assertThrows(GameOverException.class, ()-> game.movePlayer(mock(Player.class),10));
    }

    @Test
    public void testInvalidGame(){
        Game game = new Game(mock(Board.class),Arrays.asList(),new DefaultDice());
        assertThrows(InvalidGameException.class, ()-> game.getCurrentPlayer() );

        assertThrows(InvalidGameException.class, ()-> game.nextPlayer());
    }

}

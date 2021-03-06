package com.shailendrachoudhary.snakeNLadder;

import com.shailendrachoudhary.snakeNLadder.constants.GameStatus;
import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import com.shailendrachoudhary.snakeNLadder.exceptions.GameOverException;
import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidGameException;
import com.shailendrachoudhary.snakeNLadder.model.*;
import com.shailendrachoudhary.snakeNLadder.service.DefaultGameService;
import com.shailendrachoudhary.snakeNLadder.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameTest {
    GameService gameService;
    Dice mockDice;
    Board board;
    List<Player> players;
    List<Ladder> ladders;
    List<Snake> snakes;

    @BeforeEach
    public void setUp(){
        gameService = new DefaultGameService();
        mockDice = mock(Dice.class);
        snakes = Arrays.asList(new Snake(98,2),
                new Snake(92,70),
                new Snake(76,45),
                new Snake(23,5),
                new Snake(50,28)
        );

        ladders = Arrays.asList(
                new Ladder(9,13),
                new Ladder(6,97),
                new Ladder(26,48),
                new Ladder(73,91),
                new Ladder(25,79)
        );

        players = Arrays.asList(new Player("player1"),
                new Player("player2"));

        board = new Board(100, snakes, ladders);
    }


    @Test
    public void testTwoPlayerGame(){
        System.out.println("Starting a new game...");

        Game game  = new Game(board,players,mockDice);

        ReflectionTestUtils.setField(gameService,"game",game);

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

        when(mockDice.roll()).thenReturn(3);

        p=gameService.play();
        assertEquals(p.getCurrentPosition(),100);
        assertEquals(p.getPlayerStatus(), PlayerStatus.WON);

        System.out.println("Player "+p.getName()+" player status "+p.getPlayerStatus());
    }

    @Test
    public void testGameOver(){
        Game gameLocal = new Game(mock(Board.class),Arrays.asList(),mock(Dice.class));

        gameLocal.setGameStatus(GameStatus.TERMINATED);

        assertThrows(GameOverException.class, ()-> gameLocal.movePlayer(mock(Player.class),10));
    }

    @Test
    public void testInvalidGame(){
        Game gameLocal = new Game(mock(Board.class),Arrays.asList(),DiceFactory.getDefault());
        assertThrows(InvalidGameException.class, ()-> gameLocal.getCurrentPlayer() );

        assertThrows(InvalidGameException.class, ()-> gameLocal.nextPlayer());
    }

    @Test
    public void testMultipleRounds(){
        for(int i=0;i<10;i++){
            System.out.println("Starting new Game...");
            gameService.newGame(2,DiceFactory.getDefault());
            while (gameService.getGameStatus()==GameStatus.ACTIVE){
                System.out.println("Current Player:"+gameService.getCurrentPlayer().getName());
                Player p = gameService.play();
                System.out.println("Player "+p.getName()+" moved to "+p.getCurrentPosition());
            }
            assertEquals(gameService.getGameStatus(),GameStatus.TERMINATED);
            System.out.println("Game Over !\n");
        }
    }

    @Test
    public void testFirstPlayerWinGameTermination(){
            System.out.println("Starting new Game...");

            gameService = new DefaultGameService();
            gameService.newGame(2,DiceFactory.getDefault());
            while (gameService.getGameStatus()==GameStatus.ACTIVE){
                System.out.println("Current Player:"+gameService.getCurrentPlayer().getName());
                Player p = gameService.play();

                if(p.getPlayerStatus()==PlayerStatus.WON){
                    assertEquals(gameService.getGameStatus(),GameStatus.TERMINATED);
                }
                System.out.println("Player "+p.getName()+" moved to "+p.getCurrentPosition());
            }

            System.out.println("Game Over !\n");
    }



}

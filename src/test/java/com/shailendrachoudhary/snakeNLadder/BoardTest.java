package com.shailendrachoudhary.snakeNLadder;

import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import com.shailendrachoudhary.snakeNLadder.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoardTest {

    @Test
    public void testSnakeBite(){
        List<Player> players = Arrays.asList(
                new Player("palyer1")
        );

        List<Snake> snakes = Arrays.asList(
                new Snake(5,2)
        );

        List<Ladder> ladders = Arrays.asList();

        Board board = new Board(10,snakes,ladders);

        Dice mockDice = mock(Dice.class);
        when(mockDice.roll()).thenReturn(5);

        assertEquals(mockDice.roll(),5);

        Game game  = new Game(board,players,mockDice);


        game.movePlayer(players.get(0),players.get(0).getCurrentPosition()+mockDice.roll());

        System.out.println("New position: "+players.get(0).getCurrentPosition());

        assertEquals(players.get(0).getCurrentPosition(),2);

    }

    @Test
    public void testLadderJump(){

        List<Player> players = Arrays.asList(
                new Player("palyer1")
        );

        List<Snake> snakes = Arrays.asList(
        );

        List<Ladder> ladders = Arrays.asList(
                new Ladder(5,9)
        );

        Board board = new Board(10,snakes,ladders);

        Dice mockDice = mock(Dice.class);
        when(mockDice.roll()).thenReturn(5);

        assertEquals(mockDice.roll(),5);

        Game game  = new Game(board,players,mockDice);


        game.movePlayer(players.get(0),players.get(0).getCurrentPosition()+mockDice.roll());

        System.out.println("New position: "+players.get(0).getCurrentPosition());

        assertEquals(players.get(0).getCurrentPosition(),9);

    }

    @Test
    public void testWinningMove(){
        List<Player> players = Arrays.asList(
                new Player("palyer1")
        );

        List<Snake> snakes = Arrays.asList();

        List<Ladder> ladders = Arrays.asList();

        Board board = new Board(10,snakes,ladders);

        Dice mockDice = mock(Dice.class);
        when(mockDice.roll()).thenReturn(9);

        assertEquals(mockDice.roll(),9);

        Game game  = new Game(board,players,mockDice);


        game.movePlayer(players.get(0),players.get(0).getCurrentPosition()+mockDice.roll());

        System.out.println("New position: "+players.get(0).getCurrentPosition());

        assertEquals(players.get(0).getCurrentPosition(),9);

        // assertion to test the status of current player changed to WON
        assertEquals(players.get(0).getPlayerStatus(), PlayerStatus.WON);
    }
}

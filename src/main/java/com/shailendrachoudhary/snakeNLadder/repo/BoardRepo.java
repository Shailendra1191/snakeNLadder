package com.shailendrachoudhary.snakeNLadder.repo;

import com.shailendrachoudhary.snakeNLadder.model.Board;
import com.shailendrachoudhary.snakeNLadder.model.Ladder;
import com.shailendrachoudhary.snakeNLadder.model.Snake;

import java.util.Arrays;
import java.util.List;

public class BoardRepo {
    private Board defaultBoard;

    public BoardRepo(){
        List<Snake> snakes = Arrays.asList(
                new Snake(17,7),
                new Snake(62,19),
                new Snake(64,60),
                new Snake(54,34),
                new Snake(87,36),
                new Snake(93,73),
                new Snake(95,75),
                new Snake(98,79)
        );

        List<Ladder> ladders = Arrays.asList(
                new Ladder(1,38),
                new Ladder(4,14),
                new Ladder(9,31),
                new Ladder(21,42),
                new Ladder(28,84),
                new Ladder(51,67),
                new Ladder(72,91),
                new Ladder(80,99)
        );

        defaultBoard = new Board(100,snakes,ladders);
    }
    public Board getDefaultBoard(){
        return defaultBoard;
    }
}

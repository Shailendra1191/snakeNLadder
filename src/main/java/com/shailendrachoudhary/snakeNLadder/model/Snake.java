package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidSnakeException;
import lombok.Getter;

@Getter
public class Snake {
    private int start;
    private int end;

    public Snake(int start, int end){
        if(start<=end){
            throw new InvalidSnakeException(start,end);
        }
        this.start = start;
        this.end = end;
    }

    public void bite(Player player){
        player.moveTo(this.end);
    }
}

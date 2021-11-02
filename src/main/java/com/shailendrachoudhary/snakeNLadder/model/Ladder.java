package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidLadderException;
import lombok.Getter;

@Getter
public class Ladder {
    private int start;
    private int end;

    public Ladder(int start, int end){
        if(end<=start){
            throw new InvalidLadderException(start,end);
        }
        this.start = start;
        this.end = end;
    }

    public int climb(){
        return end;
    }
}

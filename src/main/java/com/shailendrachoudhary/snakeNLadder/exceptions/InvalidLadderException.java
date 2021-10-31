package com.shailendrachoudhary.snakeNLadder.exceptions;

public class InvalidLadderException extends IllegalArgumentException{
    public InvalidLadderException(int start, int end){
        super(String.format("Invalid Ladder : bottom at %s top at %s"));
    }

    public InvalidLadderException(String msg) {
        super(msg);
    }
}

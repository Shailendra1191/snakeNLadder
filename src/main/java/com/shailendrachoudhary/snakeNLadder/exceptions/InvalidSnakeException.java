package com.shailendrachoudhary.snakeNLadder.exceptions;

public class InvalidSnakeException extends IllegalArgumentException{
    public InvalidSnakeException(int start, int end){
        super(String.format("Invalid snake, mouth at %s and tail at %s",start,end));
    }
}

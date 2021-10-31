package com.shailendrachoudhary.snakeNLadder.exceptions;

public class NoActiveGameFoundException extends RuntimeException {

    public NoActiveGameFoundException(){
        super("No active game found. please start a new game");
    }
}

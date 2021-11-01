package com.shailendrachoudhary.snakeNLadder.providers;

public interface GameTerminationProvider {

    boolean canTerminate();

    void terminate();
}

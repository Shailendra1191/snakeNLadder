package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.utility.RandomUtils;

public class DefaultDice implements Dice {
    @Override
    public int roll() {
        return RandomUtils.nextRandomInt(1,6);
    }
}

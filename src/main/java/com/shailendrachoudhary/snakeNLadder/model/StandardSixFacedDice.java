package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.utility.RandomUtils;

class StandardSixFacedDice implements Dice {
    @Override
    public int roll() {
        return RandomUtils.nextRandomInt(1,6);
    }
}

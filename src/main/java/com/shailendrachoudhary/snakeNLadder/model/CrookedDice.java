package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.utility.RandomUtils;

class CrookedDice implements Dice{
    @Override
    public int roll() {
        int random = RandomUtils.nextRandomInt(1,5);
        return random*2;
    }
}

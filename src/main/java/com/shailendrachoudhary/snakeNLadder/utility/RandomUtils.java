package com.shailendrachoudhary.snakeNLadder.utility;

import java.util.Random;

public class RandomUtils {
    static Random randomGenerator = new Random();

    public static int nextRandomInt(int start, int end){
        return randomGenerator.nextInt(end)+start;
    }
}
package com.shailendrachoudhary.snakeNLadder.utility;

import java.util.Random;

public class RandomUtils {
    static Random randomGenerator = new Random(System.currentTimeMillis());

    /**
     * @param start
     * @param end
     * @return number between start and end (both inclusive)
     */
    public static int nextRandomInt(int start, int end){
        return randomGenerator.nextInt(end-start+1)+start;
    }
}

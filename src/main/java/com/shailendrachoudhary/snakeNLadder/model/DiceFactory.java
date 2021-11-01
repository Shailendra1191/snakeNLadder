package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.constants.DiceType;

import java.util.HashMap;
import java.util.Map;

public class DiceFactory {

    private static Map<DiceType,Dice> diceRepo = new HashMap<>();

    static {
        diceRepo.put(DiceType.STANDARD,new StandardSixFacedDice());
        diceRepo.put(DiceType.CROOKED, new CrookedDice());
    }

    public static Dice getDefault(){
        return diceRepo.get(DiceType.STANDARD);
    }

    public static Dice getDice(DiceType type){
        return diceRepo.get(type);
    }
}

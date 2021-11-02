package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidLadderException;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Board {

    private final int size;
    private final Map<Integer,Snake> snakes;
    private final Map<Integer,Ladder> ladders;


    public Board(int size, @NonNull List<Snake> snakes, @NonNull List<Ladder> ladders) {
        this.size = size;
        this.snakes = snakes.stream().collect(Collectors.toMap(Snake::getStart, Function.identity()));
        this.ladders = ladders.stream().collect(Collectors.toMap(Ladder::getStart, Function.identity()));

        validate();
    }

    public Snake getSnake(int pos){
        if(hasSnake(pos)){
            return snakes.get(pos);
        }
        return null;
    }

    public boolean hasSnake(int pos){
        return snakes.containsKey(pos);
    }

    public Ladder getLadder(int pos){
        if(hasLadder(pos)){
            return ladders.get(pos);
        }
        return null;
    }

    public boolean hasLadder(int pos) {
        return ladders.containsKey(pos);
    }

    public boolean isValidSpot(int pos){
        return pos>0 && pos<=size;
    }

    public boolean isFinalSpot(int pos){
        return pos == size;
    }

    public void validate(){
        for(int key : snakes.keySet()){
            if(ladders.containsKey(key)){
                throw new InvalidLadderException("Can't climb the ladder at this point snake will bite you at:"+key);
            }
        }

        // player will fall in loop if snakes mouth is at top of ladder and snakes tail is at bottom
        for(Snake sn : snakes.values()){
            for(Ladder ld: ladders.values()){
                if(sn.getEnd()==ld.getStart() && sn.getStart()==ld.getEnd()){
                    throw new InvalidLadderException("Snake and Ladder forms a loop between "+ld.getStart()+" "+ld.getEnd());
                }
            }
        }

    }
}

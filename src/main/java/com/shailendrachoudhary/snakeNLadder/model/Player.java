package com.shailendrachoudhary.snakeNLadder.model;

import com.shailendrachoudhary.snakeNLadder.constants.PlayerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {
    private String name;
    private int currentPosition;
    @Getter @Setter
    private PlayerStatus playerStatus;

    public Player(String userName){
        this.name = userName;
        this.currentPosition=0;
        playerStatus = PlayerStatus.ACTIVE;
    }

    public void moveTo(int nextPos){
        if(playerStatus==PlayerStatus.ACTIVE){
            currentPosition = nextPos;
        }
    }
}

package com.shailendrachoudhary.snakeNLadder.command;

import lombok.Getter;

@Getter
public class Command {
    private CommandName name;
    private String[] params;

    public Command(CommandName name, String args){
        this.name=name;
        params=args.split(" ");
    }
}

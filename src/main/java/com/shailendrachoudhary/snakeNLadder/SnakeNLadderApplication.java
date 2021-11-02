package com.shailendrachoudhary.snakeNLadder;

import com.shailendrachoudhary.snakeNLadder.command.CommandName;
import com.shailendrachoudhary.snakeNLadder.constants.DiceType;
import com.shailendrachoudhary.snakeNLadder.constants.GameStatus;
import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidGameException;
import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidLadderException;
import com.shailendrachoudhary.snakeNLadder.exceptions.InvalidSnakeException;
import com.shailendrachoudhary.snakeNLadder.model.DiceFactory;
import com.shailendrachoudhary.snakeNLadder.model.Player;
import com.shailendrachoudhary.snakeNLadder.service.DefaultGameService;
import com.shailendrachoudhary.snakeNLadder.service.GameService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnakeNLadderApplication {

	private static TextIO textIO = TextIoFactory.getTextIO();
	private static TextTerminal textTerminal = TextIoFactory.getTextTerminal();
	private static GameService gameService= new DefaultGameService();
	private static Player p=null;

	public static void main(String[] args) {
		SpringApplication.run(SnakeNLadderApplication.class, args);

		boolean response = true;
		do{
			do {
				response =doExecute();
			}while(!response || gameService.getGameStatus()== GameStatus.ACTIVE);
			textTerminal.printf("\n%s wins the game\n",p.getName());

		}while(textIO.newBooleanInputReader().read("Want to start new one ? "));
	}

	private static boolean doExecute(){
		try{
			CommandName command = textIO.newEnumInputReader(CommandName.class)
					.read("\nSelect option");
			switch (command){
				case NEW_GAME: {
					int players = textIO.newIntInputReader().read("Please enter number of players");
					DiceType dice = textIO.newEnumInputReader(DiceType.class).read("Select Dice");
					gameService.newGame(players, DiceFactory.getDice(dice));
					textTerminal.printf("\n%s turn\n",gameService.getCurrentPlayer().getName());
					break;
				}
				case PLAY: {
					p = gameService.play();
					textTerminal.printf("\n%s moved to %s\n",p.getName(),p.getCurrentPosition());
					if(gameService.getGameStatus()==GameStatus.ACTIVE)
						textTerminal.printf("\n%s turn",gameService.getCurrentPlayer().getName());
					break;
				}
				case TERMINATE: {
					if(textIO.newBooleanInputReader().read("Do you want to end current Game? ")){
						gameService.terminateGame();
					}
				}
				default: textTerminal.print("\nInvalid selection. try again...");
			}
		}
		catch (InvalidGameException | InvalidLadderException | InvalidSnakeException ex){
			textTerminal.printf("\n%s",ex.getMessage());
			return false;
		}
		return true;
	}

}

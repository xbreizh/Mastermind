package game;

import application.Configuration;

/**
 * That enumeration lists the game status that will be used
 * in the controller switch to get around the different
 * stages of the game.
 * Each status has a string value associated that will be displayed 
 * by the view each time a new status is selected
 * @author Xavier.Lamourec
 *
 */

public enum Status_Game {
	SETUP("Enter the secret code! ( "+Configuration.getNbDigits()+" digits) - "),
	PLAY("Find the code! ("+Configuration.getNbDigits()+" digits) - "),
	ANSWER(""),
	VERDICT("The winner is: "),
	EXIT("Game over!\nPlay again?( Y / N )\n"),
	REPLAY("\nRestart?( Y / N )\n"),
	NO_MORE_TRIES("No more attempts!\n"),
	END("Thanks for playing!\n"), 
	FOUND("Code found!\n");
	
	private String output;
	
	
	private Status_Game(String output){
		this.output=output;
	}
	
	public String getOutput() {
		return output;
	}
	
	
}

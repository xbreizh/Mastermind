package game;

import application.Configuration;

public enum Status_Game {
	SETUP("Enter the secret code! ( "+Configuration.getNbDigits()+" digits) - "),
	PLAY("Find the code! ("+Configuration.getNbDigits()+" digits) - "),
	ANSWER(""),
	EXIT("End of the game!\nDo you want to play again?( Y / N )\n"),
	VERDICT("The winner is: "),
	REPLAY("\nDo you want to restart the game?( Y / N )\n"),
	NO_MORE_TRIES("No more tries available!\n"),
	END("Thanks for playing!"), 
	FOUND("The code has been found!\n");
	
	private String output;
	
	
	Status_Game(String output){
		this.output=output;
	}
	
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	
}

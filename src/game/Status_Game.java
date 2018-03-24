package game;

import application.Configuration;

public enum Status_Game {
	SETUP("Enter the secret code! ( "+Configuration.getNbDigits()+" digits)"),
	PLAY("Find the code! ( "+Configuration.getNbDigits()+" digits)"),
	ANSWER("Answer:"),
	EXIT("End of the game!\nDo you want to play again?( Y / N )"),
	VERDICT("Congratulations, You won!"),
	REPLAY("Do you want to restart the game?( Y / N )"),
	NO_MORE_TRIES("No more tries available!"),
	END("Thanks for playing!"), 
	FOUND("The code has been found!");
	
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

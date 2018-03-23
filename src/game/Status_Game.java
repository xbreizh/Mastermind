package game;

import application.Configuration;

public enum Status_Game {
	SETUP("Enter the secret code! ( "+Configuration.nbDigits+" digits)"),
	PLAY("Find the code!"),
	ANSWER("Answer:"),
	EXIT("End of the game!\nDo you want to play again?"),
	WIN("Congratulations, You won!"),
	REPLAY("Do you want to restart the game?( Y / N )"),
	NO_MORE_TRIES("you don't have anymore tries!"),
	END("Thanks for playing!");
	
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

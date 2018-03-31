package game;

import application.Configuration;

public enum Status_Game {
	SETUP("Enter the secret code! ( "+Configuration.getNbDigits()+" digits) - "),
	PLAY("Find the code! ("+Configuration.getNbDigits()+" digits) - "),
	ANSWER(""),
	VERDICT("The winner is: "),
	EXIT("Game over!\nPlay again?( Y / N )\n"),
	REPLAY("\nRestart?( Y / N )\n"),
	NO_MORE_TRIES("No more attempts!\n"),
	END("Thanks for playing!"), 
	FOUND("Code found!\n");
	
	private String output;
	
	
	private Status_Game(String output){
		this.output=output;
	}
	
	public String getOutput() {
		return output;
	}

//	public void setOutput(String output) {
//		this.output = output;
//	}
	
	
}

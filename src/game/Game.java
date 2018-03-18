package game;

import player.Player;

public abstract class Game {
	Player p1;
	Player p2;
	protected Status_Game status;
	int secretCode;
	int[] secretCodeArray;
	int attempts = 0;
	Configuration conf = Configuration.getConfiguration();
	int max_attempts = conf.getMax_attempts();
	String error = "";
	String input = "";
	String answerToGive = "";
	String output="";

	

	

	String answer = "";

	// Constructor
	public Game(Player p1, Player p2) {

		conf.getConfiguration();
		this.p1 = p1;
		this.p2 = p2;
	}

	// abstracts methods
	abstract void getVerdict(int a, int b);

	public void validSetup() {
		if (input.length() == 0) {
			setError("You haven't typed anything");
		} else {
			int i = Integer.parseInt(input);
			if (i < 1000 || i >= 10000) {
				setError("The code should contain " + conf.getNbDigits() + " digits");
			} else {
				setSecretCode(i);
				secretCodeArray = intToArray(secretCode); // converts secretCode
															// into array
				setStatus(Status_Game.PLAY);
				setError("");
			}
			System.out.println(secretCode);
		}
	}

	public void validInput() {
		if (input.length() == 0) {
			setError("You haven't typed anything");
		} else {
			int i = Integer.parseInt(input);
			if (i < 1000 || i >= 10000) {
				setError("The code should contain " + conf.getNbDigits() + " digits");
			} else {
				setStatus(Status_Game.ANSWER);
				setError("");
			}
		}

	}

	public void validAnswer() {
		getVerdict(secretCode, Integer.parseInt(input));
		if (!answerToGive.equals(answer)) {
			setError("Wrong answer!, should be " + answerToGive);
		} else {
			setError("");
			if (Integer.parseInt(input) == (secretCode)) {
				setStatus(Status_Game.WIN);
			} else {
				setStatus(Status_Game.PLAY);
			}
		}
		checkAttempts();
	}

	public int[] intToArray(int code) {
		int[] tab = new int[conf.getNbDigits()];
		int s = conf.getNbDigits() - 1;
		while (code > 0) {
			int b = code % 10;
			tab[s] = b;
			code = code / 10;
			s--;
		}
		return tab;
	}

//	public void check(String str) {
//		attempts++;
//		System.out.println("attempts: " + attempts);
//		System.out.println(max_attempts);
//		if (Integer.parseInt(str) == (getSecretCode())) {
//			status = Status_Game.WIN;
//		} else {
//			checkAttempts();
//			System.out.println("truc");
//			status = Status_Game.NO_MORE_TRIES;
//		}
//		
//
//	}

	public void checkAttempts() {
		attempts++;
		System.out.println("Attempts: "+attempts+"/"+max_attempts);
		if (attempts == max_attempts) {
			status = Status_Game.NO_MORE_TRIES;
		}
	}
	
	public String gameResult(Player winner, Player loser){
		
		String str=winner.getClass().getSimpleName()+" wins!  "+
		loser.getClass().getSimpleName()+" loses!";
		return str;
	}

	// resets the game attempts
	public void reset() {
		this.status = status.PLAY;
		attempts = 0;

	}

	// Getters and Setters

	public String getAnswerToGive() {
		return answerToGive;
	}

	public void setAnswerToGive(String answerToGive) {
		this.answerToGive = answerToGive;
	}
	public void setStatus(Status_Game status) {
		this.status = status;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getError() {
		return error;
	}

	public void setSecretCodeArray() {
		intToArray(secretCode);
	}

	public void setError(String error) {
		this.error = error;
	}

	public Status_Game getStatus() {
		return status;
	}

	public int getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(int secretCode) {
		this.secretCode = secretCode;
	}

	public void setAnswer(String str) {
		this.answer = str;

	}
	public String getOutput() {
		return output;
	}

}

package game;

import application.Configuration;
import check.Check;
import check.InputStatus;
import player.Player;

public abstract class Game extends Check {
	protected Player p1;
	

	protected Player p2;
	protected Status_Game status;
	protected int secretCode;
	protected int[] secretCodeArray;
	protected int attempts = 0;

	protected int max_attempts = Configuration.getMax_attempts();
	protected String error = "";
	protected String answerToGive = "";
	protected Check ch;

	protected String answer = "";
	protected String verdict = "";

	// Constructor
	public Game(Player p1, Player p2) {

		this.p1 = p1;
		this.p2 = p2;
	}

	// abstracts methods
	abstract void getVerdict(int a, int b);

	public void validSetup() {
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					setSecretCode(Integer.parseInt(input));
					secretCodeArray = intToArray(secretCode); // converts
																// secretCode
																// into array
					setStatus(Status_Game.PLAY);
					setError("");
				} else {
					error = IStatus.getOutput();
				}
			} else {
				error = IStatus.getOutput();
			}
		} else {
			error = IStatus.getOutput();
		}
		incrementAttempt();
	}

	public void validInput() {
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					setStatus(Status_Game.ANSWER);
					incrementAttempt();
					setError("");
				} else {
					error = IStatus.getOutput();
				}
			} else {
				error = IStatus.getOutput();
			}
		} else {
			error = IStatus.getOutput();
		}

	}

	public void validAnswer() {
		attempts++;

	}

	protected int[] intToArray(int code) {
		int[] tab = new int[Configuration.getNbDigits()];
		int s = Configuration.getNbDigits() - 1;
		while (code > 0) {
			int b = code % 10;
			tab[s] = b;
			code = code / 10;
			s--;
		}
		return tab;
	}

	public void checkAttempts() {
		if (attempts == max_attempts) {
			setStatus(Status_Game.NO_MORE_TRIES);
		}
	}

	public void incrementAttempt() {
		attempts++;
	}

	public String gameResult(Player winner, Player loser) {

		verdict = winner.getName() + " wins!  " + loser.getName() + " loses!";
		return verdict;
	}

	// resets the game attempts
	public void reset() {
		output = "";
		input = "";
		attempts = 0;

	}

	public void validPlayAgain() {
		if (input.equalsIgnoreCase("y")) {
			reset();
			output = "There you go again!";
			status = Status_Game.SETUP;
		} else if (input.equalsIgnoreCase("n")) {
			reset();
			status = Status_Game.EXIT;
		} else {
			error = InputStatus.WRONGCHARACTER.getOutput();
		}

	}

	// Getters and Setters
	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Player getP1() {
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public int getAttempts() {
		return attempts;
	}

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
		output = p1.getName() + " answer: " + output;

		return output;
	}

	public String getVerdict() {
		return verdict;
	}

}

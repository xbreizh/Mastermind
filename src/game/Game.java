package game;

import org.apache.log4j.Logger;

import application.Configuration;
import check.Check;
import check.InputStatus;
import player.Human;
import player.Player;

public abstract class Game extends Check {
	private Logger log;
	protected Player p1;
	protected Player p2;
	// protected Status_Game status;

	protected int[] secretCodeArray;
	protected int attempts = 0;
	protected int max_attempts = Configuration.getMax_attempts();
	protected String error = "";

	protected int secretCode;
	protected String guess = "";
	protected String answerToGive = "";
	protected String answer = "";

	protected String verdict = "";
	protected String[] yesOrNo = { "y", "n" };
//	protected String nameP1;
//	protected String nameP2;
	protected Player winner;

	// Constructor
	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	// abstracts methods
	abstract void generateAnswerToGive();

	public Status_Game validSetup(Status_Game status) {
		input = p1.setup();
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					secretCode = Integer.parseInt(input);
					secretCodeArray = intToArray(secretCode);
					showParams();
					setError("");
					return Status_Game.PLAY;
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
		return status;
	}

	public Status_Game play(Status_Game status) {
		String gameName = this.getClass().getSimpleName();
		input = p2.tryToGuess(gameName);
		showParams();
		return validPlay(status);
	}

	public Status_Game validPlay(Status_Game status) {
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					guess = input;
					incrementAttempt();
					setError("");
					showParams();
					generateAnswerToGive();
					p1.setGuess(guess);
					return Status_Game.ANSWER;
				} else {
					error = IStatus.getOutput();
				}
			} else {
				error = IStatus.getOutput();
			}
		} else {
			error = IStatus.getOutput();
		}
		return status;

	}

	public Status_Game answer(Status_Game status) {

		 if(p1.getClass().equals(Human.class)){
		showParams();
		String gameName = this.getClass().getSimpleName();
		answer = p1.replyToGuess(gameName);
		 }else{
			 answer=answerToGive;
		 }
		// if(checkIfInArray(input.))

		status = validAnswer(status);
		p2.setAnswer(answer);
		return status;
	}

	public void showParams() {
	}

	public Status_Game validAnswer(Status_Game status) {
		return status;
	}

	public Status_Game validPlayAgain(Status_Game status) {
		return checkYesOrNo(status, Status_Game.SETUP, Status_Game.EXIT);

	}

	public Status_Game validExit(Status_Game status) {
		return checkYesOrNo(status, null, Status_Game.END);

	}

	protected Status_Game checkYesOrNo(Status_Game init, Status_Game yes, Status_Game no) {
		error = "";
		if (isEmpty()) {
			error = IStatus.getOutput();
			return init;
		} else if (input.equalsIgnoreCase("y")) {
			reset();
			return yes;
		} else if (input.equalsIgnoreCase("n")) {
			reset();
			return no;
		} else {
			error = InputStatus.WRONGCHARACTER.getOutput();
			return init;
		}

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

	public Status_Game checkAttempts(Status_Game status) {
		if (attempts == max_attempts) {
			winner = p1;
			return Status_Game.NO_MORE_TRIES;
		}
		return status;
	}

	private void incrementAttempt() {
		attempts++;
	}

	// resets the game attempts
	private void reset() {
		output = "";
		input = "";
		attempts = 0;

	}

//	public String getOutput() {
//		output = p1.getName() + " answer: " + output;
//
//		return output;
//	}

	// Getters and Setters
	public void setLog(Logger log) {
		this.log = log;
	}

	public Player getWinner() {
		return winner;
	}

//	public int[] getSecretCodeArray() {
//		return secretCodeArray;
//	}

//	public String getNameP1() {
//		return p1.getName();
//	}
//
//	public String getNameP2() {
//		return p2.getName();
//	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Player getP1() {
		return p1;
	}
//
//	public Player getP2() {
//		return p2;
//	}

	public int getAttempts() {
		return attempts;
	}

	public String getAnswerToGive() {
		return answerToGive;
	}

	public void setAnswerToGive(String answerToGive) {
		this.answerToGive = answerToGive;
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

	public int getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(int secretCode) {
		this.secretCode = secretCode;
	}

	public void setAnswer(String str) {
		this.answer = str;

	}

	public String getVerdict() {
		return verdict;
	}

}

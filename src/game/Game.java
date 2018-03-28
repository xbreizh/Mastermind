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

	protected String answer = "";
	protected String verdict = "";
	protected String[] yesOrNo = { "y", "n" };
	protected String nameP1;

	protected String nameP2;
	protected Player winner;

	public Player getWinner() {
		return winner;
	}

	// Constructor
	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		p1.setGame(this);
		p2.setGame(this);
		p2.setName(p2.getClass().getSimpleName());
	}

	// abstracts methods
	abstract void getVerdict(int a, int b);

	public Status_Game play(Status_Game status) {

		p2.tryToGuess();
		setInput(p2.getInput());
		p1.setInput(p2.getInput());
		return validPlay(status);
	}

	public Status_Game answer(Status_Game status) {

		p1.replyToGuess();

		setAnswer(p1.getInput());
		status = validAnswer(status);
		p2.setAnswer(output);
		return status;
	}

	public Status_Game validSetup(Status_Game status) {

		input = p1.input();
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					// saves the secret code
					secretCode = Integer.parseInt(input);
					// converts secretCode into array
					secretCodeArray = intToArray(secretCode);
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

	public Status_Game validPlay(Status_Game status) {
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					incrementAttempt();
					setError("");
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

	// overwritten in game subclasses(Mastermind / MoreLess)
	public Status_Game validAnswer(Status_Game status) {
		return status;
	}

//	public void gameVerdict() {
////System.out.println(winner);
//		verdict = "Winner: " + winner.getName();
//	}

	public Status_Game validPlayAgain() {
		return checkYesOrNo(Status_Game.SETUP, Status_Game.EXIT);

	}

	public Status_Game validExit() {
		return checkYesOrNo(null, Status_Game.END);

	}

	protected Status_Game checkYesOrNo(Status_Game yes, Status_Game no) {
		error = "";
		if (isEmpty()) {
			error = IStatus.getOutput();
		} else if (input.equalsIgnoreCase("y")) {
			reset();
			status = yes;
		} else if (input.equalsIgnoreCase("n")) {
			reset();
			status = no;
		} else {
			error = InputStatus.WRONGCHARACTER.getOutput();
		}
		return status;
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
			winner=p1;
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

	public String getOutput() {
		output = p1.getName() + " answer: " + output;

		return output;
	}

	// Getters and Setters
	public int[] getSecretCodeArray() {
		return secretCodeArray;
	}

	public String getNameP1() {
		return p1.getName();
	}

	public String getNameP2() {
		return p2.getName();
	}

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

	public String getVerdict() {
		return verdict;
	}

}

package game;

import application.Configuration;
import check.Check;
import check.InputStatus;
import player.AI;
import player.Human;
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
//	protected Check ch;

	protected String answer = "";
	protected String verdict = "";
	protected String[] yesOrNo = { "y", "n" };
	protected String nameP1;
	

	protected String nameP2;

	// Constructor
	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
//		setNames();
	}
	
//	private void setNames() {
//		if (p1.getClass().equals(Human.class) && p2.getClass().equals(Human.class)) {
//			nameP1="Paul";
//			nameP2="John";
//		} else if (p1.getClass().equals(Human.class) && p2.getClass().equals(AI.class)) {
//			nameP1="Human";
//			nameP2="AI";
//		} else {
//			nameP1="AI";
//			nameP2="Human";
//		}
//	}

	// abstracts methods
	abstract void getVerdict(int a, int b);
	 
	public void play(){
		
		p2.tryToGuess();
		
		setInput(p2.getInput());
		p1.setInput(p2.getInput());
		validPlay();
	}
	
	public void answer(){
		
		p1.replyToGuess();
		
		setAnswer(p1.getInput());
		validAnswer();
		p2.setAnswer(output);
	}

	public void validSetup() {
		// Gets input from Player 1
		// p1.input();
		input = p1.input();
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					setSecretCode(Integer.parseInt(input));
					// converts secretCode into array
					secretCodeArray = intToArray(secretCode);
					p1.setCodeToFind(input);
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

	public void validPlay() {
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

	// overwritten in game subclasses(Mastermind / MoreLess)
	public void validAnswer() {
	}

	public String gameVerdict(Player winner, Player loser) {

		verdict = winner.getName() + " wins!  " + loser.getName() + " loses!";
		return verdict;
	}

	public void validPlayAgain() {
		checkYesOrNo(Status_Game.SETUP, Status_Game.EXIT);

	}

	public void validExit() {
		checkYesOrNo(null, Status_Game.END);

	}

	protected void checkYesOrNo(Status_Game yes, Status_Game no) {
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
	public String getNameP1() {
		return nameP1;
	}

	public String getNameP2() {
		return nameP2;
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

package game;

import org.apache.log4j.Logger;

import application.Configuration;
import check.Check;
import check.InputStatus;
import player.Human;
import player.Player;

/**
 * This is the Model Application. it requires 2 players
 * and the methods return a status that can be used in the controller for switching
 * It's a child of the Check class what allows to check the user's inputs.
 * @author Xavier.Lamourec
 *
 */

public abstract class Game extends Check {
	private Logger log;
	protected Player defender;
	protected Player challenger;
	protected int[] secretCodeArray;
	protected int attempts = 0;
	protected int max_attempts = Configuration.getMax_attempts();
	protected int secretCode;
	protected String guess = "";
	protected String answerToGive = "";
	protected String answer = "";
	protected Player winner;

	/**
	 * Constructor of the game (requires 2 players)
	 * @param defender
	 * @param challenger
	 */
	public Game(Player defender, Player challenger) {
		this.defender = defender;
		this.challenger = challenger;
//		log.info(this.getClass().getSimpleName()+" created"
//				+"\nChallenger: "+challenger.getClass().getSimpleName()
//				+"\nDefender: "+defender.getClass().getSimpleName());
	}

	/**
	 * generates the answer to be given to the challenger
	 * (deined in the respective child class)
	 */
	abstract void generateAnswerToGive();
	
	/**
	 * asks the defender to provide the secret code
	 */
	private void requestSecretCode() {
		input = defender.setup();
	}
	
	/**
	 * Gets the secret Code and changes the status if valid
	 * @param status
	 * @return status (updated or no)
	 */
	public Status_Game validSetup(Status_Game status) {
		requestSecretCode();
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					secretCode = Integer.parseInt(input);
					secretCodeArray = intToArray(secretCode);
					error="";
					log.info("Secret code set: "+secretCode);
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
		return status;
	}


	
	/**
	 * Asks for challenger's guess until it's valid
	 * @param status
	 * @return
	 */
	public Status_Game play(Status_Game status) {
		String gameName = this.getClass().getSimpleName();
		input = challenger.tryToGuess(gameName);
		return validPlay(status);
	}
	
	/**
	 * checks the challenger's guess and changes the status if valid
	 * @param status
	 * @return
	 */
	public Status_Game validPlay(Status_Game status) {
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					guess = input;
					incrementAttempt();
					error="";
					generateAnswerToGive();
					defender.setGuess(guess);
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
	
	/**
	 * Asks for defender's answer until it's valid
	 * if defender is human
	 * Otherwise, it sets the answer as game's generated answer
	 * @param status
	 * @return
	 */
	public Status_Game answer(Status_Game status) {

		if (defender.getClass().equals(Human.class)) {
//			String gameName = this.getClass().getSimpleName();
			answer = defender.setup();
		} else {
			answer = answerToGive;
		}

		status = validAnswer(status);
		challenger.setAnswer(answer);
		return status;
	}
	
	
	/**
	 * asks the user if he wants to replay
	 * @param status
	 * @return
	 */
	public Status_Game validPlayAgain(Status_Game status) {
		return checkYesOrNo(status, Status_Game.SETUP, Status_Game.EXIT);

	}
	
	/**
	 * ask the user if he wants to play again
	 * @param status
	 * @return
	 */
	public Status_Game validExit(Status_Game status) {
		return checkYesOrNo(status, null, Status_Game.END);

	}
	
	/**
	 * checks the user's input and changes the status if correct
	 * @param init
	 * @param yes
	 * @param no
	 * @return
	 */
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
	
	/**
	 * converts an integer into array
	 * @param code
	 * @return
	 */
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
	
	/**
	 * checks the number of attempts and changes the status if the maximum is reached
	 * @param status
	 * @return
	 */
	public Status_Game checkAttempts(Status_Game status) {
		if (attempts == max_attempts) {
			winner = defender;
			return Status_Game.NO_MORE_TRIES;
		}
		return status;
	}
	
	/**
	 * increments the number of attempts
	 */
	private void incrementAttempt() {
		attempts++;
	}

	/**
	 * reset the game parameters
	 */
	private void reset() {
		error="";
		output = "";
		input = "";
		attempts = 0;

	}
	
	/**
	 * checks the validity of the answer provided compared to the answer built
	 * and returns a status accordingly
	 * @param answer
	 * @param answerToGive
	 * @param status
	 * @return status
	 */
	public Status_Game validAnswer(Status_Game status) {
		if (!answerToGive.equals(answer)) {
			error="Wrong answer!, should be " + answerToGive;
			return status;
		} else {
			error="";
			if (Integer.parseInt(guess) == (secretCode)) {
				winner=challenger;
				status= Status_Game.FOUND;
			} else {
				status= Status_Game.PLAY;
				status=checkAttempts(status);
				return status;
			}
		}
		return status;
	}

	// Getters and Setters
	public void setLog(Logger log) {
		this.log = log;
	}

	public Player getWinner() {
		return winner;
	}

//	public void setDefender(Player defender) {
//		this.defender = defender;
//	}
//
//	public void setChallenger(Player challenger) {
//		this.challenger = challenger;
//	}

	public Player getDefender() {
		return defender;
	}

	public int getAttempts() {
		return attempts;
	}

	public String getAnswerToGive() {
		return answerToGive;
	}

//	protected void setAnswerToGive(String answerToGive) {
//		this.answerToGive = answerToGive;
//	}

//	public String getInput() {
//		return input;
//	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getError() {
		return error;
	}

	public String getOutput() {
		return output;
	}

	// public void setSecretCodeArray() {
	// intToArray(secretCode);
	// }
//	/**
//	 * sets the error to be displayed in the view
//	 * @param error
//	 */
//	public void setError(String error) {
//		this.error = error;
//	}

//	public int getSecretCode() {
//		return secretCode;
//	}

	// public void setSecretCode(int secretCode) {
	// this.secretCode = secretCode;
	// }

//	public void setAnswer(String str) {
//		this.answer = str;
//
//	}

//	public String getVerdict() {
//		return verdict;
//	}

}

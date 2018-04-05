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
	protected int max_attempts;
	public int getMax_attempts() {
		return max_attempts;
	}

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
	}

	/**
	 * generates the answer to be given to the challenger
	 * (defined in the respective child class)
	 */
	abstract void generateAnswerToGive();
	
	/**
	 * asks the defender to provide the secret code
	 */
	private void requestSecretCode() {
		input = defender.getInput();
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
	 * @param 
	 * @return status
	 */
	public Status_Game play(Status_Game status) {
		String gameName = this.getClass().getSimpleName();
		input = challenger.tryToGuess(gameName);
		return validPlay(status);
	}
	
	/**
	 * checks the challenger's guess and changes the status if valid
	 * @param 
	 * @return status
	 */
	public Status_Game validPlay(Status_Game status) {
		log.info("Challenger guess: "+input);
		if (!isEmpty()) {
			if (isInteger()) {
				if (hasCorrectNbDigits()) {
					guess = input;
					incrementAttempt();
					error="";
					generateAnswerToGive();
					defender.setGuess(guess);
					log.debug("Challenger guess: "+guess+" validated");
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
	 * @param 
	 * @return status
	 */
	public Status_Game answer(Status_Game status) {

		if (defender.getClass().equals(Human.class)) {
			answer = defender.getInput();
		} else {
			answer = answerToGive;
		}
		log.debug("Defender answer: "+answer+"\n"
				+ "Answer to give: "+answerToGive);
		status = validAnswer(status);
		challenger.setAnswer(answer);
		return status;
	}
	
	
	/**
	 * asks the user if he wants to replay
	 * @param 
	 * @return status
	 */
	public Status_Game validPlayAgain(Status_Game status) {
		return checkYesOrNo(status, Status_Game.SETUP, Status_Game.EXIT);

	}
	
	/**
	 * ask the user if he wants to play again
	 * @param 
	 * @return status
	 */
	public Status_Game validExit(Status_Game status) {
		return checkYesOrNo(status, null, Status_Game.END);

	}
	
	/**
	 * checks the user's input and changes the status if correct
	 * @param status
	 * @param yes
	 * @param no
	 * @return status
	 */
	protected Status_Game checkYesOrNo(Status_Game status, Status_Game yes, Status_Game no) {
		error = "";
		if (isEmpty()) {
			error = IStatus.getOutput();
			return status;
		} else if (input.equalsIgnoreCase("y")) {
//			reset();
			return yes;
		} else if (input.equalsIgnoreCase("n")) {
			return no;
		} else {
			error = InputStatus.WRONGCHARACTER.getOutput();
			return status;
		}

	}
	
	/**
	 * converts an integer into array
	 * @param code
	 * @return tab
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
	 * @param 
	 * @return status
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
		log.debug("Attemps incremented: "+attempts);
	}

	/**
	 * reset the game parameters
	 */
	public void reset() {
		error="";
		output = "";
		input = "";
		guess="";
		attempts = 0;
		challenger.setFirstGuess(0);
		log.info("Game values have been reset (error, output, input, attempts)");
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
				log.debug("Answer valid");
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

	public Player getDefender() {
		return defender;
	}

	public int getAttempts() {
		return attempts;
	}

	public String getAnswerToGive() {
		return answerToGive;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getError() {
		return error;
	}

	public String getOutput() {
		return output;
	}

}

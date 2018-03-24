package game;

import application.Configuration;
import check.Check;
import check.InputStatus;
import player.Player;

public abstract class Game extends Check {
	Player p1;
	Player p2;
	protected Status_Game status;
	int secretCode;
	int[] secretCodeArray;
	int attempts = 0;
	// Settings conf = Settings.getConfiguration();
	int max_attempts = Configuration.max_attempts;
	String error = "";
	String answerToGive = "";
	Check ch;

	String answer = "";

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
		// checkAttempts();
		// System.out.println("Status here: "+status);
		// getVerdict(secretCode, Integer.parseInt(input));
		// if (!answerToGive.equals(answer)) {
		// setError("Wrong answer!, should be " + answerToGive);
		// } else {
		// setError("");
		//
		// if (Integer.parseInt(input) == (secretCode)) {
		// setStatus(Status_Game.WIN);
		// } else {
		// setStatus(Status_Game.PLAY);
		// }
		// }

	}

	public int[] intToArray(int code) {
		int[] tab = new int[Configuration.nbDigits];
		int s = Configuration.nbDigits - 1;
		while (code > 0) {
			int b = code % 10;
			tab[s] = b;
			code = code / 10;
			s--;
		}
		return tab;
	}

	// public void check(String str) {
	// attempts++;
	// System.out.println("attempts: " + attempts);
	// System.out.println(max_attempts);
	// if (Integer.parseInt(str) == (getSecretCode())) {
	// status = Status_Game.WIN;
	// } else {
	// checkAttempts();
	// System.out.println("truc");
	// status = Status_Game.NO_MORE_TRIES;
	// }
	//
	//
	// }

	public void checkAttempts() {
		System.out.println("Attempts: " + attempts + "/" + max_attempts);
		if (attempts == max_attempts) {
			System.out.println("here");
			setStatus(Status_Game.NO_MORE_TRIES);
		}
	}

	public void incrementAttempt() {
		attempts++;
	}

	public String gameResult(Player winner, Player loser) {

		String str = winner.getClass().getSimpleName() + " wins!  " + loser.getClass().getSimpleName() + " loses!";
		return str;
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

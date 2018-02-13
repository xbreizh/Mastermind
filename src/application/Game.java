package application;

import java.util.Scanner;

import model.CheckInput;

abstract class Game extends Main {
	boolean keepPlaying = true;
	protected String list;
	static int min;
	static int max;
	static int max_attempts;
	int attempts = 0;
	String input;
	Scanner sc;
	Status status=Status.keepPlaying;

	Game() {
		initialize();
	}

	void setList(String list) {
		this.list = list;
	}

	public String getList() {
		return list;
	}

	// List of abstract methods
	abstract boolean checkResult(String check);

	// List of standard methods
	Status keepPlayingCheck() {
		if (attempts == max_attempts) {
			status = Status.noMoreTries;
		}
		return status;

	}
	
	protected void initialize() {
		list = Configuration.getConfiguration().getList();
		min = Configuration.getConfiguration().getMin();
		max = Configuration.getConfiguration().getMax();
		max_attempts = Configuration.getConfiguration().getMax_attempts();
		ct = new DefenseController();
		rules();
	}
	
	void rules() {
		System.out.printf("Trouver la combinaison de %s chiffres en maximum %s essais!", list.length(), max_attempts);
		System.out.println("(taper Q pour quitter)");
		ct.controllerCheck(this);
	}

	boolean checkInput(String input) {
		if (!CheckInput.checkNotNull(input)) {
			return false;
		}
		if (CheckInput.checkValidString(input, "q")) {
			return true;
		}
		if (!CheckInput.checkRange(input, min, max)) {
			return false;
		}
		return true;

	}

	void finalResult(String input, Status status) {
		if (status == Status.win) {
			System.out.printf("Proposition : %s -> Bravo, vous avez trouvé la combinaison! en %s essais", input,
					attempts);
		}
		if (status == Status.noMoreTries) {
			System.out.println("Perdu, vous avez atteint le nombre maximum d'essais possible!");
		}
	}

	boolean playAgain(String input) {
		String[] valid = { "y", "n" };
		while (!CheckInput.checkValidString(input, valid)) {
			askRestart();
		}
		if (input.toUpperCase().equals("Y")) {
			attempts = 0;
			return true;
		} else {
			return false;
		}
	}

	void askRestart() {
		System.out.println("\nDo you want to restart the game? (Y / N)");
		ct.controllerPlayAgain(this);
	}

}

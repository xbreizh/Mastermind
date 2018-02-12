package application;

import java.util.Scanner;

import model.CheckInput;

public class MoreLess extends Game {
	String input;
	Scanner sc;

	void setList(String list) {
		this.list = list;
	}

	public String getList() {
		return list;
	}

	public MoreLess() {
		initialize();

	}

	protected void initialize() {
		list = Configuration.getConfiguration().getList();
		min = Configuration.getConfiguration().getMin();
		max = Configuration.getConfiguration().getMax();
		max_attempts = Configuration.getConfiguration().getMax_attempts();
		ct = new DefenseController();
		ct.controllerKeepPlaying(this, true);
	}

	void rules() {
		System.out.println("Règles du jeu:");
		System.out.printf("trouver la combinaison de %s chiffres en maximum %s essais!", list.length(), max_attempts);
		System.out.println("(taper Q pour quitter)");
		ct.controllerCheck(this);

	}

	boolean keepPlayingCheck() {
		if (attempts != max_attempts) {
			keepPlaying = false;
			return true;
		}
		return false;

	}

	boolean checkInput(String input) {
		if (CheckInput.checkValidString(input, "q")) {
			return true;
		}
		if (!CheckInput.checkRange(input, min, max)) {
			return false;
		}
		return true;

	}

	// checks the result
	boolean checkResult(String check, String good) {
		String result = "";
		this.attempts++;
		if (!check.equals(good)) {

			for (int i = 0; i < good.length(); i++) {
				int a = Character.getNumericValue(check.charAt(i));
				int b = Character.getNumericValue(good.charAt(i));
				if (a < b) {
					result += ("+");
				}
				if (a > b) {
					result += ("-");
				}
				if (a == b) {
					result += ("=");
				}
			}
		} else {
			return true;
		}
		System.out.printf("Proposition : %s -> Réponse : %s \n", check, result);
		return false;
	}

	void finalResult(String verdict) {
		if (verdict.equals("win")) {
			System.out.printf("Proposition : %s -> Bravo, vous avez trouvé la combinaison! en %s essais", input,
					attempts);
		}
		if (verdict.equals("lose")) {
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

package application;

import java.util.Scanner;

import model.CheckInput;

public class MoreLess extends Game {

	private String list="";
	
	public void setList(String list) {
		this.list = list;
	}

	static int min = 1000;
	static int max = 9999;
	static int max_attempts = 5;
	Scanner sc = new Scanner(System.in);
	String input;
	boolean keepPlaying=true;
	Controller ct;

	public MoreLess() {
		this.ct=new Controller();
		initialize();
//		setList("oko");
//		System.out.println(getList());
		
//		rules();
//		play();

	}

	protected void initialize() {
		setList("1234");
		ct.controllerKeepPlaying(this, keepPlaying);
		
	}
	void rules() {
		System.out.println("Règles du jeu:");
		System.out.printf("trouver la combinaison de %s chiffres en maximum %s essais!", list.length(), max_attempts);
		System.out.println("(taper Q pour quitter)");
		ct.controllerCheck(this);

	}

//	void play() {
//		boolean correct = false;
//		// game is on until the combination is found or the max_attempts is
//		// reached
//		while (!correct && attempts != max_attempts) {
//
//			rules();
//			this.input = sc.nextLine(); // gets the input value
//			if (checkInput(input)) {
//				attempts++; // increments the number of attempts
//				System.out.println("Attempt: " + attempts + " / " + max_attempts);
//				if (checkResult(input, list)) {
//					correct = true;
//				}
//
//			}
//			if (correct) {
//				finalResult("win");
//			}
//			if (attempts == max_attempts) {
//				finalResult("lose");
//			}
//		}
//		keepPlayingCheck();
//	}

	// checks that the input is a valid integer within the range
	boolean checkInput(String input) {
		if (!CheckInput.checkNotNull(input)) {
			return false;
		}
		if (!CheckInput.isInteger(input)) {
			return false;
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
	
	
	boolean playAgain() {

		String input = null;
		do {
			System.out.println("\nDo you want to restart the game? (Y / N)");
			input = sc.nextLine().toUpperCase();
		}

		while (!input.equals("Y") && !input.equals("N"));
		if (input.equals("Y")) {
			attempts = 0;
			return true;
		} else {
			return false;
		}
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

	boolean checkValid(String value) {
		return false;
	}

	@Override
	boolean keepPlayingCheck() {
		if(attempts != max_attempts){
			keepPlaying=false;
			return true;
		}
		return false;
		
	}

	@Override
	String getList() {
		return list;
	}

}

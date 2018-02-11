package application;

import java.util.Scanner;

public class Controller {

	private Scanner sc;
//	String list;

	public Controller() {
		openScanner();
	}

	Scanner openScanner() {
		sc = new Scanner(System.in);
		return sc;
	}

	public void controllerKeepPlaying(Game game, boolean keepPlaying) {
		if (!game.keepPlayingCheck()) {
			System.out.println("Perdu, vous avez atteint le nombre maximum d'essais possible!");
			controllerPlayAgain(game);
		} else {
//			System.out.println(game.getList());
			game.rules();
		}

	}

	public String controllerGetInput() {
		String input = sc.nextLine();
		return input;
	}

	public void controllerCheck(Game game) {
		String input = controllerGetInput();
		if (!game.checkInput(input)) {
			game.rules();
		} else {
			controllerResult(game, input);
		}
	}

	public void controllerResult(Game game, String input) {
		if (game.checkResult(input, game.getList())) {
			System.out.printf("Proposition : %s -> Bravo, vous avez trouvé la combinaison! en %s essais", input,
					game.attempts);
			controllerPlayAgain(game);
		} else {
			controllerKeepPlaying(game, true);
		}
	}

	public void controllerPlayAgain(Game game) {
		if (!game.playAgain()) {
			System.out.println("thanks for playing!");
			closeScanner();
		} else {
			game.attempts = 0;
			controllerKeepPlaying(game, true);
		}

	}

	public void closeScanner() {
		sc.close();
	}

}

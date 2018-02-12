package application;

public class DefenseController extends MainController{

	String input;

	public DefenseController() {
	}

	

	public void controllerKeepPlaying(Game game, boolean keepPlaying) {
		if (!game.keepPlayingCheck()) {
			System.out.println("Perdu, vous avez atteint le nombre maximum d'essais possible!");
			game.askRestart();
		} else {
			game.rules();
		}

	}

	public void controllerCheck(Game game) {
		input = controllerGetInput();
		if (!game.checkInput(input)) {
			game.rules();
		} else {
			controllerResult(game, input);
		}
	}

	public void controllerResult(Game game, String input) {
		if(input.toUpperCase().equals("q".toUpperCase())){
			game.askRestart();
		}
		else if (game.checkResult(input, game.getList())) {
			System.out.printf("Proposition : %s -> Bravo, vous avez trouvé la combinaison! en %s essais", input,
					game.attempts);
			game.askRestart();
		} else {
			controllerKeepPlaying(game, true);
		}
	}

	public void controllerPlayAgain(Game game) {

		input = controllerGetInput();
				
		if (!game.playAgain(input)) {
			System.out.println("Fin du jeu!");
			menu();
		} else {
			game.attempts = 0;
			controllerKeepPlaying(game, true);
		}

	}
	

	

	

}

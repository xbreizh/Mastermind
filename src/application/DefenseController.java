package application;

public class DefenseController extends MainController{

	String input;

	public DefenseController() {
	}

	public void controllerKeepPlaying(Game game) {
		if(status == Status.noMoreTries){
			game.finalResult(input, status);
			game.askRestart();
		}
		if(status == Status.win){
			game.askRestart();
		}
		if(status == Status.keepPlaying){
			game.rules();
		}
		if(status == Status.quit){
			game.askRestart();
		}
	}

	public void controllerCheck(Game game) {
		input = controllerGetInput();
		if (!game.checkInput(input)) {
			controllerKeepPlaying(game);
		} else {
			controllerResult(game, input);
		}
	}

	public void controllerResult(Game game, String input) {
		if(input.toUpperCase().equals("q".toUpperCase())){
			status = Status.quit;
		}
		else if (game.checkResult(input)) {
			status = Status.win;
			game.finalResult(input, status);
		} else{
			status = game.keepPlayingCheck();
		}
		controllerKeepPlaying(game);
		
	}

	public void controllerPlayAgain(Game game) {

		input = controllerGetInput();
				
		if (!game.playAgain(input)) {
			System.out.println("Fin du jeu!");
			menu();
		} else {
			game.attempts = 0;
			status= Status.keepPlaying;
		}
		controllerKeepPlaying(game);

	}
	

	

	

}

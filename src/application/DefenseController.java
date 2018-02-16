package application;

public class DefenseController extends Controller implements Observer{

	String input;

	public DefenseController() {
		System.out.println("here");
		
	}

	public void CheckStatus(){
		if(status == Status.PLAYING){
			getInput();
			}
		if(status == Status.NOMORETRIES){
			getInput();
		}
		if(status == Status.WIN){
			getInput();
		}
	}
	
	public void sendInputModel(){
		
	}
//	public void controllerKeepPlaying(Game game) {
//		if(status == Status.NOMORETRIES){
//			game.finalResult(input, status);
//			game.askRestart();
//		}
//		if(status == Status.win){
//			game.askRestart();
//		}
//		if(status == Status.PLAYING){
//			game.rules();
//		}
//		if(status == Status.quit){
//			game.askRestart();
//		}
//	}
//
//	public void controllerCheck(Game game) {
//		input = Input.controllerGetInput();
//		if (!game.checkInput(input)) {
//			controllerKeepPlaying(game);
//		} else {
//			controllerResult(game, input);
//		}
//	}
//
//	public void controllerResult(Game game, String input) {
//		if(input.toUpperCase().equals("q".toUpperCase())){
//			status = Status.quit;
//		}
//		else if (game.checkResult(input)) {
//			status = Status.win;
//			game.finalResult(input, status);
//		} else{
//			status = game.keepPlayingCheck();
//		}
//		controllerKeepPlaying(game);
//		
//	}
//
//	public void controllerPlayAgain(Game game) {
//
//		input = Input.controllerGetInput();
//				
//		if (!game.playAgain(input)) {
//			System.out.println("Fin du jeu!");
////			menu();
//			
//		} else {
//			game.attempts = 0;
//			status= Status.PLAYING;
//		}
//		controllerKeepPlaying(game);
//
//	}
	

	

	

}

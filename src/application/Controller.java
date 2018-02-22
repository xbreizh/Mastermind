package application;

public class Controller {

	Status status;
	public Human p0;
	Player p1;
	Player p2;
	Game game;
	Mode mode;
	String error;
	String input = "";
	String output = "";
	View view;
	int attempt = 0;
	int max_attempts = 5;
	String list = "1234";
	boolean developerMode = false;
	String rules = "Trouver la combinaison de " + list.length() + " chiffres en maximum " + max_attempts + " essais!\n"
			+ "(taper Q pour quitter)";
	String nothing="Vous n'avez rien écrit!";
	String wrongInput="Le code à trouver contient " + list.length() + " chiffres!";
	String askReplay="Voulez-vous rejouer? (O / N)";
	String result="";

	Controller() {
		status = Status.GAME_MENU;

		p0 = new Human();
		p0.openScanner();
		view = new View();
		checkStatus();

	}

	void checkStatus() {
		switch (status) {
		case GAME_MENU:
			checkGame();
			break;
		case MODE_MENU:
			checkMode();
			break;
		case PLAY_MENU:
			result="";
			checkPlay();
			break;
		case EXIT:
			view.displayOutput("Jeu terminé!");
			playAgain();
			break;
		case QUIT:
			view.displayOutput("Merci d'avoir joué avec nous!");
			p0.closeScanner();
			break;
		case WIN:
			view.displayOutput("Félicitations, vous avez gagné!");
			result="";
			playAgain();
			break;
		case NO_MORE_TRIES:
			view.displayOutput("Perdu! Vous n'avez plus d'essais.");
			result="";
			playAgain();
			break;
		}
	}

	void checkGame() {
		String str;
		do {
			view.displayOutput("Chose a game: \n 1-MoreLess\n 2-Mastermind");
			str = p0.input();
		} while (!str.equals("1") && !str.equals("2"));
		if (str.equals("1")) {
			game = new MoreLess();
		}
		if (str.equals("2")) {
			game = new MasterMind();
		}
		status = Status.MODE_MENU;
		checkStatus();

	}

	void checkMode() {
		String str;
		do {
			view.displayOutput("Chose a mode: \n 1-Challenger\n 2-Defenser\n 3-Dual");
			str = p0.input();
		} while (!str.equals("1") && !str.equals("2") && !str.equals("3"));
		if (str.equals("1")) {
			mode = Mode.CHALLENGER;
		}
		if (str.equals("2")) {
			mode = Mode.DEFENSER;
		}
		if (str.equals("3")) {
			mode = Mode.DUAL;
		}
		status = Status.PLAY_MENU;
		checkStatus();

	}

	void checkPlay() {
		switch (mode) {
		case CHALLENGER:
			view.displayOutput("Game: " + game.getClass().getSimpleName() + ". Mode: " + mode);
			play(p0);
			break;
		case DEFENSER:
			view.displayOutput("Game: " + game + ". Mode: " + mode);
			if(p1 == null){
				System.out.println("creation ai");
				p1 = new AI();	
				}
			play(p1);
			break;
		case DUAL:
			view.displayOutput("Game: " + game + ". Mode: " + mode);
			if(p2 == null){
				p2 = new Human();	
				}
			play(p0, p2);
			break;
		default:
			output = "Invalid MODE";
			break;
		}

	}

	void play(Player player) {
		
		input="";
		while (attempt < max_attempts && !result.equals("win") && !result.equals("quit")) {
			view.displayOutput(rules);
			String input = player.input();
			if (input.isEmpty()) {
				view.displayError(nothing);
			} 
			else if (!input.toUpperCase().equals("Q") && input.length() != list.length()) {
				view.displayError(wrongInput);
			} 
			else {
				result = game.checkResult(input);
				player.setResult(result);
				if (result.equals("win")) {
					status = Status.WIN;
					player.setResult("");
				} 
				else if (result.equals("quit")) {
					status = Status.EXIT;
					player.setResult("");
				} 
				else {
					view.displayOutput(result);
					attempt++;
				}
			}
		}
		if (attempt == max_attempts) {
			status = Status.NO_MORE_TRIES;
			player.setResult("");
		} 
		checkStatus();
	}
void play(Player p1, Player p2) {
		Player player = null;
		input="";
		while (attempt < max_attempts && !result.equals("win") && !result.equals("quit")) {
			
			if(player == null){
				player =p1;
			}
			view.displayOutput(rules);
			view.displayOutput(p1+" playing");
			String input = player.input();
			if (input.isEmpty()) {
				view.displayError(nothing);
			} 
			else if (!input.toUpperCase().equals("Q") && input.length() != list.length()) {
				view.displayError(wrongInput);
			} 
			else {
				result = game.checkResult(input);
				player.setResult(result);
				if (result.equals("win")) {
					status = Status.WIN;
					player.setResult("");
				} 
				else if (result.equals("quit")) {
					status = Status.EXIT;
					player.setResult("");
				} 
				else {
					view.displayOutput(result);
					attempt++;
				}
			}
			if(player == p1){
				player =p2;
			}else{
				player=p1;
			}
			
		}
		if (attempt == max_attempts) {
			status = Status.NO_MORE_TRIES;
			player.setResult("");
		} 
		checkStatus();
	}

	void playAgain() {
		while (!input.toUpperCase().equals("O") && !input.toUpperCase().equals("N")) {
			view.displayOutput(askReplay);
			input = p0.input();
		}
		if (input.toUpperCase().equals("O")) {
			attempt=0;
			status = Status.GAME_MENU;
		} if (input.toUpperCase().equals("N")) {
			attempt=0;
			status = Status.QUIT;
		}
		checkStatus();
	}
	//
	// boolean checkInput(String input){
	// if(input.isEmpty())
	//
	// return false;
	//
	// }

	// Scanner sc;
	// protected Status status = Status.SELECTGAME;
	// String input = "";
	// String error="";
	// String[] toCheck;
	// String output = "";
	// String inputToTest="";
	// String nothing="vous n'avez rien écrit";
	// String wrongInput="entrée invalide: ";
	// Menu menu;
	//
	//
	//
	// public void setMenu(Menu menu) {
	// this.menu = menu;
	// }
	//
	//
	// public Controller() {
	// sc=Input.openScanner();
	// }
	//
	//
	// public void CheckStatus(){
	// if(status == Status.QUIT){
	// Input.closeScanner();
	// }
	// if(status == Status.SELECTGAME){
	// getInput();
	// }
	// if(status == Status.SELECTMODE){
	// getInput();
	// }
	// }
	//
	// public void getInput(){
	// inputToTest =sc.nextLine();
	// checkInput(inputToTest);
	// }
	//
	// public void checkInput(String inputToTest){
	// if(!CheckInput.checkNotNull(inputToTest)){
	// sendView(output);
	// sendError(nothing);
	// getInput();
	// }else{
	// if(!CheckInput.checkValidString(inputToTest, toCheck)){
	// sendView(output);
	// sendError(wrongInput+inputToTest);
	// getInput();
	// }else{
	// setInput(inputToTest);
	// sendInputModel();
	// }
	//
	// }
	// }
	//
	// public void sendInputModel(){
	// if(status == Status.SELECTGAME){
	// menu.checkGame(input);
	// }
	// else if(status == Status.SELECTMODE){
	// menu.checkMode(input);
	// }
	// else if(status == Status.QUIT){
	// }
	//
	// }
	//
	//
	// // sends the message to display
	// public void sendView(String output) {
	// View.display(output);
	//
	// }
	//
	// public void setInput(String input) {
	// this.input = input;
	// }
	//
	// // sends the error to display
	// public void sendError(String error) {
	// View.error(error);
	//
	// }
	//
	//
	// public void quit() {
	// Input.closeScanner();
	// }
	// private void updateStatus(Status status) {
	// this.status = status;
	// System.out.println("new status: "+status);
	//
	// }
	// private void updateToCheck(String[] toCheck) {
	// this.toCheck = toCheck;
	// }
	//
	//
	//
	// @Override
	// public void update(Status status, String output, String[] toCheck) {
	// updateStatus(status);
	// updateToCheck(toCheck);
	//
	// this.output=output;
	// sendView(output);
	// CheckStatus();
	// }
	//

}

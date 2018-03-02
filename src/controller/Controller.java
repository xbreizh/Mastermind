package controller;

import javax.swing.text.View;

import game.Configuration;
import game.MasterMind;
import game.MoreLess;
import players.AI;
import players.Human;
import players.Player;

public class Controller {

	static int max_attempts = Configuration.getMax_attempts();
	static String list = Configuration.getList();
	static boolean developerMode = Configuration.isDeveloperMode();
	static String rules = Configuration.getRules();
	static String nothing = Configuration.getNothing();
	static String wrongInput = Configuration.getWrongInput();
	static String askReplay = Configuration.getAskReplay();
	static String end = Configuration.getEnd();
	Status status;
	public Human p0;
	Player p1;
	Game game;
	Mode mode;
	String error = "";
	String input = "";
	String output = "";
	View view;
	int attempt = 0;

	String result = "";
	String winner = "";
	String name = "";

	// Initiates the controller
	Controller() {
		status = Status.GAME_MENU;

		p0 = new Human();
		p0.openScanner();
		view = new View();
		checkStatus();

	}

	// Checks the status and assigns accordingly
	void checkStatus() {
		switch (status) {
		case GAME_MENU:
			checkGame();
			break;
		case MODE_MENU:
			checkMode();
			break;
		case PLAY_MENU:
			result = "";
			checkPlay();
			break;
		case EXIT:
			view.displayOutput(end);
			playAgain();
			break;
		case QUIT:
			view.displayOutput("Merci d'avoir joué avec nous!");
			p0.closeScanner();
			break;
		case WIN:
			view.displayOutput("Félicitations, " + winner + " wins in " + attempt + " attempts!");
			result = "";
			attempt = 0;
			playAgain();
			break;
		case NO_MORE_TRIES:
			view.displayOutput("Perdu! Vous n'avez plus d'essais.");
			result = "";
			playAgain();
			break;
		}
	}

	// Asks to chose a Game
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

	// Asks to chose a Mode
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

	// Initiates the game
	void checkPlay() {
		switch (mode) {
		case CHALLENGER:
			view.displayOutput("Game: " + game.getClass().getSimpleName() + ". Mode: " + mode);
			name = "Joueur 1";
			play(p0);
			break;
		case DEFENSER:
			view.displayOutput("Game: " + game + ". Mode: " + mode);
			if (p1 == null) {
				p1 = new AI();
			}
			name = "Computer";
			play(p1);
			break;
		case DUAL:
			view.displayOutput("Game: " + game + ". Mode: " + mode);
			play(p0, 1);
			break;
		default:
			view.displayError("Invalid MODE");
			break;
		}

	}

	// Core of the game (single)
	void play(Player player) {

		input = "";
		while (attempt < max_attempts && !result.equals("win") && !result.equals("quit")) {
			view.displayOutput(rules);
			String input = player.input();
			if (input.isEmpty()) {
				view.displayError(nothing);
			} else if (!input.toUpperCase().equals("Q") && input.length() != list.length()) {
				view.displayError(wrongInput);
			} else {
				result = game.checkResult(input);
				player.setResult(result);
				if (result.equals("win")) {
					status = Status.WIN;
					winner = name;
					player.setResult("");
				} else if (result.equals("quit")) {
					status = Status.EXIT;
					player.setResult("");
				} else {
					view.displayOutput(result);

				}
				attempt++;
			}
		}
		if (attempt == max_attempts) {
			status = Status.NO_MORE_TRIES;
			player.setResult("");
		}
		checkStatus();
	}

	// Core of the game (dual)
	void play(Player player, int o) {

		input = "";
		while (attempt < (max_attempts * 2) && !result.equals("win") && !result.equals("quit")) {

			if (name.equals("") || name.equals("Computer")) {
				name = "Joueur 1";
			}

			view.displayOutput(rules);
			view.displayOutput(name + " playing");
			String input = player.input();
			if (input.isEmpty()) {
				view.displayError(nothing);
			} else if (!input.toUpperCase().equals("Q") && input.length() != list.length()) {
				view.displayError(wrongInput);
			} else {
				result = game.checkResult(input);
				player.setResult(result);
				if (result.equals("win")) {
					status = Status.WIN;
					winner = name;
					player.setResult("");
				} else if (result.equals("quit")) {
					status = Status.EXIT;
					player.setResult("");
				} else {
					view.displayOutput(result);
					attempt++;
					if (name.equals("Joueur 1")) {
						name = "Joueur 2";
					} else {
						name = "Joueur 1";
					}
				}

			}

		}
		if (attempt == (max_attempts * 2)) {
			status = Status.NO_MORE_TRIES;
			player.setResult("");
		}
		checkStatus();
	}

	// Asks the user if he wants to replay or quit
	void playAgain() {
		while (!input.toUpperCase().equals("O") && !input.toUpperCase().equals("N")) {
			view.displayOutput(askReplay);
			input = p0.input();
		}
		if (input.toUpperCase().equals("O")) {
			attempt = 0;
			status = Status.GAME_MENU;
		}
		if (input.toUpperCase().equals("N")) {
			attempt = 0;
			status = Status.QUIT;
		}
		checkStatus();
	}

}

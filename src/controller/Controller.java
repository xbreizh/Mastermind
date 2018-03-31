package controller;

import org.apache.log4j.Logger;

import application.Configuration;
import application.Log4J;
import game.Game;
import game.GameFactory;
import game.Status_Game;
import menu.GamesList;
import menu.Menu;
import menu.ModeList;
import menu.Status_Menu;
import player.Human;
import view.View;

public class Controller {
	private static final Logger log = Logger.getLogger(Log4J.class);
	private Human human;
	private View view;
	private Status_Menu stMenu;
	private Status_Game stGame;
	private GamesList gameType;
	private Game game;
	private Game[] gameArray;
	private Menu menu;
	private ModeList mode;
	private String[] resultArray;

	public Controller() {
		stGame = null;
		stMenu = Status_Menu.MENU_GAME;
	}

	public void switchMenu() {
		while (stGame != Status_Game.SETUP) {
			checkMenuError();
			checkStatusMenu();
		}

		playing();

	}

	private void checkStatusMenu() {
		log.debug(stMenu);
		view.displayLineBreak(stMenu.getOutput());
		switch (stMenu) {
		case MENU_GAME:
			stMenu = menu.selectAndValidGame(stMenu);
			gameType = menu.getGame();
			log.debug(stMenu + " " + gameType);
			break;
		case MENU_MODE:
			stMenu = menu.selectAndValidMode(stMenu);
			if (stMenu == Status_Menu.GAME) {
				gameType = menu.getGame();
				mode = menu.getMode();
				log.debug(ModeList.values()[Integer.parseInt(menu.getInput()) - 1]);
			}
			break;
		case GAME:
			initGames(gameType, mode);
			break;

		}
	}

	private void initGames(GamesList gameType, ModeList mode) {
		log.info(mode);
		new GameFactory();
		gameArray = GameFactory.createGameArray(gameType, mode, human);

		// Fills the gameArray with the games created
		for (int i = 0; i < gameArray.length; i++) {
			game = gameArray[i];
			stGame = Status_Game.SETUP;
		}
		resultArray = new String[gameArray.length];

	}

	private void playing() {
		for (int i = 0; i < gameArray.length; i++) {
			stGame = Status_Game.SETUP;
			game = gameArray[i];
			log.debug(stGame);
			while (stGame != Status_Game.REPLAY) {

				checkStatusGame();
			}
			resultArray[i] = game.getWinner().getName();
			view.displayLineBreak(resultArray[i]);
		}
		getFinalresult();
		stGame = Status_Game.REPLAY;
		checkKeepPlaying();
	}

	private void getFinalresult() {
//		System.out.println("Winner game1: "+resultArray[0]);
//		System.out.println("Winner game2: "+resultArray[1]);
		String finalWinner = "";
		if (resultArray.length == 2) {
			if (resultArray[0] != resultArray[1]) {
				finalWinner = "Both players win!";
			} else {
				finalWinner = resultArray[0] + " wins!";
			}
			view.displayLineBreak("Final result: " + finalWinner);
		}
	}

	private void checkKeepPlaying() {
		while (stGame == Status_Game.REPLAY) {
			checkStatusGame();
		}
		if (stGame == Status_Game.SETUP) {
			playing();
		} else {
			endGame();
		}
	}

	private void endGame() {
		while (stGame == Status_Game.EXIT) {
			checkStatusGame();
		}
		if (stGame == null) {
			stMenu = Status_Menu.MENU_GAME;
			switchMenu();
		} else {
			checkStatusGame();
		}
	}

	private void checkStatusGame() {
		log.debug(stGame);
		checkError();
		view.displayInline(stGame.getOutput());
		switch (stGame) {
		case SETUP:
			view.displayLineBreak(game.getNameP1());
			stGame = game.validSetup(stGame);
			log.info("Secret Code: " + game.getSecretCode());
			break;
		case PLAY:
			view.displayLineBreak(game.getNameP2() + " Attempt: " + Integer.toString(game.getAttempts() + 1) + "/"
					+ Configuration.getMax_attempts());
			stGame = game.play(stGame);
			view.displayLineBreak(game.getInput());
			break;
		case ANSWER:
			log.debug("Code to find: " + game.getSecretCode());
			if (game.getP1().getClass().getName().equals(Human.class.getName())) {
				view.displayLineBreak(gameType.getHowToAnswer());
			}
			stGame = game.answer(stGame);
			view.displayLineBreak(game.getAnswerToGive());
			break;
		case NO_MORE_TRIES:
			stGame = Status_Game.VERDICT;
			break;
		case FOUND:
			stGame = Status_Game.VERDICT;
			break;
		case VERDICT:
			// round++;
			stGame = Status_Game.REPLAY;
			break;
		case REPLAY:
			game.setInput(human.setup());
			stGame = game.validPlayAgain(stGame);
			break;
		case EXIT:
			game.setInput(human.setup());
			stGame = game.validExit(stGame);
			break;
		case END:
			break;
		default:
			break;

		}
	}

	private void checkMenuError() {
		String error = menu.getOutput();
		if (!error.equals("")) {
			log.warn(error);
			view.displayError(error);
		}
	}

	private void checkError() {
		String error = game.getError();
		if (!error.equals("")) {
			log.warn(error);
			view.displayError(error);
		}
	}

	// Getters and Setters

	public void setView(View view) {
		this.view = view;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public void setStMenu(Status_Menu stMenu) {
		this.stMenu = stMenu;
	}

	// public void setStGame(Status_Game stGame) {
	// this.stGame = stGame;
	// }

}

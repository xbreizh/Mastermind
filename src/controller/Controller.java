package controller;

import org.apache.log4j.Logger;

import application.Configuration;
import check.Check;
import game.Game;
import game.GameFactory;
import game.Status_Game;
import menu.GamesList;
import menu.Menu;
import menu.ModeList;
import menu.Status_Menu;
import player.Human;
import view.View;

/**
 * @author Xavier.Lamourec
 *
 */
public class Controller {
	private Logger log;
	

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

	
	/**
	 * loops the menu until correct values are passed
	 */
	public void switchMenu() {
		while (stGame != Status_Game.SETUP) {
			checkError(menu);
			checkStatusMenu();
		}

		playing();

	}
/**
 * gets the Game type and mode, then gets to Game initiation
 */
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
/**
 * Uses the gameType and mode to generate the array of games
 * @param gameType (from the GamesList)
 * @param mode (from the ModeList)
 */
	private void initGames(GamesList gameType, ModeList mode) {
		log.info(mode);
		new GameFactory();
		gameArray = GameFactory.createGameArray(gameType, mode, human);

		for (int i = 0; i < gameArray.length; i++) {
			game = gameArray[i];
			stGame = Status_Game.SETUP;
			game.setLog(log);
		}
		resultArray = new String[gameArray.length];

	}
/**
 * loops the game methods until status REPLAY is reached
 */
	private void playing() {
		for (int i = 0; i < gameArray.length; i++) {
			stGame = Status_Game.SETUP;
			game = gameArray[i];
			log.debug(stGame);
			while (stGame != Status_Game.REPLAY) {

				checkStatusGame();
			}
			resultArray[i] = game.getWinner().getClass().getSimpleName();
			view.displayLineBreak(resultArray[i]);
		}
		getFinalresult();
		stGame = Status_Game.REPLAY;
		checkKeepPlaying();
	}
/**
 * gets and displays the final result
 */
	private void getFinalresult() {
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
/**
 * asks the user if he wants to restart the game
 */
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
/**
 * asks the user if he wants to play another game (Back to menu) or exit the application
 */
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
/**
 * switches around the game methods depending of its status
 */
	private void checkStatusGame() {
		log.debug(stGame);
		checkError(game);
		view.displayInline(stGame.getOutput());
		switch (stGame) {
		case SETUP:
			view.displayLineBreak(game.getClass().getSimpleName());
			stGame = game.validSetup(stGame);
			log.info("Secret Code: " + game.getSecretCode());
			break;
		case PLAY:
			view.displayLineBreak(game.getClass().getSimpleName() + " Attempt: " + Integer.toString(game.getAttempts() + 1) + "/"
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
/**
 * returns any input error 
 */
	private void checkError(Check object) {
		String error = object.getOutput();
		if (!error.equals("")) {
			log.warn(error);
			view.displayError(error);
		}
	}

//	private void checkError() {
//		String error = game.getError();
//		if (!error.equals("")) {
//			log.warn(error);
//			view.displayError(error);
//		}
//	}

	// Getters and Setters
	/**
	 * sets the log
	 * @param log
	 */
	public void setLog(Logger log) {
		this.log = log;
	}
	/**
	 * sets the view
	 * @param view
	 */
	public void setView(View view) {
		this.view = view;
	}
	/**
	 * sets the menu
	 * @param menu
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	/**
	 * sets the user(human)
	 * @param human
	 */
	public void setHuman(Human human) {
		this.human = human;
	}

//	public void setStMenu(Status_Menu stMenu) {
//		this.stMenu = stMenu;
//	}


}

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
import player.Player;
import view.View;

public class Controller {
	private static final Logger log = Logger.getLogger(Log4J.class);
	private Player p0;
//	private Player p1;
//	private Player p2;
	private View view;
	private Status_Menu stMenu;
	private Status_Game stGame;
	private GamesList gameType;
	private Game game;
	public Game getGame() {
		return game;
	}

	private Game[] gameArray;
	private Menu menu;
	private int round = 0;
	ModeList mode;

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
			if(stMenu == Status_Menu.GAME){
			gameType = menu.getGame();
			mode=menu.getMode();
			log.debug(ModeList.values()[Integer.parseInt(menu.getInput()) - 1]);
			}
			break;
		case GAME:
			initGames(gameType,mode );
			break;

		}
	}
	
	private void initGames(GamesList gameType, ModeList mode) {
		log.info(mode);
		gameArray=GameFactory.createGameArray(gameType, mode);
		

		// Fills the gameArray with the games created
		for (int i = 0; i < gameArray.length; i++) {
			game = gameArray[i];
			stGame=Status_Game.SETUP;
		}

	}

	private void playing() {
		round = 0;
		for (int i = round; i < gameArray.length; i++) {
			game = gameArray[i];
//			p1 = gameArray[i].getP1();
//			p2 = gameArray[i].getP2();
			log.debug(stGame);
			while (stGame != Status_Game.REPLAY) {

				checkStatusGame();
			}
		}
//		game.gameVerdict();
		view.displayInline(game.getWinner().getName());
		stGame=Status_Game.REPLAY;
		checkKeepPlaying();
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
			stGame=game.validSetup(stGame);
			log.info("Secret Code: " + game.getSecretCode());
			break;
		case PLAY:
			view.displayLineBreak(game.getNameP2() + " Attempt: " + Integer.toString(game.getAttempts()) + "/"
					+ Configuration.getMax_attempts());
			stGame=game.play(stGame);
			view.displayLineBreak(game.getInput());
			break;
		case ANSWER:
			log.debug("Code to find: " + game.getSecretCode());
			if(game.getP1().getClass().getName().equals(Human.class.getName())){
			view.displayLineBreak(gameType.getHowToAnswer());
			}
			stGame=game.answer(stGame);
			view.displayLineBreak(game.getAnswerToGive());
			break;
		case NO_MORE_TRIES:
			stGame=Status_Game.VERDICT;
			break;
		case FOUND:
			stGame=Status_Game.VERDICT;
			break;
		case VERDICT:
//			game.gameVerdict();
//			view.displayOutput(game.getVerdict());
//			view.displayOutput(stGame.getOutput());
			round++;
			stGame=Status_Game.REPLAY;
			break;
		case REPLAY:
			p0.input();
			game.setInput(p0.getInput());
			stGame=game.validPlayAgain();
			break;
		case EXIT:
			p0.input();
			game.setInput(p0.getInput());
			stGame=game.validExit();
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

	public void setP0(Player p0) {
		this.p0 = p0;
	}

	public void setStMenu(Status_Menu stMenu) {
		this.stMenu = stMenu;
	}

	public void setStGame(Status_Game stGame) {
		this.stGame = stGame;
	}

}

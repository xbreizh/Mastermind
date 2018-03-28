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
import player.Player;
import view.View;

public class Controller {
	private static final Logger log = Logger.getLogger(Log4J.class);
	private Player p0;
	private Player p1;
	private Player p2;
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
		view.displayOutput(stMenu.getOutput());
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
		System.out.println("mode: "+mode);
		log.info(mode);
		gameArray=GameFactory.createGameArray(gameType, mode);
		

		// Fills the gameArray with the games created
		for (int i = 0; i < gameArray.length; i++) {
			game = gameArray[i];
//			updateStatusGame(Status_Game.SETUP);
			stGame=Status_Game.SETUP;
		}

	}

	private void playing() {
		round = 0;
		for (int i = round; i < gameArray.length; i++) {
			game = gameArray[i];
			p1 = gameArray[i].getP1();
			p2 = gameArray[i].getP2();
			log.debug(stGame);
			while (stGame != Status_Game.VERDICT) {

				checkStatusGame();
//				updateStatusGame(game.getStatus());
			}
//			view.displayOutput(game.getVerdict());
//			updateStatusGame(Status_Game.SETUP);
		}

//		updateStatusGame(Status_Game.REPLAY);
//		checkStatusGame();
		view.displayOutput(game.getVerdict());
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
		view.displayOutput(stGame.getOutput());
		switch (stGame) {
		case SETUP:
			view.displayOutput(p1.getName());
			stGame=game.validSetup(stGame);
			log.info("Secret Code: " + game.getSecretCode());
			break;
		case PLAY:
			view.displayOutput(p2.getName() + " Attempt: " + Integer.toString(game.getAttempts()) + "/"
					+ Configuration.getMax_attempts());
			stGame=game.play(stGame);
			view.displayOutput(game.getInput());
			break;
		case ANSWER:
			log.debug("Code to find: " + game.getSecretCode());
			stGame=game.answer(stGame);
			System.out.println(stGame);
			view.displayOutput(game.getAnswerToGive());
			break;
		case NO_MORE_TRIES:
			stGame=Status_Game.VERDICT;
			break;
		case FOUND:
			stGame=Status_Game.VERDICT;
		case VERDICT:
//			view.displayOutput(game.gameVerdict(p1, p2));
			game.gameVerdict(p1, p2);
//			view.displayOutput(game.getVerdict());
//			stGame=Status_Game.REPLAY;
			round++;
			break;
		case REPLAY:
			p0.input();
			game.setInput(p0.getInput());
			stGame=game.validPlayAgain();
//			updateStatusGame(game.getStatus());
			break;
		case EXIT:
			p0.input();
			game.setInput(p0.getInput());
			stGame=game.validExit();
//			updateStatusGame(game.getStatus());
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

//	private void updateStatusGame(Status_Game status) {
//		this.stGame = status;
//		game.setStatus(status);
//	}

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

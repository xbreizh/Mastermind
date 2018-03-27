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
	private Game[] gameArray;
//	private int dual = 0;
	private Menu menu;
	private int round = 0;
	ModeList mode;

	public Controller() {
		stGame = null;
		stMenu = Status_Menu.MENU_GAME;
	}

	public void switchMenu() {
		log.debug(stMenu);

		while (stGame != Status_Game.SETUP) {
			checkMenuError();
			checkStatusMenu();
		}

		play();

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
//		if (mode.equals(Integer.toString(ModeList.values()[0].getReference()))) {
//			p1 = new AI();
//			p2 = new Human();
//			gameArray = new Game[1];
//			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
//
//		}
//		if (p0.getInput().equals(Integer.toString(ModeList.values()[1].getReference()))) {
//			p1 = new Human();
//			p2 = new AI();
//			gameArray = new Game[1];
//			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
//		}
//		if (p0.getInput().equals(Integer.toString(ModeList.values()[2].getReference()))) {
//			p1 = new Human();
//			p2 = new Human();
//			gameArray = new Game[2];
//			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
//			gameArray[1] = GameFactory.createGame(gameType, p2, p1);
//		}
		System.out.println("mode: "+mode);
		gameArray=GameFactory.createGameArray(gameType, mode);
		

		// Fills the gameArray with the games created
		for (int i = 0; i < gameArray.length; i++) {
			game = gameArray[i];
			updateStatusGame(Status_Game.SETUP);
		}
		
//		dual = gameArray.length;
//		log.debug(p1.getClass().getName());
//		log.debug(p2.getClass().getName());
//		log.debug(gameType);
//		log.debug("Number of games: " + dual);
//		p1.setGame(gameType);
//		p2.setGame(gameType);
//		setNames();
//		p1=game.getP1();
//		p2=game.getP2();

	}
	
//	private void setNames() {
//		if (p1.getClass().equals(Human.class) && p2.getClass().equals(Human.class)) {
//			p1.setName("Paul");
//			p2.setName("John");
//		} else if (p1.getClass().equals(Human.class) && p2.getClass().equals(AI.class)) {
//			p1.setName("Human");
//			p2.setName("AI");
//		} else {
//			p1.setName("AI");
//			p2.setName("Human");
//		}
//	}

	private void play() {
		round = 0;
		for (int i = round; i < gameArray.length; i++) {
			game = gameArray[i];
			p1 = gameArray[i].getP1();
			p2 = gameArray[i].getP2();
			log.debug(stGame);
			while (stGame != Status_Game.VERDICT) {

				checkStatusGame();
				updateStatusGame(game.getStatus());
			}
//			System.out.println("round: " + round);
			view.displayOutput(game.getVerdict());
			updateStatusGame(Status_Game.SETUP);
		}

		updateStatusGame(Status_Game.REPLAY);
		checkStatusGame();
		checkKeepPlaying();
	}

	private void checkKeepPlaying() {
		while (stGame == Status_Game.REPLAY) {
			checkStatusGame();
		}
		if (stGame == Status_Game.SETUP) {
			play();
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
		log.info(stGame);
		checkError();
		view.displayOutput(stGame.getOutput());
		switch (stGame) {
		case SETUP:
			view.displayOutput(p1.getName());
			game.validSetup();
			log.info("Secret Code: " + game.getSecretCode());
			break;
		case PLAY:
			view.displayOutput(p2.getName() + " Attempt: " + Integer.toString(game.getAttempts()) + "/"
					+ Configuration.getMax_attempts());
			game.play();
//			p2.tryToGuess();
//			game.setInput(p2.getInput());
//			p1.setInput(p2.getInput());
//			game.validPlay();
			view.displayOutput(game.getInput());
			break;
		case ANSWER:
//			log.debug("Code to find: " + p1.getCodeToFind());
			game.answer();
//			p1.replyToGuess();
//			game.setAnswer(p1.getInput());
//			game.validAnswer();
//			p2.setAnswer(game.getOutput());
			view.displayOutput(game.getAnswerToGive());
			break;
		case NO_MORE_TRIES:
			updateStatusGame(Status_Game.VERDICT);
			break;
		case FOUND:
			updateStatusGame(Status_Game.VERDICT);
		case VERDICT:
			game.gameVerdict(p1, p2);
			round++;
			break;
		case REPLAY:
			p0.input();
			game.setInput(p0.getInput());
			game.validPlayAgain();
			updateStatusGame(game.getStatus());
			break;
		case EXIT:
			p0.input();
			game.setInput(p0.getInput());
			game.validExit();
			updateStatusGame(game.getStatus());
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

	private void updateStatusGame(Status_Game status) {
		this.stGame = status;
		game.setStatus(status);
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

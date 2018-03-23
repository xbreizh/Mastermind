package controller;

import org.apache.log4j.Logger;

import application.Log4J;
import game.Game;
import game.GameFactory;
import game.GamesList;
import game.Status_Game;
import menu.Menu;
import menu.ModeList;
import menu.Status_Menu;
import player.AI;
import player.Human;
import player.Player;
import view.View;

public class Controller {
	public static final Logger log = Logger.getLogger(Log4J.class);
	Player p0;
	Player p1;
	Player p2;
	View view;
	Status_Menu stMenu;
	Status_Game stGame;
	GamesList gameType;
	GameFactory gameFactory;
	Game game;
	Game[] gameArray;
	int dual = 0;
	Menu menu;

	public Controller() {
//		view = new View();
//		setP0(new Human());
//		 = new GameFactory();
		stMenu = Status_Menu.MENU_GAME;
		stGame=null;
		stMenu=Status_Menu.MENU_GAME;
//		menu = new Menu();
//		log.info("Menu Created");
//		checkStatusMenu();
	}
	
	public void switchMenu(){
		log.info(stMenu);
		while(stGame!=Status_Game.SETUP){
			checkStatusMenu();
		}

		
		switchGame();
		
	}

	public void switchGame(){
		log.info(stGame);
		while(stGame!= Status_Game.END && stMenu!=Status_Menu.MENU_GAME){
			stGame=game.getStatus();
				checkStatusGame();
			
		}
		if(stMenu==Status_Menu.MENU_GAME){
		System.out.println("back to menu");
		switchMenu();
		}else{
			System.out.println("the very end!");
		}
		
	}
	

	public void checkStatusMenu() {
		log.info(stMenu);
		switch (stMenu) {
		case MENU_GAME:
			view.displayOutput(stMenu.getOutput());
			p0.input();
			menu.setInput(p0.getInput());
			if (menu.validGame()) {
				stMenu = stMenu.MENU_MODE;
				gameType = menu.getGame();
				log.info(stMenu + " " + gameType);
			} else {
				checkMenuError();
			}
//			checkStatusMenu();
			break;
		case MENU_MODE:
			view.displayOutput(stMenu.getOutput());
			p0.input();
			menu.setInput(p0.getInput());
			if (menu.validMode()) {
				log.info(ModeList.values()[Integer.parseInt(p0.getInput())-1]);
				stMenu = stMenu.GAME;
			} else {
				checkMenuError();
			}
			break;
//			checkStatusMenu();
		case GAME:
			initGame();
			for (int i = 0; i < gameArray.length; i++) {
				game = gameArray[i];
				stGame=Status_Game.SETUP;
				game.setStatus(stGame);
				System.out.println("dual: " + dual);
//				checkStatusGame();
				dual--;

			}break;
		
		}
	}

	public void checkStatusGame() {
//		stGame = game.getStatus();
		log.info(stGame);
		switch (stGame) {
		case SETUP:
			view.displayOutput(stGame.getOutput());
			p1.input();
			p1.setCodeToFind(p1.getInput());
			game.setInput(p1.getCodeToFind());
			game.validSetup();
			checkError();
			stGame=game.getStatus();
			view.displayOutput("Secret Code: "+game.getInput());
			System.out.println(stGame);
//			checkStatusGame();
			break;
		case PLAY:
			view.displayOutput(stGame.getOutput());
			p2.tryToGuess();
//			System.out.println(p2.getInput());
			game.setInput(p2.getInput());
			p1.setInput(p2.getInput());
			view.displayOutput(p2.getInput());
			game.validInput();
			checkError();
			stGame=game.getStatus();
//			System.out.println(stGame);
//			checkStatusGame();
			break;
		case ANSWER:
			game.checkAttempts();
			view.displayOutput(stGame.getOutput());
			view.displayOutput("Code to find: "+p1.getCodeToFind());
			p1.replyToGuess();
			game.setAnswer(p1.getInput());
			game.validAnswer();
			view.displayOutput(game.getOutput());
			p2.setAnswer(game.getOutput());
			checkError();
//			p2.input();
//			System.out.println("status: "+stGame);
			stGame=game.getStatus();
//			checkStatusGame();
			break;
		case REPLAY:
			view.displayOutput(stGame.getOutput());
			p0.input();
			game.setInput(p0.getInput());
			game.validPlayAgain();
			checkError();
			stGame=game.getStatus();
			System.out.println("status replay: "+stGame);
//			checkStatusGame();
			break;
		case EXIT:
			if (dual != 2) {
				view.displayOutput(stGame.getOutput());
				p0.input();
				if (p0.getInput().toLowerCase().equals("y")) {
					stMenu = Status_Menu.MENU_GAME;
//					checkStatusMenu();
				}
				if (p0.getInput().toLowerCase().equals("n")) {
					stGame=Status_Game.END;
//					checkStatusGame();
				}
			} else {
				view.displayOutput("Game " + (dual - 1) + " over!");
				game.setStatus(stGame.SETUP);
				
//				checkStatusGame();
			}

			break;
		case NO_MORE_TRIES:
			view.displayOutput(stGame.getOutput());
			view.displayOutput(game.gameResult(p2, p1));
			game.reset();
			game.setStatus(stGame.REPLAY);
//			checkStatusGame();
			break;
		case WIN:
			view.displayOutput(game.gameResult(p2, p1));
			game.setStatus(Status_Game.REPLAY);
			game.reset();
//			checkStatusGame();
			break;
		case END:
			view.displayOutput(stGame.getOutput());
			break;
		
		}
	}

	protected void initGame() {
		if (p0.getInput().equals(Integer.toString(ModeList.values()[0].geti()))) {
			p1 = new AI();
			p2 = new Human();
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);

		}
		if (p0.getInput().equals(Integer.toString(ModeList.values()[1].geti()))) {
			p1 = new Human();
			p2 = new AI();
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
		}
		if (p0.getInput().equals(Integer.toString(ModeList.values()[2].geti()))) {
			p1 = new Human();
			p2 = new Human();
			gameArray = new Game[2];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
			gameArray[1] = GameFactory.createGame(gameType, p2, p1);
			dual = 2;
		}
		log.info(p1.getClass().getName());
		log.info(p2.getClass().getName());
		log.info(gameType);
		p1.setGame(gameType);
		p2.setGame(gameType);

	}
	
	// Getters and Setters
	
	public void setGameFactory(GameFactory gf) {
		this.gameFactory = gf;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Player getP0() {
		return p0;
	}

	public void setP0(Player p0) {
		this.p0 = p0;
	}

	public Status_Menu getStMenu() {
		return stMenu;
	}

	public void setStMenu(Status_Menu stMenu) {
		this.stMenu = stMenu;
	}

	public Status_Game getStGame() {
		return stGame;
	}

	public void setStGame(Status_Game stGame) {
		this.stGame = stGame;
	}

	public void checkMenuError() {
		String error = menu.getOutput();
		if (!error.equals("")) {
			log.warn(error);
			view.displayError(error);
		}
	}

	public void checkError() {
		String error = game.getError();
		if (!error.equals("")) {
			log.warn(error);
			view.displayError(error);
		}
	}

}

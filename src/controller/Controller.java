package controller;

import org.apache.log4j.Logger;

import application.Log4J;
import check.Check;
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
	public static final Logger log =Logger.getLogger(Log4J.class);
	Player p0;
	Player p1;
	Player p2;
	View view;
	Status_Menu stMenu;
	Status_Game stGame;
	GamesList gameType;
	Game game;
	Game[] gameArray;
	int dual = 0;
	Check ch;
	Menu menu;

	public Controller() {
		view = new View();
		setP0(new Human());
		GameFactory gf = new GameFactory();
		stMenu = Status_Menu.MENU_GAME;
		menu = new Menu();
		log.info("Menu Created");
		checkStatusMenu();
	}

	public Player getP0() {
		return p0;
	}

	public void setP0(Player p0) {
		this.p0 = p0;
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
				log.info(stMenu+" "+gameType);
			} else {
				view.displayError(menu.getOutput());
			}
			checkStatusMenu();
			break;
		case MENU_MODE:
			view.displayOutput(stMenu.getOutput());
			p0.input();
			menu.setInput(p0.getInput());
			if (menu.validMode()) {
				log.info(ModeList.values()[Integer.parseInt(p0.getInput())]);
				stMenu = stMenu.GAME;	
			} else {
				view.displayError(menu.getOutput());
			}

			checkStatusMenu();
		case GAME:
			initGame();
			for (int i = 0; i < gameArray.length; i++) {
				game = gameArray[i];
				setStGame(Status_Game.SETUP);
				game.setStatus(Status_Game.SETUP);			
				System.out.println("dual: "+dual);
				checkStatusGame();
				dual--;
				
			}
		}
	}



	public void checkStatusGame() {
		stGame = game.getStatus();
		log.info(stGame);
		switch (stGame) {
		case SETUP:
			view.displayOutput(stGame.getOutput());
			p1.input();
			p1.setCodeToFind(p1.getInput());
			game.setInput(p1.getCodeToFind());
			game.validSetup();
			checkError();
			checkStatusGame();
			break;
		case PLAY:
			view.displayOutput(stGame.getOutput());
			p2.tryToGuess();
			game.setInput(p2.getInput());
			p1.setInput(p2.getInput());
			view.displayOutput(p2.getInput());
			game.validInput();
			checkError();
			checkStatusGame();
			break;
		case ANSWER:
			view.displayOutput(stGame.getOutput());
			p1.replyToGuess();
			game.setAnswer(p1.getInput());
			game.validAnswer();
			view.displayOutput(game.getOutput());
			p2.setAnswer(game.getOutput());
			game.checkAttempts();
			checkError();
			checkStatusGame();
			break;
		case REPLAY:
			view.displayOutput(stGame.getOutput());
			p0.input();
			game.setInput(p0.getInput());
			game.validPlayAgain();
			checkError();
			checkStatusGame();
			break;
		case EXIT:
			if (dual != 2) {
				view.displayOutput(stGame.getOutput());
				p0.input();
				if (p0.getInput().toLowerCase().equals("y")) {
					stMenu = Status_Menu.MENU_GAME;
					checkStatusMenu();
				}
				if (p0.getInput().toLowerCase().equals("n")) {
					game.setStatus(stGame.END);
					checkStatusGame();
				}
			} else {
				view.displayOutput("Game " + (dual - 1) + " over!");
				game.setStatus(stGame.SETUP);
				checkStatusGame();
			}

			break;
		case NO_MORE_TRIES:
			view.displayOutput(stGame.getOutput());
			view.displayOutput(game.gameResult(p2, p1));
			game.reset();
			game.setStatus(stGame.REPLAY);
			checkStatusGame();
			break;
		case WIN:
			view.displayOutput(game.gameResult(p2, p1));
			game.reset();
			game.setStatus(stGame.REPLAY);
			checkStatusGame();
			break;
		case END:
			view.displayOutput(stGame.getOutput());
			break;
		default:
			break;
		}
	}
	
	protected void initGame() {
		if (p0.getInput().equals(Integer.toString(ModeList.values()[0].geti()))) {
			p1 = new AI();
			p2 = new Human();
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
			log.info("Number of games:"+gameArray.length+"\n"
					+"P1:"+p1+"\n"
					+ "P2:"+p2+
					"Game:"+gameType);
			
		}
		if (p0.getInput().equals(Integer.toString(ModeList.values()[1].geti()))) {
			p1 = new Human();
			p2 = new AI();
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
			log.info("Number of games:"+gameArray.length+"\n"
					+"P1:"+p1+"\n"
					+ "P2:"+p2+
					"Game:"+gameType);
		}
		if (p0.getInput().equals(Integer.toString(ModeList.values()[2].geti()))) {
			p1 = new Human();
			p2 = new Human();
			gameArray = new Game[2];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
			gameArray[1] = GameFactory.createGame(gameType, p2, p1);
			dual = 2;
			log.info("Number of games:"+gameArray.length+"\n"
					+"P1:"+p1+"\n"
					+ "P2:"+p2+
					"Game:"+gameType);
		}
		p1.setGame(gameType);
		p2.setGame(gameType);
		
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
			view.displayError(error);
		}
	}

	public void checkError() {
		String error = game.getError();
		if (!error.equals("")) {
			view.displayError(error);
			log.error(error);
		}
	}

}

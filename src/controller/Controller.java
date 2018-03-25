package controller;

import org.apache.log4j.Logger;

import application.Configuration;
import application.Log4J;
import check.InputStatus;
import game.Game;
import game.GameFactory;
import game.Status_Game;
import menu.GamesList;
import menu.Menu;
import menu.ModeList;
import menu.Status_Menu;
import player.AI;
import player.Human;
import player.Player;
import view.View;

public class Controller {
	private  static final Logger log = Logger.getLogger(Log4J.class);
	private Player p0;
	private Player p1;
	private Player p2;
	private View view;
	private Status_Menu stMenu;
	private Status_Game stGame;
	private GamesList gameType;
	private Game game;
	private Game[] gameArray;
	private int dual = 0;
	private Menu menu;
	private int round=0;

	public Controller() {
		stGame = null;
		stMenu = Status_Menu.MENU_GAME;
	}

	public void switchMenu() {
		log.info(stMenu);
		
		while (stGame != Status_Game.SETUP) {
			checkMenuError();
			checkStatusMenu();
		}

		play();

	}
	
	private void play(){
		round=0;
		for (int i=round; i < gameArray.length; i++) {
			System.out.println("Array: "+gameArray.length);
			game=gameArray[i];
			p1=gameArray[i].getP1();
			p2=gameArray[i].getP2();
			log.info(stGame);
			while (stGame != Status_Game.VERDICT) {
				
				checkStatusGame();
				updateStatusGame(game.getStatus());
			}
			System.out.println("round: "+round);
			view.displayOutput(game.getVerdict());
			updateStatusGame(Status_Game.SETUP);
		}	
		
		updateStatusGame(Status_Game.REPLAY);
		checkStatusGame();
		checkKeepPlaying();
	}
	
	private void checkKeepPlaying(){
		while(stGame==Status_Game.REPLAY){
			checkStatusGame();
		}
		if(stGame==Status_Game.SETUP){
			play();
		}else{
		endGame();
		}
	}
	
	private void endGame(){
		while(stGame==Status_Game.EXIT){
			checkStatusGame();
			}
//			view.displayOutput(stGame.getOutput());
//			p0.input();
//			if(p0.getInput().equalsIgnoreCase("y")){
//				stMenu = Status_Menu.MENU_GAME;
//				updateStatusGame(null);
//				switchMenu();
//			}
			if(stGame==null){
				stMenu = Status_Menu.MENU_GAME;
				switchMenu();
			}
			else {
				checkStatusGame();
			}
//		}
	}

	private void checkStatusMenu() {
		log.info(stMenu);
		switch (stMenu) {
		case MENU_GAME:
			view.displayOutput(stMenu.getOutput());
			p0.input();
			menu.setInput(p0.getInput());
			if (menu.validGame()) {
				stMenu = Status_Menu.MENU_MODE;
				gameType = menu.getGame();
				log.info(stMenu + " " + gameType);
			}
			break;
		case MENU_MODE:
			view.displayOutput(stMenu.getOutput());
			p0.input();
			menu.setInput(p0.getInput());
			if (menu.validMode()) {
				log.info(ModeList.values()[Integer.parseInt(p0.getInput()) - 1]);
				stMenu = Status_Menu.GAME;
			}
			break;
		case GAME:
			initGame();
			break;

		}
	}

	private void checkStatusGame() {
		log.info(stGame);
		checkError();
		view.displayOutput(stGame.getOutput());
		switch (stGame) {
		case SETUP:
//			view.displayOutput("Game "+dual+"\n"+p1.getName()+" - "+stGame.getOutput());
			view.displayOutput(p1.getName());
			p1.input();
			p1.setCodeToFind(p1.getInput());
			game.setInput(p1.getCodeToFind());
			game.validSetup();
			log.info("Secret Code: " + game.getInput());
			break;
		case PLAY:
//			view.displayOutput(p2.getName()+" - "+stGame.getOutput()+
//					" Attempt: "+Integer.toString(game.getAttempts())+"/"+Configuration.getMax_attempts());
			view.displayOutput(p2.getName()+" Attempt: "+Integer.toString(game.getAttempts())+"/"+Configuration.getMax_attempts());
			p2.tryToGuess();
			game.setInput(p2.getInput());
			p1.setInput(p2.getInput());
			game.validInput();
			view.displayOutput(p2.getInput());
			break;
		case ANSWER:
			log.info("Code to find: " + p1.getCodeToFind());
			p1.replyToGuess();
			game.setAnswer(p1.getInput());
			game.validAnswer();
//			view.displayOutput(game.getOutput());
			p2.setAnswer(game.getOutput());
			break;
		case NO_MORE_TRIES:
//			view.displayOutput(stGame.getOutput());
//			game.reset();
			
			game.gameResult(p1, p2);
			updateStatusGame(Status_Game.VERDICT);
			System.out.println("verdict");
			break;
		case FOUND:
//			view.displayOutput(stGame.getOutput());
//			game.reset();
			game.gameResult(p2, p1);
			updateStatusGame(Status_Game.VERDICT);
			stGame=game.getStatus();
		case VERDICT:
//			checkError();
//			view.displayOutput(stGame.getOutput());
			updateStatusGame(game.getStatus());
			round++;
			break;
		case REPLAY:
			
//			view.displayOutput(stGame.getOutput());
			p0.input();
			game.setInput(p0.getInput());
			game.validPlayAgain();
			updateStatusGame(game.getStatus());
			break;
		case EXIT:
//			view.displayOutput(stGame.getOutput());
			p0.input();
			if(p0.getInput().equalsIgnoreCase("y")){
//				stMenu = Status_Menu.MENU_GAME;
				updateStatusGame(null);
//				switchMenu();
			}
			else if(p0.getInput().equalsIgnoreCase("n")){
				updateStatusGame(Status_Game.END);
//				view.displayOutput(stGame.getOutput());
			}
			break;
		case END:
//			view.displayOutput(stGame.getOutput());
			break;
		default:
			break;

		}
	}

	private void initGame() {
		if (p0.getInput().equals(Integer.toString(ModeList.values()[0].getReference()))) {
			p1 = new AI();
			p2 = new Human();
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);

		}
		if (p0.getInput().equals(Integer.toString(ModeList.values()[1].getReference()))) {
			p1 = new Human();
			p2 = new AI();
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
		}
		if (p0.getInput().equals(Integer.toString(ModeList.values()[2].getReference()))) {
			p1 = new Human();
			p2 = new Human();
			gameArray = new Game[2];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
			gameArray[1] = GameFactory.createGame(gameType, p2, p1);
		}
		
		// Fills the gameArray with the games created
		for (int i = 0; i < gameArray.length; i++) {
		game = gameArray[i];
		updateStatusGame(Status_Game.SETUP);
//		stGame = Status_Game.SETUP;
//		game.setStatus(stGame);
	}
		dual=gameArray.length;
		log.info(p1.getClass().getName());
		log.info(p2.getClass().getName());
		log.info(gameType);
		log.info("Number of games: "+dual);
		p1.setGame(gameType);
		p2.setGame(gameType);
		setNames();

	}
	private void setNames(){
		System.out.println("Set Names!!");
		if(p1.getClass().equals(Human.class) && p2.getClass().equals(Human.class)){
			p1.setName("Paul");
			p2.setName("John");
		}else if(p1.getClass().equals(Human.class) && p2.getClass().equals(AI.class)){
			p1.setName("Human");
			p2.setName("AI");
		}else{
			p1.setName("AI");
			p2.setName("Human");
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
	private void updateStatusGame(Status_Game status){
		this.stGame=status;
		game.setStatus(status);
	}
//	private void updateStatusMenu(Status_Menu status){
//		this.stMenu=status;
//		menu.setStatus(status);
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

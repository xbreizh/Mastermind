package application;

import controller.Controller;
import controller.Mode;
import controller.Status;
import game.Game;
import game.Games;


public class Menu {
	Controller ct;
	Game[] gameArray=new Game[Games.values().length];
	public Menu(){
		
	}
	
	public Games checkGame() {
		int choice;
		do {
			sendOutput(Status.GAME_MENU.getOutput());
			choice = askForInput();
		} while (choice!=1 && choice!=2);
		if (choice==1) {
			return Games.MoreLess;
		}
		else {
			return Games.MasterMind;
		}

	}
	public Mode checkMode() {
		int choice;
		do {
			sendOutput(Status.MODE_MENU.getOutput());
			choice = askForInput();
		} while (choice!=1 && choice!=2 && choice!=3);
		if (choice==1) {
			return Mode.CHALLENGER;
		}
		if (choice==2) {
			return Mode.DEFENSER;
		}
		else {
			return Mode.DUAL;
		}

	}
	int askForInput() {
		int input = ct.getInput();
		return input;
	}
	
	void sendOutput(String output) {
		ct.sendOutput(output);
	}

	public void setController(Controller ct) {
		this.ct = ct;
	}

	public Game initGame(Games game){
		if()
	}

}

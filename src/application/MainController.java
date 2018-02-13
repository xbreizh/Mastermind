package application;

import java.util.Scanner;

import model.CheckInput;

public class MainController extends OpenScanner{

	protected Game game = null;
	Status status = Status.keepPlaying;
	public MainController() {
		
	}

	

	public void menu() {
		String input = "";
		String[] choices={"1", "2", "Q"};
		do {
			System.out.println("Choisissez votre jeu (taper Q pour quitter)");
			System.out.println("Moreless - tapez 1");
			System.out.println("Mastermind - tapez 2");
			input = controllerGetInput();
		}while (!CheckInput.checkValidString(input, choices));

		if (input.equals("1")) {
			controllerCreateGame(1);
		}
		if (input.equals("2")) {
			controllerCreateGame(2);
		}
		if (input.toUpperCase().equals("Q")) {
			quit();
		}
	}
//	public void checkMenuInput(String input){
//		ch.checkNotNull(input);
//	}

	public void controllerCreateGame(int value) {
		
		if (value == 1) {
			game = new MoreLess();
		}
		if (value == 2) {
			game = new Mastermind();
//			System.out.println("mastermind created");
		}
	}

	

	

	public void quit(){
		System.out.println("Thanks for playing!");
		closeScanner();
	}

}

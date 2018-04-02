package player;

import java.util.Scanner;

	/**
	 * The Human class provides String
	 * from user's input
	 * @author Xavier.Lamourec
	 *
	 */

public class Human extends Player {
	private Scanner sc;
	
	/**
	 * Initiates the Human object
	 * and the scanner
	 */
	public Human(){
		sc= new Scanner(System.in);
	}
	
	
	@Override
	public String tryToGuessMoreLess() {
		return getInput();

	}

	@Override
	public String tryToGuessMasterMind() {
		return getInput();

	}

	
	public String answerToGuess() {
		return getInput();
	}

	public String getInput() {
		return sc.nextLine();
	}

	

	
	

}

package player;

import java.util.Scanner;

public class Human extends Player {
	private Scanner sc;
	
	/**
	 * Initiates the Human object
	 * and the scanner
	 */
	public Human(){
		sc= new Scanner(System.in);
	}
	
	
	public String answerToGuess() {
//		if (gameName.equals(GamesList.MoreLess.name())) {
//			return replyMoreLess(gameName);
//		}
//		if (gameName.equals(GamesList.MasterMind.name())) {
//			return replyMasterMind(gameName);
//		}
//		return "Invalid reply";
		return setup();
	}

	public String setup() {
//		String str = sc.nextLine();
		return sc.nextLine();
	}

	@Override
	public String tryToGuessMoreLess() {
		return setup();

	}

//	@Override
//	private String replyMoreLess(String gameName) {
//		return setup();
//
//	}

	@Override
	public String tryToGuessMasterMind() {
		return setup();

	}

////	@Override
//	private String replyMasterMind(String gameName) {
//		return setup();
//
//	}
	
	

}

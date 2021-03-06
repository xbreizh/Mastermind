package menu;

/**
 *  List of available games.
 *  Each is associated with a numerical reference, an explanation for guessing
 *  and one for answering
 * @author Xavier.Lamourec
 *
 */

public enum GamesList {

	MoreLess(1, " ( Guess the code with 'more', 'less' or 'equals' as reply )", 
			"Enter \"=\",  \"+\" or  \"-\" : (Ex: \"=--+\")"), 
	MasterMind(2, " ( Guess the code with 'is present' or 'is well placed' as reply )", 
			"Enter 2 digits: (Ex: for 2 found, 1 well placed, enter \"21\")");

	private String howToGuess;
	private String howToAnswer;
	private int reference;

	private GamesList(int reference,String howToGuess, String howToAnswer) {
		this.reference=reference;
		this.howToGuess = howToGuess;
		this.howToAnswer = howToAnswer;
	}
	
	public int getReference(){
		return reference;
	}

	public String getHowToGuess() {
		return howToGuess;
	}
	
	public String getHowToAnswer() {
		return howToAnswer;
	}

}

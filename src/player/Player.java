package player;

import menu.GamesList;

	/**
	 * The Player class's role is to deal with inputs
	 * that will feed the game. They can be from the user(Human)
	 * or auto-generated(AI).
	 * The Player is a parent class for both Human and AI.
	 * @author Xavier.Lamourec
	 *
	 */

public abstract class Player {
	protected String input="";
	protected String secretCode = "";
	protected String answer = "";
	protected String answerToGive = "";
	protected String name="";
	protected String guess;
	
	/**
	 * Instantiates the Player
	 */
	public Player(){
		
	}

	/**
	 * Gets input 
	 * @return input
	 */
	
	public abstract String getInput();
	
	/**
	 * gets input for the game MoreLess
	 * @return
	 */
	
	public abstract String tryToGuessMoreLess();
	
	/**
	 * gets input for the game MasterMind
	 * @return
	 */
	
	public abstract String tryToGuessMasterMind();


	/**
	 * redirects to the corresponding methods depending
	 * on the game played and return the result
	 * @param gameName 
	 * @return guess as a String
	 */
	public String tryToGuess(String gameName) {

		if (gameName.equals(GamesList.MoreLess.name())) {
			return tryToGuessMoreLess();
		}
		else {
			return tryToGuessMasterMind();
		}
	}

	// getters & setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setGuess(String guess) {
		this.guess=guess;
	}
}

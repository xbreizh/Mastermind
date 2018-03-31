package player;

import menu.GamesList;

public abstract class Player {
	protected String input="";
	protected String secretCode = "";
	protected String answer = "";
	protected String answerToGive = "";
	protected String name="";
	protected String guess = "";
	
	public Player(){
		
	}

	// Abstract methods
	public abstract String setup();

	public abstract String tryToGuessMoreLess();

//	public abstract String replyMoreLess(String gameName);

	public abstract String tryToGuessMasterMind();

//	public abstract String replyMasterMind(String gameName);


	// Trying to guess the result
	public String tryToGuess(String gameName) {

		if (gameName.equals(GamesList.MoreLess.name())) {
			return tryToGuessMoreLess();
		}
		if (gameName.equals(GamesList.MasterMind.name())) {
			return tryToGuessMasterMind();
		}
		return "invalid";
	}

	// Provides feedback on other player's input
//	public String replyToGuess(String gameName) {
//		if (gameName.equals(GamesList.MoreLess.name())) {
//			return replyMoreLess(gameName);
//		}
//		if (gameName.equals(GamesList.MasterMind.name())) {
//			return replyMasterMind(gameName);
//		}
//		return "Invalid reply";
//	}

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

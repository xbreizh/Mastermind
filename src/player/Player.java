package player;

import game.Game;
import menu.GamesList;

public abstract class Player {

	protected String input = "";
	// protected String wrong = "wrong answer";
	protected String answer = "";
	protected String answerToGive = "";
	protected String name;
	protected String guess = "";
	// protected Game game;

	// Abstract methods
	public abstract String setup();

	public abstract String tryToGuessMoreless();

	public abstract String replyMoreless(Game game);

	public abstract String tryToGuessMasterMind();

	public abstract String replyMasterMind(Game game);

	// public Status_Game tryToGuess(Game game){
	//
	// }

	// Trying to guess the result
	public String tryToGuess(Game game) {

		if (game.getClass().getSimpleName().equals(GamesList.MoreLess.name())) {
			return tryToGuessMoreless();
		}
		if (game.getClass().getSimpleName().equals(GamesList.MasterMind.name())) {
			return tryToGuessMasterMind();
		}
		return "invalid";
	}

	// Provides feedback on other player's input
	public String replyToGuess(Game game) {
		if (game.getClass().getSimpleName().equals(GamesList.MoreLess.name())) {
			return replyMoreless(game);
		}
		if (game.getClass().getSimpleName().equals(GamesList.MasterMind.name())) {
			return replyMasterMind(game);
		}
		return "Invalid reply";
	}

	// getters & setters
	// public void setGame(Game game) {
	// this.game = game;
	// }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getInput() {
		return input;
	}
}

package player;

import game.Game;
import menu.GamesList;

public abstract class Player {
	
	protected String input = "";
//	protected String wrong = "wrong answer";
	protected String feedBack = "";
	protected String answerToGive = "";
	protected String name;
	protected Game game;

	

	

	// Abstract methods
	public abstract String input();

	public abstract void tryToGuessMoreless();

	public abstract void replyMoreless();

	public abstract void tryToGuessMasterMind();

	public abstract void replyMasterMind();

	// Trying to guess the result
	public void tryToGuess() {
		if (game.getClass().getSimpleName().equals(GamesList.MoreLess.name())) {
			tryToGuessMoreless();
		}
		if (game.getClass().getSimpleName().equals(GamesList.MasterMind.name())) {
			tryToGuessMasterMind();
		}
	}

	// Provides feedback on other player's input
	public void replyToGuess() {
		if (game.getClass().getSimpleName().equals(GamesList.MoreLess.name())) {
			replyMoreless();
		}
		if (game.getClass().getSimpleName().equals(GamesList.MasterMind.name())) {
			replyMasterMind();
		}
	}

	// getters & setters
	public void setGame(Game game) {
		this.game = game;
	}
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
		return feedBack;
	}

	public void setAnswer(String answer) {
		this.feedBack = answer;
	}

	public String getInput() {
		return input;
	}
}

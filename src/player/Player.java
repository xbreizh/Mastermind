package player;

import menu.GamesList;

public abstract class Player {
	
	protected String input = "";
	protected String wrong = "wrong answer";
	protected String feedBack = "";
	protected String codeToFind;
	protected String answerToGive = "";
	protected GamesList game;
	protected String name;

	

	// Abstract methods
	public abstract String input();

	public abstract void tryToGuessMoreless();

	public abstract void replyMoreless();

	public abstract void tryToGuessMasterMind();

	public abstract void replyMasterMind();

	// Trying to guess the result
	public void tryToGuess() {
		if (game.equals(GamesList.MoreLess)) {
			tryToGuessMoreless();
		}
		if (game.equals(GamesList.MasterMind)) {
			tryToGuessMasterMind();
		}
	}

	// Provides feedback on other player's input
	public void replyToGuess() {
		if (game.equals(GamesList.MoreLess)) {
			replyMoreless();
		}
		if (game.equals(GamesList.MasterMind)) {
			replyMasterMind();
		}
	}

	// getters & setters
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

	public String getCodeToFind() {
		return codeToFind;
	}

	public void setCodeToFind(String codeToFind) {
		this.codeToFind = codeToFind;
	}

	public GamesList getGame() {
		return game;
	}

	public void setGame(GamesList game) {
		this.game = game;
	}
}

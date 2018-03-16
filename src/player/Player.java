package player;

import java.util.Scanner;

import game.Game;
import game.GamesList;

public abstract class Player {
	Scanner sc=new Scanner(System.in);
	String input="";
	String wrong="wrong answer";
	String feedBack="";
	String codeToFind;
	String answerToGive="";
	GamesList game;
	
	
	//Abstract methods
	public abstract String input();
	public abstract void guessMoreless();
	public abstract void replyMoreless();
	public abstract void guessMasterMind();
	public abstract void replyMasterMind();

	// Trying to guess the result
	public void tryToGuess() {
		if(game.equals(GamesList.MoreLess)){
			guessMoreless();
		}
		if(game.equals(GamesList.MasterMind)){
			guessMasterMind();
		}
	}
	
	
	//Provides feedback on other player's input
	public void replyToGuess() {
		if(game.equals(GamesList.MoreLess)){
			replyMoreless();
		}
		if(game.equals(GamesList.MasterMind)){
			replyMasterMind();
		}
	}
	
	
	
	//getters & setters
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

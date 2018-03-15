package player;

import java.util.Scanner;

public abstract class Player {
	Scanner sc=new Scanner(System.in);
	String input="";
	String wrong="wrong answer";
	String feedBack="";
	String codeToFind;
	String answerToGive="";
	
	
	public abstract String input();
	public abstract void tryToGuess();
	public abstract void replyToGuess();

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
}

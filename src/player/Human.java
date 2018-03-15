package player;

import java.util.Scanner;

import game.Game;

public class Human extends Player{
	Scanner sc=new Scanner(System.in);
	
	public String input(){
		String str=sc.nextLine();
		if(str.equals(null)){
			str="1234";
		}
		return this.input=str;
	}

	@Override
	public void tryToGuess() {
		input();
		
	}

	@Override
	public void replyToGuess() {
		input();
		
	}
	
}

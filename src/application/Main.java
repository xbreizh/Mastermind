package application;

import game.Check;

public class Main {

	public static void main(String[] args) {

//		Controller ct = new Controller();
		
		Check ch = new Check();
		
		ch.setInput("");
		ch.checkInteger();
		System.err.println(ch.getOutput());

	}

}

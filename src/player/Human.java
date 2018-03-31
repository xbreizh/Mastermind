package player;

import java.util.Scanner;

import game.Game;

public class Human extends Player {
	private Scanner sc = new Scanner(System.in);

	public String setup() {
		String str = sc.nextLine();
		return this.input = str;
	}

	@Override
	public String tryToGuessMoreless() {
		return setup();

	}

	@Override
	public String replyMoreless(Game game) {
		return setup();

	}

	@Override
	public String tryToGuessMasterMind() {
		return setup();

	}

	@Override
	public String replyMasterMind(Game game) {
		return setup();

	}

}

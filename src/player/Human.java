package player;

import java.util.Scanner;

public class Human extends Player {
	private Scanner sc = new Scanner(System.in);

	public String setup() {
		String str = sc.nextLine();
		return this.input = str;
	}

	@Override
	public String tryToGuessMoreLess() {
		return setup();

	}

	@Override
	public String replyMoreLess(String gameName) {
		return setup();

	}

	@Override
	public String tryToGuessMasterMind() {
		return setup();

	}

	@Override
	public String replyMasterMind(String gameName) {
		return setup();

	}

}

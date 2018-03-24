package player;

import java.util.Scanner;

public class Human extends Player {
	private Scanner sc = new Scanner(System.in);

	public String input() {
		String str = sc.nextLine();
		return this.input = str;
	}

	@Override
	public void guessMoreless() {
		input();

	}

	@Override
	public void replyMoreless() {
		input();

	}

	@Override
	public void guessMasterMind() {
		input();

	}

	@Override
	public void replyMasterMind() {
		input();

	}

}

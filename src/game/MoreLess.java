package game;

import player.Player;

	/**
	 * The MoreLess classes inherits the Game class
	 * It contains a specific method for generating a valid answer
	 * and another for validating a suggestion of answer.
	 * The user must guess a digits code and gets symbols as reply(+, -, =)
	 * @author Xavier.Lamourec
	 *
	 */

public class MoreLess extends Game {
	
	/**
	 * Initiates the MoreLess game based on the parent(Game) class
	 * @param defender
	 * @param challenger
	 */

	public MoreLess(Player defender, Player challenger) {
		super(defender, challenger);
	}

	/**
	 * generates the answer to provide based on the secret code
	 * and the input provided(guess)
	 * @param secretCode
	 * @param guess
	 */
	public void generateAnswerToGive() {
		int[] a = secretCodeArray;
		int[] b = intToArray(Integer.parseInt(guess));
		String str = "";
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				str += "=";
			} else if (a[i] < b[i]) {
				str += "-";
			} else if (a[i] > b[i]) {
				str += "+";
			}
		}
		output=str;
		answerToGive = str;
	}

	

}

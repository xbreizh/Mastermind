package game;

import java.util.ArrayList;

/**
 * The MasterMind classes inherits the Game class
 * It contains a specific method for generating a valid answer
 * and another for validating a suggestion of answer.
 * The user must guess the number of values found and values well placed in return
 * @author Xavier.Lamourec
 *
 */

import player.Player;

public class MasterMind extends Game {

	/**
	 * Initiates the MasterMind game based on the parent(Game) class
	 * @param defender
	 * @param challenger
	 */

	public MasterMind(Player defender, Player challenger) {
		super(defender, challenger);
	}

	/**
	 * generates the answer to provide based on the secret code
	 * and the input provided(guess)
	 * @param secretCode
	 * @param guess
	 */
	
	@Override
	void generateAnswerToGive() {
		int[] a = secretCodeArray;
		int[] b = intToArray(Integer.parseInt(guess));
		ArrayList<Integer> aa = new ArrayList<>();
		ArrayList<Integer> bb = new ArrayList<>();

		arrayToList(a, aa);
		arrayToList(b, bb);
		int found = 0;
		int placed = 0;

		for (int i = 0; i < bb.size(); i++) {
			if (aa.get(i) == bb.get(i)) {
				bb.set(i, i * 10);
				placed++;
			}
			for (int j = 0; j < bb.size(); j++) {

				if (aa.get(j) == bb.get(i)) {
					bb.set(i, i * 10);
					found++;

				}

			}

		}
		output = "Found: " + found + ", well placed: " + placed;
		answerToGive = found + "" + placed;
	}



}

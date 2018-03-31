package game;

import java.util.ArrayList;

import player.Player;

public class MasterMind extends Game {

	public MasterMind(Player defender, Player challenger) {
		super(defender, challenger);
	}

	String result = "";

	@Override
	void generateAnswerToGive() {
		int[] a = intToArray(secretCode);
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

//	public Status_Game validAnswer(Status_Game status) {
////		generateAnswerToGive(secretCode, Integer.parseInt(input));
//		if (!answerToGive.equals(answer)) {
//			error="Wrong answer!, should be " + answerToGive;
//			status= Status_Game.ANSWER;
//		} else {
//			error="";
////			String goodResult="0"+Integer.toString(Configuration.getNbDigits());
//			if (Integer.parseInt(guess) == (secretCode)) {
//				winner=challenger;
//				status= Status_Game.FOUND;
//			} else {
//				status = Status_Game.PLAY;
//				status = checkAttempts(status);
//				return status;
//			}
//		}
//		return status;
//
//	}



}

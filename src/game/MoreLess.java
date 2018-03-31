package game;

import player.Player;

public class MoreLess extends Game {

	public MoreLess(Player p1, Player p2) {
		super(p1, p2);
	}

	public void generateAnswerToGive() {
		int[] a = intToArray(secretCode);
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
		answerToGive = str;
//		output = answerToGive;
	}

	public Status_Game validAnswer(Status_Game status) {
//		generateAnswerToGive();
		if (!answerToGive.equals(answer)) {
			System.out.println("ici");
			error="Wrong answer!, should be " + answerToGive;
			return status;
		} else {
			error="";
			if (Integer.parseInt(input) == (secretCode)) {
				winner=challenger;
				status= Status_Game.FOUND;
			} else {
				status= Status_Game.PLAY;
				status=checkAttempts(status);
				return status;
			}
		}
		return status;
	}

}

package game;

import player.Player;

public class MoreLess extends Game {

	public MoreLess(Player p1, Player p2) {
		super(p1, p2);
	}

	public void getVerdict(int n1, int n2) {
		int[] a = intToArray(n1);
		int[] b = intToArray(n2);
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
		output = answerToGive;
	}

	public Status_Game validAnswer(Status_Game status) {
		getVerdict(secretCode, Integer.parseInt(input));
		if (!answerToGive.equals(answer)) {
			setError("Wrong answer!, should be " + answerToGive);
			return status;
		} else {
			setError("");
			if (Integer.parseInt(input) == (secretCode)) {
				winner=p1;
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

package game;

import java.util.ArrayList;

import player.Player;

public class MasterMind extends Game {

	public MasterMind(Player p1, Player p2) {
		super(p1, p2);
	}

	String result = "";

	@Override
	void getVerdict(int n1, int n2) {
		int[] a = intToArray(n1);
		int[] b = intToArray(n2);
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

	public void validAnswer() {
		getVerdict(secretCode, Integer.parseInt(input));
		if (!answerToGive.equals(answer)) {
			setError("Wrong answer!, should be " + answerToGive);
			setStatus(Status_Game.ANSWER);
		} else {
			setError("");
			if (answerToGive.equals("04")) {
				setStatus(Status_Game.FOUND);
			} else {
				setStatus(Status_Game.PLAY);
				checkAttempts();

			}
		}

	}

	void arrayToList(int[] array, ArrayList<Integer> aList) {
		for (int i = 0; i < array.length; i++) {
			aList.add(array[i]);

		}

	}

}

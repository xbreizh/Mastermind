package player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import application.Configuration;

public class AI extends Player {

	private String newProposition = "";
	// Settings conf = Settings.getConfiguration();
	private int min;
	private int max;
	
	public AI(){
		setMinAndMax(Configuration.getNbDigits());
		
	}
private void setMinAndMax(int maxNbDigits){
		
		String minString="1";
		String maxString="9";
			for (int i = 0; i < Configuration.getNbDigits()-1; i++) {
				
				minString+="0";
				maxString+="9";
				min=Integer.parseInt(minString);
				max=Integer.parseInt(maxString);
			}
		
	}
	@Override
	public String input() {
		return this.input = Integer.toString(ThreadLocalRandom.current().nextInt(min, max));
	}

	@Override
	public void tryToGuessMoreless() {
		if (input.length() == 0) {
			input = Integer.toString(max/2);
		} else {
			String newInput = "";
			for (int i = 0; i < input.length(); i++) {
				int a = Character.getNumericValue(input.charAt(i));
				if (feedBack.substring(i, i + 1).equals("=")) {
					newInput += a;
				}
				if (feedBack.substring(i, i + 1).equals("-")) {
					newInput += (a - 1);

				}
				if (feedBack.substring(i, i + 1).equals("+")) {
					newInput += (a + 1);
				}
			}
			input = newInput;
			waiting();
		}

	}
	@Override
	public void tryToGuessMasterMind() {
		input = Integer.toString(ThreadLocalRandom.current().nextInt(min, max));

		waiting();

	}

	protected void waiting() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public int[] intToArray(int code) {
		int[] tab = new int[Configuration.getNbDigits()];
		int s = Configuration.getNbDigits() - 1;
		while (code > 0) {
			int b = code % 10;
			tab[s] = b;
			code = code / 10;
			s--;
		}
		return tab;
	}

	public String getNewProposition() {
		return newProposition;
	}

	

	@Override
	public void replyMoreless() {
		int[] a = game.getSecretCodeArray();
		int[] b = intToArray(Integer.parseInt(game.getInput()));
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
		input = str;

	}

	@Override
	public void replyMasterMind() {
		int[] a = game.getSecretCodeArray();
		int[] b = intToArray(Integer.parseInt(input));

		ArrayList<Integer> aa = new ArrayList<>();
		ArrayList<Integer> bb = new ArrayList<>();
		int found = 0;
		int placed = 0;

		arrayToList(a, aa);
		arrayToList(b, bb);

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
		input = found + "" + placed;

	}

	void arrayToList(int[] array, ArrayList<Integer> aList) {
		for (int i = 0; i < array.length; i++) {
			aList.add(array[i]);
		}

	}

}

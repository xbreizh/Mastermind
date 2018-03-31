package player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import application.Configuration;

public class AI extends Player {

	private String newProposition = "";
	private int min;
	private int max;

	public AI() {
		setMinAndMax(Configuration.getNbDigits());

	}

	private void setMinAndMax(int maxNbDigits) {

		String minString = "1";
		String maxString = "9";
		for (int i = 0; i < Configuration.getNbDigits() - 1; i++) {

			minString += "0";
			maxString += "9";
			min = Integer.parseInt(minString);
			max = Integer.parseInt(maxString);
		}

	}
	
	private String initFirstGuess(){
		for (int i = 0; i < Configuration.getNbDigits(); i++) {
			guess+="5";
		}
//		System.out.println("Guessing: "+guess);
		return guess;
		
	}

	@Override
	public String setup() {
		return secretCode = Integer.toString(ThreadLocalRandom.current().nextInt(min, max));
	}

	@Override
	public String tryToGuessMoreLess() {
		String newGuess = "";
		if (guess.length() == 0) {
			newGuess=initFirstGuess();
		} else {
			for (int i = 0; i < guess.length(); i++) {
				int a = Character.getNumericValue(guess.charAt(i));
				if (answer.substring(i, i + 1).equals("=")) {
					newGuess += a;
				}
				if (answer.substring(i, i + 1).equals("-")) {
					newGuess += (a - 1);

				}
				if (answer.substring(i, i + 1).equals("+")) {
					newGuess += (a + 1);
				}
			}
			waiting();
		}
		
		return guess=newGuess;

	}

	@Override
	public String tryToGuessMasterMind() {
		initFirstGuess();
		input = Integer.toString(ThreadLocalRandom.current().nextInt(min, max));
//		String str= Integer.toString(Configuration.getNbDigits());
//		for (int i = 0; i < str.length(); i++) {
//			input
//			
//		}
//		input = 
		input = "55";
		waiting();
		return input;

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
	public String replyMoreLess(String gameName) {
//		int[] a = intToArray(Integer.parseInt(secretCode));
//		int[] b = intToArray(Integer.parseInt(guess));
//		String str = "";
//		for (int i = 0; i < a.length; i++) {
//			if (a[i] == b[i]) {
//				str += "=";
//			} else if (a[i] < b[i]) {
//				str += "-";
//			} else if (a[i] > b[i]) {
//				str += "+";
//			}
//		}
		return "suka";

	}

	@Override
	public String replyMasterMind(String gameName) {
//		int[] a = intToArray(Integer.parseInt(secretCode));
//		int[] b = intToArray(Integer.parseInt(guess));
//
//		ArrayList<Integer> aa = new ArrayList<>();
//		ArrayList<Integer> bb = new ArrayList<>();
//		int found = 0;
//		int placed = 0;
//
//		arrayToList(a, aa);
//		arrayToList(b, bb);
//
//		for (int i = 0; i < bb.size(); i++) {
//			if (aa.get(i) == bb.get(i)) {
//				bb.set(i, i * 10);
//				placed++;
//			}
//			for (int j = 0; j < bb.size(); j++) {
//
//				if (aa.get(j) == bb.get(i)) {
//					bb.set(i, i * 10);
//					found++;
//
//				}
//
//			}
//
//		}
//		return input = found + "" + placed;
return "nada";
	}

	void arrayToList(int[] array, ArrayList<Integer> aList) {
		for (int i = 0; i < array.length; i++) {
			aList.add(array[i]);
		}

	}

}

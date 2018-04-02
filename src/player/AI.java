package player;

import java.util.concurrent.ThreadLocalRandom;

import application.Configuration;

public class AI extends Player {

	private int min;
	private int max;
	
	/**
	 * Initiates the AI and setups the min and max to 
	 * define the range of possible guessing
	 */

	public AI() {
		setMinAndMax(Configuration.getNbDigits());

	}
	
	/**
	 * generates and returns the secret code
	 * @param secretCode
	 */
	@Override
	public String getInput() {
		return secretCode = Integer.toString(ThreadLocalRandom.current().nextInt(min, max));
	}

	/**
	 * setups the min and max bases on the configuration file
	 * @param maxNbDigits
	 */
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
	
	/**
	 * returns the first guess of the game
	 * which will be 55
	 * @return
	 */
	
	private String initFirstGuess(){
		guess="";
		for (int i = 0; i < Configuration.getNbDigits(); i++) {
			guess+="5";
		}
		return guess;
		
	}

	/**
	 * returns a guess of the possible secretCode
	 * for the MoreLess game
	 * based on the other player's feedback and the 
	 * previous guess
	 * @param answer (other player's feedback)
	 * @param guess (previous guess)
	 */

	@Override
	public String tryToGuessMoreLess() {
		String newGuess = "";
		if (guess==null) {
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
		}
		
		return guess=newGuess;

	}
	
	/**
	 * returns a guess of the possible secretCode
	 * for the MoreLess game
	 * based on the other player's feedback and the 
	 * previous guess
	 * @param answer (other player's feedback)
	 * @param guess (previous guess)
	 */

	@Override
	public String tryToGuessMasterMind() {
		initFirstGuess();
		return guess;

	}

//	protected void waiting() {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException ex) {
//			Thread.currentThread().interrupt();
//		}
//	}
	
	/**
	 * Converts an integer into an array of
	 * integer and returns that array
	 * @param code
	 * @return
	 */

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

}

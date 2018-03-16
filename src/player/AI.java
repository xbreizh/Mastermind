package player;

import game.Configuration;

public class AI extends Player {

	private String newProposition = "";
	Configuration conf = Configuration.getConfiguration();

	@Override
	public String input() {
		return this.input = "1234";
	}

//	@Override
//	public void tryToGuess() {
//		if (input.length() == 0) {
//			input = "5555";
//		} else {
//			String newInput = "";
//			for (int i = 0; i < input.length(); i++) {
//				int a = Character.getNumericValue(input.charAt(i));
//				if (feedBack.substring(i, i + 1).equals("=")) {
//					newInput += a;
//				}
//				if (feedBack.substring(i, i + 1).equals("-")) {
//					newInput += (a - 1);
//
//				}
//				if (feedBack.substring(i, i + 1).equals("+")) {
//					newInput += (a + 1);
//				}
//			}
//			input = newInput;
//			waiting();
//		}
//	}

	/**
	 * 
	 */
	protected void waiting() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}


	public void giveFeedback() {
		int[] a = intToArray(Integer.parseInt(codeToFind));
		int[] b = intToArray(Integer.parseInt(input));
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

	public int[] intToArray(int code) {
		int[] tab = new int[conf.getNbDigits()];
		int s = conf.getNbDigits() - 1;
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
	public void guessMoreless() {
		if (input.length() == 0) {
			input = "5555";
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
	public void replyMoreless() {
		int[] a = intToArray(Integer.parseInt(codeToFind));
		int[] b = intToArray(Integer.parseInt(input));
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
	public void guessMasterMind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replyMasterMind() {
		int[] a = intToArray(Integer.parseInt(codeToFind));
		int[] b = intToArray(Integer.parseInt(input));
		int inList=0;
		int	onSpot=0;
		
		for (int i = 0; i < b.length; i++) {
				if(a[i]==b[i]){
					onSpot++;
					System.out.println("ok");
				}
				if(a[i]==b[i] && a[i]!=b[i]){
					inList++;
				}
				
			
		}
		input=inList+""+onSpot;
		
	}

}

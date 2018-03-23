package player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import game.Configuration;

public class AI extends Player {

	private String newProposition = "";
	Configuration conf = Configuration.getConfiguration();

	@Override
	public String input() {
		return this.input = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 9999));
	}

	@Override
	public void guessMasterMind() {
		input = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 9999));
		
		waiting();
		
	}

	
	protected void waiting() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}


//	public void giveFeedback() {
//		int[] a = intToArray(Integer.parseInt(codeToFind));
//		int[] b = intToArray(Integer.parseInt(input));
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
//		input = str;
//	}

	public int[] intToArray(int code) {
		int[] tab = new int[Configuration.nbDigits];
		int s = Configuration.nbDigits - 1;
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
	public void replyMasterMind() {
		int[] a = intToArray((Integer.parseInt(codeToFind)));
		int[] b = intToArray(Integer.parseInt(input));
		
		ArrayList<Integer> aa=new ArrayList<>();
		ArrayList<Integer> bb=new ArrayList<>();
		int found=0;
		int	placed=0;
		
		arrayToList(a, aa);
		arrayToList(b, bb);
		
		for (int i = 0; i < bb.size(); i++) {
			if(aa.get(i)==bb.get(i)){
				bb.set(i,i*10);
				placed++;
			}
			for (int j = 0; j < bb.size(); j++) {
				
				if(aa.get(j)==bb.get(i)){
					bb.set(i,i*10);
					found++;
					
				}
				
			}
			
			
		}
		input=found+""+placed;
		
	}
	void arrayToList(int[] array, ArrayList<Integer> aList){
		for (int i = 0; i < array.length; i++) {
			aList.add(array[i]);
		}
		
	}

}

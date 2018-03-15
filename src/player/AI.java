package player;

import game.Configuration;

public class AI extends Player{

	private String newProposition="";
	Configuration conf = Configuration.getConfiguration();
	
	@Override
	public String input() {
		return this.input="1234";
	}

	@Override
	public void tryToGuess() {
		if(input.length()==0){
			input="5555";
		}else{
			String newInput="";
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
			input=newInput;
			waiting();
		}
	}

	/**
	 * 
	 */
	protected void waiting() {
		try        
		{
		    Thread.sleep(1000);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
	}

	@Override
	public void replyToGuess() {
		giveFeedback();
		waiting();
		System.out.println(answerToGive);
		
	}
	
	public void giveFeedback(){
		int[] a=intToArray(Integer.parseInt(codeToFind));
		int[] b=intToArray(Integer.parseInt(input));
		String str="";
		for (int i = 0; i < a.length; i++) {
			if(a[i]==b[i]){
				str+="=";
			}else if(a[i]<b[i]){
				str+="-";
			}else if(a[i]>b[i]){
				str+="+";
			}
		}
		input=str;
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

	
//	public void analyseMoreLess() {
//			for (int i = 0; i < input.length(); i++) {
//				int a = Character.getNumericValue(input.charAt(i));
//				if (answer.substring(i, i + 1).equals("=")) {
//					input += a;
//				}
//				if (answer.substring(i, i + 1).equals("-")) {
//					input += (a - 1);
//
//				}
//				if (answer.substring(i, i + 1).equals("+")) {
//					input += (a + 1);
//				} 
//			}
//
//	}

	public String getNewProposition() {
		return newProposition;
	}

}

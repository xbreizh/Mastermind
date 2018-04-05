package player;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import application.Configuration;

public class AI extends Player {

	private int min;
	private int max;
//	private ArrayList<Integer> possible= new ArrayList<>();
	private int value;
	private ArrayList<Integer> possible = new ArrayList<>();
	private ArrayList<Integer> initialList = new ArrayList<>();
	private ArrayList<Integer> foundList = new ArrayList<>();
	private ArrayList<Integer[]> finalList = new ArrayList<>();
	private int maxDigits = Configuration.getNbDigits();
//	private String guess;
	private int phase=0;
	private int test=15;
	private int position=0;
	private int pick;
	private int get=0;
	
//	private void initPossibleList(){
//		for (int i = 0; i < 5; i++) {
//			possible.add(i);
//		}
//	}
	
	
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
		if (firstGuess==0) {
			System.out.println("first guess");
			newGuess=initFirstGuess();
			firstGuess++;
		} else {
			for (int i = 0; i < guess.length(); i++) {
				int a = Character.getNumericValue(guess.charAt(i));
				if (answer.substring(i, i + 1).equals("=")) {
					newGuess += a;
				}
				if (answer.substring(i, i + 1).equals("-")) {
					if(Integer.parseInt(guess.substring(i, i + 1))==5){
						newGuess +=3;
					}else{
						newGuess += (a-1);
					}
				}
				if (answer.substring(i, i + 1).equals("+")) {
					if(Integer.parseInt(guess.substring(i, i + 1))==5){
						newGuess +=7;
					}else{
						newGuess += (a+1);
					}
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
//		guess();
		return guess=guess();

	}
	
//	private void convertArrayListToString(){
//		
//	}

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
///////////////////////////////////////////////////////////////////////////////
	
	public void initFirstPossibleList(){
		for (int i = 0; i < 10; i++) {
			possible.add(i);
		}
	}

	public int getRandomFromList(ArrayList<Integer> list){
		Random random = new Random();
		pick=random.nextInt(list.size());
		int number=list.get(pick);
//		list.remove(pick);
		System.out.println("Number: "+number);
		System.out.println(list);
		return number;
	}

		public String initList(){
			initialList.clear();
			value=getRandomFromList(possible);
			possible.remove(pick);
			while(initialList.size() < maxDigits){
				initialList.add(value);
			}
			
			return arrayListToString(initialList);
		}
		
		public String initSecondList(){
			possible.clear();
			value=foundList.get(get);
			System.out.println("Value: "+value);
			for (int i = 0; i < maxDigits-1; i++) {
				possible.add(test);
			}
			possible.add(position, value);
			
//			possible.add(position, value);
			
			return arrayListToString(possible);
		}

		public String guess(){
			if(answer.length()==0){
				initFirstPossibleList();
			}
			System.out.println("Phase: "+phase);
			if(phase==0){
				System.out.println("first guess");
				guess=initList();
				phase=1;
			}
			else if(phase==1){	
				if(foundList.size()<maxDigits){
					if(initialList.size()==1){
						foundList.add(initialList.get(0));
						phase=2;
					}else{
					guess=analyseFirstPhase();
					}
				}else{
					possible.clear();
					value=getRandomFromList(foundList);
					phase=2;
				}
			}
			else if(phase==2){
				if(foundList.size() >0){
				System.out.println("first guess second phase: ");
				guess=analyseSecondPhase();
				
			}
			
			}else{
				phase=3;
			}
			if(phase==3){
				guess=getFinalResult();
			}
			System.out.println("Guess: "+guess);
			return guess;
		}
		
		private String analyseSecondPhase(){
			if(finalList.size()== maxDigits){
				System.out.println("Victory is near");
			}
				
			int placed = Integer.parseInt(answer.substring(1, 2));
			Integer[] array = {position, value};
//			if(foundList.size()>0)
			if(placed >0){
				finalList.add(array);
				System.out.println("removed: "+foundList.get(0));
				foundList.remove(get);
				System.out.println("foundList: "+foundList);
				if(foundList.size()>0){
//				value=getRandomFromList(foundList);
				System.out.println("placed");
//				value=getRandomFromList(possible);
				position++;
				get=0;
				}else{
					return getFinalResult();
				}
			}else{
				get++;
			}
			System.out.println("second list: "+possible);
			
			
			return initSecondList();
			
			
		}
		
		private String getFinalResult(){
			ArrayList<Integer> finalResult = new ArrayList<>();
			for (int i = 0; i < finalList.size(); i++) {
				Integer[] array = finalList.get(i);
				finalResult.add(array[0], array[1]);
			}
			return arrayListToString(finalResult);
		}

		private String analyseFirstPhase(){
//			int found = Integer.parseInt(answer.substring(0, 1));
			int placed = Integer.parseInt(answer.substring(1, 2));
			if(placed > 0){
				for (int i = 0; i < placed; i++) {
					foundList.add(value);
				}
				
			}else{
				if(test == 15){
					test=value;
					System.out.println("test initiated: "+value);
				}
				
			}
			if(initialList.size()==1){
				foundList.add(initialList.get(0));
				phase=2;
				initialList.clear();
			}
			else if(foundList.size()==maxDigits){
				System.out.println("full");
				if(test == 15){
					test=getRandomFromList(possible);
					System.out.println("test initiated at the end: "+test);
				}
				phase=2;
				initialList.clear();
				value=getRandomFromList(foundList);
				return initSecondList();
				
			}
				System.out.println("Foundlist: "+foundList);
				System.out.println("Foundlist size: "+foundList.size());
				return initList();
		}

		/**
		 * 
		 */
		private void initiateTest(int value) {
			if(test == 15){
				test=value;
			}
			System.out.println("test initiated: "+value);
		}
		
		
		
		public ArrayList<Integer> stringToArrayList(String str){
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < str.length(); i++) {
				list.add(Integer.parseInt(str.substring(0, i+1)));
			}
			
			return list;
			
		}
		
		public String arrayListToString(ArrayList<Integer> list){
			String str="";
			
			for (int i = 0; i < list.size(); i++) {
				str+=Integer.toString(list.get(i));
			}
			
			return str;
		}
	
	
	
	
	
	
}

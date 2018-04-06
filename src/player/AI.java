package player;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import application.Configuration;

public class AI extends Player {

	private int min;
	private int max;
	private int value;
	private ArrayList<Integer> possible = new ArrayList<>();
	private ArrayList<Integer> initialList = new ArrayList<>();
	private ArrayList<Integer> foundList = new ArrayList<>();
	private ArrayList<Integer[]> finalList = new ArrayList<>();
	private int maxDigits = Configuration.getNbDigits();
	private int test=15;
	private int position=0;
	private int pick;
	private int get=0;
	
	
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
		return guess=guess();

	}
	
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
	
	/**
	 * Core of the Mastermind guessing for AI
	 * phase 0: initiates first list
	 * phase 1: tests the possible integer until the required number of digits is found
	 * phase 2: tests the possible position of each previous found integer
	 * phase 3: returns the final result
	 * @return
	 */
	
	public String guess(){
		if(answer.length()==0){
			initFirstPossibleList();
		}
		 if(phase==0){
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
			guess=analyseSecondPhase();
			
		}
		
		}else{
			
			guess=getFinalResult();
			phase=0;
			
		}
		return guess;
	}
	
	/**
	 * initiates the first list for the application MasterMind
	 */
	
	private void initFirstPossibleList(){
		possible.clear();
		foundList.clear();
		finalList.clear();
		position=0;
		for (int i = 0; i < 10; i++) {
			possible.add(i);
		}
	}

	/**
	 * returns a random int from a list of integer
	 * @param list
	 * @return integer
	 */
	
	private int getRandomFromList(ArrayList<Integer> list){
		Random random = new Random();
		pick=random.nextInt(list.size());
		int number=list.get(pick);
		return number;
	}

	/**
	 * creates a new list by taking a random integer from another list
	 * and duplicating it until the list size matches the maximum possible number of digits
	 * @return string
	 */
		private String initList(){
			initialList.clear();
			value=getRandomFromList(possible);
			possible.remove(pick);
			while(initialList.size() < maxDigits){
				initialList.add(value);
			}
			
			return arrayListToString(initialList);
		}
		/**
		 * creates a new list filled with a rejected number duplicated and an integer from the possible list
		 * the first integer is duplicated to fill the list but there will be only 1 integer from the possible 
		 * list
		 * @return string
		 */
		private String initSecondList(){
			possible.clear();
			value=foundList.get(get);
			for (int i = 0; i < maxDigits-1; i++) {
				possible.add(test);
			}
			possible.add(position, value);
			
			return arrayListToString(possible);
		}

		/**
		 * tests the position of each integer from the found list.
		 * if ko -> moves digit to the next position
		 * if ok -> stores the position and integer in final result list
		 * @return string
		 */
		
		private String analyseSecondPhase(){
			int placed = Integer.parseInt(answer.substring(1, 2));
			Integer[] array = {position, value};
			if(placed >0){
				finalList.add(array);
				foundList.remove(get);
				if(foundList.size()>0){
				position++;
				get=0;
				}else{
					phase=3;
					return getFinalResult();
				}
			}else{
				get++;
			}
			return initSecondList();
			
			
		}
		
		/**
		 * returns a string from the arraylist where the digits and their position are stored
		 * @return string
		 */
		private String getFinalResult(){
			ArrayList<Integer> finalResult = new ArrayList<>();
			for (int i = 0; i < finalList.size(); i++) {
				Integer[] array = finalList.get(i);
				finalResult.add(array[0], array[1]);
			}
			return arrayListToString(finalResult);
		}
		
		/**
		 * tests the integer from the first initiated list.
		 * if first ko -> stored as test
		 * if ko -> removed from the list
		 * if ok -> stored in found list
		 * @return string
		 */

		private String analyseFirstPhase(){
			int placed = Integer.parseInt(answer.substring(1, 2));
			if(placed > 0){
				for (int i = 0; i < placed; i++) {
					foundList.add(value);
				}
				
			}else{
				if(test == 15){
					test=value;
				}
				
			}
			if(initialList.size()==1){
				foundList.add(initialList.get(0));
				phase=2;
				initialList.clear();
			}
			else if(foundList.size()==maxDigits){
				if(test == 15){
					test=getRandomFromList(possible);
				}
				phase=2;
				initialList.clear();
				value=getRandomFromList(foundList);
				return initSecondList();
				
			}
				return initList();
		}
		
		
		/**
		 * Converts an arraylist of integers into a string
		 * @param list
		 * @return string
		 */
		private String arrayListToString(ArrayList<Integer> list){
			String str="";
			
			for (int i = 0; i < list.size(); i++) {
				str+=Integer.toString(list.get(i));
			}
			
			return str;
		}
	
	
	
	
	
	
}

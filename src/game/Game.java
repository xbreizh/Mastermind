package application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

abstract class Game{
	
	public static int min = 0;
	public static int max = 10;
	String statement ="init";
	Scanner sc= new Scanner(System.in);
	int nbActivePlayers= 1;
	int codePlayer0;
	int codePlayer1;
	String currentPlayer="Player 0";
	int currentInput;
	int[]currentInputArray;
	int currentResult;
	int[] currentToFind;
	int nbDigits=4;
//	int[][] result=new int[2][2];
//	int rr=1234;
	int finalResult;
	String[] verdict=new String[nbDigits];
	HashMap<Integer, int[]> codeMap=new HashMap<>();
	protected String output="";
	
	Game(){
		
	}
	
	abstract String play(String input);
	abstract String checkResult(String input);
	abstract String getVerdict(int[] a, int[] b);
	
	
void setup(){
		while(statement.equals("init")){
			for (int i = 0; i < nbActivePlayers; i++) {
				sendOutput("Enter your code to setup");
				int input=askForInput();
				int[] tab=intToArray(input);
				codeMap.put(i, tab);
				switchPlayer();
			}	
			statement ="play";
		}
}
	public int[] intToArray(int code){
		int[] tab= new int[nbDigits];
		int s=nbDigits-1;
		while (code > 0) {
		    int b=code % 10;
		    tab[s]=b;
		    code = code / 10;
		    s--;
		}
		return tab;
	}
	boolean compareArrays(int[] a,int[]b){
		if(Arrays.equals(a,b)){
			return true;
		}else{
			return false;
		}
	}
	void playing(){
		while(statement.equals("play")){
			
			for (int i = 0; i < nbActivePlayers; i++) {
				sendOutput("Player "+(i+1)+": enter 4 digits");
				currentInput=askForInput();
				currentInputArray=intToArray(currentInput);
				sendOutput(getVerdict(currentToFind, currentInputArray));
				if(compareArrays(currentToFind,currentInputArray ))	{
					if(finalResult==0){
					finalResult=i+1;
					sendOutput("final result :"+finalResult);
					}else{
						finalResult=3;//3 -> both players win
					}
				}
				switchPlayer();
			
			}
			if(checkFinalResult()){
				statement="win";
				analyseResult();}
			else{
			readArray();
			}
		}
	}
	
	
	





		
		void play(){
//			System.out.println("I play");
		}
		
		void switchPlayer(){
		if(nbActivePlayers>1){
			if(currentToFind == codeMap.get(0)){
				currentToFind = codeMap.get(1);
			}else{
				currentToFind = codeMap.get(0);
			}
		}else{
			currentToFind =codeMap.get(0);
		}
	}
		void readArray(){
			String str="";
			for (int i = 0; i < currentToFind.length; i++) {
				str+=currentInputArray[i];
			}
//			sendOutput(str);
		}
		boolean checkFinalResult(){
			if(finalResult!=0){
				return true;
			}return false;
		}
		void analyseResult(){
			if(finalResult==3){
				sendOutput("the two players win!");
			}else if(finalResult==1){
				sendOutput("Player 1 wins!");
			}else{sendOutput("Player 2 wins!");}
			
		}
	int askForInput(){
		int input= sc.nextInt();
		return input;
	}
	String sendOutput(String output){
		System.out.println(output);
		return output;
	}
	
}

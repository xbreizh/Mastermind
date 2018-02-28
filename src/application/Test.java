package application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	
	
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
	int[][] result=new int[2][2];
	int rr=1234;
	int finalResult;
	String[] verdict=new String[nbDigits];
	HashMap<Integer, int[]> codeMap=new HashMap<>();
	String output="";
	Test(){
		
	}
	
void setup(){
		
		while(statement.equals("init")){
			for (int i = 1; i < nbActivePlayers; i++) {
				System.out.println("Enter your code to setup");
				int input=sc.nextInt();
				int[] tab=intToArray(input);
				codeMap.put(i, tab);
				switchPlayer();
			}	
			
			System.out.println("code player 1: "+Arrays.toString(codeMap.get(0)));
			System.out.println("code player 2: "+Arrays.toString(codeMap.get(1)));
			statement ="play";
//			readArray();
//			currentToFind=Arrays.toString(a)
//			System.out.println("to find now: "+currentToFind);
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
				System.out.println("Player "+(i+1)+": enter 4 digits");
				currentInput=sc.nextInt();
				currentInputArray=intToArray(currentInput);
				System.out.println(currentInput);
				System.out.println(getVerdict(currentInputArray, currentToFind));
				if(compareArrays(currentToFind,currentInputArray ))	{
					if(finalResult==0){
					finalResult=i+1;
					System.out.println("final result :"+finalResult);
					}else{
						finalResult=3;//3 -> both players win
					}
				}
				switchPlayer();
				play();			
			}
			if(checkFinalResult()){
				statement="win";
				analyseResult();}
			else{
			readArray();
			}
		}
	}
	
	
	String getVerdict(int[] a, int[] b){
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
		return str;
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
		}
	}
		void readArray(){
			String str="";
			for (int i = 0; i < currentToFind.length; i++) {
				str+=currentInputArray[i];
			}
//			System.out.println(str);
		}
		boolean checkFinalResult(){
			if(finalResult!=0){
				return true;
			}return false;
		}
		void analyseResult(){
			if(finalResult==3){
				System.out.println("the two players win!");
			}else if(finalResult==1){
				System.out.println("Player 1 wins!");
			}else{System.out.println("Player 2 wins!");}
			
		}

}

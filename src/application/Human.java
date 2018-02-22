package application;

import java.util.Scanner;

public class Human implements Player {
	private Scanner sc;
	
	
	Human(){
//		openScanner();
	}

	@Override
	public String input() {
		String input= sc.nextLine();
		
		return input;

	}
	
	public void openScanner(){
		sc= new Scanner(System.in);
	}
	
	public void closeScanner(){
		sc.close();
		System.out.println("scanner closed");
	}

	@Override
	public void setResult(String result) {
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}

}

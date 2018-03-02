package players;

import java.util.Scanner;

public class Human implements Player {
	private Scanner sc;

	public Human() {
		openScanner();
	}

	@Override
	public int input() {
		int input = sc.nextInt();

		return input;

	}

	public Scanner openScanner() {
		if(sc==null){
		return sc = new Scanner(System.in);}
		else{
			return sc;
		}
	}

	public void closeScanner() {
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

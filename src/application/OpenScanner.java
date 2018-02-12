package application;

import java.util.Scanner;

public class OpenScanner {

	private Scanner sc;

	OpenScanner() {
		openScanner();
	}

	Scanner openScanner() {
		sc = new Scanner(System.in);
		return sc;
	}

	public String controllerGetInput() {
		String input = sc.nextLine();
		return input;
	}

	public void closeScanner() {
		sc.close();
	}

}

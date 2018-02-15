package application;

import java.util.Scanner;

public class Input {

	private static Input inputs;
	private static Scanner sc;

	private Input() {
		if (inputs == null) {
			inputs = new Input();
		}
	}

	public static Scanner openScanner() {
		sc = new Scanner(System.in);
		return sc;
	}

	public static String controllerGetInput() {
		String input = sc.nextLine();
		return input;
	}

	public static void closeScanner() {
		sc.close();
	}

}

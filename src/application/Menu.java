package application;

import java.util.Scanner;

public class Menu {
	
	Scanner sc = new Scanner(System.in);
	
	Menu(){
		this.presentation();
		this.openScanner();
	}
	
	void presentation(){
		System.out.println("Please choose one of the following options: ");
	}
	String openScanner(){
	
	String input = sc.nextLine();
	return input;
	}

}

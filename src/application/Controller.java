package application;


import java.util.Scanner;

import model.CheckInput;

public class Controller implements Observer{
	Scanner sc;
	private Status status = Status.SELECTGAME;
	String input = "";
	String error="";
	String[] validGame = { "1", "2", "Q" };
	String[] validMode = { "1", "2", "3", "4", "Q" };
	String[] toCheck;
	String output = "";
	String inputToTest="";
	String nothing="vous n'avez rien écrit";
	String wrongInput="entrée invalide: ";
	Menu menu;
	
	

	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	public Controller() {
		sc=Input.openScanner();
		output=Menu.selectGame;
		sendView(output);
		toCheck=validGame;
	}

	
	public void CheckStatus(){
		if(status == Status.QUIT){
			Input.closeScanner();
		}
		if(status == Status.SELECTGAME){
			getInput();
			}
		if(status == Status.SELECTMODE){
			toCheck=validMode;
			getInput();
		}
	}
	
	public void getInput(){
		inputToTest =sc.nextLine();
		checkInput(inputToTest);
	}
	
	public void checkInput(String inputToTest){
		if(!CheckInput.checkNotNull(inputToTest)){
			sendError(nothing);
			sendView(output);
			getInput();
		}else{
			if(!CheckInput.checkValidString(inputToTest, toCheck)){
				sendError(wrongInput+inputToTest);
				sendView(output);
				getInput();
			}else{
				setInput(inputToTest);
				sendInputModel();
			}
			
		}
	}
	
	public void sendInputModel(){
		if(status == Status.SELECTGAME){
			menu.checkGame(input);	
		}
		if(status == Status.SELECTMODE){
//			System.out.println(output);
//			System.out.println("la");
			menu.checkMode(input);	
		}
		
	}
	
	
	// sends the message to display
	public void sendView(String output) {
		View.display(output);
		
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	// sends the error to display
		public void sendError(String error) {
			View.error(error);
			
		}


	public void quit() {
		Input.closeScanner();
	}
	private void updateStatus(Status status) {
		this.status = status;
		System.out.println("new status: "+status);
		
	}



	@Override
	public void update(Status status, String output) {
		updateStatus(status);	
		this.output=output;
		sendView(output);
		CheckStatus();
	}


	

}

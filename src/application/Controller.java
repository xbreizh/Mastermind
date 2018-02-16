package application;


import java.util.Scanner;

import model.CheckInput;

public class Controller implements Observer{
	Scanner sc;
	protected Status status = Status.SELECTGAME;
	String input = "";
	String error="";
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
	}

	
	public void CheckStatus(){
		if(status == Status.QUIT){
			Input.closeScanner();
		}
		if(status == Status.SELECTGAME){
			getInput();
			}
		if(status == Status.SELECTMODE){
			getInput();
		}
	}
	
	public void getInput(){
		inputToTest =sc.nextLine();
		checkInput(inputToTest);
	}
	
	public void checkInput(String inputToTest){
		if(!CheckInput.checkNotNull(inputToTest)){
			sendView(output);
			sendError(nothing);		
			getInput();
		}else{
			if(!CheckInput.checkValidString(inputToTest, toCheck)){
				sendView(output);
				sendError(wrongInput+inputToTest);
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
		else if(status == Status.SELECTMODE){
			menu.checkMode(input);	
		}
		else if(status == Status.QUIT){
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
	private void updateToCheck(String[] toCheck) {
		this.toCheck = toCheck;		
	}



	@Override
	public void update(Status status, String output, String[] toCheck) {
		updateStatus(status);	
		updateToCheck(toCheck);
		
		this.output=output;
		sendView(output);
		CheckStatus();
	}


	

}

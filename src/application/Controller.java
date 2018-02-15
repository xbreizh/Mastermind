package application;


import java.util.Scanner;

import model.CheckInput;

public class Controller implements Observer{
	Scanner sc;
	private Status status = Status.keepPlaying;
	String input = "";
	String error="";
	String[] valid = { "1", "2", "Q" };
	String output = "";
	String nothing="vous n'avez rien écrit";
	String wrongInput="entrée invalide: ";
	Menu menu;
	
	

	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	public Controller() {
		
		sc=Input.openScanner();
		sendView(Menu.menu);
	}

	
	public void CheckStatus(){
		if(status == Status.quit){
			Input.closeScanner();
		}
	}
	
	public void getInput(){
		checkInput(sc.nextLine());
	}
	
	public void checkInput(String inputToTest){
		if(!CheckInput.checkNotNull(inputToTest)){
			sendError(nothing);
			sendView(Menu.menu);
			getInput();
		}else{
			if(!CheckInput.checkValidString(inputToTest, valid)){
				sendError(wrongInput+inputToTest);
				sendView(Menu.menu);
				getInput();
			}else{
				setInput(inputToTest);
				menu.checkMenu(input);
			}
			
			
		}
	}
	
	public void sendInputModel(){
		menu.checkMenu(input);
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
		
	}



	@Override
	public void update(Status status, String output) {
		updateStatus(status);
		CheckStatus();
		sendView(output);
	}


	

}

package application;

import java.util.ArrayList;

public class Menu implements Observable{
	
	
	final static String menu="Choisissez votre jeu (taper Q pour quitter)\n"
			+ "Moreless - tapez 1\n"
			+"Mastermind - tapez 2";
	final static String bye= "Thanks for Playing!";
	ArrayList<Observer> observers= new ArrayList<>();
	Status status = Status.keepPlaying;
	String output="ss";
	
	
	Menu(){
		
	}
	
	
	void checkMenu(String input){
		if (input.equals("1")) {
			output="creation d un moreless";
			notifyObserver();
			new MoreLess();
		}
		else if (input.equals("2")) {
			output="creation d un mastermind";
			notifyObserver();
			new Mastermind();
		}
		else if (input.toUpperCase().equals("Q")) {
			output=bye;
			setStatus(Status.quit);
			notifyObserver();
		}
	}
	
	private void setStatus(Status status) {
		this.status = status;
		
	}

	@Override
	public void addObserver(Observer obs) {
		this.observers.add(obs);	
	}

	@Override
	public void removeObserver(Observer obs) {
		this.observers.remove(obs);
		
	}

	@Override
	public void notifyObserver() {
		for(Observer obs: observers){
			obs.update(status, output);
		}
		
	}
	
	

}

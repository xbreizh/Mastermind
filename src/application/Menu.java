package application;

import java.util.ArrayList;

public class Menu implements Observable{
	
	
	final static String selectGame="Choix du jeu: (taper Q pour quitter)\n"
			+ "Moreless - tapez 1\n"
			+"Mastermind - tapez 2";
	final static String bye= "Thanks for Playing!";
	final static String selectMode="Choix du mode: (taper Q pour quitter)\n"
			+ "Defenseur - tapez 1\n"
			+"Challenger - tapez 2\n"
			+"Dual - tapez 3\n"
			+"Developpeur - tapez 4";
	ArrayList<Observer> observers= new ArrayList<>();
	Status status = Status.PLAYING;
	String output="ss";
	
	
	Menu(){
		
	}
	
	
	void checkGame(String input){
		if (input.equals("1")) {
//			output="creation d un moreless";
			setStatus(Status.SELECTMODE);
			output=selectMode;
			notifyObserver();
//			new MoreLess();
		}
		else if (input.equals("2")) {
//			output="creation d un mastermind";
			
			setStatus(Status.SELECTMODE);
			output=selectMode;
			notifyObserver();
//			new Mastermind();
		}
		else if (input.toUpperCase().equals("Q")) {
			output=bye;
			setStatus(Status.QUIT);
			notifyObserver();
		}
	}
	
	void checkMode(String input){
		if (input.equals("1")) {
			output="mode defense selectionné";
			setStatus(Status.PLAYING);
//			notifyObserver();
//			new MoreLess();
		}
		else if (input.equals("2")) {
			output="mode challenger selectionné";
			notifyObserver();
//			new Mastermind();
		}
		else if (input.equals("3")) {
			output="mode dual selectionné";
			notifyObserver();
//			new Mastermind();
		}
		else if (input.equals("4")) {
			output="mode developpeur selectionné";
			notifyObserver();
//			new Mastermind();
		}
		else if (input.toUpperCase().equals("Q")) {
			output=bye;
			setStatus(Status.QUIT);
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

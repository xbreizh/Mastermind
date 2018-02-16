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
	String[] validGame = { "1", "2", "Q" };
	String[] validMode = { "1", "2", "3", "4", "Q" };
	String[] toCheck ;
	ArrayList<Observer> observers= new ArrayList<>();
	protected Status status;
	String output="";
	Game game;
	Observer obs;
	
	
	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	Menu(){
		output=selectGame;
		toCheck=validGame;
		status=Status.SELECTGAME;
		
	}
	
	
	void checkGame(String input){
		System.out.println(input);
		if (input.equals("1")) {
			setStatus(Status.SELECTMODE);
			output=selectMode;
			game=new MoreLess();
			notifyObserver();
			
			
		}
		else if (input.equals("2")) {
			
			setStatus(Status.SELECTMODE);
			output=selectMode;
			notifyObserver();
			game=new Mastermind();
		}
		else if (input.toUpperCase().equals("Q")) {
			output=bye;
			setStatus(Status.QUIT);
			notifyObserver();
		}
	}
	
	void checkMode(String input){
		if (input.equals("1")) {	
			obs=new DefenseController();
//			game.removeObserver(obs);
			notifyObserver();
		}
		else if (input.equals("2")) {
			
			game.addObserver(new ChallengerController());
		}
		else if (input.equals("3")) {
			
			game.addObserver(new DualController());
		}
		else if (input.equals("4")) {
			
			game.addObserver(new DevelopperController());
			game.notifyObserver();
		}
		else if (input.toUpperCase().equals("Q")) {
			output=bye;
			setStatus(Status.QUIT);
			game.removeObserver(obs);
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
			obs.update(status, output, toCheck);
		}
		
	}
	
	

}

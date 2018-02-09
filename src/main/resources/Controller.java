package main.resources;

import java.util.Scanner;

public class Controller {
	
	boolean stop=false;
	Controller(){
		while( stop==false){
			System.out.println(getInput());
			}
	}
	// ouvre le flux du scanner et recupère l'input sous forme de String
	 Scanner ouvreFlux(){
		Scanner sc = new Scanner(System.in);
		return sc;
	}

	/**
	 * @param sc
	 * @return
	 */
	protected String getInput() {
		String str= ouvreFlux().next();
		return str;
	}
	 
	 //a utiliser a la fin du jeu pour fermer definitivement le flux du scanner
	 void closeFlux(){
		 stop=true;
		 ouvreFlux().close();
	 }
	 
	

}

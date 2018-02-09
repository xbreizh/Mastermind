package main.resources;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Jeu {
	private int[] valeurs;
	private ArrayList<Integer> valeursListe;
	int nbreEssaisMax = 5;// nombre maximum essais autorisés
	
	int trouve = 0;// nombre de valeurs trouvées
	int nbreEssais = 1;// nombre d'essais
	public static int min=0;
	public static int max=100;
	
	Jeu(){
		play();
	}

abstract void play();

protected abstract ArrayList<Integer> initialise();


abstract void consigne();

	

abstract boolean checkValid(String value);
protected static boolean checkRange(String value){
boolean valid=false;
int nb=Integer.parseInt(value);

if(nb>=min&&nb<=max){
	valid=true;
}else{
	System.err.println("Nombre invalide: "+value+".\nVous devez entrer une valeur entre "+min+" et "+max+".");
}
return valid;
}
	
	//verifie si la valeur est un entier
	public static boolean isInteger(String value) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(value);
	 
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex){
	    	  System.err.println("Nombre invalide: "+value);
	      }
	 
	      return isValidInteger;
	   }

}

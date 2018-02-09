package main.resources;

import java.util.ArrayList;
import java.util.Scanner;

public class CombiChiffres extends Jeu{
	
	CombiChiffres(){
		super();
	}
	
public void play(){

		
		ArrayList<Integer> liste = initialise();
		int tailleCombi = liste.size();// taille de la combinaison

		
		Scanner sc = new Scanner(System.in); // on ouvre le scanner
	
		

		int referenceCombi = 1;// reference du nombre a trouver
		String valeur_entree;
		int proposition = 0;
		
		// Explication du jeu
		consigne();
		
		for (int i = 0; liste.size() > 0; i++) {
			while (nbreEssais <= nbreEssaisMax && trouve != tailleCombi) {
				// le jeu continue tant qu'il reste des essais ou que la combinaison
				// est trouvée
			boolean valid=false;
			boolean correct;
			
			while(!valid){
			System.out.println("trouvez la valeur "+referenceCombi);
			valeur_entree=sc.nextLine();
			
			if(valeur_entree.length()==0){
				System.err.println("vous n'avez entré aucune valeur");
			}else{
				if(isInteger(valeur_entree)){
					Integer.parseInt(valeur_entree);
					if(Integer.valueOf(valeur_entree)>=0 && Integer.valueOf(valeur_entree)<=100){
						proposition=Integer.valueOf(valeur_entree);
						valid=true;
					}else{
						System.err.println("nombre invalide: \""+valeur_entree+"\". \nle nombre doit etre compris entre "+min+" et "+max+"!");
					}	
					
				}else{
				System.err.println("nombre invalide.\n\""+valeur_entree+"\" n'est pas un entier valide");
				}
					
			}
			
				}
			int reponse = liste.get(0);
			if (proposition == reponse) { // cas ou le nombre est trouvé
				System.out.println("bravo, chiffre " + referenceCombi + " trouvé");
				liste.remove(0); // retire le nombre de la liste
				trouve++; // incrémente le nombre de nombres trouvées
				referenceCombi++;// passe au nombre suivant
			} else {// verifie si la valeur proposée est plus grande ou plus
					// petite et renvoie la réponse
				if (proposition > reponse) {
					System.out.println("le nombre à trouver est plus petit");
				} else {
					System.out.println("le nombre à trouver est plus grand");
				}
			}
			nbreEssais++;
		}
		System.out.println("\njeu terminé");
		sc.close();
		break;

	}
	if (trouve == tailleCombi) {
		System.out.println("félicitations, vous avez trouvé le code en " + nbreEssais + " essais!");
	} else {
		System.out.println(
				"désolé, vous n'avez pas trouvé la combinaison cette fois ci. Score:" + trouve + "/"+tailleCombi);
	}

}

protected  ArrayList<Integer> initialise() {
	int[] init = { 1, 2, 3, 4 };
	ArrayList<Integer> list = new ArrayList<>();
	for (int i = 0; i < init.length; i++) {
		list.add(init[i]);
	}
	return list;
}
boolean checkValid(String value){
boolean valid=false;

if(checkNotNull(value)&&Jeu.isInteger(value)&&checkRange(value)){
	valid=true;
}
return valid;
}
protected static boolean checkNotNull(String value){
	 boolean valid=false;
	 if(value.length()==0){
		 System.err.println("vous n'avez rien écrit");
	 }else{
		 valid=true;
	 }
	 
	return valid;
}
	
	void consigne() {
		System.out.println("TROUVER LE NOMBRE");
		System.out.println("Vous devez trouver un nombre entier compris entre "+min+" et "+max);
		System.out.println("Vous avez droit à "+nbreEssaisMax+" essais maximum.\n");
		
	}

}

package model;

public class CheckInput {
	
	//checks if the value is not null
	public static boolean checkNotNull(String value){
		 boolean valid=false;
		 if(value.length()==0){
			 System.err.println("vous n'avez rien écrit");
		 }else{
			 valid=true;
		 }
		 
		return valid;
	}

	// checks the value within a range
	public static boolean checkRange(String value, int min, int max){
		boolean valid=true;
		int nb=Integer.parseInt(value);

		if(nb<min){
			System.err.println("la combinaison a trouver contient plus de chiffres");
			return valid=false;
		}
		if(nb>max){
			System.err.println("la combinaison a trouver contient moins de chiffres");
			return valid=false;
		}
		return valid;
		}
			
	//checks if the value is an integer
	public static boolean isInteger(String value) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(value);
	 
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex){
	    	  
	      }
	      if(!isValidInteger){
	    	  System.err.println("Nombre invalide: "+value);
	    	  
	    	  
	      }
	 
	      return isValidInteger;
	   }

}

package game;

public class Check {
	
	String input="";
	
	String output="";
	int nbChar=4;
	
	public Check(){
		
	}
	
	
	
	public void checkInteger(){
		if(!isEmpty()){
			if(isInteger()){
				isInRange();
			}
		}
		
	}
	
	boolean isEmpty(){
		if(input.length()==0){
			output="You haven't typed anything";
			return true;
		}
		return false;
	}
	
	boolean isInteger(){
	        try {
	         Integer.parseInt(this.input);
	        } catch (NumberFormatException e) {
	         output="You need to enter an Integer";
	         return false;
	        }
			return true;
	}
	boolean isInRange(){
       if(!(input.length()==nbChar)){
    	   output="The code should have "+nbChar+" digits!";
    	   return false;
       }
       return true;
}
	
	
	
	//Setters and Getters
	
	public String getOutput() {
		return output;
	}



	public void setInput(String input) {
		this.input = input;
	}

}

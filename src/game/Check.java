package game;

import java.util.ArrayList;
import java.util.Iterator;

public class Check {
	
	String input="";
	
	String output="";
	int nbChar=4;
	
	public Check(){
		
	}
	
	
	
	public boolean checkInteger(){
		if(isEmpty())return false;
		if(!isInteger())return false;
//		if(!isInList)
		return true;
		
	}
	
	boolean isEmpty(){
		if(input.length()==0){
			output="You haven't typed anything";
			return true;
		}
		return false;
	}
	
	public boolean isInteger(){
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
	public boolean isInList(ArrayList<Integer> list){
		for (int j = 0; j < list.size(); j++) {
			if(Integer.parseInt(input)==(list.get(j))){
				return true;
			}
			
		}
		output="Invalid!";
		return false;
		
	}
	
	
	
	//Setters and Getters
	
	public String getOutput() {
		return output;
	}



	public void setInput(String input) {
		this.input = input;
	}

}

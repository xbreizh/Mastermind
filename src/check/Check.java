package check;

import controller.Controller;
import game.Configuration;

public class Check {

	protected String input = "";
	protected String output = "";
	int nbChar = Configuration.nbDigits;
	String[] symbols = { "+", "-", "=" };
	protected InputStatus IStatus = InputStatus.VALID;
	

	int test=1324;
	protected String[] valid;

	

	public Check() {

	}
	
	public boolean checkIfInArrayNumber(){
		
		for (int i = 0; i < valid.length; i++) {
			if(input.equals(valid[i])){
				return true;
			}
		}
		IStatus = InputStatus.WRONGCHARACTER;
		output = IStatus.getOutput();
		return false;
		
		
	}
	
	public boolean checkIfInArray(){
		for (int i = 0; i < input.length(); i++) {
			int ok=0;
			for (int j = 0; j < valid.length; j++) {
				if(String.valueOf(input.charAt(i)).equals(valid[j])){
					ok++;
				}
			}
			if(ok==0){
				IStatus = InputStatus.WRONGCHARACTER;
				output = IStatus.getOutput();
				return false;
			}
			
		}
		return true;
		
	}

	public boolean checkSymbol() {
		if (!hasCorrectNbDigits()) {
			for (int i = 0; i < input.length(); i++) {

				if ((!input.substring(i, i + 1).equals(symbols[0])) && (!input.substring(i, i + 1).equals(symbols[1]))
						&& (!input.substring(i, i + 1).equals(symbols[2]))) {
					IStatus = InputStatus.WRONGCHARACTER;
					output = IStatus.getOutput();
					return false;
				}
			}
		}
		return true;

	}

	public InputStatus getCheckStatus() {
		return IStatus;
	}

	public boolean isValidInteger(){
		if(isInteger()){
			if(hasCorrectNbDigits()){
				return true;
			}
		}
		return false;
		
	}
	public boolean hasCorrectNbDigits() {
		if(!isEmpty()){
			if (input.length() != nbChar) {
				IStatus = InputStatus.WRONGCHARACTER;
				output = IStatus.getOutput();
				return false;
			}
		}
		
		return true;

	}

	public boolean isEmpty() {
		output = "";
		if (input.length() == 0) {
			IStatus = InputStatus.EMPTY;
			output = IStatus.getOutput();
			return true;
		}
		return false;
	}

	public boolean isInteger() {
		if (isEmpty())
			return false;
		try {
			Integer.parseInt(this.input);
		} catch (NumberFormatException e) {
			IStatus = InputStatus.NOTINTEGER;
			output = IStatus.getOutput();
			return false;
		}
		return true;
	}

	// Setters and Getters
	
	
	public void setValid(String[] valid) {
		this.valid = valid;
	}
	
	public void setNbChar(int nbChar) {
		this.nbChar = nbChar;
	}

	public String getOutput() {
		return output;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}

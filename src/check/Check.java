package check;

import application.Configuration;

/**
 * This class is about checking the user's input 
 * and returning the validity with a boolean
 * @author Xavier.Lamourec
 *
 */

public class Check {

	protected String input = "";
	protected String output = "";
	protected int nbChar = Configuration.getNbDigits();
	protected String[] symbols = { "+", "-", "=" };
	protected InputStatus IStatus = InputStatus.VALID;
	protected String error = "";
	protected String[] valid;

	public Check() {

	}
	/**
	 * checks if user's input is within the valid array.
	 * Needs valid to be instantiated
	 * @param valid array is checked for validation
	 * @param input
	 * @return
	 */
	protected boolean checkIfInArrayNumber() {

		for (int i = 0; i < valid.length; i++) {
			if (input.equals(valid[i])) {
				return true;
			}
		}
		IStatus = InputStatus.WRONGCHARACTER;
		error = IStatus.getOutput();
		return false;

	}

//	protected boolean checkIfInArray(String[] valid) {
//		for (int i = 0; i < input.length(); i++) {
//			int ok = 0;
//			for (int j = 0; j < valid.length; j++) {
//				if (String.valueOf(input.charAt(i)).equals(valid[j])) {
//					ok++;
//				}
//			}
//			if (ok == 0) {
//				IStatus = InputStatus.WRONGCHARACTER;
//				error = IStatus.getOutput();
//				return false;
//			}
//
//		}
//		return true;
//
//	}

//	protected boolean checkSymbol() {
//		if (!hasCorrectNbDigits()) {
//			for (int i = 0; i < input.length(); i++) {
//
//				if ((!input.substring(i, i + 1).equals(symbols[0])) && (!input.substring(i, i + 1).equals(symbols[1]))
//						&& (!input.substring(i, i + 1).equals(symbols[2]))) {
//					IStatus = InputStatus.WRONGCHARACTER;
//					error = IStatus.getOutput();
//					return false;
//				}
//			}
//		}
//		return true;
//
//	}

//	protected InputStatus getCheckStatus() {
//		return IStatus;
//	}

//	protected boolean isValidInteger() {
//		if (isInteger()) {
//			if (hasCorrectNbDigits()) {
//				return true;
//			}
//		}
//		return false;
//
//	}
	/**
	 * checks if the input has the right number of digits
	 * updates the value for error
	 * returns a boolean
	 * @param input
	 * @return
	 */

	protected boolean hasCorrectNbDigits() {
		if (!isEmpty()) {
			if (input.length() != nbChar) {
				IStatus = InputStatus.WRONGCHARACTER;
				error = IStatus.getOutput();
				return false;
			}
		}

		return true;

	}
	/**
	 * checks if the input is empty
	 * updates the value for error
	 * returns a boolean
	 * @param input
	 * @return
	 */
	protected boolean isEmpty() {
		error = "";
		if (input.length() == 0) {
			IStatus = InputStatus.EMPTY;
			error = IStatus.getOutput();
			return true;
		}
		return false;
	}
	
	/**
	 * checks if the input is an integer
	 * updates the value for error
	 * returns a boolean
	 * @return
	 */
	protected boolean isInteger() {
		if (isEmpty())
			return false;
		try {
			Integer.parseInt(this.input);
		} catch (NumberFormatException e) {
			IStatus = InputStatus.NOTINTEGER;
			error = IStatus.getOutput();
			return false;
		}
		return true;
	}

	// Setters and Getters

	public String getError() {
		return error;
	}

//	protected void setValid(String[] valid) {
//		this.valid = valid;
//	}

//	protected void setNbChar(int nbChar) {
//		this.nbChar = nbChar;
//	}

//	protected String getOutput() {
//		return output;
//	}

	public String getInput() {
		return input;
	}

//	protected void setInput(String input) {
//		this.input = input;
//	}

}

package model;

public class CheckInput {

	private static CheckInput checking;

	private CheckInput() {
		if (checking == null) {
			checking = new CheckInput();
		}
	}

	// checks if the value is not null
	public static boolean checkNotNull(String input) {
		boolean valid = false;
		if (input.length() == 0) {
			
		} else {
			valid = true;
		}

		return valid;
	}

	// checks if the value is valid within an array of values
	public static boolean checkValidString(String input, String[] validString) {
		if (!checkNotNull(input)) {
			return false;
		}
		for (int i = 0; i < validString.length; i++) {
			if (validString[i].toUpperCase().equals(input.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	// checks if the value corresponds to a specific value
	public static boolean checkValidString(String input, String validString) {
		if (!checkNotNull(input)) {
			return false;
		}
		if (input.equals(validString)) {
			return true;
		}
		return false;
	}

	// checks if the value is an integer
	public static boolean checkIsInteger(String input) {
		boolean isValidInteger = false;
		if (!checkNotNull(input)) {
			return isValidInteger = false;
		}
		try {
			Integer.parseInt(input);

			isValidInteger = true;
		} catch (NumberFormatException ex) {

		}
		if (!isValidInteger) {
			System.err.println("Nombre invalide: " + input);

		}

		return isValidInteger;
	}

	// checks the value within a range
	public static boolean checkRange(String input, int min, int max) {
		if (!checkIsInteger(input)) {
			return false;
		}
		int nb = Integer.parseInt(input);

		if (nb < min) {
			System.err.println("la combinaison a trouver contient plus de chiffres");
			return false;
		}
		if (nb > max) {
			System.err.println("la combinaison a trouver contient moins de chiffres");
			return false;
		}
		return true;
	}

}

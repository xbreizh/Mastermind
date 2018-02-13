package application;

public class Mastermind extends Game {

	Mastermind() {
		super();
	}

	// Checks if the values are present and at the right place
	// if present, increments found
	// if at the right place, increments fine (but not present)
	protected boolean checkResult(String check) {
		int found = 0;
		int fine = 0;
		
		this.attempts++;
		if (check.equals(list)) {
			return true;
		} else {

			for (int i = 0; i < list.length(); i++) {

				for (int j = 0; j < list.length(); j++) {
					if (check.charAt(j) == list.charAt(i)) {
						if (check.charAt(j) == list.charAt(j)) {
							fine++;
						} else {
							found++;
						}
					}

				}

			}

			getAnswer(check, found, fine);
			return false;
		}
	}

	// prepares the answer based on the result with attention to the grammar
	
	protected void getAnswer(String check, int found, int fine) {
		String answer = "Proposition: " + check + " -> Réponse : ";
		String present = "";
		String placed = "";
		if (found <= 1) {
			present = "présent";
		} // sets the answer into singular / plural
		if (found > 1) {
			present = "présents";
		}
		if (fine <= 0) {
			placed = "bien placé";
		}
		if (fine > 1) {
			placed = "bien placés";
		}

		if (found != 0 && fine != 0) { // both found and fine positive
			System.out.println(answer + found + " " + present + ", " + fine + " " + placed + ".");
		} else if (found == 0 && fine == 0) { // no positive
			System.out.println(answer + found + " " + present + ", " + fine + " " + placed + ".");
		} else if (found != 0 && fine == 0) { // only found positive
			System.out.println(answer + found + " " + present + ".");
		} else if (found == 0 && fine != 0) { // only fine negative
			System.out.println(answer + fine + " " + placed + ".");
		}
	}
}

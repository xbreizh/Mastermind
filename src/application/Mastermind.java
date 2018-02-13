package application;

public class Mastermind extends MoreLess {

	Mastermind() {
super();
	}

	boolean checkResult(String check, String good) {
		int found = 0;
		int fine = 0;

		this.attempts++;
		if (check.equals(good)) {
			return true;
		} else {

			for (int i = 0; i < good.length(); i++) {

				for (int j = 0; j < good.length(); j++) {
					if (check.charAt(j) == good.charAt(i)) {
						if (check.charAt(j) == good.charAt(j)) {
							fine++;
						} else {
							found++;
						}
					}

				}

			}
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
			return false;
		}
	}
}

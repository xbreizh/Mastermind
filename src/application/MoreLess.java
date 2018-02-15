package application;

public class MoreLess extends Game {

	public MoreLess() {
//		super();
		System.out.println("moreless createdsss");

	}

	// returns answer based on each number's position
	// returns boolean
	protected boolean checkResult(String check) {
		String result = "";
		this.attempts++;
		if (!check.equals(list)) {

			for (int i = 0; i < list.length(); i++) {
				int a = Character.getNumericValue(check.charAt(i));
				int b = Character.getNumericValue(list.charAt(i));
				if (a < b) {
					result += ("+");
				}
				if (a > b) {
					result += ("-");
				}
				if (a == b) {
					result += ("=");
				}
			}
		} else {
			return true;
		}
		System.out.printf("Proposition : %s -> Réponse : %s \n", check, result);
		return false;
	}

	
}

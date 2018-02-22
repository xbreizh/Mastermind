package application;

public class MoreLess extends Game {
	
	
	String list="9999";

	public MoreLess() {
		super();
		System.out.println("moreless created");

	}

	@Override
	String play(String input) {
		
		output="well played!";
		
		return output;
	}

	
	protected String checkResult(String check) {
		
		String result = "";
		String output="";
		if(check.toUpperCase().equals("Q")){
			return output="quit";
		}
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
			return output="win";
		}
//		output="Proposition : "+check+" -> Réponse : "+result;
		output=result;
		return output;
	}

	
}

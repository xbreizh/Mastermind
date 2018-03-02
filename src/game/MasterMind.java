package game;

public class MasterMind extends Game {



//	@Override
//	public String checkResult(String check) {

//		String result = "";
//		String output = "";
//		int found = 0;
//		int fine = 0;
//		if (check.toUpperCase().equals("Q")) {
//			return output = "quit";
//		}
//		if (!check.equals(list)) {
//
//			for (int i = 0; i < list.length(); i++) {
//
//				for (int j = 0; j < list.length(); j++) {
//					if (check.charAt(j) == list.charAt(i)) {
//						if (check.charAt(j) == list.charAt(j)) {
//							fine++;
//						} else {
//							found++;
//						}
//					}
//
//				}
//
//			}
//		} else {
//			return output = "win";
//		}
//		result = "" + found + fine + "";
//		output = "Proposition : " + check + " -> Réponse : " + found + " trouvés, " + fine + " bien placés";
//		return output;
//	}

	MasterMind(int nbActivePlayers) {
		super(nbActivePlayers);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getResult(int[] a, int[] b) {
		// TODO Auto-generated method stub
		return null;
	}

}

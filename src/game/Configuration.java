package game;

public class Configuration {
	
	private static Configuration config= null;
	private static String  list = "8765";
	private final static int max_attempts=10;
	private static boolean developerMode=false;
	private static String rules = "Trouver la combinaison de " + list.length() + " chiffres en maximum " + max_attempts + " essais!\n"
			+ "(taper Q pour quitter)";
	private static String nothing="Vous n'avez rien écrit!";
	private static String wrongInput="Le code à trouver contient " + list.length() + " chiffres!";
	private static String askReplay="Voulez-vous rejouer? (O / N)";
	private static String end="Jeu terminé!";
	
	public static String getEnd() {
		return end;
	}

	public static String getNothing() {
		return nothing;
	}

	public static String getWrongInput() {
		return wrongInput;
	}

	public static String getAskReplay() {
		return askReplay;
	}

	public static boolean isDeveloperMode() {
		return developerMode;
	}

	public static String getList() {
		return list;
	}

	
	public static int getMax_attempts() {
		return max_attempts;
	}

	
	public static String getRules() {
		return rules;
	}

	
	
	
	private Configuration(){
		
	}

	public static Configuration getConfiguration() {
		
		if(config == null){
			config = new Configuration();
		}
		
		return config;
		
	}
	
	public void shout(){
		System.out.println("aaaaaaaaaaa");
	}

}

package menu;

public enum GamesList {

	MoreLess(1, " ( Guess the code with 'more', 'less' or 'equals' as reply )"), 
	MasterMind(2, " ( Guess the code with 'is present' or 'is well placed' as reply )");

	private String rules;
	private int reference;

	GamesList(int reference,String rules) {
		this.reference=reference;
		this.rules = rules;
	}
	
	public int getReference(){
		return reference;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

}

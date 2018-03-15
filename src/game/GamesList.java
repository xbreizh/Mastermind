package game;

public enum GamesList {

	MoreLess(1), 
	MasterMind(2);
	
	private int reference;
	
	GamesList(int i) {
		this.reference=i;
	}
	
	public int getReference() {
		return reference;
	}

	public void setreference(int reference) {
		this.reference = reference;
	}
	
}

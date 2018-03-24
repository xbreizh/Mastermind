package menu;

public enum ModeList {

	CHALLENGER(1), DEFENDER(2), DUAL(3);

	int i;

	ModeList(int i) {
		this.i = i;
	}

	private String output;

	public int geti() {
		return i;
	}

	public void seti(int i) {
		this.i = i;
	}
}

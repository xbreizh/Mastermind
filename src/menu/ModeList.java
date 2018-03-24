package menu;

public enum ModeList {

	CHALLENGER(1), DEFENDER(2), DUAL(3);

	int reference;

	ModeList(int reference) {
		this.reference = reference;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}
}

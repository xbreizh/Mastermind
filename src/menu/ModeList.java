package menu;


/**
 * Lists the different game modes.
 * Each is associated with its reference
 * @author Xavier.Lamourec
 *
 */
public enum ModeList {

	CHALLENGER(1), DEFENDER(2), DUAL(3), CYBER(4);

	private int reference;

	ModeList(int reference) {
		this.reference = reference;
	}

	public int getReference() {
		return reference;
	}

//	public void setReference(int reference) {
//		this.reference = reference;
//	}
}

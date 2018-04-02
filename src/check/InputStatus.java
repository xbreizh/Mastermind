package check;


/**
 * InpusStatus lists the status used by the check Class.
 * Each occurrence is associated with a String value
 * that describes it.
 * @author Xavier.Lamourec
 *
 */
public enum InputStatus {

	VALID("Input valid"), 
	EMPTY("Nothing entered"), 
	NOTINTEGER("Not an Integer"), 
	OUTOFRANGE("Out of range"), 
	WRONGCHARACTER("Invalid Input");

	private String output;

	InputStatus(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

}

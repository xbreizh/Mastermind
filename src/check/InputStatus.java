package check;

public enum InputStatus {

	VALID("Input valid"), EMPTY("Nothing entered"), NOTINTEGER("Not an Integer"), OUTOFRANGE(
			"Out of range"), WRONGCHARACTER("Invalid Input");

	private String output;

	InputStatus(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}

package check;

public enum InputStatus {
	
	
	
	VALID("Input is valid"),
	EMPTY("You haven't typed anything"),
	NOTINTEGER("You haven't typed a valid integer"),
	OUTOFRANGE("Your input is out of range"),
	WRONGCHARACTER("Invalid Character");
	
	private String output;
	
	InputStatus(String output){
		this.output=output;
	}
	
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}

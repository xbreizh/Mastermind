package menu;

public enum Status_Menu {

	MENU_GAME("Chose a game\n1-MoreLess\n2-MasterMind"), MENU_MODE(
			"Chose a mode\n1-Challenger\n2-Defender\n3-Dual"), GAME("");

	private String output;

	Status_Menu(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}

package menu;


/**
 * Presents the main menu
 * @author Xavier.Lamourec
 *
 */
public enum Status_Menu {

	MENU_GAME("Chose a Game:\n"+GamesList.MoreLess.getReference()+"- "+GamesList.MoreLess+GamesList.MoreLess.getHowToGuess()+"\n"
			+GamesList.MasterMind.getReference()+"- "+GamesList.MasterMind+GamesList.MasterMind.getHowToGuess()), 
	
	MENU_MODE("Chose a Mode:\n"+
			ModeList.CHALLENGER.getReference()+"- "+ModeList.CHALLENGER+"\n"+
			ModeList.DEFENDER.getReference()+"- "+ModeList.DEFENDER+"\n"+
			ModeList.DUAL.getReference()+"- "+ModeList.DUAL),
	
	GAME("");

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

package menu;


/**
 * Lists the different Menu stages:
 * 1- game selection
 * 2- mode selection
 * 3- game initiation
 * It gets feeds from the GameList and the ModeList
 * @author Xavier.Lamourec
 *
 */
public enum Status_Menu {

	MENU_GAME("Chose a Game:\n"+GamesList.MoreLess.getReference()+"- "+GamesList.MoreLess+GamesList.MoreLess.getHowToGuess()+"\n"
			+GamesList.MasterMind.getReference()+"- "+GamesList.MasterMind+GamesList.MasterMind.getHowToGuess()), 
	
	MENU_MODE("Chose a Mode:\n"+
			ModeList.CHALLENGER.getReference()+"- "+ModeList.CHALLENGER+"\n"+
			ModeList.DEFENDER.getReference()+"- "+ModeList.DEFENDER+"\n"+
			ModeList.DUAL.getReference()+"- "+ModeList.DUAL+"\n"+
			ModeList.CYBER.getReference()+"- "+ModeList.CYBER),
	
	GAME("");

	private String output;

	private Status_Menu(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}


}

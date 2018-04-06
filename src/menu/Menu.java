package menu;

import check.Check;
import player.Human;

	/** The Menu class inherits the Check class
	 * and allows the user to chose his game's configuration
	 * (gameType, mode)
	 * 
	 * @author Xavier.Lamourec
	 *@param input (user input)
	 *@param game (from gameList enumeration)
	 *@param mode (from modeList enumeration)
	 */
public class Menu extends Check {

	private String[] validList;
	private GamesList game;
	private Human user;
	
	private ModeList mode;

	
   /**
    * Initiates the Menu
    */
	

	public Menu() {

	}
	
	/**
	 * This method gets user input and checks its validation.
	 * If the validation is fine, the game and the status are updated
	 * @param status
	 * @return
	 */

	public Status_Menu selectAndValidGame(Status_Menu status) {
		input=user.getInput();
		nbChar=1;
		initValidGameList();
		if (isEmpty())
			return status;
		if (!isInteger())
			return status;
		if (!checkIfInArrayNumber())
			return status;
		game = GamesList.values()[Integer.valueOf(input) - 1];
		return Status_Menu.MENU_MODE;

	}
	
	/**
	 * 	This method gets user input and checks its validation.
	 * 	If the validation is fine, the mode and the status are updated
	 * @param status
	 * @return status
	 */


	public Status_Menu selectAndValidMode(Status_Menu status) {
		input=user.getInput();
		initValidModeList();
		if (isEmpty())
			return status;
		if (!isInteger())
			return status;
		if (!checkIfInArrayNumber())
			return status;
		mode=ModeList.values()[Integer.parseInt(input )-1];
		return Status_Menu.GAME;

	}
	
	/**
	 * Initialises the validation list for the games
	 */

	private void initValidGameList() {
		initValidList(GamesList.values().length);
	}

	/**
	 * Initialises the validation list for the modes
	 */
	private void initValidModeList() {

		initValidList(ModeList.values().length);
	}
	
	/**
	 * Initialises the validation array
	 * @param validList list of accepted inputs
	 */
	private void initValidList(int listLength) {
		validList = new String[listLength];
		for (int j = 0; j < listLength; j++) {
			validList[j] = String.valueOf(j + 1);
		}
		valid=validList;
	}


	public void setUser(Human user) {
		this.user = user;
	}

	public GamesList getGame() {
		return game;
	}
	public ModeList getMode() {
		return mode;
	}
	
}

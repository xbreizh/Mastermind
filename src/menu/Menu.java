package menu;

import check.Check;
import player.Player;

public class Menu extends Check {

	private String[] validList;
	private GamesList game;
	private Player player;
	private ModeList mode;

	

	

	public Menu() {

	}

	public Status_Menu selectAndValidGame(Status_Menu status) {
		input=player.input();
		setNbChar(1);
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


	public Status_Menu selectAndValidMode(Status_Menu status) {
		input=player.input();
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

	public void initValidGameList() {
		initValidList(GamesList.values().length);
	}

	public void initValidModeList() {

		initValidList(ModeList.values().length);
	}

	private void initValidList(int listLength) {
		validList = new String[listLength];
		for (int j = 0; j < listLength; j++) {
			validList[j] = String.valueOf(j + 1);
		}
		valid=validList;
	}

	
//	Getters and Setters
	
	public String[] getValidList() {
		return validList;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GamesList getGame() {
		return game;
	}
	public ModeList getMode() {
		return mode;
	}
	
}

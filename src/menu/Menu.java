package menu;

import check.Check;
import game.GamesList;

public class Menu extends Check {

	String[] validList;
	Status_Menu status;
	GamesList game;

	public GamesList getGame() {
		return game;
	}

	public Menu() {

	}

	public boolean validGame() {
		setNbChar(1);
		initValidGameList();
		valid = validList;
		if (isEmpty())
			return false;
		if (!isInteger())
			return false;
		if (!checkIfInArrayNumber())
			return false;
		game = GamesList.values()[Integer.valueOf(input) - 1];
		return true;

	}

	public void readArray() {
		for (int i = 0; i < valid.length; i++) {
			System.out.println(valid[i]);
		}
	}

	public boolean validMode() {
		initValidModeList();
		valid = validList;
		if (isEmpty())
			return false;
		if (!isInteger())
			return false;
		if (!checkIfInArrayNumber())
			return false;
		return true;

	}

	public void initValidGameList() {
		int listLength = GamesList.values().length;
		validList = new String[listLength];
		for (int j = 0; j < listLength; j++) {
			validList[j] = String.valueOf(j + 1);
		}
	}

	public void initValidModeList() {

		int listLength = ModeList.values().length;
		validList = new String[listLength];
		for (int j = 0; j < listLength; j++) {
			validList[j] = String.valueOf(j + 1);
		}
	}

	public String[] getValidList() {
		return validList;
	}

}

package menu;

import check.Check;
import player.Player;

public class Menu extends Check {

	String[] validList;
	GamesList game;
	Player player;

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GamesList getGame() {
		return game;
	}

	public Menu() {

	}

	public boolean validGame() {
		input=player.input();
		setNbChar(1);
		initValidGameList();
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
		input=player.input();
		initValidModeList();
//		valid = validList;
		if (isEmpty())
			return false;
		if (!isInteger())
			return false;
		if (!checkIfInArrayNumber())
			return false;
		return true;

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

	public String[] getValidList() {
		return validList;
	}

}

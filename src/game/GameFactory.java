package game;

import menu.GamesList;
import menu.ModeList;
import player.AI;
import player.Human;
import player.Player;

public class GameFactory {

	public GameFactory() {
	}

	public static Game[] createGameArray(GamesList gameType, ModeList mode, Human human) {
		Player challenger = null;
		Player defender = null;
		AI robot = new AI();
		Game[] gameArray = null;
		if (mode.equals(ModeList.CHALLENGER)) {
			challenger = robot;
			defender = human;
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, challenger, defender);

		}
		if (mode.equals(ModeList.DEFENDER)) {
			challenger = human;
			defender = robot;
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, challenger, defender);
		}
		if (mode.equals(ModeList.DUAL)) {
			challenger = human;
			defender = robot;
			gameArray = new Game[2];
			gameArray[0] = GameFactory.createGame(gameType, challenger, defender);
			gameArray[1] = GameFactory.createGame(gameType, defender, challenger);
		}
		return gameArray;

	}

	private static Game createGame(GamesList gameType, Player p1, Player p2) {
		switch (gameType.getReference()) {
		case 1:
			return new MoreLess(p1, p2);
		case 2:
			return new MasterMind(p1, p2);
		default:
			return null;

		}
	}
}

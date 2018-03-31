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
		Player p1 = human;
		Player p2 = new AI();
		// Player p2= new TestPlayer();
		Game[] gameArray = null;
		if (mode.equals(ModeList.CHALLENGER)) {
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, p2, p1);

		}
		if (mode.equals(ModeList.DEFENDER)) {
			gameArray = new Game[1];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
		}
		if (mode.equals(ModeList.DUAL)) {
			gameArray = new Game[2];
			gameArray[0] = GameFactory.createGame(gameType, p1, p2);
			gameArray[1] = GameFactory.createGame(gameType, p2, p1);
		}

		// Fills the gameArray with the games created
		for (int i = 0; i < gameArray.length; i++) {
			setNames(p1, p2);

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

	private static void setNames(Player p1, Player p2) {
		if (p1.getClass().equals(Human.class) && p2.getClass().equals(Human.class)) {
			p1.setName("Paul");
			p2.setName("John");
		} else if (p1.getClass().equals(Human.class) && p2.getClass().equals(AI.class)) {
			p1.setName("Human");
			p2.setName("AI");
		} else {
			p1.setName("AI");
			p2.setName("Human");
		}
	}
}

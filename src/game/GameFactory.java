package game;

import menu.GamesList;
import menu.ModeList;
import player.AI;
import player.Human;
import player.Player;

	/** This Class creates the games using the gameType and mode
	 * It also creates the required missing player and passes 
	 * the games into an array
	 * @param gameType
	 * @param mode
	 * @author Xavier.Lamourec
	 *
	 */

public class GameFactory {
	
	/**
	 * Instantiates the GameFactory
	 */

	public GameFactory() {
	}

	/**
	 * creates the games using the gameType and mode
	 * @param gameType
	 * @param mode
	 * @param human
	 * @return
	 */
	public static Game[] createGameArray(GamesList gameType, ModeList mode, Human human) {
		Player challenger = null;
		Player defender = null;
		AI robot = new AI();
		AI robot2 = new AI();
		Game[] gameArray = null;
		if (mode.equals(ModeList.CHALLENGER)) {
			challenger = human;
			defender = robot;
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
		if (mode.equals(ModeList.CYBER)) {
			challenger = robot2;
			defender = robot;
			gameArray = new Game[2];
			gameArray[0] = GameFactory.createGame(gameType, challenger, defender);
			gameArray[1] = GameFactory.createGame(gameType, defender, challenger);
		}
		return gameArray;

	}
	
	/**
	 * creates and return the game from the gameType and Players received
	 * The first player received will be the 
	 * @param gameType
	 * @param p1
	 * @param p2
	 * @return
	 */

	private static Game createGame(GamesList gameType, Player challenger, Player defender) {
		switch (gameType.getReference()) {
		case 1:
			return new MoreLess(defender, challenger);
		case 2:
			return new MasterMind(defender, challenger);
		default:
			return null;

		}
	}
}

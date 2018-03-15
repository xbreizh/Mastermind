package game;

import player.Player;

public class GameFactory {
	
	Game game;
	
	public GameFactory(){
		
	}
	
	public static Game createGame(GamesList gameType, Player p1, Player p2){
		switch(gameType.getReference()){
		case 1:
			return new MoreLess(p1, p2);
		case 2:
			return new MasterMind(p1,p2);
		default:
			return null;

		}
	}
}

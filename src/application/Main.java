package application;

import controller.Controller;
import game.GameFactory;
import menu.Menu;
import player.Human;
import player.Player;
import view.View;

public class Main {

	public static void main(String[] args) {
		new Configuration();
		new GameFactory();
		View view = new View();
		Controller ct = new Controller();
		Menu menu = new Menu();
		Player player = new Human();
		ct.setP0(player);
		menu.setPlayer(player);
		ct.setMenu(menu);
		ct.setView(view);
		ct.switchMenu();
//		Game game=new MasterMind(new Human(), new Human()); 
//		
//		System.out.println("Game: "+game.getClass().getSimpleName());
//		System.out.println(GamesList.MasterMind.name());
////		if (game.getClass().getName().equals(GamesList.MoreLess.name())) {
//////			tryToGuessMoreless();
////		}
//		if (game.getClass().getSimpleName().equals(GamesList.MasterMind.name())) {
//			System.out.println("win");
////			tryToGuessMasterMind();
//		}
		
	}

}

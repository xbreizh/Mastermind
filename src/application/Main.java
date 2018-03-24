package application;

import controller.Controller;
import game.GameFactory;
import menu.Menu;
import player.Human;
import view.View;

public class Main {

	public static void main(String[] args) {

		new Configuration();
//		GamesList.valueOf()
		new GameFactory();
		View view = new View();
		Controller ct = new Controller();
		Menu menu = new Menu();
		ct.setP0(new Human());
		ct.setMenu(menu);
		ct.setView(view);
		ct.switchMenu();

	}

}

package application;

import controller.Controller;
import game.GameFactory;
import menu.Menu;
import player.Human;
import view.View;

public class Main {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		View view = new View();
		Controller ct = new Controller();
		Menu menu = new Menu();
		ct.setP0(new Human());
		ct.setMenu(menu);
		ct.setView(view);
		ct.setGameFactory(new GameFactory());
		ct.switchMenu();

	}

}

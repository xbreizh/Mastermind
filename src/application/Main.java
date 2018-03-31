package application;

import controller.Controller;
import menu.Menu;
import player.Human;
import view.View;

public class Main {

	public static void main(String[] args) {
		new Configuration();
		Human human = new Human();
		View view = new View();
		Controller ct = new Controller();
		Menu menu = new Menu();

		ct.setHuman(human);
		menu.setPlayer(human);
		ct.setMenu(menu);
		ct.setView(view);
		ct.switchMenu();

	}

}

package application;

import org.apache.log4j.Logger;

import controller.Controller;
import menu.Menu;
import player.Human;
import view.View;

public class Main {
	
	private static final Logger log = Logger.getLogger(Log4J.class);

	public static void main(String[] args) {
		new Configuration();
		Human human = new Human();
		View view = new View();
		Controller ct = new Controller();
		ct.setLog(log);
		Menu menu = new Menu();

		ct.setHuman(human);
		menu.setPlayer(human);
		ct.setMenu(menu);
		ct.setView(view);
		ct.switchMenu();
		

	}

}

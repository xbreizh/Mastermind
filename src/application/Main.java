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
//		

//		new Configuration();
//		int str=Configuration.getNbDigits();
//		System.out.println();
//		String guess="";
//		for (int i = 0; i < str; i++) {
//			System.out.println(i);
//			guess+="5";
//		}
//		System.out.println("Guessing: "+guess);
//		
		
		
		
		
	}

}

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
	

		
		//

		// Menu ch = new Menu();
		//
		// ch.setInput("3");
		// String[] valid={"1", "2", "3"};
		// ch.setValid(valid);
		// System.out.println(ch.isEmpty());
		// System.out.println(ch.isInteger());
		// System.out.println("Is in array: "+ch.checkIfInArray());
		// Menu menu = new Menu();
		// menu.initValidModeList();
		//
		// Scanner sc= new Scanner(System.in);
		// String str;
		// while(1>0){
		// str=sc.nextLine();
		// menu.setInput(str);
		//
		// System.out.println(menu.validMode());
		// menu.readArray();
		// }

		// ModeList.values()[1]

		// Check ch = new Check();
		// Scanner sc = new Scanner(System.in);
		// String a = "";
		// String[] valid={"1", "2", "3"};
		// ch.setValid(valid);
		//
		// while ((a = sc.nextLine()) != null) {
		// ch.setInput(a);
		//
		//
		// System.out.println(ch.checkIfInArray());

		// System.err.println("Output: " + ch.getOutput());


}

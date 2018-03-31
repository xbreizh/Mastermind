package application;

import org.apache.log4j.Logger;

import controller.Controller;
import menu.Menu;
import player.Human;
import view.View;


	/**
	 * Main class of the application.
	 * tries to load the configuration from the configuration file
	 * initiates the view(View), the controller(Controller), 
	 * the menu, the human user and the logger(log)
	 * The first phase of the application (switchMenu)
	 * is also initiated here after passing the user as
	 * parameter
	 * @author Xavier.Lamourec
	 *@param View
	 *@param Controller
	 *@param Human
	 *@param Menu
	 *@param Logger
	 */
public class Main {
	
	private static final Logger log = Logger.getLogger(Controller.class);

	public static void main(String[] args) {
		if(new Configuration().getError()!=null){
			log.fatal("Error while loading the configuration file\n "
					+ "The application can't be launched");
		}else{
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

}

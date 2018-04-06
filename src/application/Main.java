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
		Configuration conf = new Configuration();
		
		if(conf.getError()!=null){
			log.fatal("Error while loading the configuration file\n "
					+ conf.getError());
		}else{
			
		Human human = new Human();
		log.debug("human created");
		View view = new View();
		log.debug("view created");
		Controller ct = new Controller();
		log.debug("controller created");
		ct.setLog(log);
		Menu menu = new Menu();
		log.debug("menu created");
		ct.setHuman(human);
		menu.setUser(human);
		ct.setMenu(menu);
		ct.setView(view);
		ct.switchMenu();
		}
		
		
	}

}

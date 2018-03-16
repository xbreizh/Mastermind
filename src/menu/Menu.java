package menu;

import java.util.ArrayList;

import game.GamesList;
import game.Status_Game;

public class Menu {
	
	ArrayList<Integer> validList=new ArrayList<>();
	
	public Menu(){
		
	}
	
public	void initValidGameList(){
		
	for (int j = 1; j <= 2; j++) {
		validList.add(j);
	}	
}
	
public void initValidModeList(){
		
	for (int j = 1; j <= 3; j++) {
		validList.add(j);
	}
	}

public ArrayList<Integer> getValidList() {
	return validList;
}

}

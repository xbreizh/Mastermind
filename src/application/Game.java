package application;

abstract class Game{
	
	String output;
	
	protected String list=Configuration.getList();
	
	Game(){
		
	}
	
	abstract String play(String input);
	abstract String checkResult(String input);
	
}

package application;

abstract class Game extends Main {
	boolean keepPlaying = true;
	protected String list;
	static int min;
	static int max;
	static int max_attempts;
	int attempts = 0;

	

	

	// List of abstract methods
	abstract void rules();
	abstract boolean keepPlayingCheck();
	abstract boolean checkInput(String input);
	abstract String getList();
	abstract void setList(String list);
	abstract boolean checkResult(String check, String good);

	abstract void finalResult(String verdict);
	
	abstract boolean playAgain(String input);
	abstract void askRestart();

	

	

	

	
}

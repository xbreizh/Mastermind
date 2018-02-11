package application;

abstract class Game {
	String list="";
	int attempts = 0;
	abstract String getList();
	Controller ct = new Controller();

	abstract void rules();

	// abstract List initialize();
	// abstract void play();
	// abstract boolean checkInput();
	abstract boolean playAgain();
	 abstract boolean checkResult(String check, String good);
	 abstract void finalResult(String verdict);
	 abstract boolean keepPlayingCheck();
	abstract boolean checkInput(String input);

}

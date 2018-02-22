package application;

abstract class Game{
	
	String output;
	
	Game(){
		
	}
	
	abstract String play(String input);
	abstract String checkResult(String input);
//	String restart="Do you want to restart(Y / N)";
//	protected String list="";
//	static int max_attempts;
//	String rules="Trouver la combinaison de "+list.length()+" chiffres en maximum "+max_attempts+" essais!\n"
//		+"(taper Q pour quitter)";
//	String[] validPlay={"1", "2","3","4","5","6","7","8","9","q"};
//	
////	String output="";
//	
//	boolean keepPlaying = true;
//
//	static int min;
//	static int max;
//	static Observer obs;
//
//	int attempts = 0;
//	String input;
//	Scanner sc;
////	Status status=Status.PLAYING;
//	static Game game;
//
//	Game() {
//		output=rules;
//		toCheck=validPlay;
//		obs=this.obs;
////		setStatus(Status.PLAYING);
//		
//	}
//	
//	
//
//	
//	Game checkMenu(String input){
//		if (input.equals("1")) {
//			new MoreLess();
//		}
//		else if (input.equals("2")) {
//			new Mastermind();
//		}
//		else if (input.toUpperCase().equals("Q")) {
//			System.out.println("received Q");
//			removeObserver(obs);
//			obs=new Controller();
//			addObserver(obs);
////			setStatus(Status.quit);
////			notifyObserver();
//		}else{
//			System.out.println("received else");
//		}
//		return game;
//	}
//
//	private void setStatus(Status status) {
//		this.status = status;
//		
//	}
//
//
//	void setList(String list) {
//		this.list = list;
//	}
//
//	public String getList() {
//		return list;
//	}
//	
//
//	// List of abstract methods
////	abstract boolean checkResult(String check);
//
//	// List of standard methods
////	Status keepPlayingCheck() {
////		if (attempts == max_attempts) {
////			status = Status.noMoreTries;
////		}
////		return status;
////
////	}
//	
//	protected void initialize() {
//		list = Configuration.getConfiguration().getList();
//		min = Configuration.getConfiguration().getMin();
//		max = Configuration.getConfiguration().getMax();
//		max_attempts = Configuration.getConfiguration().getMax_attempts();
//		output=rules;
//		System.out.println("initialized");
//		notifyObserver();
//		
////		ct = new DefenseController();
////		rules();
//	}
//	
//	@Override
//	public void addObserver(Observer obs) {
//		this.observers.add(obs);
//		
//	}
//
//	@Override
//	public void removeObserver(Observer obs) {
//		this.observers.remove(obs);
//		
//	}
//
//	@Override
//	public void notifyObserver() {
//		for(Observer obs: observers){
////			obs.update(status, output);
//		}
//	}
////	void rules() {
////		System.out.printf("Trouver la combinaison de %s chiffres en maximum %s essais!", list.length(), max_attempts);
////		System.out.println("(taper Q pour quitter)");
////		ct.controllerCheck(this);
////	}
//
////	boolean checkInput(String input) {
////		if (!CheckInput.checkNotNull(input)) {
////			return false;
////		}
////		if (CheckInput.checkValidString(input, "q")) {
////			return true;
////		}
////		if (!CheckInput.checkRange(input, min, max)) {
////			return false;
////		}
////		return true;
////
////	}
//	
////	void 
////
////	void finalResult(String input, Status status) {
////		if (status == Status.win) {
////			System.out.printf("Proposition : %s -> Bravo, vous avez trouvé la combinaison! en %s essais", input,
////					attempts);
////		}
////		if (status == Status.noMoreTries) {
////			System.out.println("Perdu, vous avez atteint le nombre maximum d'essais possible!");
////		}
////	}
//
////	boolean playAgain(String input) {
////		String[] valid = { "y", "n" };
////		while (!CheckInput.checkValidString(input, valid)) {
////			System.out.println("\nDo you want to restart the game? (Y / N)");
////		}
////		if (input.toUpperCase().equals("Y")) {
////			attempts = 0;
////			return true;
////		} else {
////			return false;
////		}
////	}
////
////	void askRestart() {
////		System.out.println("\nDo you want to restart the game? (Y / N)");
////		ct.controllerPlayAgain(this);
////	}
	
}

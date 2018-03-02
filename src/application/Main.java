package application;

import controller.Controller;
import game.Game;
import game.Games;
import game.MoreLess;

public class Main {
	// public static int min = 0;
	// public static int max = 10;
	// String statement ="init";
	// Scanner sc= new Scanner(System.in);
	// int nbActivePlayers= 2;
	// int codePlayer1;
	// int codePlayer2;
	// protected static DefenseController ct;

	Main() {

	}

	public static void main(String[] args) {

//		 new Controller();
		 
//		Games[] re =Games.values();
//System.out.println(re[0]);
		
//		System.out.println(new Menu().checkGame());
		Game t1 = new MoreLess();
		t1.setup();
		t1.play();
		//
		//
		// int result=1234;
		// int result2=1281;
		// int nbDigits=4;
		////
		// int[] a=t1.intToArray(nbDigits,result);
		// int[] b=t1.intToArray(nbDigits,result2);
		////
		// int code=1234;
		// int[]tab =t1.intToArray(4, code);
		// for (int i = 0; i < tab.length; i++) {
		// System.out.println(tab[i]);
		// }

		// System.out.println(t1.intToArray(nbDigits,result));
		// System.out.println(t1.intToArray(nbDigits,result2));

		// System.out.println(t1.compareArrays(a, b));
		// String[] verdict=new String[nbDigits];

		// String str="";
		// for (int i = 0; i < a.length; i++) {
		// if(a[i]==b[i]){
		// str+="=";
		// }else if(a[i]<b[i]){
		// str+="-";
		// }else if(a[i]>b[i]){
		// str+="+";
		// }
		// }
		// System.out.println(t1.getVerdict(a, b));
		//

		// System.out.println(result[0][1]);
		// System.out.println(result[0][2]);

	}

//	public int[] intToArray(int code) {
//		int[] tab = null;
//		while (code > 0) {
//			int s = 0;
//			int b = code % 10;
//			tab[s] = b;
//			code = code / 10;
//			s++;
//		}
//		return tab;
//	}
//
}

// Configuration.getConfiguration().shout();
// AI po = new AI();
//
// System.out.println(po.toString());
////
// String test ="malo";
//
// System.out.println(test.substring(0,1));
// int i=0;
// test+=i;
// System.out.println(test);
// test+=i+1;
// System.out.println(test);
// test+=i+2;
// System.out.println(test);
// test="";
// test+="1";
// test+="2";
// test+="3";
// test+="4";
// System.out.println(test);

// ArrayList<String> list = new ArrayList<>();
// String proposition="5555";
// for (int i = 0; i < proposition.length(); i++) {
// list.add(proposition.substring(i));
//
// }
// System.out.println(list.size());
//// String destring="";
//
// StringBuilder sb = null;
// for(String str:list){
// sb.append(str);
// }
// proposition =sb.toString();

// new Controller().menu();
// View.display(Game.menu);

// controller.setMenu(menu);
// controller.getInput();
// menu.addObserver(controller);
// Game.addObserver(controller);

// Scanner sc = new Scanner(System.in);

// String check= "1231";
// String good= "1234";
// int found=0;
// int fine =0;
//
// for (int i = 0; i < good.length(); i++) {
//
// for (int j = 0; j < good.length(); j++) {
// if(check.charAt(j) == good.charAt(i)){
// if(check.charAt(j) == good.charAt(j)){
// System.out.println("fine "+good.charAt(j));
// fine++;
// }else{
// System.out.println("found "+good.charAt(j));
// found++;
// }
// }
//
// }
//
// }
// System.out.println("trouvés: "+found);
// System.out.println("bien placés: "+fine);

// Game g =new Game();
//
// g.playAgain();

// Scanner sc = new Scanner(System.in);
// String input = "e";
// CheckInput ch = null;
//
// while(!input.equals("q")){
// System.out.println("type something: ");
// input=sc.nextLine();
// String[] test = {"a", "b"};
// System.out.println(ch.checkValidString(input, "ww" ));
// }
// System.out.println("valid!");

// MoreLess ml = new MoreLess();
// new Menu();

// ml.rules();
// ml.input = ct.controllerGetInput();
// ml.ct.controllerCheck(ml);

// System.out.println("you typed: "+input);
// System.out.println("type something else: ");
// input = ct.controllerGetInput();
// System.out.println("you typed: "+input);
// System.out.println(input);
// input = Controller.controllerGetInput();
// System.out.println(input);

// String input = sc.nextLine();
// int good = Integer.valueOf(list.get(0));
// int check = Character.getNumericValue(input.charAt(0));
// System.out.println(check+" "+good);
//
//
// if(good == check){
// System.out.println("found");
// }else{
// System.out.println("not found");
// }

// String test = "bato";
// System.out.println(test.charAt(2));
// boucle infinie
// boolean stop=false;
// Controller ct =new Controller();
// System.out.println(ct.getInput());

// }

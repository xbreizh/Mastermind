package application;

import java.util.Scanner;

public class AI implements Player {

	String result = "";
	String proposition = "";
	String newProposition = "";
	Scanner sc = new Scanner(System.in);

	public String getResult() {
		return result;
	}

	AI() {

	}

	@Override
	public String input() {

		// String pl = sc.nextLine();
		if (proposition.isEmpty() || result.isEmpty()) {
			proposition = "5555";
			System.out.println("here");

		} else{
			if(result.contains("+")||result.contains("-")||result.contains("=")){
				System.out.println("Result is: "+result);
				proposition = analyse();
			}else{
				System.out.println(sc.nextLine());
			}
		}
		return proposition;

	}
	
//	public String input() {
//		String input= sc.nextLine();
//		System.out.println("input length: "+input.length());
//		return input;
//
//	}

	public String analyse() {
		newProposition = "";
		if (1==0) {
			newProposition = "5555";
			System.out.println("here");

		} else {
			for (int i = 0; i < proposition.length(); i++) {
				int a = Character.getNumericValue(proposition.charAt(i));
				if (result.substring(i, i + 1).equals("=")) {
					newProposition += a;
				}
				if (result.substring(i, i + 1).equals("-")) {
					newProposition += (a - 1);

				}
				if (result.substring(i, i + 1).equals("+")) {
					newProposition += (a + 1);
				} else {
				}
			}

			System.out.println(newProposition);
		}
		return newProposition;

	}

	@Override
	public void setResult(String result) {
		this.result = result;
	}

}

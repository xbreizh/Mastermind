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
	// Selects the logic according to the input received and uses the right analysis
	@Override
	public String input() {

		if (proposition.isEmpty() || result.isEmpty()) {
			proposition = "5555";
			System.out.println("here");

		} else{
			if(result.contains("+")||result.contains("-")||result.contains("=")){
				System.out.println("Result is: "+result);
				proposition = analyseMoreLess();
			}else{
				proposition = analyseMasterMind();
			}
		}
		return proposition;

	}
	
	// Analysis for MoreLess
	public String analyseMoreLess() {
		newProposition = "";
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

		return newProposition;

	}
	// Analysis for MasterMind
	public String analyseMasterMind() {
		
			

		return newProposition="0000";

	}

	@Override
	public void setResult(String result) {
		this.result = result;
	}

}

package application;

public class MasterMind extends Game {
	
	String list="1234";

	@Override
	String play(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String checkResult(String check) {
		
//		String result = "";
		String output="";
		int found=0;
		int fine =0;
		if(check.toUpperCase().equals("Q")){
			return output="quit";
		}
		if (!check.equals(list)) {

			
			
			
			for (int i = 0; i < list.length(); i++) {
				
				for (int j = 0; j < list.length(); j++) {
					if(check.charAt(j) == list.charAt(i)){
						if(check.charAt(j) == list.charAt(j)){
							fine++;
						}else{
						found++;
						}
					}
					
				}
				
			}
		} else {
			return output="win";
		}
		output="Proposition : "+check+" -> Réponse : "+found+" trouvés, "+fine+" bien placés";
		return output;
	}

}

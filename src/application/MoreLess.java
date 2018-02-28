package application;

public class MoreLess extends Game {
	
	
	

	public MoreLess() {
		super();
		System.out.println("moreless created");

	}

	@Override
	String play(String input) {
		
//		output="well played!";
//		
		return output;
	}
	String getVerdict(int[] a, int[] b){
		String str="";
		for (int i = 0; i < a.length; i++) {
			if(a[i]==b[i]){
				str+="=";
			}else if(a[i]<b[i]){
				str+="-";
			}else if(a[i]>b[i]){
				str+="+";
			}
		}
		return str;
	}

	@Override
	String checkResult(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	
///*	protected String checkResult(String check) {
//		
//		String result = "";
//		String output="";
//		if(check.toUpperCase().equals("Q")){
//			return output="quit";
//		}
//		if (!check.equals(list)) {
//
//			for (int i = 0; i < list.length(); i++) {
//				int a = Character.getNumericValue(check.charAt(i));
//				int b = Character.getNumericValue(list.charAt(i));
//				if (a < b) {
//					result += ("+");
//				}
//				if (a > b) {
//					result += ("-");
//				}
//				if (a == b) {
//					result += ("=");
//				}
//			}
//		} else {
//			return output="win";
//		}
//		output=result;
//		return output;
//	}*/

	
}

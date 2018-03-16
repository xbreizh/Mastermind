package game;

import player.Player;

public class MasterMind extends Game{

	public MasterMind(Player p1, Player p2) {
		super(p1, p2);
	}

	
	String result="";
	@Override
	void getVerdict(int n1, int n2) {
		int[] a = intToArray((secretCode));
		int[] b = intToArray(Integer.parseInt(input));
		int inList=0;
		int	onSpot=0;
		
//		for (int i = 0; i < b.length; i++) {
//			for (int j = 0; j < b.length; j++) {
//				
//			
//				if(a[i]==b[j]){
//					onSpot++;
//				}
////				if(a[i]==b[i] && a[i]!=b[i]){
////					inList++;
////				}
				
//			}
//		}
		answerToGive=inList+""+onSpot;
		output="Found: "+inList+" well placed: "+onSpot;
		
	}
	
	

}

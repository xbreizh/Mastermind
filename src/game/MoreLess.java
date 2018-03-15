package game;


import player.Player;

public class MoreLess extends Game{

	public MoreLess(Player p1, Player p2) {
		super(p1, p2);
		// TODO Auto-generated constructor stub
	}
	
	
	public void getVerdict(int n1, int n2){
		int[] a=intToArray(n1);
		int[] b=intToArray(n2);
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
		answerToGive=str;
	}




}

package application;

public class View {
	
	private static View view;
	static String output="";
	static String error="";
	
	private View(){
		createView();
	}
	
	public View createView(){
		if(view == null){
			view = new View();
		}
		return view;
	}
	
	public static void display(String newOutput){
		changeOuput(newOutput);
		System.out.println(output);
	}
	
	private static void changeOuput(String newOuput){
		output= newOuput;
	}
	private static void changeError(String newError){
		error= newError;
	}

	public static void error(String newError) {
		changeError(newError);
		System.err.println(newError);
		
	}
	
	
}

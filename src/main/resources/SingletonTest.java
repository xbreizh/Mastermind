package main.resources;

public class SingletonTest {
	
	private static SingletonTest instance;
	
	private  SingletonTest(){
		
	}
	
	public static void plouf(){
		System.out.println("plouf");
	}

	public static SingletonTest getInstance(){
		
		if (instance == null){
			instance = new SingletonTest();
		}
		return instance;
	}
}

package application;

public class Configuration {
	
	private static Configuration instance;
	private String list="1234";

	private Configuration(){};
	
	public static Configuration getConfiguration(){
		
		if(instance == null){
			instance = new Configuration();
		}
		return instance;
		
	}
	
	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

}

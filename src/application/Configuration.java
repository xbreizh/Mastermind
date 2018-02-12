package application;

public class Configuration {
	
	private static Configuration instance;
	private String list="1234";
	private int min = 1000;
	private int max = 9999;
	private int max_attempts = 5;

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
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMax_attempts() {
		return max_attempts;
	}

	public void setMax_attempts(int max_attempts) {
		this.max_attempts = max_attempts;
	}

}

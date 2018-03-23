package game;

public class Configuration {
	
	//Singleton pattern
	
	private static Configuration conf;
	public static final int max_attempts=5;
	public static final int nbDigits=4;
	
//	public static int getNbDigits() {
//		return nbDigits;
//	}


//	public int getMax_attempts() {
//		return max_attempts;
//	}
//
//
	private Configuration(){
		
	}
	
//	
	public static Configuration getConfiguration(){
		
		if(conf == null){
			conf = new Configuration();
		}
		return conf;
		
	}

}

package game;

public class Configuration {
	
	//Singleton pattern
	
	private static Configuration conf;
	private int max_attempts=5;
	private int nbDigits=4;
	
	public int getNbDigits() {
		return nbDigits;
	}


	public void setNbDigits(int nbDigits) {
		this.nbDigits = nbDigits;
	}


	public int getMax_attempts() {
		return max_attempts;
	}


	private Configuration(){
		
	}
	
	
	public static Configuration getConfiguration(){
		
		if(conf == null){
			conf = new Configuration();
		}
		return conf;
		
	}

}

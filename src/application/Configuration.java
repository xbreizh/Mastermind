package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


	/**
	 * Gets the informations from the configuration file
	 * @author Xavier.Lamourec
	 *
	 */
public class Configuration {

	private static Properties prop = new Properties();
	private static int max_attempts;
	private static int nbDigits;
	private static Exception error=null;
	private static String filePath="/Files/config.properties";

	static FileInputStream fs;
	
	/**
	 * Initiates the class and loads the configuration
	 */

	Configuration() {
		loadConfiguration();
	}
	
	/**
	 * Reads the configuration file
	 */
	private static void loadConfiguration() {
		try {
			fs = new FileInputStream(System.getProperty("user.dir") + filePath);
			prop.load(fs);
			max_attempts = Integer.parseInt(prop.getProperty("max_attempts"));
			nbDigits = Integer.parseInt(prop.getProperty("nbDigits"));
		} catch (IOException e) {
			 System.err.println(error=e);
		} 		
		
	}
	
	// Getters and Setters
	
	public static int getMax_attempts() {
		return max_attempts;
	}

	public static int getNbDigits() {
		return nbDigits;
	}

	public Exception getError() {
		return error;
	}

}

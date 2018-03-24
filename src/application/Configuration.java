package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private static Properties prop = new Properties();
	private static int max_attempts;
	private static int nbDigits;
	private String error="";

	static FileInputStream fs;

	Configuration() {
		loadConfiguration();
	}

	private static void loadConfiguration() {

		try {
			fs = new FileInputStream(System.getProperty("user.dir") + "/Files/config.properties");
			prop.load(fs);
			max_attempts = Integer.parseInt(prop.getProperty("max_attempts"));
			nbDigits = Integer.parseInt(prop.getProperty("nbDigits"));
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	// Getters and Setters
	
	public static int getMax_attempts() {
		return max_attempts;
	}

	public static int getNbDigits() {
		return nbDigits;
	}

	public String getError() {
		return error;
	}

}
